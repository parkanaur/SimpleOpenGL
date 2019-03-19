package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import lombok.*;
import ru.dansstuff.simpleopengl.math.Vec3;

@NoArgsConstructor
public class Cylinder extends GLObject {
    @Getter @Setter
    private Vec3 center;
    @Getter @Setter
    private float radius;
    @Getter @Setter
    private float height;
    @Getter @Setter
    private OpenGLColor color;
    @Getter
    private final String type = "Cylinder";

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
