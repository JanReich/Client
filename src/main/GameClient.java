package main;

import abitur.netz.Client;

    public class GameClient extends Client {

                //Attribute
            private boolean spectator;

                //Referenzen
            private String myUsername;
            private ConnectorMenu menu;
            private GameManager gameManager;

        public GameClient(ConnectorMenu menu, String pServerIP, int pServerPort, String username, boolean spectator) {

            super(pServerIP, pServerPort);

            this.menu = menu;
            this.myUsername = username;
            this.spectator = spectator;

            send("RegisterClient: username: " + username + ", spectator: " + spectator);
        }

        @Override
        public void processMessage(String pMessage) {

                //Register
            if(pMessage.startsWith("RegisterSuccessful: ")) {

                String[] tokens = pMessage.split(": ");
                menu.startGame(Integer.parseInt(tokens[1]));
                gameManager = menu.getManager();
            }

                //New Player
            else if(pMessage.startsWith("NewPlayer: ")) {

                String[] messages = pMessage.split(":");

                String username = messages[4];
                int clientID = Integer.parseInt(messages[2]);

                if(!username.equalsIgnoreCase(myUsername)) {

                    gameManager.addOnlineClient(clientID);
                    gameManager.addReadybutton(username, clientID, false);
                }
            }

            else if(pMessage.startsWith("ClientData: ")) {

                String[] messages = pMessage.split(":");

                String username = messages[4];
                int clientID = Integer.parseInt(messages[2]);
                gameManager.addOnlineClient(clientID);
                gameManager.addReadybutton(username, clientID,false);

                if(messages.length >= 9) {

                    username = messages[8];
                    clientID = Integer.parseInt(messages[6]);

                    gameManager.addOnlineClient(clientID);
                    gameManager.addReadybutton(username, clientID,false);
                } else {

                    System.out.println(messages.length);
                }
            }

                //Disconnect from Server
            else if(pMessage.startsWith("Disconnect: ")) {

                close();
                System.err.println(pMessage.replace("Disconnect: ", ""));
            } else {

                System.out.println(pMessage);
            }
         }
    }
