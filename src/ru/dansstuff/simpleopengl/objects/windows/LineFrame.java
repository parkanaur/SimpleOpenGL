package ru.dansstuff.simpleopengl.objects.windows;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.objects.OpenGLColor;
import ru.dansstuff.simpleopengl.objects.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

public class LineFrame extends TypeBaseFrame {
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

    private JLabel rLabel = new JLabel("R");
    private JTextField rField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel gLabel = new JLabel("G");
    private JTextField gField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel bLabel = new JLabel("B");
    private JTextField bField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JButton okButton = new JButton("OK");

    public LineFrame() {
        this(new Line());
    }

    public LineFrame(GLObject o) {
        this.object = o;
        setLayout(new FlowLayout());

        if (!creatingObject) {
            Line line = (Line)o;
            x1Field.setText(String.valueOf(line.getP1().getX()));
            y1Field.setText(String.valueOf(line.getP1().getY()));
            z1Field.setText(String.valueOf(line.getP1().getZ()));
            x2Field.setText(String.valueOf(line.getP2().getX()));
            y2Field.setText(String.valueOf(line.getP2().getY()));
            z2Field.setText(String.valueOf(line.getP2().getZ()));
            rField.setText(String.valueOf(line.getColor().getR()));
            gField.setText(String.valueOf(line.getColor().getG()));
            bField.setText(String.valueOf(line.getColor().getB()));
        }

        x1Field.setColumns(5); y1Field.setColumns(5); z1Field.setColumns(5);
        x2Field.setColumns(5); y2Field.setColumns(5); z2Field.setColumns(5);
        rField.setColumns(5); gField.setColumns(5); bField.setColumns(5);

        add(x1Label); add(x1Field); add(y1Label); add(y1Field); add(z1Label); add(z1Field);
        add(x2Label); add(x2Field); add(y2Label); add(y2Field); add(z2Label); add(z2Field);
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
        Line line = (Line)object;
        line.setP1(new Vec3(getNum(x1Field), getNum(y1Field), getNum(z1Field)));
        line.setP2(new Vec3(getNum(x2Field), getNum(y2Field), getNum(z2Field)));
        line.setColor(new OpenGLColor(getNum(rField), getNum(gField), getNum(bField)));
        if (creatingObject) {
            getParent().getCurrentObject().addChild(line);
        }
    }
}
