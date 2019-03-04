package ru.dansstuff.simpleopengl.viewer;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import java.util.Random;

public interface ISceneViewer extends GLEventListener {
    void drawDebugText(GLAutoDrawable drawable);

    void rotLeft(float deg);
    void rotRight(float deg);
    void rotUp(float deg);
    void rotDown(float deg);
    void scale(int direction);

    void moveForward(float dist);
    void moveBackward(float dist);
    void moveLeft(float dist);
    void moveRight(float dist);
    void moveUp(float dist);
    void moveDown(float dist);

    void clear();

    default float safeRnd() {
        return random.nextFloat() * 2 - 1;
    }
    Random random = new Random();
}
