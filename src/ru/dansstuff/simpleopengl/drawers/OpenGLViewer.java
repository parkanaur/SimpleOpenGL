package ru.dansstuff.simpleopengl.drawers;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;
import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.operations.OpenGLOperation;
import ru.dansstuff.simpleopengl.operations.Rotation;
import ru.dansstuff.simpleopengl.operations.Translation;
import ru.dansstuff.simpleopengl.primitives.Line;
import ru.dansstuff.simpleopengl.primitives.OpenGLColor;
import ru.dansstuff.simpleopengl.primitives.Primitive;
import ru.dansstuff.simpleopengl.primitives.Triangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OpenGLViewer implements ISceneViewer {
    private GLU glu;
    private GLCanvas canvas;
    private TextRenderer textRenderer;

    private Vec3 cam;
    private Vec3 center;

    private List<Primitive> objects;
    private Queue<OpenGLOperation> pendingOperations;


    public OpenGLViewer(GLCanvas canvas) {
        glu = new GLU();
        this.canvas = canvas;
        textRenderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 12));

        cam = new Vec3(0, 0, -1);
        center = new Vec3(0 ,0, -6);

        objects = new ArrayList<>();
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
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );

        // ---scene render---
        gl.glPushMatrix();
       // gl.glLoadIdentity();
        gl.glTranslatef(center.x, center.y, center.z);
        // X axis
        drawLine(new Line<>(-10, 0, 0, 10, 0, 0, OpenGLColor.RED));
        // Y axis
        drawLine(new Line<>(0, -10, 0, 0, 10, 0, OpenGLColor.GREEN));
        // Z axis
        drawLine(new Line<>(0, 0, -10, 0, 0, 10, OpenGLColor.BLUE));

        for (Primitive primitive : objects) {
             primitive.draw(gl);
        }
        gl.glPopMatrix();
        // ---scene render end---

    //    gl.glRotatef(1, 0, 0, 1);

        // cam transformations
        while (!pendingOperations.isEmpty()) {
            pendingOperations.remove().doOperation(drawable);
        }

        drawDebugText(drawable);
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
    public void drawDebugText(GLAutoDrawable drawable) {
        textRenderer.beginRendering(canvas.getWidth(), canvas.getHeight());
        textRenderer.draw("cam x " + (int)cam.x + " y " + (int)cam.y + " z " + (int)cam.z, 10, canvas.getHeight() - 15);
        textRenderer.draw("controls: WASD/arrows + shift/ctrl for Y axis", 10, canvas.getHeight() - 30);
        textRenderer.endRendering();
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
        rotLeft(-deg);
    }

    @Override
    public void rotUp(float deg) {
        pendingOperations.add(new Rotation(-deg, 1, 0, 0));
    }

    @Override
    public void rotDown(float deg) {
        rotUp(-deg);
    }

    @Override
    public void scale(int direction) {
        moveForward(direction * 0.1f);
    }

    @Override
    public void moveForward(float dist) {
        cam.z += dist;
        pendingOperations.add(new Translation(0, 0, dist));
    }

    @Override
    public void moveBackward(float dist) {
        ////center.z -= dist;
        moveForward(-dist);
    }

    @Override
    public void moveLeft(float dist) {
        // center.x += dist;
        cam.x += dist;
        pendingOperations.add(new Translation(dist, 0, 0));
    }

    @Override
    public void moveRight(float dist) {
        //center.x -= dist;
        moveLeft(-dist);
    }

    @Override
    public void moveUp(float dist) {
        // center.x += dist;
        cam.y += dist;
        pendingOperations.add(new Translation(0, -dist, 0));
    }

    @Override
    public void moveDown(float dist) {
        //center.x -= dist;
        moveUp(-dist);
    }

    @Override
    public void clear() {
        objects.clear();
        cam = new Vec3(0, 0, -1);
        center = new Vec3(0 ,0, -6);
    }
}
