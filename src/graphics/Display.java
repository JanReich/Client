package graphics;

import config.DisplayConfig;
import graphics.interfaces.WindowEvents;
import toolBox.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;

    public class Display extends JFrame implements WindowListener {

                //Attribute

                //Referenzen
            private DisplayConfig config;
            private ArrayList<Panel> panels;
            private DrawingPanel activeDrawingPanel;

            private ArrayList<WindowEvents> listeners;

        public Display() {

            panels = new ArrayList<>();
            this.config = new DisplayConfig();

            listeners = new ArrayList<>();

            init();
        }

        private void init() {

            addWindowListener(this);
            setTitle(config.getTitle());
            setResizable(config.isResizable());
            setAlwaysOnTop(config.isAlwaysOnTop());
            setDefaultCloseOperation(EXIT_ON_CLOSE);

                //Größe des Fensters festlegen
            Dimension windowDimension = new Dimension(config.getWidth() * config.getScale(), config.getHeight() * config.getScale());

            if(config.isResizable()) {

                setSize(windowDimension);
                setResizable(true);
            } else {

                setSize(windowDimension);
                setResizable(false);
                setMinimumSize(windowDimension);
                setMaximumSize(windowDimension);
                setPreferredSize(windowDimension);

                setIconImage(ImageHelper.getImage("res/images/Controller.png"));
            }

                //Position des Displays setzen
            if(config.isCentered()) setLocationRelativeTo(null);
            else setLocation(config.getWindowX(), config.getWindowY());

            addDrawingPanel();

            setVisible(true);
        }

        public void addPanel(Panel panel) {

            panels.add(panel);
        }

        public DrawingPanel addDrawingPanel() {

            if(config != null) {

                DrawingPanel panel = new DrawingPanel(config);
                panels.add(panel);
                if(activeDrawingPanel == null) setActivePanel(panel);
                return panel;
            } else throw new NullPointerException("Die Config konnte nicht geladen werden... Um den Fehler zu beheben starte das Programm neu!");
        }

        public void setActivePanel(Panel panel) {

            if(panel instanceof DrawingPanel) {

                if(activeDrawingPanel != null) {

                    remove(activeDrawingPanel);

                    activeDrawingPanel = (DrawingPanel) panel;
                    add(activeDrawingPanel);
                    revalidate();
                } else {

                    add(panel);
                    activeDrawingPanel = (DrawingPanel) panel;
                }
            } else {

                //TODO: Andere Panels
            }
        }

        public void removePanel(Panel panel) {

            if(panels.contains(panel)) {

                if(panel.equals(activeDrawingPanel)) {

                    activeDrawingPanel = null;
                    remove(panel);
                    panels.remove(panel);
                } else {

                    panels.remove(activeDrawingPanel);
                }
            } else throw new IllegalArgumentException("Du kannst nur Panels entfernen, die du vorher hinzugefügt hast!");
        }

        public DrawingPanel getActivePanel() {

            return activeDrawingPanel;
        }

        public ArrayList<Panel> getInactivePanels() {

            ArrayList<Panel> inactivePanels = new ArrayList<>();

            for(Panel panel : panels) {

                if(!(panel.equals(activeDrawingPanel))) {

                    inactivePanels.add(panel);
                }
            }
            return inactivePanels;
        }

        public void addWindowListener(WindowEvents listener) {

            SwingUtilities.invokeLater(() -> listeners.add(listener));
        }

        public void removeWindowListener(WindowEvents listener) {

            SwingUtilities.invokeLater(() -> listeners.add(listener));
        }

            //Events
        @Override
        public void windowClosing(WindowEvent e) {

                //Wenn das Fenster geschlossen wird
            Iterator<WindowEvents> iterator = listeners.iterator();
            while (iterator.hasNext()) {

                WindowEvents tempObject = iterator.next();
                tempObject.windowClosing(e);
            }
        }

        @Override
        public void windowActivated(WindowEvent e) {

                //Window wieder fokussieren
            Iterator<WindowEvents> iterator = listeners.iterator();
            while (iterator.hasNext()) {

                WindowEvents tempObject = iterator.next();
                tempObject.windowActivated(e);
            }
        }

        @Override
        public void windowDeactivated(WindowEvent e) {

                //Window focus verloren
            Iterator<WindowEvents> iterator = listeners.iterator();
            while (iterator.hasNext()) {

                WindowEvents tempObject = iterator.next();
                tempObject.windowDeactivated(e);
            }
        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }
    }
