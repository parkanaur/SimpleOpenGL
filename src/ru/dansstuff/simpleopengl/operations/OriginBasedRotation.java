package ru.dansstuff.simpleopengl.operations;

import com.jogamp.opengl.GLAutoDrawable;
import lombok.Getter;
import lombok.Setter;

public class OriginBasedRotation extends OpenGLOperation {
    @Getter @Setter
    protected float angle;
    @Getter @Setter
    protected float speed;

    @Override
    public void doOperation(GLAutoDrawable glAutoDrawable) {

    }
}
