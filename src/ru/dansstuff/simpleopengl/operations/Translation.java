package ru.dansstuff.simpleopengl.operations;

import com.jogamp.opengl.GLAutoDrawable;
import lombok.Getter;
import lombok.Setter;

public class Translation implements OpenGLOperation {
    @Getter @Setter
    public float x;
    @Getter @Setter
    public float y;
    @Getter @Setter
    public float z;

    public Translation() {

    }

    public Translation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public void doOperation(GLAutoDrawable drawable) {
        drawable.getGL().getGL2().glTranslatef(x, y, z);
    }
}
