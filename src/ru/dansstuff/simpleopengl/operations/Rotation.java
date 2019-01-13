package ru.dansstuff.simpleopengl.operations;

import com.jogamp.opengl.GLAutoDrawable;

public class Rotation implements OpenGLOperation {
    public float x;
    public float y;
    public float z;
    public float angle;

    public Rotation(float angle, float x, float y, float z) {
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public void doOperation(GLAutoDrawable drawable) {
        drawable.getGL().getGL2().glRotatef(angle, x, y, z);
    }
}
