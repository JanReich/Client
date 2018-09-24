package main;

import abitur.netz.Client;

    public class GameClient extends Client {

                //Attribute
            private boolean spectator;

                //Referenzen
            private String username;
            private ConnectorMenu menu;
            private GameManager gameManager;

        public GameClient(ConnectorMenu menu, String pServerIP, int pServerPort, String username, boolean spectator) {

            super(pServerIP, pServerPort);

            this.menu = menu;
            this.username = username;
            this.spectator = spectator;

            send("RegisterClient: username: " + username + ", spectator: " + spectator);
        }

        @Override
        public void processMessage(String pMessage) {

                //Register
            if(pMessage.startsWith("RegisterSuccessful: ")) {


                String[] tokens = pMessage.split(": ");
                menu.startGame(Integer.parseInt(tokens[1]));
                menu.getManager();
            }

                //New Player
            else if(pMessage.startsWith("NewPlayer: ")) {

                String[] messages = pMessage.split(":");

                String username = messages[4];
                int clientID = Integer.parseInt(messages[2]);

                gameManager.addReadybutton(username, clientID);
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
