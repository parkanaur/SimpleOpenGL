package ru.dansstuff.simpleopengl.objects.windows;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.EmptyObject;
import ru.dansstuff.simpleopengl.objects.GLObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

public class EmptyObjectFrame extends TypeBaseFrame {
    private JLabel xLabel = new JLabel("X");
    private JTextField xField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel yLabel = new JLabel("Y");
    private JTextField yField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel zLabel = new JLabel("Z");
    private JTextField zField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JButton okButton = new JButton("OK");

    public EmptyObjectFrame() {
        this(new EmptyObject());
    }

    public EmptyObjectFrame(GLObject o) {
        this.object = o;
        setLayout(new FlowLayout());

        if (!creatingObject) {
            EmptyObject box = (EmptyObject)o;
            xField.setText(String.valueOf(box.getCenter().getX()));
            yField.setText(String.valueOf(box.getCenter().getY()));
            zField.setText(String.valueOf(box.getCenter().getZ()));
        }

        xField.setColumns(5); yField.setColumns(5); zField.setColumns(5);

        add(xLabel); add(xField); add(yLabel); add(yField); add(zLabel); add(zField);

        okButton.addActionListener(e -> {
            try {
                createObject();
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });
        add(okButton);
    }

    protected void createObject() {
        EmptyObject emptyObject = (EmptyObject)object;
        emptyObject.setCenter(new Vec3(getNum(xField), getNum(yField), getNum(zField)));
        if (creatingObject) {
            getParent().getCurrentObject().addChild(emptyObject);
        }
    }
}
