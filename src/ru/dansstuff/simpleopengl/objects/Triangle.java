package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import ru.dansstuff.simpleopengl.math.Vec3;

@NoArgsConstructor
@RequiredArgsConstructor
public class Triangle extends GLObject {
    @Getter @Setter @NonNull
    private Vec3 p1, p2, p3;
    @Getter @Setter @NonNull
    private OpenGLColor color;
    @Getter
    private final String type = "Triangle";

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());
        gl.glVertex3f(p2.getX(), p2.getY(), p2.getZ());
        gl.glVertex3f(p3.getX(), p3.getY(), p3.getZ());
        gl.glEnd();
    }
}
