package ru.dansstuff.simpleopengl.misc.helpers;

import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.objects.windows.*;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ObjectCreationFrameFactory {
    private static Map<Class, Class> frameMap;

    static {
        frameMap = new HashMap<>();
        try {
            for (Class clazz : GLObject.getObjectTypes()) {
                frameMap.put(clazz, ((GLObject)clazz.getConstructor().newInstance()).getFrameClass());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static JFrame getFrame(Class clazz, OpenGLTestFrame parent) {
        TypeBaseFrame frame;
        try {
            frame = (TypeBaseFrame)frameMap.get(clazz).getConstructor().newInstance();
        }
        catch (Exception ex) {
            frame = new TypeBaseFrame() {
                @Override
                protected void createObject() {

                }
            };
        }

        frame.setParent(parent);
        frame.setVisible(true);
        frame.setTitle(clazz.getSimpleName());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = parent.getWidth() / 4;
        int height = parent.getHeight() / 4;
        frame.setBounds((screenSize.width - width) / 2,
                (screenSize.height - height) / 2,
                width, height);

        return frame;
    }
}
