package ru.dansstuff.simpleopengl.objects.windows;

import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import javax.swing.*;

public abstract class TypeBaseFrame extends JFrame {
    @Getter @Setter
    protected OpenGLTestFrame parent;

    @Getter @Setter
    protected boolean creatingObject;

    @Getter @Setter
    protected GLObject object;

    abstract protected void createObject();

    protected float getNum(JTextField field) {
        return Float.parseFloat(field.getText());
    }
}
