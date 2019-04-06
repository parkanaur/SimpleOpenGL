package ru.dansstuff.simpleopengl.viewer.listeners;

import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OpenGLViewerMouseListener implements MouseListener {
    private OpenGLViewer viewer;

    public OpenGLViewerMouseListener(OpenGLViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(1);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
