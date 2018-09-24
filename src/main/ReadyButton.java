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

                //Referenzen
            private BufferedImage image;

        public ReadyButton(int x, int y) {

            this.x = x;
            this.y = y;

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

        }

        @Override
        public void update(double delta) {

        }

        @Override
        public void draw(DrawHelper draw) {

            draw.drawImage(image, x, y);
        }
    }