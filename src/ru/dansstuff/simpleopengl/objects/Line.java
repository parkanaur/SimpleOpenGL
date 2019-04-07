package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.Vec3;

@Builder
@EqualsAndHashCode(callSuper = false)
public class Line extends GLObject {
    @Getter @Setter
    private Vec3 p1, p2;
    @Getter @Setter
    private OpenGLColor color;

    public Line() {
        this(new Vec3(0, 0, 0), new Vec3(1, 0, 0), OpenGLColor.WHITE);
    }

    public Line(Vec3 p1, Vec3 p2, OpenGLColor color) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
        //this.type = "Line";
    }

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());
        gl.glVertex3f(p2.getX(), p2.getY(), p2.getZ());
        gl.glEnd();
    }
}
