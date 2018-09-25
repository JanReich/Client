package config;

import toolBox.FileHelper;

import java.io.File;
import java.util.HashMap;

    public class ConnectionConfig extends Config {

                //Attribute
            private int serverPort;
            private boolean spectator;

                //Referenzen
            private String serverIP;
            private String username;

        public ConnectionConfig(File file) {

            super(file);
        }

        @Override
        public void save() {

            HashMap<String, String> config = new HashMap<>();

            config.put("", "");
            config.put("ServerIP", serverIP);
            config.put("ServerPort", serverPort + "");
            config.put("", "");
            config.put("Username", username);
            config.put("Spectator", spectator + "");

            FileHelper.setProperty(file, config);
        }

        @Override
        public void readConfig() {

            serverIP = FileHelper.getProperty(file, "ServerIP");
            username = FileHelper.getProperty(file, "Username");

            serverPort = Integer.parseInt(FileHelper.getProperty(file, "ServerPort"));
            spectator = Boolean.parseBoolean(FileHelper.getProperty(file, "Spectator"));
        }

        @Override
        public void setStandards() {

            if(!file.exists()) {

                HashMap<String, String> config = new HashMap<>();

                config.put("ServerIP", "localhost");
                config.put("ServerPort", "666");
                config.put("Username", "Jan");
                config.put("Spectator", "false");

                FileHelper.setProperty(file, config);
            }
        }

        // --------------- GETTER AND SETTER --------------- \\

        public int getServerPort() {

            return serverPort;
        }

        public void setServerPort(int serverPort) {

            this.serverPort = serverPort;
        }

        public boolean isSpectator() {

            return spectator;
        }

        public void setSpectator(boolean spectator) {

            this.spectator = spectator;
        }

        public String getServerIP() {

            return serverIP;
        }

        public void setServerIP(String serverIP) {

            this.serverIP = serverIP;
        }

        public String getUsername() {

            return username;
        }

        public void setUsername(String username) {

            this.username = username;
        }
    }
