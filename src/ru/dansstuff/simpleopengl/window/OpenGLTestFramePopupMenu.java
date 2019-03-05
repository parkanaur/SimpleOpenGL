package ru.dansstuff.simpleopengl.window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class OpenGLTestFramePopupMenu extends JPopupMenu {

    public OpenGLTestFramePopupMenu(GLCanvasWrapper glCanvasWrapper) {
        JMenuItem loadSceneItem = new JMenuItem("Load scene...");
        loadSceneItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();

                if (fc.showOpenDialog(OpenGLTestFramePopupMenu.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        glCanvasWrapper.getViewer().setRoot(glCanvasWrapper.parseTree(fc.getSelectedFile()));
                    }
                    catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(loadSceneItem);

        JMenuItem enableViewerItem = new JMenuItem("Start/stop rendering");
        enableViewerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glCanvasWrapper.getViewer().setEnabled(!glCanvasWrapper.getViewer().isEnabled());
            }
        });
        add(enableViewerItem);

        JMenuItem showAxisItem = new JMenuItem("Show axis");
        showAxisItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glCanvasWrapper.getViewer().setDrawAxis(!glCanvasWrapper.getViewer().isDrawAxis());
            }
        });
        add(showAxisItem);
    }
}
