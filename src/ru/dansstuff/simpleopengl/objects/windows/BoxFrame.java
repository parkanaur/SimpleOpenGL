package ru.dansstuff.simpleopengl.objects.windows;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.Box;
import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.objects.OpenGLColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

public class BoxFrame extends TypeBaseFrame {
    private JLabel xLabel = new JLabel("X");
    private JTextField xField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel yLabel = new JLabel("Y");
    private JTextField yField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel zLabel = new JLabel("Z");
    private JTextField zField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel lenLabel = new JLabel("Length");
    private JTextField lenField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel rLabel = new JLabel("R");
    private JTextField rField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel gLabel = new JLabel("G");
    private JTextField gField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel bLabel = new JLabel("B");
    private JTextField bField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JButton okButton = new JButton("OK");

    public BoxFrame() {
        setLayout(new GridLayout(4, 4));

        xField.setColumns(5);
        yField.setColumns(5);
        zField.setColumns(5);

        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(zLabel);
        add(zField);

        add(lenLabel);
        add(lenField);

        add(rLabel);
        add(rField);
        add(gLabel);
        add(gField);
        add(bLabel);
        add(bField);

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

    private void createObject() {
        Box box = new Box();
        box.setCenter(new Vec3(getNum(xField), getNum(yField), getNum(zField)));
        box.setColor(new OpenGLColor(getNum(rField), getNum(gField), getNum(bField)));
        box.setLength(getNum(lenField));
        getParent().getCurrentObject().addChild(box);
    }

    private int getNum(JTextField field) {
        return Integer.parseInt(field.getText());
    }
}
