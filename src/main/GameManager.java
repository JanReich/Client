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


        public GameManager(Display display, GameClient client, int clientID) {

            this.client = client;
            this.display = display;

            this.clientID = clientID;

            addReadybutton(clientID);
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

        private void addReadybutton(int clientID) {

            switch (clientID) {

                case 1:

                    ReadyButton button1 = new ReadyButton(10,400, 200, 75 ,true);
                    display.getActivePanel().drawObjectOnPanel(button1);
                    break;
                case 2:
                    ReadyButton button2 = new ReadyButton(710,400, 200, 75 ,true);
                    display.getActivePanel().drawObjectOnPanel(button2);
                    break;
                case 3:
                    ReadyButton button3 = new ReadyButton(350,10, 200, 75 ,true);
                    display.getActivePanel().drawObjectOnPanel(button3);
                    break;
            }
        }
    }
