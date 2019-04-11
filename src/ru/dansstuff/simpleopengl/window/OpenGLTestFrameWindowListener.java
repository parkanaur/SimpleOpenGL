package ru.dansstuff.simpleopengl.window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OpenGLTestFrameWindowListener
    extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

        public void windowClosed(WindowEvent e) {
            System.exit(0);
        }
}
