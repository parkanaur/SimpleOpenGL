package ru.dansstuff.simpleopengl.window;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import lombok.Getter;
import lombok.Setter;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpenGLTestFrame
        extends JFrame {

    @Getter
    private int width;
    @Getter
    private int height;

    public void setWidth(int width) {
        this.width = width;
        viewer.setSize(this.width, this.height);
    }

    public void setHeight(int height) {
        this.height = height;
        viewer.setSize(this.width, this.height);
    }

    @Getter @Setter
    private OpenGLViewer viewer;

    private Point curPos;

    public OpenGLTestFrame(int width, int height) {
        this.width = width;
        this.height = height;
        setResizable(true);
        initViewer();

        curPos = new Point(-1, -1);
        initWindow();

        Animator animator = new Animator(viewer);
        animator.start();
    }

    private void initViewer() {
        viewer = new OpenGLViewer();
        viewer.setSize(width, height);
        viewer.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                viewer.scale(e.getWheelRotation());
            }
        });
        viewer.addMouseListener(new MouseListener() {
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
        viewer.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getX() != curPos.x) {
                    if (curPos.x != -1)
                        viewer.rotLeft((e.getX() - curPos.x) * 0.1f);
                }
                if (e.getY() != curPos.y) {
                    if (curPos.y != -1)
                        viewer.rotUp((e.getY() - curPos.y) * 0.1f);
                }
                curPos.x = e.getX();
                curPos.y = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        viewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    OpenGLTestFramePopupMenu menu = new OpenGLTestFramePopupMenu(viewer);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        add(viewer, BorderLayout.WEST);
    }

    private void initWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);

        setTitle(this.getClass().getSimpleName());

        setVisible(true);
        setBackground(Color.BLACK);

        addWindowListener(new OpenGLTestFrameWindowListener());
    }
}
