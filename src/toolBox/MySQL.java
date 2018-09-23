package toolBox;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

    public class MySQL {

                //Referenzen
            private String host;
            private String port;
            private String database;
            private String username;
            private String password;

            private Connection con;

        public MySQL() {

            setStandart();
            read();
        }

        /**
         * Mit dieser Methode verbindet sich der Server (das Plugin) mit der Datenbank
         */
        public void connect() {

            if(!isConnected()) {

                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
                    System.out.println("[MySQL] Verbindung wurde aufgebaut!");
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }

        /**
         * Mit dieser Methode kann die Verbindung zur Datenbank geschlossen werden
         */
        public void disconnect() {

            if(isConnected()) {

                try {

                    con.close();
                    System.out.print("[MySQL] Verbindung wurde geschlossen!");
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        }

        /**
         * Mit dieser Methode kann man abfragen, ob eine Verbindung zur Datenbank besteht.
         * @return connectionStatus
         */
        public boolean isConnected() {

            return (con == null ? false : true);
        }

            // --------------- MySQL - Configreader --------------- //

        private void read() {

            host = FileHelper.getProperty(getFile(), "host");
            port = FileHelper.getProperty(getFile(), "port");
            database = FileHelper.getProperty(getFile(), "database");
            username = FileHelper.getProperty(getFile(), "username");
            password = FileHelper.getProperty(getFile(), "password");
        }

        private void setStandart() {

            if(!FileHelper.isFileExisting(getFile())) {

                FileHelper.createFile(getFile());

                HashMap<String, String> config = new HashMap<>();

                config.put("host", "localhost");
                config.put("port", "3306");
                config.put("database", "javaschool");
                config.put("username", "root");
                config.put("password", "");

                FileHelper.setProperty(getFile(), config);
            }
        }

        private File getFile() {

            return new File("res/configs/MySQL.properties");
        }
    }
