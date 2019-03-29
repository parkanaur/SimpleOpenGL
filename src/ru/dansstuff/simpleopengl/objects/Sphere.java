package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.Vec3;

@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Sphere extends GLObject {
    @Getter @Setter
    private Vec3 center;
    @Getter @Setter
    private float radius;
    @Getter @Setter
    private OpenGLColor color;

    public Sphere() {
        this(new Vec3(0, 0, 0), 1, OpenGLColor.WHITE);
    }

    public Sphere(Vec3 center, float radius, OpenGLColor color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        //this.type = "Sphere";
    }

    @Override
    public void draw(GL2 gl) {
        GLU glu = new GLU();

        gl.glTranslatef(center.getX(), center.getY(), center.getZ());
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        GLUquadric q = glu.gluNewQuadric();
        glu.gluSphere(q, radius, 100, 100);

    }
}
