package ru.dansstuff.simpleopengl.viewer;

import com.jogamp.opengl.awt.GLCanvas;
import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;
import ru.dansstuff.simpleopengl.viewer.listeners.*;

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

public class GLViewerCanvas extends GLCanvas {
    @Getter @Setter
    private Point curPos;

    @Getter
    private OpenGLViewer viewer;

    public void setViewer(OpenGLViewer viewer) {
        if (viewer == null) {
            return;
        }
        removeGLEventListener(this.viewer);
        this.viewer = viewer;
        addGLEventListener(this.viewer);
    }

    private void setListeners() {
        MouseAdapter listener = new OpenGLViewerMouseListener(viewer);
        addMouseListener(listener);
        addMouseWheelListener(listener);
        addMouseMotionListener(listener);

        addKeyListener(new OpenGLViewerKeyListener(viewer));
    }

    public GLViewerCanvas() {
        this(new OpenGLViewer());
    }

    public GLViewerCanvas(OpenGLViewer viewer) {
        setViewer(viewer);
        setListeners();
        curPos = new Point(-1, -1);
    }
}
