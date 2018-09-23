package graphics.interfaces;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface ManagementObject {

        void update(double delta);

        void keyReleased(KeyEvent e);

        void mouseReleased(MouseEvent e);
    }
