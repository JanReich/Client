package graphics.interfaces;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

    public interface BasicInteractableObject extends GraphicalObject {

        void keyPressed(KeyEvent event);

        void keyReleased(KeyEvent event);

        void mouseReleased(MouseEvent event);
    }
