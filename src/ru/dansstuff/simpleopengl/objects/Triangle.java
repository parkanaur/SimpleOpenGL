package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;

public class Triangle<T extends Number> implements Primitive {
    public final T x1, x2, x3, y1, y2, y3, z1, z2, z3;
    private final OpenGLColor color;

    public Triangle(T x1, T y1, T z1, T x2, T y2, T z2, T x3, T y3, T z3, OpenGLColor color) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.z1 = z1;
        this.z2 = z2;
        this.z3 = z3;
        this.color = color;
    }

    public OpenGLColor getColor() { return color; }

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        gl.glVertex3f(x1.floatValue(), y1.floatValue(), z1.floatValue());
        gl.glVertex3f(x2.floatValue(), y2.floatValue(), z2.floatValue());
        gl.glVertex3f(x3.floatValue(), y3.floatValue(), z3.floatValue());
        gl.glEnd();
    }
}
