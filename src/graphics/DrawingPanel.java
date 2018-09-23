package graphics;

import config.DisplayConfig;
import graphics.interfaces.*;
import toolBox.DrawHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

    public class DrawingPanel extends Panel implements KeyListener, MouseListener, MouseMotionListener {

                //Attribute
            private int fps;
            private int frames;
            private double delta;

            private long lastLoop;
            private long firstFrame;
            private long elapsedTime;
            private long currentFrame;

            private boolean requested;

                //Referenzen
            private Timer time;
            private DisplayConfig config;
            private DrawHelper drawHelper;

            private ArrayList<TimeBasedObject> timeBasedObjects;
            private ArrayList<GraphicalObject> graphicalObjects;
            private ArrayList<ManagementObject> managementObjects;

        public DrawingPanel(DisplayConfig config) {

            this.config = config;
            timeBasedObjects = new ArrayList<>();
            graphicalObjects = new ArrayList<>();
            managementObjects = new ArrayList<>();

            setDoubleBuffered(true);

            time = new Timer(10, this);
            time.start();
        }

        public void paintComponent(Graphics g) {

            if(!requested) {

                addKeyListener(this);
                addMouseListener(this);
                addMouseMotionListener(this);

                requestFocusInWindow();
                requested = !requested;
            }

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

                //ANTIALIASING einschalten
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //Graphics setzen oder updaten
            if(!(drawHelper != null)) drawHelper = new DrawHelper(g2d);
            else drawHelper.updateGraphics(g2d);

            drawHelper.setScreenWidth(getWidth());
            drawHelper.setScreenHeight(getHeight());

                //FPS-Settings
            frames++;
            currentFrame = System.currentTimeMillis();
            if(currentFrame > firstFrame + 1000) {

                firstFrame = currentFrame;
                fps = frames;
                frames = 0;
            }

            if(config.isShowFPS()) {

                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("", Font.BOLD, 24));
                g2d.drawString("FPS: " + fps, 5, 24);
            }

                //Updating Frame
            Iterator<GraphicalObject> iterator = graphicalObjects.iterator();
            while (iterator.hasNext()) {

                GraphicalObject gObject = iterator.next();
                gObject.draw(drawHelper);
                gObject.update(delta / 1000);
            }

            Iterator<ManagementObject> mIterator = managementObjects.iterator();
            while (mIterator.hasNext()) {

                ManagementObject mObject = mIterator.next();
                mObject.update(delta / 1000);
            }

            Iterator<TimeBasedObject> tIterator = timeBasedObjects.iterator();
            while (tIterator.hasNext()) {

                TimeBasedObject tObject = tIterator.next();
                tObject.update(delta / 1000);
            }

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            elapsedTime = System.nanoTime() - lastLoop;
            lastLoop = System.nanoTime();
            delta = (int) ((elapsedTime / 1000000L) + 0.5);
            if ( delta == 0 ) delta = 1;
            repaint();
        }



        public void drawObjectOnPanel(GraphicalObject gObject) {

            SwingUtilities.invokeLater(() -> graphicalObjects.add(gObject));
        }

        public void addManagement(ManagementObject mObject) {

            SwingUtilities.invokeLater(() -> managementObjects.add(mObject));
        }

        public void addTimeBasedObject(TimeBasedObject tObject) {

            SwingUtilities.invokeLater(() -> timeBasedObjects.add(tObject));
        }

        public void removeObjectFromPanel(Object object) {

            if(object != null) {

                if(object instanceof GraphicalObject || object instanceof BasicInteractableObject || object instanceof AdvancedInteractableObject) {

                    if(graphicalObjects.contains(object))SwingUtilities.invokeLater(() -> graphicalObjects.remove(object));
                } else if(object instanceof ManagementObject) {

                    if(managementObjects.contains(object)) SwingUtilities.invokeLater(() -> managementObjects.remove(object));
                } else if(object instanceof TimeBasedObject) {

                    if(timeBasedObjects.contains(object)) SwingUtilities.invokeLater(() -> timeBasedObjects.remove(object));
                } else throw new IllegalArgumentException("Du kannst nur Objekte vom Panel entfernen, die du vorher auch hinzugefügt hast!");
            }
        }


        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            Iterator<GraphicalObject> iterator = graphicalObjects.iterator();
            while (iterator.hasNext()) {

                GraphicalObject obj = iterator.next();
                if(obj instanceof BasicInteractableObject) {

                    ((BasicInteractableObject) obj).keyPressed(e);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            Iterator<GraphicalObject> iterator = graphicalObjects.iterator();
            while (iterator.hasNext()) {

                GraphicalObject obj = iterator.next();
                if(obj instanceof BasicInteractableObject) {

                    ((BasicInteractableObject) obj).keyReleased(e);
                }
            }

            Iterator<ManagementObject> mIterator = managementObjects.iterator();
            while (mIterator.hasNext()) {

                ManagementObject obj = mIterator.next();
                obj.keyReleased(e);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            Iterator<GraphicalObject> iterator = graphicalObjects.iterator();
            while (iterator.hasNext()) {

                GraphicalObject obj = iterator.next();
                if(obj instanceof BasicInteractableObject) {

                    ((BasicInteractableObject) obj).mouseReleased(e);
                }
            }

            Iterator<ManagementObject> mIterator = managementObjects.iterator();
            while (mIterator.hasNext()) {

                ManagementObject obj = mIterator.next();
                obj.mouseReleased(e);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            Iterator<GraphicalObject> iterator = graphicalObjects.iterator();
            while (iterator.hasNext()) {

                GraphicalObject obj = iterator.next();
                if(obj instanceof AdvancedInteractableObject) {

                    ((AdvancedInteractableObject) obj).mouseDragged(e);
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            Iterator<GraphicalObject> iterator = graphicalObjects.iterator();
            while (iterator.hasNext()) {

                GraphicalObject obj = iterator.next();
                if(obj instanceof AdvancedInteractableObject) {

                    ((AdvancedInteractableObject) obj).mouseMoved(e);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
    }
