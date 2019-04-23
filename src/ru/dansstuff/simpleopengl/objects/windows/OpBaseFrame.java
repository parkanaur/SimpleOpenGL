package ru.dansstuff.simpleopengl.objects.windows;

import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import javax.swing.*;

public class OpBaseFrame extends JFrame {
    @Getter @Setter
    protected OpenGLTestFrame parent;

    public float getNum(JTextField field) {
        return Float.parseFloat(field.getText());
    }
}
