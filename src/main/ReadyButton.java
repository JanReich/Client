package main;

import graphics.interfaces.BasicInteractableObject;
import toolBox.DrawHelper;
import toolBox.Inputmanager;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.MouseEvent;


public class ReadyButton implements BasicInteractableObject {


                //Attribute
            private int x;
            private int y;
            private int width;
            private int height;
            private boolean active;
            private Inputmanager ready;

                //Referenzen
            private String username;
            private BufferedImage image;

        public ReadyButton(String username, int x, int y, int width, int height, boolean active,Inputmanager ready) {

            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.ready = ready;

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

                if(ready.isInside(e,x,y,width,height)){

                    System.out.println("Ich bin bereit !!!");

                }



            }
        }

        @Override
        public void update(double delta) {

        }

        @Override
        public void draw(DrawHelper draw) {

            draw.drawString("Name: " + username.toUpperCase(), x, y + 100);
            draw.drawImage(image, x, y, width, height);
        }
    }