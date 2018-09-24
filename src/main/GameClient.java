package main;

import abitur.netz.Client;

    public class GameClient extends Client {

                //Attribute
            private boolean spectator;

                //Referenzen
            private String username;
            private ConnectorMenu menu;
            private GameManager gameManager;

        public GameClient(ConnectorMenu menu, String pServerIP, int pServerPort, String username, boolean spectator, GameManager gameManager) {

            super(pServerIP, pServerPort);

            this.menu = menu;
            this.username = username;
            this.spectator = spectator;
            this.gameManager = gameManager;

            send("RegisterClient: username: " + username + ", spectator: " + spectator);
        }

        @Override
        public void processMessage(String pMessage) {

                //Register
            if(pMessage.startsWith("RegisterSuccessful: ")) {


                String[] tokens = pMessage.split(": ");
                menu.startGame(Integer.parseInt(tokens[1]));
            }

                //New Player
            else if(pMessage.startsWith("NewPlayer: ")) {

                String message = pMessage.replace("NewPlayer: ", "");
                String[] messages = message.split("|");
                username = messages[1].replace("username:", "");
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
