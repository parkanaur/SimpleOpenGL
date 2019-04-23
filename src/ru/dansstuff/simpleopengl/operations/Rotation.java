package ru.dansstuff.simpleopengl.operations;

import com.jogamp.opengl.GLAutoDrawable;
import lombok.Getter;
import lombok.Setter;

public class Rotation extends OpenGLOperation {
    @Getter @Setter
    private float x;
    @Getter @Setter
    private float y;
    @Getter @Setter
    private float z;
    @Getter @Setter
    private float angle;

    public Rotation() {
        
    }

    public Rotation(float angle, float x, float y, float z) {
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public void doOperation(GLAutoDrawable drawable) {
        drawable.getGL().getGL2().glRotatef(angle, x, y, z);
    }
}
