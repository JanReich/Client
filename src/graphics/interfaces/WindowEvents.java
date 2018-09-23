package graphics.interfaces;

import java.awt.event.WindowEvent;

    public interface WindowEvents {

        void windowClosing(WindowEvent e);

        void windowActivated(WindowEvent e);

        void windowDeactivated(WindowEvent e);
    }
