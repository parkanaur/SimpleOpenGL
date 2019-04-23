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
    public static JFrame getFrame(Class clazz, OpenGLTestFrame parent, boolean creatingObject, GLObject o) {
        TypeBaseFrame frame;
        try {
            frame = (TypeBaseFrame)(frameMap.get(clazz).getDeclaredConstructor(GLObject.class).newInstance(o));
            frame.setCreatingObject(creatingObject);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        frame.setParent(parent);
        frame.setTitle(clazz.getSimpleName());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = parent.getWidth() / 4;
        int height = parent.getHeight() / 3;
        frame.setBounds((screenSize.width - width) / 2,
                (screenSize.height - height) / 2,
                width, height);

        frame.setVisible(true);
        return frame;
    }
}
