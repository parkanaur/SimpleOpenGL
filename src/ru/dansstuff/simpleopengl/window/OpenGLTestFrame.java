package ru.dansstuff.simpleopengl.window;

import com.jogamp.opengl.util.Animator;
import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.viewer.GLViewerCanvas;
import ru.dansstuff.simpleopengl.viewer.listeners.OpenGLTestFramePopupMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpenGLTestFrame
        extends JFrame {

    @Getter
    private int width;
    @Getter
    private int height;
    @Getter @Setter
    private GLViewerCanvas canvas;

    public void setWidth(int width) {
        this.width = width;
        canvas.setSize(this.width, this.height);
    }

    public void setHeight(int height) {
        this.height = height;
        canvas.setSize(this.width, this.height);
    }

    public OpenGLTestFrame(int width, int height) {
        this.width = width;
        this.height = height;
        setResizable(true);

        canvas = new GLViewerCanvas();
        add(canvas);
        initWindow();

        Animator animator = new Animator(canvas);
        animator.start();
    }

    private void initWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);

        setTitle(this.getClass().getSimpleName());

        setVisible(true);
        setBackground(Color.BLACK);

        addWindowListener(new OpenGLTestFrameWindowListener());
    }
}
