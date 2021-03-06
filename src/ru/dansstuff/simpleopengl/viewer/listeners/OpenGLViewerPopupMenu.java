package ru.dansstuff.simpleopengl.viewer.listeners;

import ru.dansstuff.simpleopengl.misc.helpers.SceneFileHelper;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpenGLViewerPopupMenu extends JPopupMenu {

    public OpenGLViewerPopupMenu(OpenGLViewer viewer) {
        JMenuItem loadSceneItem = new JMenuItem("Load scene...");
        loadSceneItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter("Model files", ".json"));
                if (fc.showOpenDialog(OpenGLViewerPopupMenu.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        viewer.setRoot(SceneFileHelper.readScene(fc.getSelectedFile()));
                        viewer.setNeedTextureResolution(true);
                    }
                    catch (IOException | IllegalStateException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(loadSceneItem);

        JMenuItem enableViewerItem = new JMenuItem("Start/stop rendering");
        enableViewerItem.addActionListener(e -> viewer.setEnabled(!viewer.isEnabled()));
        add(enableViewerItem);

        JMenuItem showAxisItem = new JMenuItem("Show axis");
        showAxisItem.addActionListener(e -> viewer.setDrawAxis(!viewer.isDrawAxis()));
        add(showAxisItem);

        JMenuItem resolveTexturesItem = new JMenuItem("Resolve textures");
        resolveTexturesItem.addActionListener(e -> viewer.setNeedTextureResolution(true));
        add(resolveTexturesItem);

        JMenuItem clearSceneItem = new JMenuItem("Clear scene");
        clearSceneItem.addActionListener(e -> viewer.clear());
        add(clearSceneItem);
    }
}
