package ru.dansstuff.simpleopengl.objects.windows;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.Cylinder;
import ru.dansstuff.simpleopengl.objects.OpenGLColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

public class CylinderFrame extends TypeBaseFrame {
    private JLabel xLabel = new JLabel("X");
    private JTextField xField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel yLabel = new JLabel("Y");
    private JTextField yField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel zLabel = new JLabel("Z");
    private JTextField zField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel radiusLabel = new JLabel("Radius");
    private JTextField radiusField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel heightLabel = new JLabel("Height");
    private JTextField heightField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel rLabel = new JLabel("R");
    private JTextField rField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel gLabel = new JLabel("G");
    private JTextField gField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel bLabel = new JLabel("B");
    private JTextField bField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JButton okButton = new JButton("OK");

    public CylinderFrame() {
        setLayout(new GridLayout(5, 4));

        xField.setColumns(5);
        yField.setColumns(5);
        zField.setColumns(5);

        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(zLabel);
        add(zField);

        add(radiusLabel);
        add(radiusField);

        add(heightLabel);
        add(heightField);

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

    protected void createObject() {
        Cylinder cylinder = new Cylinder();
        cylinder.setCenter(new Vec3(getNum(xField), getNum(yField), getNum(zField)));
        cylinder.setColor(new OpenGLColor(getNum(rField), getNum(gField), getNum(bField)));
        cylinder.setRadius(getNum(radiusField));
        cylinder.setHeight(getNum(heightField));
        getParent().getCurrentObject().addChild(cylinder);
    }
}
