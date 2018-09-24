package main;

import graphics.interfaces.BasicInteractableObject;
import toolBox.DrawHelper;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;


public class ReadyButton implements BasicInteractableObject {


                //Attribute
            private int x;
            private int y;
            private int width;
            private int height;
            private boolean active;

                //Referenzen
            private String username;
            private BufferedImage image;

        public ReadyButton(String username, int x, int y, int width, int height, boolean active) {

            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

            this.active = active;
            this.username = username;

            try {

                image = ImageIO.read(new File("res/images/rdyButton.png"));
            } catch (Exception e) {

            }
        }


        @Override
        public void keyPressed(KeyEvent event) {

        }

        @Override
        public void keyReleased(KeyEvent event) {

        }

        public void mouseReleased(MouseEvent e) {


                //Damit nur der eigene Button gedrückt werden kann.
            if(active) {


            }
        }

        @Override
        public void update(double delta) {

        }

        @Override
        public void draw(DrawHelper draw) {

            draw.drawString("Name:" + username, x, y + 100);
            draw.drawImage(image, x, y, width, height);
        }
    }