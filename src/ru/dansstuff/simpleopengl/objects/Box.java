package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.windows.BoxFrame;

@Builder
@EqualsAndHashCode(callSuper = false)
public class Box extends GLObject {
    @Getter @Setter
    private Vec3 center;
    @Getter @Setter
    private float length;
    @Getter @Setter
    private OpenGLColor color;

    public Box() {
        this(new Vec3(0, 0, 0), 1, OpenGLColor.WHITE);
    }

    public Box(Vec3 center, float length, OpenGLColor color) {
        this.center = center;
        this.length = length;
        this.color = color;
        frameClass = BoxFrame.class;
    }

    public void draw (GL2 gl) {
        GLUT glut = new GLUT();
        gl.glTranslatef(center.getX(), center.getY(), center.getZ());
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        glut.glutSolidCube(length);
    }

    public void update() {

    }
}
