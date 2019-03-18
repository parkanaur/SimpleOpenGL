package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import ru.dansstuff.simpleopengl.math.Vec3;

@NoArgsConstructor
@RequiredArgsConstructor
public class Line extends GLObject {
    @Getter @Setter @NonNull
    private Vec3 p1, p2;
    @Getter @Setter @NonNull
    private OpenGLColor color;
    @Getter
    private final String type = "Line";

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(color.getR(), color.getG(), color.getB());
        gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());
        gl.glVertex3f(p2.getX(), p2.getY(), p2.getZ());
        gl.glEnd();
    }
}
