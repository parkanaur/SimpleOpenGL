package ru.dansstuff.simpleopengl.operations;

import com.jogamp.opengl.GLAutoDrawable;
import lombok.Getter;
import lombok.Setter;

public class Rotation implements OpenGLOperation {
    @Getter @Setter
    public float x;
    @Getter @Setter
    public float y;
    @Getter @Setter
    public float z;
    @Getter @Setter
    public float angle;

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
