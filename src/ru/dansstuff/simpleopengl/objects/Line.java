package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dansstuff.simpleopengl.math.Vec3;

@AllArgsConstructor
public class Line implements GLObject {
    @Getter
    private final Vec3 p1, p2;
    @Getter
    private final OpenGLColor color;

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        gl.glVertex3f(p1.x, p1.y, p1.z);
        gl.glVertex3f(p2.x, p2.y, p2.z);
        gl.glEnd();
    }
}
