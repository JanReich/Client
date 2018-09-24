package main;

import graphics.Display;
import graphics.interfaces.ManagementObject;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameManager implements ManagementObject {

                //Attribute
            private int clientID;

                //Referenzen
            private Display display;
            private GameClient client;
            private ArrayList<Integer> onlineClientIDs;

            private String myUsername;
            private boolean gameStarted;

                //Buttons
            private ReadyButton button1;
            private ReadyButton button2;
            private ReadyButton button3;

    public GameManager(Display display, GameClient client, String username, int clientID) {

            this.client = client;
            this.display = display;

            this.clientID = clientID;
            this.myUsername = username;

            onlineClientIDs = new ArrayList<>();
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

        public void addOnlineClient(int clientID) {

            onlineClientIDs.add(clientID);
        }

        public void removeClient(int clientID) {

            System.out.println(onlineClientIDs);
            //if(onlineClientIDs.contains(clientID)) onlineClientIDs.remove(clientID);
        }

        public void addReadybutton(String username, int clientID, boolean active) {

            switch (clientID) {

                case 1:
                    button1 = new ReadyButton(username,10,400, 200, 75 , active);
                    display.getActivePanel().drawObjectOnPanel(button1);
                    break;
                case 2:
                    button2 = new ReadyButton(username,710,400, 200, 75 , active);
                    display.getActivePanel().drawObjectOnPanel(button2);
                    break;
                case 3:
                    button3 = new ReadyButton(username, 350,10, 200, 75 , active);
                    display.getActivePanel().drawObjectOnPanel(button3);
                    break;
            }
        }

        public void removeReadybutton(int clientID) {

            switch (clientID) {

                case 1:
                    display.getActivePanel().removeObjectFromPanel(button1);
                case 2:
                    display.getActivePanel().removeObjectFromPanel(button2);
                case 3:
                    display.getActivePanel().removeObjectFromPanel(button3);
            }
        }

    public boolean isGameStarted() {

        return gameStarted;
    }
}
