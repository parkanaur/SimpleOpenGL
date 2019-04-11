package ru.dansstuff.simpleopengl.window;

import com.jogamp.opengl.util.Animator;
import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.misc.helpers.SceneFileHelper;
import ru.dansstuff.simpleopengl.viewer.GLViewerCanvas;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;
import ru.dansstuff.simpleopengl.viewer.listeners.OpenGLTestFramePopupMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OpenGLTestFrame
        extends JFrame {

    @Getter
    private int width;
    @Getter
    private int height;
    @Getter @Setter
    private GLViewerCanvas canvas;

    private final double editorPanelWidth = 0.25;

    public void setWidth(int width) {
        this.width = width;
        canvas.setSize(this.width, this.height);
    }

    public void setHeight(int height) {
        this.height = height;
        canvas.setSize(this.width, this.height);
    }

    public OpenGLTestFrame(int width, int height) {
        canvas = new GLViewerCanvas();
        setWidth(width);
        setHeight(height);
        setSize(width, height);
        setResizable(true);

        add(canvas);

        initWindow();

        Animator animator = new Animator(canvas);
        animator.start();
    }

    private void initWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, (int)(width * (1 + editorPanelWidth)), height);

        setTitle(this.getClass().getSimpleName());

        setVisible(true);
        setBackground(Color.BLACK);

        addWindowListener(new OpenGLTestFrameWindowListener());

        initEditorArea();
    }

    private void initEditorArea() {
        JPanel editorPanel = new JPanel();
        editorPanel.setSize((int)(width * editorPanelWidth), height);

        JButton loadBtn = new JButton("Load scene...");
        loadBtn.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();

            if (fc.showOpenDialog(OpenGLTestFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    canvas.getViewer().setRoot(SceneFileHelper.readScene(fc.getSelectedFile()));
                }
                catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        editorPanel.add(loadBtn);

        JButton saveBtn = new JButton("Save scene...");
        saveBtn.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();

            if(fc.showSaveDialog(OpenGLTestFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    SceneFileHelper.writeScene(canvas.getViewer().getRoot(), fc.getSelectedFile());
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        editorPanel.add(saveBtn);

        add(editorPanel, BorderLayout.WEST);
    }
}
