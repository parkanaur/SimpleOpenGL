package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.Vec3;

@Builder
@EqualsAndHashCode(callSuper = false)
public class Cylinder extends GLObject {
    @Getter @Setter
    private Vec3 center;
    @Getter @Setter
    private float radius;
    @Getter @Setter
    private float height;
    @Getter @Setter
    private OpenGLColor color;

    public Cylinder() {
        this(new Vec3(0, 0, 0), 1, 1, OpenGLColor.WHITE);
    }

    public Cylinder(Vec3 center, float radius, float height, OpenGLColor color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.height = height;
    }

    @Override
    public void draw(GL2 gl) {
        GLU glu = new GLU();

        gl.glTranslatef(center.getX(), center.getY(), center.getZ());
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        GLUquadric q = glu.gluNewQuadric();
        glu.gluCylinder(q, radius, radius, height, 100, 100);

    }
}
