package ru.dansstuff.simpleopengl.operations;

import com.jogamp.opengl.GLAutoDrawable;
import lombok.Getter;
import lombok.Setter;

public class Translation extends OpenGLOperation {
    @Getter @Setter
    private float x;
    @Getter @Setter
    private float y;
    @Getter @Setter
    private float z;

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
