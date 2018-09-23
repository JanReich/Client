package main;

import graphics.Display;

import java.awt.*;

public class MainProgram {

                //Attribute

                //Referenzen
            private Display display;


        public MainProgram() {

            display = new Display();

            ConnectorMenu connectormenü = new ConnectorMenu(display);
            display.getActivePanel().drawObjectOnPanel(connectormenü);
        }

        public static void main(String[] args) {

            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {

                    new MainProgram();
                }
            });
        }
    }
