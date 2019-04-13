package ru.dansstuff.simpleopengl.window;

import com.jogamp.opengl.util.Animator;
import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.misc.helpers.SceneFileHelper;
import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.viewer.GLViewerCanvas;

import javax.swing.*;
import java.awt.*;
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

    private GLObject currentObject;

    public void setWidth(int width) {
        this.width = width;
        canvas.setSize(this.width, this.height);
    }

    public void setHeight(int height) {
        this.height = height;
        canvas.setSize(this.width, this.height);
    }

    public OpenGLTestFrame(int width, int height) {
        this(width, height, new GLViewerCanvas());
    }

    public OpenGLTestFrame(int width, int height, GLViewerCanvas canvas) {
        this.canvas = canvas;
        currentObject = canvas.getViewer().getRoot();
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
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);

        setTitle(this.getClass().getSimpleName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(Color.BLACK);

        initMenuBar();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        setVisible(true);
    }

    private void initMenuBar() {
        JMenuBar mainBar = new JMenuBar();

        // --- load/save menu ---
        JMenu loadSaveMenu = new JMenu("File");

        JMenuItem loadMenuItem = new JMenuItem("Open scene...");
        loadMenuItem.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();

            if (fc.showOpenDialog(OpenGLTestFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    canvas.getViewer().setRoot(SceneFileHelper.readScene(fc.getSelectedFile()));
                    canvas.getViewer().setNeedTextureResolution(true);
                }
                catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JMenuItem saveMenuItem = new JMenuItem("Save scene...");
        saveMenuItem.addActionListener(e -> {
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

        loadSaveMenu.add(loadMenuItem);
        loadSaveMenu.add(saveMenuItem);
        mainBar.add(loadSaveMenu);

        // --- scene handling menu ---
        JMenu sceneHandlingMenu = new JMenu("Scene");

        JMenuItem enableSceneItem = new JMenuItem("Enable/disable rendering");
        enableSceneItem.addActionListener(e -> {
            canvas.getViewer().setEnabled(!canvas.getViewer().isEnabled());
        });

        JMenuItem enableAxisItem = new JMenuItem("Enable/disable axis");
        enableAxisItem.addActionListener(e -> {
            canvas.getViewer().setDrawAxis(!canvas.getViewer().isDrawAxis());
        });

        sceneHandlingMenu.add(enableSceneItem);
        sceneHandlingMenu.add(enableAxisItem);
        mainBar.add(sceneHandlingMenu);

        setJMenuBar(mainBar);
    }
}
