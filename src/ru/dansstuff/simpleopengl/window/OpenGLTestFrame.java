package ru.dansstuff.simpleopengl.window;

import com.jogamp.opengl.util.Animator;
import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.misc.helpers.ObjectCreationFrameFactory;
import ru.dansstuff.simpleopengl.misc.helpers.SceneFileHelper;
import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.viewer.GLViewerCanvas;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;

public class OpenGLTestFrame
        extends JFrame {

    @Getter
    private int width;
    @Getter
    private int height;
    @Getter @Setter
    private GLViewerCanvas canvas;
    @Getter @Setter
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

        mainBar.add(getLoadSaveMenu());
        mainBar.add(getSceneHandlingMenu());
        mainBar.add(getObjectHandlingMenu());

        setJMenuBar(mainBar);
    }

    private JMenu getLoadSaveMenu() {
        JMenu loadSaveMenu = new JMenu("File");

        JMenuItem loadMenuItem = new JMenuItem("Open scene...");
        loadMenuItem.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Model files (*.json)", "json"));
            if (fc.showOpenDialog(OpenGLTestFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    canvas.getViewer().setRoot(SceneFileHelper.readScene(fc.getSelectedFile()));
                    canvas.getViewer().setNeedTextureResolution(true);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(this, "Could not parse scene from file");
                }
            }
        });

        JMenuItem saveMenuItem = new JMenuItem("Save scene...");
        saveMenuItem.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Model files (*.json)", "json"));
            if(fc.showSaveDialog(OpenGLTestFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    SceneFileHelper.writeScene(canvas.getViewer().getRoot(), fc.getSelectedFile());
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> {
            System.exit(0);
        });

        loadSaveMenu.add(loadMenuItem);
        loadSaveMenu.add(saveMenuItem);
        loadSaveMenu.addSeparator();
        loadSaveMenu.add(exitMenuItem);

        return loadSaveMenu;
    }

    private JMenu getSceneHandlingMenu() {
        JMenu sceneHandlingMenu = new JMenu("Scene");

        JMenuItem enableSceneItem = new JMenuItem("Enable/disable rendering");
        enableSceneItem.addActionListener(e -> {
            canvas.getViewer().setEnabled(!canvas.getViewer().isEnabled());
        });

        JMenuItem enableAxisItem = new JMenuItem("Enable/disable axis");
        enableAxisItem.addActionListener(e -> {
            canvas.getViewer().setDrawAxis(!canvas.getViewer().isDrawAxis());
        });

        JMenuItem enableDebugTextItem = new JMenuItem("Enable/disable debug text");
        enableDebugTextItem.addActionListener(e -> {
            canvas.getViewer().setDrawDebugText(!canvas.getViewer().isDrawDebugText());
        });

        JMenuItem clearSceneItem = new JMenuItem("Clear scene");
        clearSceneItem.addActionListener(e -> canvas.getViewer().clear());
        add(clearSceneItem);

        sceneHandlingMenu.add(enableSceneItem);
        sceneHandlingMenu.add(enableAxisItem);
        sceneHandlingMenu.add(enableDebugTextItem);
        sceneHandlingMenu.add(clearSceneItem);

        return sceneHandlingMenu;
    }

    private JMenu getObjectHandlingMenu() {
        JMenu objectHandlingMenu = new JMenu("Objects");

        JMenu objectAddingMenu = new JMenu("Add child object");
        for (Class clazz : GLObject.getObjectTypes()) {
            JMenuItem typeItem = new JMenuItem(clazz.getSimpleName());
            typeItem.addActionListener(e -> {
                JFrame typeCreationFrame = ObjectCreationFrameFactory.getFrame(clazz, this);
            });

            objectAddingMenu.add(typeItem);
        }

        JMenuItem currentObjectSelectionItem = new JMenuItem("Select current object");

        JMenuItem objectEditingItem = new JMenuItem("Edit object...");


        objectHandlingMenu.add(objectAddingMenu);
        objectHandlingMenu.addSeparator();
        objectHandlingMenu.add(currentObjectSelectionItem);
        objectHandlingMenu.add(objectEditingItem);

        return objectHandlingMenu;
    }
}
