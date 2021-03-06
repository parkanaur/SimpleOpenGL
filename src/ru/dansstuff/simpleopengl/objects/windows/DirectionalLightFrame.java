package ru.dansstuff.simpleopengl.objects.windows;

import ru.dansstuff.simpleopengl.math.Vec4;
import ru.dansstuff.simpleopengl.objects.DirectionalLight;
import ru.dansstuff.simpleopengl.objects.GLObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

public class DirectionalLightFrame extends TypeBaseFrame {
    private JLabel xLabel = new JLabel("X");
    private JTextField xField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel yLabel = new JLabel("Y");
    private JTextField yField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel zLabel = new JLabel("Z");
    private JTextField zField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel wLabel = new JLabel("W");
    private JTextField wField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel rLabel = new JLabel("R");
    private JTextField rField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel gLabel = new JLabel("G");
    private JTextField gField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel bLabel = new JLabel("B");
    private JTextField bField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JLabel aLabel = new JLabel("A");
    private JTextField aField = new JFormattedTextField(NumberFormat.getNumberInstance());

    private JButton okButton = new JButton("OK");

    public DirectionalLightFrame() {
        this(new DirectionalLight());
    }

    public DirectionalLightFrame(GLObject o) {
        this.object = o;
        setLayout(new FlowLayout());

        if (!creatingObject) {
            DirectionalLight light = (DirectionalLight)o;
            xField.setText(String.valueOf(light.getPos().getX()));
            yField.setText(String.valueOf(light.getPos().getY()));
            zField.setText(String.valueOf(light.getPos().getZ()));
            wField.setText(String.valueOf(light.getPos().getW()));
            rField.setText(String.valueOf(light.getColor().getX()));
            gField.setText(String.valueOf(light.getColor().getY()));
            bField.setText(String.valueOf(light.getColor().getZ()));
            aField.setText(String.valueOf(light.getColor().getW()));
            idField.setText(String.valueOf(light.getId()));
        }

        xField.setColumns(5); yField.setColumns(5); zField.setColumns(5); wField.setColumns(5);
        rField.setColumns(5); gField.setColumns(5); bField.setColumns(5); aField.setColumns(5);
        idField.setColumns(5);

        add(idLabel); add(idField);
        add(xLabel); add(xField); add(yLabel); add(yField); add(zLabel); add(zField); add(wLabel); add(wField);
        add(rLabel); add(rField); add(gLabel); add(gField); add(bLabel); add(bField); add(aLabel); add(aField);

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
        DirectionalLight light = (DirectionalLight)object;
        light.setId(idField.getText());
        light.setColor(new Vec4(getNum(rField), getNum(gField), getNum(bField), getNum(aField)));
        light.setPos(new Vec4(getNum(xField), getNum(yField), getNum(zField), getNum(wField)));
        if (creatingObject) {
            getParent().getCurrentObject().addChild(light);
        }
    }
}
