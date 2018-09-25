package main;

import config.ConnectionConfig;
import graphics.Display;
import graphics.interfaces.BasicInteractableObject;
import toolBox.DrawHelper;
import toolBox.Inputmanager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

public class ConnectorMenu implements BasicInteractableObject {

                //Attribute
            private boolean spectator;

                //Referenzen
            private Display display;
            private GameClient client;
            private GameManager manager;
            private ConnectionConfig config;

            private Inputmanager ipInput;
            private Inputmanager portInput;
            private Inputmanager nameInput;

        public ConnectorMenu(Display display) {

            this.display = display;
            this.spectator = false;
            this.config = new ConnectionConfig(new File("res/configs/SavedConnection.properties"));

            this.ipInput = new Inputmanager();
            display.getActivePanel().addManagement(ipInput);

            this.portInput = new Inputmanager();
            display.getActivePanel().addManagement(portInput);

            this.nameInput = new Inputmanager();
            display.getActivePanel().addManagement(nameInput);
        }

        @Override
        public void update(double delta) {

        }

        @Override
        public void draw(DrawHelper draw) {

            draw.drawRec(100, 200, 500, 75);    //Server-IP
            draw.setFont(draw.createFont(Font.BOLD, 35));
            if(ipInput.getInputQuerry() != "") {

                draw.setColour(Color.BLACK);
                draw.drawString(ipInput.getInputQuerry(), 125, 255);
            } else {

                draw.setColour(Color.gray);
                draw.drawString("Server-IP Adresse", 125, 255);
            }

            draw.drawRec(675, 200, 200, 75);    //Server-Port
            draw.setFont(draw.createFont(Font.BOLD, 35));
            if(portInput.getInputQuerry() != "") {

                draw.setColour(Color.BLACK);
                draw.drawString(portInput.getInputQuerry(), 700, 255);
            } else {

                draw.setColour(Color.gray);
                draw.drawString("Port", 705, 255);
            }

            draw.drawRec(100, 325, 775, 75);    //Username
            draw.setFont(draw.createFont(Font.BOLD, 35));
            if(nameInput.getInputQuerry() != "") {

                draw.setColour(Color.BLACK);
                draw.drawString(nameInput.getInputQuerry().toUpperCase(), 125, 375);
            } else {

                draw.setColour(Color.gray);
                draw.drawString("User-Displayname", 125, 375);
            }

            if(spectator) draw.setColour(Color.green);
            else draw.setColour(Color.red);
            draw.fillRec(display.getWidth() / 2 - 75, 450, 15, 15);
            draw.setColour(Color.BLACK);
            draw.setFont(draw.createFont(Font.BOLD, 15));
            draw.drawString("Join als Spectator", display.getWidth() / 2 - 50, 463);

            draw.drawRec(330, 500, 300, 100);
            draw.setFont(draw.createFont(Font.BOLD, 50));
            draw.drawString("Connect", 380, 565);

                //Load
            draw.drawRec(0, 550, 300, 100);
            draw.drawString("Load", 80, 620);

                //Save
            draw.drawRec(display.getWidth() - 300, 550, 300, 100);
            draw.drawString("Save", display.getWidth() - 220, 620);
        }

        @Override
        public void keyPressed(KeyEvent event) {

        }

        @Override
        public void keyReleased(KeyEvent event) {

        }

        @Override
        public void mouseReleased(MouseEvent event) {

            if(ipInput.isInside(event, 100, 200, 500, 75)) {

                ipInput.setTyping(true);
                portInput.setTyping(false);
                nameInput.setTyping(false);
            }

            else if(ipInput.isInside(event, 675, 200, 200, 75)) {

                ipInput.setTyping(false);
                portInput.setTyping(true);
                nameInput.setTyping(false);
            }

            else if(ipInput.isInside(event, 100, 325, 775, 75)) {

                ipInput.setTyping(false);
                portInput.setTyping(false);
                nameInput.setTyping(true);
            }

            else {

                ipInput.setTyping(false);
                portInput.setTyping(false);
                nameInput.setTyping(false);
            }

                //Connect
            if(ipInput.isInside(event, 350, 500, 300, 100)) {

                connect();
            }

                //Load
            if(ipInput.isInside(event,0, 550, 300, 100)) {

                spectator = config.isSpectator();
                ipInput.setInputQuerry(config.getServerIP());
                portInput.setInputQuerry(config.getServerPort() + "");
                nameInput.setInputQuerry(config.getUsername());
            }

                //Save
            if(ipInput.isInside(event, display.getWidth() - 300, 550, 300, 100)) {

                if(ipInput.getInputQuerry() != "" && portInput.getInputQuerry() != "" && nameInput.getInputQuerry() != "") {

                    if(ipInput.getInputQuerry().contains(".") || ipInput.getInputQuerry().equalsIgnoreCase("localhost")) {

                        if(nameInput.getInputQuerry().length() >= 3 && nameInput.getInputQuerry() != "username:") {

                            config.setSpectator(spectator);
                            config.setServerIP(ipInput.getInputQuerry());
                            config.setServerPort(Integer.parseInt(portInput.getInputQuerry()));
                            config.setUsername(nameInput.getInputQuerry());

                            config.save();

                        } else System.err.println("Der Username muss mindestens 3 Zeichen beinhalten...");
                    } else System.err.print("Die IP-Adresse wurde in keinem gültigem Format angegeben");
                } else System.err.println("Die IP-Adresse und der Port, sowie der Username dürfen nicht leer bleiben...");
            }

            if(ipInput.isInside(event, display.getWidth() / 2 - 75, 450, 15, 15)) {

                spectator = !spectator;
            }
        }

        public void connect() {

            if(ipInput.getInputQuerry() != "" && portInput.getInputQuerry() != "" && nameInput.getInputQuerry() != "") {

                if(ipInput.getInputQuerry().contains(".") || ipInput.getInputQuerry().equalsIgnoreCase("localhost")) {

                    if(nameInput.getInputQuerry().length() >= 3 && nameInput.getInputQuerry() != "username:") {


                        client = new GameClient(this, ipInput.getInputQuerry(), Integer.parseInt(portInput.getInputQuerry()), nameInput.getInputQuerry(), spectator);
                    } else {

                        System.err.println("Der Username muss mindestens 3 Zeichen beinhalten...");
                    }
                } else {

                    ipInput.setInputQuerry("");
                    portInput.setInputQuerry("");
                    nameInput.setInputQuerry("");
                    System.err.print("Die IP-Adresse wurde in keinem gültigem Format angegeben");
                }
            } else {

                System.err.println("Die IP-Adresse und der Port, sowie der Username dürfen nicht leer bleiben...");
            }
        }

        public void startGame(int clientID) {

            manager = new GameManager(display, client, nameInput.getInputQuerry(), clientID);
            display.getActivePanel().addManagement(manager);
            display.getActivePanel().removeObjectFromPanel(this);
        }

        public void startGame() {

            manager = new GameManager(display, client, nameInput.getInputQuerry());
            display.getActivePanel().addManagement(manager);
            display.getActivePanel().removeObjectFromPanel(this);
        }

        public GameManager getManager() {

            return manager;
        }
    }