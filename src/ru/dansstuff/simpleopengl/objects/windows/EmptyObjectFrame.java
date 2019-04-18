package ru.dansstuff.simpleopengl.objects.windows;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.EmptyObject;

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
        setLayout(new FlowLayout());

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
        EmptyObject emptyObject = new EmptyObject();
        emptyObject.setCenter(new Vec3(getNum(xField), getNum(yField), getNum(zField)));
        getParent().getCurrentObject().addChild(emptyObject);
    }
}
