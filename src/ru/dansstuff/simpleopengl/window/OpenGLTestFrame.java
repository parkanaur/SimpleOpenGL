package ru.dansstuff.simpleopengl.window;

import com.jogamp.opengl.util.Animator;
import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.misc.helpers.SceneFileHelper;
import ru.dansstuff.simpleopengl.objects.EmptyObject;
import ru.dansstuff.simpleopengl.objects.GLObject;
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

    private GLObject currentObject;

    private final double editorPanelCoef = 0.2;

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
        canvas.getViewer().setRoot(new EmptyObject());
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
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, (int)(width * (1 + editorPanelCoef)), height);

        setTitle(this.getClass().getSimpleName());

        setVisible(true);
        setBackground(Color.BLACK);

        addWindowListener(new OpenGLTestFrameWindowListener());

        initEditorArea();
    }

    private JButton loadBtn;
    private JButton saveBtn;

    private JComboBox<Class> objectChooserBox;
    private JButton addObjectBtn;

    private JList<GLObject> objectJList;
    private DefaultListModel<GLObject> objectList;

    private void initEditorArea() {
        int editorPanelWidth = (int)(width * editorPanelCoef);
        JPanel editorPanel = new JPanel();
        editorPanel.setPreferredSize(new Dimension(editorPanelWidth, height));
       // editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.Y_AXIS));

        // --- load/save row ---

        loadBtn = new JButton("Load scene...");
        Dimension lbSize = loadBtn.getPreferredSize();
        loadBtn.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();

            if (fc.showOpenDialog(OpenGLTestFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    canvas.getViewer().setRoot(SceneFileHelper.readScene(fc.getSelectedFile()));
                    objectList.clear();
                    for (GLObject obj : canvas.getViewer().getRoot().getTreeAsList()) {
                        objectList.addElement(obj);
                    }
                }
                catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        editorPanel.add(loadBtn);

        saveBtn = new JButton("Save scene...");
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

        // --- object choosing/creation row ---
        objectChooserBox = new JComboBox<>(GLObject.getObjectTypes());
        objectChooserBox.setSize(new Dimension(editorPanelWidth / 2, height / 16));
        editorPanel.add(objectChooserBox);

        addObjectBtn = new JButton("Add child");
        addObjectBtn.setSize(new Dimension(editorPanelWidth / 6, height / 16));
        editorPanel.add(addObjectBtn);

        // --- object selection row ---
        objectList = new DefaultListModel<>();
        objectList.addElement(canvas.getViewer().getRoot());
        JList<GLObject> objectJList = new JList<>(objectList);
        editorPanel.add(objectJList);

        JLabel emptyLabel = new JLabel();
        editorPanel.add(emptyLabel);

        JLabel currentObjectLbl = new JLabel();
        currentObjectLbl.setText(String.format("<html>Selected: <br />%s</html>", currentObject));
        editorPanel.add(currentObjectLbl);

        add(editorPanel, BorderLayout.WEST);
        pack();
    }
}
