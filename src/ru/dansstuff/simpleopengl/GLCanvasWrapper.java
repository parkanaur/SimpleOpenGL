package ru.dansstuff.simpleopengl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import ru.dansstuff.simpleopengl.drawers.PrimitiveDrawable;
import ru.dansstuff.simpleopengl.drawers.PrimitiveDrawer;

public class GLCanvasWrapper {
    private final PrimitiveDrawable drawer;
    private final GLCanvas glCanvas;

    public GLCanvasWrapper(int width, int height) {
        glCanvas = new GLCanvas();
        glCanvas.setSize(width, height);

        drawer = new PrimitiveDrawer(glCanvas);
        glCanvas.addGLEventListener(drawer);
    }

    public PrimitiveDrawable getDrawer() { return drawer; }

    public GLCanvas getGlCanvas() {
        return glCanvas;
    }

    public void clear() {
        drawer.clear();
    }
}
