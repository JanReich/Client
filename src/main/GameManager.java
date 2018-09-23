package main;

import graphics.Display;
import graphics.interfaces.ManagementObject;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameManager implements ManagementObject {

                //Attribute

                //Referenzen
            private Display display;
            private GameClient client;


        public GameManager(Display display, GameClient client) {

            this.client = client;
            this.display = display;
        }

        @Override
        public void update(double delta) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
