package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;

public class Line implements Primitive {
    public final float x1, x2, y1, y2, z1, z2;
    private final OpenGLColor color;

    public Line(float x1, float y1, float z1, float x2, float y2, float z2, OpenGLColor color) {
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
        gl.glVertex3f(x1, y1 ,z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glEnd();
    }
}
