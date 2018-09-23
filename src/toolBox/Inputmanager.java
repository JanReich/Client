package toolBox;

import graphics.interfaces.ManagementObject;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

    public class Inputmanager implements ManagementObject {

                //Attribute
            private boolean typing;

                //Referenzen
            private String inputQuerry;

        public Inputmanager() {

            inputQuerry = "";
        }

        public Inputmanager(boolean typing) {

            inputQuerry = "";
            this.typing = typing;
        }

        public Inputmanager(String inputQuerry) {

            this.inputQuerry = inputQuerry;
        }

        public Inputmanager(String inputQuerry, boolean typing) {

            this.typing = typing;
            this.inputQuerry = inputQuerry;
        }

        @Override
        public void keyReleased(KeyEvent event) {

            switch (event.getKeyCode()) {

                case KeyEvent.VK_A:

                    appendToString('a');
                    break;
                case KeyEvent.VK_B:

                    appendToString('b');
                    break;
                case KeyEvent.VK_C:

                    appendToString('c');
                    break;
                case KeyEvent.VK_D:

                    appendToString('d');
                    break;
                case KeyEvent.VK_E:

                    appendToString('e');
                    break;
                case KeyEvent.VK_F:

                    appendToString('f');
                    break;
                case KeyEvent.VK_G:

                    appendToString('g');
                    break;
                case KeyEvent.VK_H:

                    appendToString('h');
                    break;
                case KeyEvent.VK_I:

                    appendToString('i');
                    break;
                case KeyEvent.VK_J:

                    appendToString('j');
                    break;
                case KeyEvent.VK_K:

                    appendToString('k');
                    break;
                case KeyEvent.VK_L:

                    appendToString('l');
                    break;
                case KeyEvent.VK_M:

                    appendToString('m');
                    break;
                case KeyEvent.VK_N:

                    appendToString('n');
                    break;
                case KeyEvent.VK_O:

                    appendToString('o');
                    break;
                case KeyEvent.VK_P:

                    appendToString('p');
                    break;
                case KeyEvent.VK_Q:

                    appendToString('q');
                    break;
                case KeyEvent.VK_R:

                    appendToString('r');
                    break;
                case KeyEvent.VK_S:

                    appendToString('s');
                    break;
                case KeyEvent.VK_T:

                    appendToString('t');
                    break;
                case KeyEvent.VK_U:

                    appendToString('u');
                    break;
                case KeyEvent.VK_V:

                    appendToString('v');
                    break;
                case KeyEvent.VK_W:

                    appendToString('w');
                    break;
                case KeyEvent.VK_X:

                    appendToString('x');
                    break;
                case KeyEvent.VK_Y:

                    appendToString('y');
                    break;
                case KeyEvent.VK_Z:

                    appendToString('z');
                    break;
                case KeyEvent.VK_0:

                    appendToString('0');
                    break;
                case KeyEvent.VK_1:

                    appendToString('1');
                    break;
                case KeyEvent.VK_2:

                    appendToString('2');
                    break;
                case KeyEvent.VK_3:

                    appendToString('3');
                    break;
                case KeyEvent.VK_4:

                    appendToString('4');
                    break;
                case KeyEvent.VK_5:

                    appendToString('5');
                    break;
                case KeyEvent.VK_6:

                    appendToString('6');
                    break;
                case KeyEvent.VK_7:

                    appendToString('7');
                    break;
                case KeyEvent.VK_8:

                    appendToString('8');
                    break;
                case KeyEvent.VK_9:

                    appendToString('9');
                    break;
                case KeyEvent.VK_SPACE:

                    appendToString(' ');
                    break;
                case KeyEvent.VK_BACK_SPACE:

                    if(inputQuerry.length() >= 1 && typing) inputQuerry = inputQuerry.substring(0, inputQuerry.length() - 1);
                    if(inputQuerry.length() == 0) inputQuerry = "";
                    break;
                case KeyEvent.VK_PERIOD:    //Punkt

                    appendToString('.');
                    break;
                case KeyEvent.VK_BACK_SLASH:

                    appendToString('/');
                    break;
            }
        }

        private void appendToString(char c) {

            if(typing) inputQuerry += c;
        }

        @Override
        public void mouseReleased(MouseEvent event) {

        }

        @Override
        public void update(double delta) {

        }

            //GETTER AND SETTER
        public boolean isTyping() {

            return typing;
        }

        public void setTyping(boolean typing) {

            this.typing = typing;
        }

        public String getInputQuerry() {

            return inputQuerry;
        }

        public void setInputQuerry(String inputQuerry) {

            this.inputQuerry = inputQuerry;
        }

        public boolean isInside(MouseEvent e, int x, int y, int scale) {

            return isInside(e, x, y, scale, scale);
        }

        public boolean isInside(MouseEvent e, int x, int y, int scaleX, int scaleY) {

            if(e.getX() >= x && e.getX() <= x + scaleX && e.getY() >= y && e.getY() <= y + scaleY) return true; return false;
        }
    }
