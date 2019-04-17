package ru.dansstuff.simpleopengl.viewer.listeners;

import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OpenGLViewerKeyListener extends KeyAdapter {
    private OpenGLViewer viewer;

    public OpenGLViewerKeyListener(OpenGLViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (viewer == null) {
            return;
        }
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                viewer.moveForward(0.1f);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                viewer.moveBackward(0.1f);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                viewer.moveLeft(0.1f);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                viewer.moveRight(0.1f);
                break;
            case KeyEvent.VK_SHIFT:
                viewer.moveUp(0.1f);
                break;
            case KeyEvent.VK_CONTROL:
                viewer.moveDown(0.1f);
                break;
        }
    }
}
