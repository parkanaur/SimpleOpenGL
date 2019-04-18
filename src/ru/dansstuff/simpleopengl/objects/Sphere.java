package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.windows.SphereFrame;

@Builder
@EqualsAndHashCode(callSuper = false)
public class Sphere extends GLObject {
    @Getter @Setter
    private Vec3 center;
    @Getter
    private float radius;
    @Getter @Setter
    private OpenGLColor color;

    public void setRadius(float radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Invalid radius: " + radius);
        }
    }

    public Sphere() {
        this(new Vec3(0, 0, 0), 1, OpenGLColor.WHITE);
    }

    public Sphere(Vec3 center, float radius, OpenGLColor color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        frameClass = SphereFrame.class;
    }

    @Override
    public void draw(GL2 gl) {
        GLU glu = new GLU();

        gl.glTranslatef(center.getX(), center.getY(), center.getZ());
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        GLUquadric q = glu.gluNewQuadric();
        if (texture != null) {
            glu.gluQuadricTexture(q, true);
            gl.glBindTexture(gl.GL_TEXTURE_2D, texture.getTextureObject(gl));
        }
        glu.gluSphere(q, radius, 100, 100);
        glu.gluDeleteQuadric(q);
    }

    public void update() {

    }
}
