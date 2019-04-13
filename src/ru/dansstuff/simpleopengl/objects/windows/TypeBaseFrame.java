package ru.dansstuff.simpleopengl.objects.windows;

import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import javax.swing.*;

public class TypeBaseFrame extends JFrame {
    @Getter @Setter
    private OpenGLTestFrame parent;
}
