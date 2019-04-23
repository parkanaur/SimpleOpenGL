package ru.dansstuff.simpleopengl.viewer.frames;

import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import javax.swing.*;
import java.awt.*;

public class CurrentObjectSelectionFrame extends JFrame {
    private OpenGLTestFrame parent;

    private JList<GLObject> objectJList;
    private DefaultListModel<GLObject> list;

    private JButton okButton;

    public CurrentObjectSelectionFrame(OpenGLTestFrame parent) {
        this.parent = parent;
        setLayout(new FlowLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = parent.getWidth() / 4;
        int height = parent.getHeight() / 2;
        setBounds((screenSize.width - width) / 2,
                (screenSize.height - height) / 2,
                width, height);

        setTitle("Select current object");

        list = new DefaultListModel<>();
        objectJList = new JList<>(list);
        objectJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        for (GLObject glObject : parent.getCanvas().getViewer().getRoot().getTreeAsList()) {
            list.addElement(glObject);
        }

        objectJList.setPreferredSize(new Dimension(width, height * 3 / 4));
        add(objectJList);

        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            if (objectJList.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "No object selected");
                return;
            }

            parent.setCurrentObject(objectJList.getSelectedValue());
        });
        add(okButton);

        setVisible(true);
    }
}
