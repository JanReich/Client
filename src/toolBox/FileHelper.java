package toolBox;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

    public class FileHelper {

                //Attribute

                //Referenzen

        /**
         * In dieser Methode wird der übergebene File erstellet, falls er noch nicht existiert...
         * @param file - File (can be null -> check first)
         * @return true -> Wenn der File erstellt wird || false -> wenn der File schon existiert.
         */
        public static boolean createFile(File file) {

            if(file != null) {

                if(isFileExisting(file)) return false;
                else {

                    try {

                        file.createNewFile();
                        return true;
                    } catch (IOException e) {

                        e.printStackTrace();
                        return false;
                    }
                }
            } else return false;
        }

        /**
         * In dieser Methode wird überprüft, ob der File existiert.
         * @param file - File (can be null -> check first)
         * @return true -> File existiert || false -> file existiert noch nicht!
         */
        public static boolean isFileExisting(File file) {

            if(file != null) {

                if(file.exists())  return true;
                else return false;
            } else return false;
        }

        public static BufferedReader createReader(String file) {

            try {

                return new BufferedReader(new FileReader(new File(file)));
            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }
        }

        public static void closeReader(BufferedReader reader) {

            try {

                reader.close();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        public static String readLine(BufferedReader reader) {

            try {

                return reader.readLine();
            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }
        }

        /**
         * In dieser Methode wird ein Wert aus der Config ausgelesen der mit einem bestimmten Key verknüpft ist
         * @param file
         * @param key
         * @return
         */
        public static String getProperty(File file, String key) {

            try {

                if(file != null && key != null && key != "") {

                    Properties properties = new Properties();
                    BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
                    properties.load(stream);
                    stream.close();
                    return properties.getProperty(key);
                } else return null;
            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }
        }

        public static void setProperty(File file, HashMap<String, String> configs) {

            if(file != null && configs != null) {

                try {

                    Properties properties = new Properties();

                    for (Map.Entry<String, String> config : configs.entrySet()) {

                        properties.setProperty(config.getKey(), config.getValue());
                    }

                    FileOutputStream writer = new FileOutputStream(file);
                    properties.store(writer, "Writing properties to System.out stream");
                    writer.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }
