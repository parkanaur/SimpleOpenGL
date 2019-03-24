package ru.dansstuff.simpleopengl.viewer.listeners;

import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class OpenGLViewerMouseWheelListener implements MouseWheelListener {
    private OpenGLViewer viewer;

    public OpenGLViewerMouseWheelListener(OpenGLViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        viewer.scale(e.getWheelRotation());
    }
}
