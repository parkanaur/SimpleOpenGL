package ru.dansstuff.simpleopengl.objects.windows;

import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import javax.swing.*;

public abstract class TypeBaseFrame extends JFrame {
    @Getter @Setter
    protected OpenGLTestFrame parent;

    abstract protected void createObject();

    protected int getNum(JTextField field) {
        return Integer.parseInt(field.getText());
    }
}
