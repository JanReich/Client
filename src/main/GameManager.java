package main;

import graphics.Display;
import graphics.interfaces.ManagementObject;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

    public class GameManager implements ManagementObject {

                //Attribute
            private int clientID;

                //Referenzen
            private Display display;
            private GameClient client;

            private String username;

        public GameManager(Display display, GameClient client, String username, int clientID) {

            this.client = client;
            this.display = display;

            this.clientID = clientID;
            this.username = username;

            addReadybutton(username, clientID, true);
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

        public void addReadybutton(String username, int clientID, boolean active) {

            switch (clientID) {

                case 1:

                    ReadyButton button1 = new ReadyButton(username,10,400, 200, 75 , active);
                    display.getActivePanel().drawObjectOnPanel(button1);
                    break;
                case 2:
                    ReadyButton button2 = new ReadyButton(username,710,400, 200, 75 , active);
                    display.getActivePanel().drawObjectOnPanel(button2);
                    break;
                case 3:
                    ReadyButton button3 = new ReadyButton(username, 350,10, 200, 75 , active);
                    display.getActivePanel().drawObjectOnPanel(button3);
                    break;
            }
        }
    }
