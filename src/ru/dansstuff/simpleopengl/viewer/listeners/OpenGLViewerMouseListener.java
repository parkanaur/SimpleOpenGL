package ru.dansstuff.simpleopengl.viewer.listeners;

import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

public class OpenGLViewerMouseListener extends MouseAdapter {
    private OpenGLViewer viewer;

    public OpenGLViewerMouseListener(OpenGLViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        viewer.getCurMousePos().x = -1;
        viewer.getCurMousePos().y = -1;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        viewer.getCurMousePos().x = -1;
        viewer.getCurMousePos().y = -1;

        if (e.isPopupTrigger()) {
            OpenGLTestFramePopupMenu menu = new OpenGLTestFramePopupMenu(viewer);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (viewer == null) {
            return;
        }
        if (e.getX() != viewer.getCurMousePos().x) {
            if (viewer.getCurMousePos().x != -1)
                viewer.rotLeft((e.getX() - viewer.getCurMousePos().x) * 0.1f);
        }
        if (e.getY() != viewer.getCurMousePos().y) {
            if (viewer.getCurMousePos().y != -1)
                viewer.rotUp((e.getY() - viewer.getCurMousePos().y) * 0.1f);
        }
        viewer.getCurMousePos().x = e.getX();
        viewer.getCurMousePos().y = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        viewer.scale(e.getWheelRotation());
    }
}
