package ru.dansstuff.simpleopengl;

import com.jogamp.opengl.awt.GLCanvas;
import ru.dansstuff.simpleopengl.drawers.ISceneViewer;
import ru.dansstuff.simpleopengl.drawers.OpenGLViewer;

public class GLCanvasWrapper {
    private final ISceneViewer drawer;
    private final GLCanvas glCanvas;

    public GLCanvasWrapper(int width, int height) {
        glCanvas = new GLCanvas();
        glCanvas.setSize(width, height);

        drawer = new OpenGLViewer(glCanvas);
        glCanvas.addGLEventListener(drawer);
    }

    public ISceneViewer getDrawer() { return drawer; }

    public GLCanvas getGlCanvas() {
        return glCanvas;
    }

    public void clear() {
        drawer.clear();
    }
}
