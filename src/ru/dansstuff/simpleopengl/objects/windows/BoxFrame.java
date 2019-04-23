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
        this(new Box());
    }

    public BoxFrame(GLObject o) {
        this.object = o;
        setLayout(new FlowLayout());

        if (!creatingObject) {
            Box box = (Box)o;
            xField.setText(String.valueOf(box.getCenter().getX()));
            yField.setText(String.valueOf(box.getCenter().getY()));
            zField.setText(String.valueOf(box.getCenter().getZ()));
            rField.setText(String.valueOf(box.getColor().getR()));
            gField.setText(String.valueOf(box.getColor().getG()));
            bField.setText(String.valueOf(box.getColor().getB()));
            lenField.setText(String.valueOf(box.getLength()));
            idField.setText(String.valueOf(box.getId()));
        }

        xField.setColumns(5); yField.setColumns(5); zField.setColumns(5);
        rField.setColumns(5); gField.setColumns(5); bField.setColumns(5);
        lenField.setColumns(5);
        idField.setColumns(5);

        add(idLabel); add(idField);

        add(xLabel); add(xField); add(yLabel); add(yField); add(zLabel); add(zField);
        add(lenLabel); add(lenField);
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
        Box box = (Box)object;
        box.setId(idField.getText());
        box.setCenter(new Vec3(getNum(xField), getNum(yField), getNum(zField)));
        box.setColor(new OpenGLColor(getNum(rField), getNum(gField), getNum(bField)));
        box.setLength(getNum(lenField));
        if (creatingObject) {
            getParent().getCurrentObject().addChild(box);
        }
    }
}
