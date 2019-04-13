package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.Vec3;

@Builder
@EqualsAndHashCode(callSuper = false)
public class Triangle extends GLObject {
    @Getter @Setter
    private Vec3 p1, p2, p3;
    @Getter @Setter
    private OpenGLColor color;

    public Triangle() {
        this(new Vec3(0, 0, 0), new Vec3(1, 0, 0), new Vec3(0, 1, 0), OpenGLColor.WHITE);
    }

    public Triangle(Vec3 p1, Vec3 p2, Vec3 p3, OpenGLColor color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = OpenGLColor.WHITE;
    }

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());
        gl.glVertex3f(p2.getX(), p2.getY(), p2.getZ());
        gl.glVertex3f(p3.getX(), p3.getY(), p3.getZ());
        gl.glEnd();
    }
}
