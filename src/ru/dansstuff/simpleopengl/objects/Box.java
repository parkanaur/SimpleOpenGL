package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import ru.dansstuff.simpleopengl.math.Vec3;

public class Box implements Primitive {
    public final Vec3 center;
    public final float length;
    public final OpenGLColor color;

    public Box(Vec3 center, float length, OpenGLColor color) {
        this.center = center;
        this.length = length;
        this.color = color;
    }

    public void draw (GL2 gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glTranslatef(center.x, center.y, center.z);
        gl.glBegin(gl.GL_QUADS);
            gl.glColor3f(color.getR(), color.getG(), color.getB());
           // gl.glVertex3f();
        gl.glEnd();
        gl.glPopMatrix();
    }
}
