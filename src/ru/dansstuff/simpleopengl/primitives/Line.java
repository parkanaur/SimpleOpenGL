package ru.dansstuff.simpleopengl.primitives;

import com.jogamp.opengl.GL2;

public class Line<T extends Number> implements Primitive {
    public final T x1, x2, y1, y2, z1, z2;
    private final OpenGLColor color;

    public Line(T x1, T y1, T z1, T x2, T y2, T z2, OpenGLColor color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
        this.color = color;
    }

    public OpenGLColor getColor() { return color; }

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        gl.glVertex3f(x1.floatValue(), y1.floatValue(),z1.floatValue());
        gl.glVertex3f(x2.floatValue(), y2.floatValue(),z2.floatValue());
        gl.glEnd();
    }
}
