package ru.dansstuff.simpleopengl;

import com.jogamp.opengl.util.Animator;
import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpenGLTestFrame
        extends JFrame {

    private int width;
    private int height;

    @Getter @Setter
    private GLCanvasWrapper canvasWrapper;
    private Point curPos;

    public int getWidth()  { return width; }
    public int getHeight() { return height; }

    public OpenGLTestFrame(int width, int height) {
        this.width = width;
        this.height = height;
        setResizable(true);
        bindCanvas();
        curPos = new Point(-1, -1);
        initWindow(width, height);
        bindControls();
        Animator animator = new Animator(canvasWrapper.getGlCanvas());
        animator.start();
    }

    private void bindControls() {
        Panel controlsPanel = new Panel();
        controlsPanel.setLayout(new GridLayout(2, 1));

        Button showAxisBtn = new Button("Show axis");
        showAxisBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenGLViewer viewer = (OpenGLViewer)canvasWrapper.getViewer();
                viewer.setDrawAxis(!viewer.getDrawAxis());
            }
        });
        controlsPanel.add(showAxisBtn, BorderLayout.EAST);

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
                canvasWrapper.getViewer().scale(e.getWheelRotation());
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
                        canvasWrapper.getViewer().moveForward(0.1f);
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        canvasWrapper.getViewer().moveBackward(0.1f);
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        canvasWrapper.getViewer().moveLeft(0.1f);
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        canvasWrapper.getViewer().moveRight(0.1f);
                        break;
                    case KeyEvent.VK_SHIFT:
                        canvasWrapper.getViewer().moveUp(0.1f);
                        break;
                    case KeyEvent.VK_CONTROL:
                        canvasWrapper.getViewer().moveDown(0.1f);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        canvasWrapper.getGlCanvas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                curPos.x = -1;
                curPos.y = -1;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                curPos.x = -1;
                curPos.y = -1;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        canvasWrapper.getGlCanvas().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                OpenGLViewer drawer = canvasWrapper.getViewer();
                if (e.getX() != curPos.x) {
                    if (curPos.x != -1)
                        drawer.rotLeft((e.getX() - curPos.x) * 0.1f);
                }
                if (e.getY() != curPos.y) {
                    if (curPos.y != -1)
                        drawer.rotUp((e.getY() - curPos.y) * 0.1f);
                }
                curPos.x = e.getX();
                curPos.y = e.getY();
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
