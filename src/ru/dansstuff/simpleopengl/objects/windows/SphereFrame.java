package ru.dansstuff.simpleopengl.objects.windows;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.objects.OpenGLColor;
import ru.dansstuff.simpleopengl.objects.Sphere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

public class SphereFrame extends TypeBaseFrame {
    private JLabel xLabel = new JLabel("X");
    private JTextField xField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel yLabel = new JLabel("Y");
    private JTextField yField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel zLabel = new JLabel("Z");
    private JTextField zField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel radiusLabel = new JLabel("Radius");
    private JTextField radiusField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel rLabel = new JLabel("R");
    private JTextField rField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel gLabel = new JLabel("G");
    private JTextField gField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel bLabel = new JLabel("B");
    private JTextField bField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel tLabel = new JLabel("Texture file");
    private JTextField tField = new JTextField();

    private JButton okButton = new JButton("OK");

    public SphereFrame() {
        this(new Sphere());
    }

    public SphereFrame(GLObject o) {
        this.object = o;
        setLayout(new FlowLayout());

        if (!creatingObject) {
            Sphere sphere = (Sphere)o;
            xField.setText(String.valueOf(sphere.getCenter().getX()));
            yField.setText(String.valueOf(sphere.getCenter().getY()));
            zField.setText(String.valueOf(sphere.getCenter().getZ()));
            rField.setText(String.valueOf(sphere.getColor().getR()));
            gField.setText(String.valueOf(sphere.getColor().getG()));
            bField.setText(String.valueOf(sphere.getColor().getB()));
            radiusField.setText(String.valueOf(sphere.getRadius()));
        }

        xField.setColumns(5); yField.setColumns(5); zField.setColumns(5);
        radiusField.setColumns(5);
        tField.setColumns(25);
        rField.setColumns(5); gField.setColumns(5); bField.setColumns(5);

        add(xLabel); add(xField); add(yLabel); add(yField); add(zLabel); add(zField);
        add(radiusLabel); add(radiusField);
        add(rLabel); add(rField); add(gLabel); add(gField); add(bLabel); add(bField);
        add(tLabel); add(tField);

        okButton.addActionListener(e -> {
            try {
                createObject();
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
                ex.printStackTrace();
            }
        });
        add(okButton);
    }

    protected void createObject() {
        Sphere sphere = (Sphere)object;
        sphere.setCenter(new Vec3(getNum(xField), getNum(yField), getNum(zField)));
        sphere.setColor(new OpenGLColor(getNum(rField), getNum(gField), getNum(bField)));
        sphere.setRadius(getNum(radiusField));
        sphere.setTextureFile(tField.getText().replace('\\', '/'));
        if (creatingObject) {
            getParent().getCurrentObject().addChild(sphere);
        }
    }
}
