package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import ru.dansstuff.simpleopengl.math.Vec3;

public class Line extends GLObject {
    @Getter @Setter
    private Vec3 p1, p2;
    @Getter @Setter
    private OpenGLColor color;
    @Getter
    private final String type = "Line";

    public Line() {
        this.p1 = new Vec3(0, 0, 0);
        this.p2 = new Vec3(1, 0, 0);
        this.color = OpenGLColor.WHITE;
    }

    public Line(Vec3 p1, Vec3 p2, OpenGLColor color) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());
        gl.glVertex3f(p2.getX(), p2.getY(), p2.getZ());
        gl.glEnd();
    }
}
