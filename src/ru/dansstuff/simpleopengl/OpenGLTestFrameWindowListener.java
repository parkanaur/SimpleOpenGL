package ru.dansstuff.simpleopengl;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OpenGLTestFrameWindowListener
    implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

        public void windowClosed(WindowEvent e) {
            System.exit(0);
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
}
