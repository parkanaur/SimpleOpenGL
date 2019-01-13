package ru.dansstuff.simpleopengl.drawers;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import ru.dansstuff.simpleopengl.Vec3;
import ru.dansstuff.simpleopengl.operations.OpenGLOperation;
import ru.dansstuff.simpleopengl.operations.Rotation;
import ru.dansstuff.simpleopengl.primitives.Line;
import ru.dansstuff.simpleopengl.primitives.OpenGLColor;
import ru.dansstuff.simpleopengl.primitives.Primitive;
import ru.dansstuff.simpleopengl.primitives.Triangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PrimitiveDrawer implements PrimitiveDrawable {
    private List<Primitive> objects;
    private GLU glu;
    private GLCanvas canvas;

    private Vec3 cam;
    private Vec3 center;

    private Queue<OpenGLOperation> pendingOperations;


    public PrimitiveDrawer(GLCanvas canvas) {
        objects = new ArrayList<>();
        glu = new GLU();
        this.canvas = canvas;

        cam = new Vec3(0, 0, 0);
        center = new Vec3(0 ,0, -5);

        pendingOperations = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0);
        gl.glShadeModel(gl.GL_FLAT);
        gl.glEnable(gl.GL_DEPTH_TEST);
        glu.gluPerspective(45.0f,  (double)canvas.getSize().width / canvas.getSize().height, 0.1f, 1000f);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );

        gl.glPushMatrix();

        gl.glTranslatef(center.x, center.y, center.z);

        // X axis
        drawLine(new Line<>(-1000, 0, 0, 1000, 0, 0, OpenGLColor.RED));
        // Y axis
        drawLine(new Line<>(0, -1000, 0, 0, 1000, 0, OpenGLColor.GREEN));
        // Z axis
        drawLine(new Line<>(0, 0, -1000, 0, 0, 1000, OpenGLColor.BLUE));

        for (Primitive primitive : objects) {
             primitive.draw(gl);
        }

        gl.glPopMatrix();

        while (!pendingOperations.isEmpty()) {
            pendingOperations.remove().doOperation(glAutoDrawable);
        }
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height == 0) height = 1;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f,  (double)width / height, 0.1f, 1000f);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void drawLine(Primitive primitive) {
        if (primitive instanceof Line) {
            objects.add(primitive);
        }
    }

    @Override
    public void drawRandomLine() {
        objects.add(new Line<>(safeRnd(), safeRnd(), safeRnd(), safeRnd(), safeRnd(), safeRnd(), OpenGLColor.getRandomColor()));
    }

    @Override
    public void drawTriangle(Primitive primitive) {
        if (primitive instanceof Triangle) {
            objects.add(primitive);
        }
    }

    @Override
    public void drawRandomTriangle() {
        objects.add(new Triangle<>(safeRnd(), safeRnd(), safeRnd(), safeRnd(), safeRnd(), safeRnd(), safeRnd(), safeRnd(), safeRnd(), OpenGLColor.getRandomColor()));
    }

    @Override
    public void rotLeft(float deg) {
       // float r = Math.abs(cam.z - center.z);
       // float curAngle = (float)Math.acos(cam.x / r);
       // cam.x = (float)(r * Math.cos(curAngle + deg));
       // cam.z = (float)(r * Math.sin(curAngle + deg));
        pendingOperations.add(new Rotation(-deg, 0, 1, 0));
    }

    @Override
    public void rotRight(float deg) {
        pendingOperations.add(new Rotation(deg, 0, 1, 0));
    }

    @Override
    public void scale(int direction) {
        center.z -= direction * 0.1;
        if (center.z > -0.1f) center.z = -0.1f;
    }

    @Override
    public void moveForward(float dist) {
        center.z += dist;
        if (center.z > -0.1f) center.z = -0.1f;
    }

    @Override
    public void moveBackward(float dist) {
        center.z -= dist;
        if (center.z < -999.9f) center.z = -999.9f;
    }

    @Override
    public void moveLeft(float dist) {
        center.x += dist;
    }

    @Override
    public void moveRight(float dist) {
        center.x -= dist;
    }

    @Override
    public void clear() {
        objects.clear();
        center = new Vec3(0, 0, -5);
        cam = new Vec3(0, 0 ,0);
    }
}
