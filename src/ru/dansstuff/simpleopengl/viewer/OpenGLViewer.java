package ru.dansstuff.simpleopengl.viewer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.operations.OpenGLOperation;
import ru.dansstuff.simpleopengl.operations.Translation;
import ru.dansstuff.simpleopengl.tree.GLNode;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@NoArgsConstructor
public class OpenGLViewer implements GLEventListener, Serializable {
    @Getter @Setter
    private GL2 gl;
    @Getter @Setter
    private GLU glu;
    @Getter @Setter
    private GLCanvas canvas;
    private TextRenderer textRenderer;
    private TextRenderer labelRenderer;

    @Getter @Setter
    private Vec3 cam;
    @Getter @Setter
    private Vec3 rotn;
    @Getter @Setter
    private Vec3 center;

    @Getter @Setter
    private boolean drawAxis = true;

    @Getter @Setter
    private boolean enabled = false;

    private List<GLObject> axis;
    private Queue<OpenGLOperation> pendingOperations;

    @Getter @Setter
    private GLNode root;


    public OpenGLViewer(GLCanvas canvas) {
        glu = new GLU();
        this.canvas = canvas;
        textRenderer = new TextRenderer(new Font("Monospaced", Font.PLAIN, 12));
        labelRenderer = new TextRenderer(new Font("Monospaced", Font.PLAIN, 12));

        cam = new Vec3(0, 0, -1);
        rotn = new Vec3(15, 45, 0);
        center = new Vec3(0 ,0, -6);

        axis = getAxis();
        pendingOperations = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0);
        gl.glShadeModel(gl.GL_FLAT);

        // Lighting setup
        /*
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_COLOR_MATERIAL);
        gl.glColorMaterial(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT_AND_DIFFUSE);
        float[] ambColor = new float[] {0.5f, 0.5f, 0.5f, 1};
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, ambColor, 0);
        gl.glEnable(gl.GL_NORMALIZE);*/

        gl.glEnable(gl.GL_DEPTH_TEST);

        gl.glMatrixMode(gl.GL_PROJECTION);
        glu.gluPerspective(45.0f,  (double)canvas.getSize().width / canvas.getSize().height, 0.1f, 1000f);
        gl.glMatrixMode(gl.GL_MODELVIEW);

        moveBackward(5);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        if (!enabled) {
            return;
        }

        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear (gl.GL_COLOR_BUFFER_BIT |  gl.GL_DEPTH_BUFFER_BIT );

        // ---scene render---
        gl.glPushMatrix();

        gl.glTranslatef(center.getX(), center.getY(), center.getZ());
        gl.glRotatef(rotn.getX(), 1, 0, 0);
        gl.glRotatef(rotn.getY(), 0, 1, 0);
        gl.glRotatef(rotn.getZ(), 0, 0, 1);

        if (drawAxis) {
            for (GLObject ax : axis) {
                ax.draw(gl);
            }
        }

        if (root != null) {
            root.drawTree(gl);
        }

        /*labelRenderer.begin3DRendering();
        for (GLObject light : lights) {
            light.draw(gl);
            float[] pos = ((DirectionalLight)light).pos;
            labelRenderer.draw3D(String.format("light %.02f %.02f %.02f", pos[0], pos[1], pos[2]), pos[0], pos[1], pos[2], 0.01f);
        }
        labelRenderer.end3DRendering();*/

        gl.glPopMatrix();
        // ---scene render end---

        // cam transformations
        while (!pendingOperations.isEmpty()) {
            pendingOperations.remove().doOperation(drawable);
        }

        drawDebugText(drawable);

    }

    public List<GLObject> getAxis() {
        List<GLObject> axis = new ArrayList<>();
        // X axis
        axis.add(new Line(new Vec3(-3, 0, 0), new Vec3(3, 0, 0), OpenGLColor.RED));
        axis.add(new Line(new Vec3(3, 0, 0), new Vec3(2.9f, 0, -0.1f), OpenGLColor.RED));
        axis.add(new Line(new Vec3(3, 0, 0), new Vec3(2.9f, 0, 0.1f), OpenGLColor.RED));
        // Y axis
        axis.add(new Line(new Vec3(0, -3, 0), new Vec3(0, 3, 0), OpenGLColor.GREEN));
        axis.add(new Line(new Vec3(0, 3, 0), new Vec3(-0.1f, 2.9f, 0), OpenGLColor.GREEN));
        axis.add(new Line(new Vec3(0, 3, 0), new Vec3(0.1f, 2.9f, 0), OpenGLColor.GREEN));
        // Z axis
        axis.add(new Line(new Vec3(0, 0, -3), new Vec3(0, 0, 3), OpenGLColor.BLUE));
        axis.add(new Line(new Vec3(0, 0, 3), new Vec3(0, -0.1f, 2.9f), OpenGLColor.BLUE));
        axis.add(new Line(new Vec3(0, 0, 3), new Vec3(0, 0.1f, 2.9f), OpenGLColor.BLUE));

        return axis;
    }

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

    public void drawDebugText(GLAutoDrawable drawable) {
        if (root != null) {
            textRenderer.beginRendering(canvas.getWidth(), canvas.getHeight());
            textRenderer.draw(String.format("rotn x %.02f y %.02f z %.02f ", rotn.getX(), rotn.getY(), rotn.getZ()), 10, canvas.getHeight() - 15);
            textRenderer.draw(String.format("objects count: %d", root.getObjectsCount()), 10, canvas.getHeight() - 30);
            //textRenderer.draw("controls: WASD/arrows + shift/ctrl for Y axis", 10, canvas.getHeight() - 30);
            textRenderer.endRendering();
        }
    }

    public void rotLeft(float deg) {
        rotn.setY(rotn.getY() + deg);
        if (rotn.getY() >= 360 || rotn.getY() <= -360) rotn.setY(0);
        //pendingOperations.add(new Rotation(-deg, 0, 1, 0));
    }

    public void rotRight(float deg) {
        rotLeft(-deg);
    }

    public void rotUp(float deg) {
        rotn.setX(rotn.getX() + deg);
        if (rotn.getX() >= 360 || rotn.getX() <= -360) rotn.setX(0);
    }

    public void rotDown(float deg) {
        rotUp(-deg);
    }

    public void scale(int direction) {
        moveBackward(direction);
    }

    public void moveForward(float dist) {
        cam.setZ(cam.getZ() + dist);
        pendingOperations.add(new Translation(0, 0, dist));
    }

    public void moveBackward(float dist) {
        moveForward(-dist);
    }

    public void moveLeft(float dist) {
        // center.x += dist;
        cam.setX(cam.getX() + dist);
        pendingOperations.add(new Translation(dist, 0, 0));
    }

    public void moveRight(float dist) {
        //center.x -= dist;
        moveLeft(-dist);
    }

    public void moveUp(float dist) {
        // center.x += dist;
        cam.setY(cam.getY() + dist);
        pendingOperations.add(new Translation(0, -dist, 0));
    }

    public void moveDown(float dist) {
        //center.x -= dist;
        moveUp(-dist);
    }

    public void clear() {
        root.clearChildren();
        root = null;
        cam = new Vec3(0, 0, -1);
        center = new Vec3(0 ,0, -6);
        rotn = new Vec3(15, 45, 0);
    }
}
