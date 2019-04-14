package ru.dansstuff.simpleopengl.objects.windows;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.Triangle;
import ru.dansstuff.simpleopengl.objects.OpenGLColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

public class TriangleFrame extends TypeBaseFrame {
    private JLabel x1Label = new JLabel("X1");
    private JTextField x1Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel y1Label = new JLabel("Y1");
    private JTextField y1Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel z1Label = new JLabel("Z1");
    private JTextField z1Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel x2Label = new JLabel("X2");
    private JTextField x2Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel y2Label = new JLabel("Y2");
    private JTextField y2Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel z2Label = new JLabel("Z2");
    private JTextField z2Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel x3Label = new JLabel("X3");
    private JTextField x3Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel y3Label = new JLabel("Y3");
    private JTextField y3Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel z3Label = new JLabel("Z3");
    private JTextField z3Field = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel rLabel = new JLabel("R");
    private JTextField rField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel gLabel = new JLabel("G");
    private JTextField gField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel bLabel = new JLabel("B");
    private JTextField bField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JButton okButton = new JButton("OK");

    public TriangleFrame() {
        setLayout(new GridLayout(5, 6));

        x1Field.setColumns(5); y1Field.setColumns(5); z1Field.setColumns(5);
        x2Field.setColumns(5); y2Field.setColumns(5); z2Field.setColumns(5);
        x3Field.setColumns(5); y3Field.setColumns(5); z3Field.setColumns(5);
        rField.setColumns(5); gField.setColumns(5); bField.setColumns(5);

        add(x1Label); add(x1Field); add(y1Label); add(y1Field); add(z1Label); add(z1Field);
        add(x2Label); add(x2Field); add(y2Label); add(y2Field); add(z2Label); add(z2Field);
        add(x3Label); add(x3Field); add(y3Label); add(y3Field); add(z3Label); add(z3Field);
        add(rLabel); add(rField); add(gLabel); add(gField); add(bLabel); add(bField);

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
        Triangle triangle = new Triangle();
        triangle.setP1(new Vec3(getNum(x1Field), getNum(y1Field), getNum(z1Field)));
        triangle.setP2(new Vec3(getNum(x2Field), getNum(y2Field), getNum(z2Field)));
        triangle.setP3(new Vec3(getNum(x3Field), getNum(y3Field), getNum(z3Field)));
        triangle.setColor(new OpenGLColor(getNum(rField), getNum(gField), getNum(bField)));
        getParent().getCurrentObject().addChild(triangle);
    }
}
