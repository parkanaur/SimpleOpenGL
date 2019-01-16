package ru.dansstuff.simpleopengl.operations;

import com.jogamp.opengl.GLAutoDrawable;

public class Translation implements OpenGLOperation {
    public float x;
    public float y;
    public float z;

    public Translation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public void doOperation(GLAutoDrawable drawable) {
        drawable.getGL().getGL2().glTranslatef(x, y, z);
    }
}
