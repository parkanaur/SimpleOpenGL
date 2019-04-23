package ru.dansstuff.simpleopengl.viewer.frames;

import lombok.Getter;
import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class CurrentObjectSelectionFrame extends JFrame {
    @Getter
    private OpenGLTestFrame parent;
    private FrameCallbackAction action;

    @Getter
    private JList<GLObject> objectJList;
    @Getter
    private DefaultListModel<GLObject> list;

    private JButton okButton;

    public CurrentObjectSelectionFrame(OpenGLTestFrame parent, FrameCallbackAction action) {
        this.parent = parent;
        this.action = action;
        setLayout(new FlowLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = parent.getWidth() / 2;
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
            action.doAction(this);
            CurrentObjectSelectionFrame.this.dispatchEvent(new WindowEvent(CurrentObjectSelectionFrame.this, WindowEvent.WINDOW_CLOSING));
        });
        add(okButton);

        setVisible(true);
    }
}
