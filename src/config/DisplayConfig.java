package config;

import toolBox.FileHelper;

import java.io.File;
import java.util.HashMap;

public class DisplayConfig {

                //Config-Werte
            private int width;
            private int height;

            private boolean resizable;
            private boolean alwaysOnTop;

            private String title;

                //Display - Position
            private int scale;
            private int windowX;
            private int windowY;

            private boolean showFPS;
            private boolean isCentered;

                //Referenzen
            private File file = new File("res/configs/DisplayConfig.properties");


        public DisplayConfig() {

            if(FileHelper.isFileExisting(file)) setStandards();
            readConfig();
        }

        private void readConfig() {

            width = Integer.parseInt(FileHelper.getProperty(file, "width"));
            height = Integer.parseInt(FileHelper.getProperty(file, "height"));

            resizable = Boolean.parseBoolean(FileHelper.getProperty(file, "resizable"));
            alwaysOnTop = Boolean.parseBoolean(FileHelper.getProperty(file, "alwaysOnTop"));

            title = FileHelper.getProperty(file, "title");

            scale = Integer.parseInt(FileHelper.getProperty(file, "scale"));
            windowX = Integer.parseInt(FileHelper.getProperty(file, "windowX"));
            windowY = Integer.parseInt(FileHelper.getProperty(file, "windowY"));

            showFPS = Boolean.parseBoolean(FileHelper.getProperty(file, "showFPS"));
            isCentered = Boolean.parseBoolean(FileHelper.getProperty(file, "isCentered"));
        }

        private void setStandards() {

            if(!FileHelper.isFileExisting(file)) {

                FileHelper.createFile(file);

                HashMap<String, String> config = new HashMap<>();
                config.put("width", "1920");
                config.put("height", "1080");

                config.put("resizable", "false");
                config.put("alwaysOnTop", "true");

                config.put("title", "Test-Window");

                config.put("scale", "1");
                config.put("windowX", "0");
                config.put("windowY", "0");

                config.put("showFPS", "true");
                config.put("isCentered", "true");

                FileHelper.setProperty(file, config);
            }
        }

            //TODO: SAVE-METHODE
        public void save() {

        }

            //--------------- GETTER & SETTER ---------------//

        public int getWidth() {

            return width;
        }

        public int getHeight() {

            return height;
        }

        public boolean isResizable() {

            return resizable;
        }

        public boolean isAlwaysOnTop() {

            return alwaysOnTop;
        }

        public String getTitle() {

            return title;
        }

        public int getScale() {

            return scale;
        }

        public int getWindowX() {

            return windowX;
        }

        public int getWindowY() {

            return windowY;
        }

        public boolean isShowFPS() {

            return showFPS;
        }

        public boolean isCentered() {

                return isCentered;
        }
    }
