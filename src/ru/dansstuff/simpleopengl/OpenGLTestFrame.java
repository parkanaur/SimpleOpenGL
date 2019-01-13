package ru.dansstuff.simpleopengl;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpenGLTestFrame
        extends JFrame {

    private int width;
    private int height;

    private GLCanvasWrapper canvasWrapper;

    public int getWidth()  { return width; }
    public int getHeight() { return height; }

    public OpenGLTestFrame(int width, int height) {
        this.width = width;
        this.height = height;
        setResizable(true);
        bindCanvas();
        initWindow(width, height);
        bindControls();
        Animator animator = new Animator(canvasWrapper.getGlCanvas());
        animator.start();
    }

    private void bindControls() {
        Panel controlsPanel = new Panel();
        controlsPanel.setLayout(new GridLayout(3, 2));

        Button triangleAddBtn = new Button("Add random triangle");
        triangleAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasWrapper.getDrawer().drawRandomTriangle();
            }
        });
        controlsPanel.add(triangleAddBtn, BorderLayout.EAST);

        Button lineAddBtn = new Button("Add random line");
        lineAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasWrapper.getDrawer().drawRandomLine();
            }
        });
        controlsPanel.add(lineAddBtn, BorderLayout.EAST);

        Button rotLeftBtn = new Button("Rotate <-");
        rotLeftBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasWrapper.getDrawer().rotLeft(1f);
            }
        });
        controlsPanel.add(rotLeftBtn, BorderLayout.EAST);

        Button rotRightBtn = new Button("Rotate ->");
        rotRightBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasWrapper.getDrawer().rotRight(1f);
            }
        });
        controlsPanel.add(rotRightBtn, BorderLayout.EAST);

        Button clearBtn = new Button("Clear");
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasWrapper.clear();
            }
        });
        controlsPanel.add(clearBtn, BorderLayout.EAST);

        getContentPane().add(controlsPanel, BorderLayout.EAST);
        pack();
        setVisible(true);
    }

    private void bindCanvas() {
        canvasWrapper = new GLCanvasWrapper(width * 2 / 3, height);
        canvasWrapper.getGlCanvas().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                canvasWrapper.getDrawer().scale(e.getWheelRotation());
            }
        });
        canvasWrapper.getGlCanvas().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyCode() + "keyPressed");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                System.out.println(key + " keyPressed");
                switch (key) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        canvasWrapper.getDrawer().moveForward(1);
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        canvasWrapper.getDrawer().moveBackward(1);
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        canvasWrapper.getDrawer().moveLeft(1);
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        canvasWrapper.getDrawer().moveRight(1);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        canvasWrapper.getGlCanvas().addMouseMotionListener(new MouseMotionListener() {
            int lastX = -1;
            int lastY = -1;
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        add(canvasWrapper.getGlCanvas(), BorderLayout.WEST);
    }

    private void initWindow(int width, int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);

        setTitle(this.getClass().getSimpleName());

        setVisible(true);
        setBackground(Color.BLACK);

        this.addWindowListener(new OpenGLTestFrameWindowListener());
    }
}
