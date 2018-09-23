package main;

import abitur.netz.Client;

    public class GameClient extends Client {

                //Attribute
            private boolean spectator;

                //Referenzen
            private String username;
            private ConnectorMenu menu;



        //PROTOKOLL-EINTRÄGE:
        // RegisterClient <username> <spectator>

        public GameClient(ConnectorMenu menu, String pServerIP, int pServerPort, String username, boolean spectator) {

            super(pServerIP, pServerPort);

            this.menu = menu;
            this.username = username;
            this.spectator = spectator;

            send("RegisterClient: username: " + username + ", spectator: " + spectator);
        }

        @Override
        public void processMessage(String pMessage) {

            if(pMessage.equalsIgnoreCase("RegisterSuccessful:")) {

                menu.startGame();
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
