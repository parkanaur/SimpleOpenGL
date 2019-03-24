package ru.dansstuff.simpleopengl.window;

import ru.dansstuff.simpleopengl.misc.helpers.SceneFileHelper;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class OpenGLTestFramePopupMenu extends JPopupMenu {

    public OpenGLTestFramePopupMenu(OpenGLViewer viewer) {
        JMenuItem loadSceneItem = new JMenuItem("Load scene...");
        loadSceneItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();

                if (fc.showOpenDialog(OpenGLTestFramePopupMenu.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        viewer.setRoot(SceneFileHelper.readScene(fc.getSelectedFile()));
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
                viewer.setEnabled(!viewer.isEnabled());
            }
        });
        add(enableViewerItem);

        JMenuItem showAxisItem = new JMenuItem("Show axis");
        showAxisItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.setDrawAxis(!viewer.isDrawAxis());
            }
        });
        add(showAxisItem);
    }
}
