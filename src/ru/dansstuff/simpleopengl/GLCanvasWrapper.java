package ru.dansstuff.simpleopengl;

import com.jogamp.opengl.awt.GLCanvas;
import ru.dansstuff.simpleopengl.viewer.ISceneViewer;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

public class GLCanvasWrapper {
    private final ISceneViewer viewer;
    private final GLCanvas glCanvas;

    public GLCanvasWrapper(int width, int height) {
        glCanvas = new GLCanvas();
        glCanvas.setSize(width, height);

        viewer = new OpenGLViewer(glCanvas);
        glCanvas.addGLEventListener(viewer);
    }

    public ISceneViewer getViewer() { return viewer; }

    public GLCanvas getGlCanvas() {
        return glCanvas;
    }

    public void clear() {
        viewer.clear();
    }
}
