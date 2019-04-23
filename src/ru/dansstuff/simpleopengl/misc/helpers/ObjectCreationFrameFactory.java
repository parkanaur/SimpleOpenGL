package ru.dansstuff.simpleopengl.misc.helpers;

import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.objects.windows.*;
import ru.dansstuff.simpleopengl.operations.OpenGLOperation;
import ru.dansstuff.simpleopengl.operations.OriginBasedRotation;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class ObjectCreationFrameFactory {
    private static Map<Class, Class> objFrameMap;
    private static Map<Class, Class> opFrameMap;

    static {
        objFrameMap = new HashMap<>();
        opFrameMap = new HashMap();
        try {
            for (Class clazz : GLObject.getObjectTypes()) {
                objFrameMap.put(clazz, ((GLObject) clazz.getConstructor().newInstance()).getFrameClass());
            }
            for (Class clazz : OpenGLOperation.getObjectTypes()) {
                opFrameMap.put(clazz, ((OpenGLOperation)clazz.getConstructor().newInstance()).getFrameClass());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static JFrame getOpFrame(Class clazz, OpenGLTestFrame parent) {
        OpBaseFrame frame = new OpBaseFrame();
        frame.setParent(parent);
        frame.setLayout(new FlowLayout());

        JLabel aLabel = new JLabel("Angle");
        JTextField aField = new JFormattedTextField(NumberFormat.getNumberInstance());
        aField.setColumns(5);

        JLabel sLabel = new JLabel("Speed");
        JTextField sField = new JFormattedTextField(NumberFormat.getNumberInstance());
        sField.setColumns(5);

        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(e -> {
            OriginBasedRotation r = new OriginBasedRotation();
            r.setAngle(frame.getNum(aField));
            r.setSpeed(frame.getNum(sField));
            frame.getParent().getCurrentObject().addTransform(r);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });

        frame.add(aLabel); frame.add(aField);
        frame.add(sLabel); frame.add(sField);
        frame.add(okBtn);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = parent.getWidth() / 2;
        int height = parent.getHeight() / 2;
        frame.setBounds((screenSize.width - width) / 2,
                (screenSize.height - height) / 2,
                width, height);
        frame.setVisible(true);
        return frame;
    }

    public static JFrame getFrame(Class clazz, OpenGLTestFrame parent, boolean creatingObject, GLObject o) {
        TypeBaseFrame frame;
        try {
            frame = (TypeBaseFrame)(objFrameMap.get(clazz).getDeclaredConstructor(GLObject.class).newInstance(o));
            frame.setCreatingObject(creatingObject);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        frame.setParent(parent);
        frame.setTitle(clazz.getSimpleName());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = parent.getWidth() / 2;
        int height = parent.getHeight() / 2;
        frame.setBounds((screenSize.width - width) / 2,
                (screenSize.height - height) / 2,
                width, height);

        frame.setVisible(true);
        return frame;
    }
}
