package ru.dansstuff.simpleopengl.drawers;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import ru.dansstuff.simpleopengl.primitives.Line;
import ru.dansstuff.simpleopengl.primitives.Primitive;
import ru.dansstuff.simpleopengl.primitives.Triangle;

import java.util.Random;

public interface PrimitiveDrawable extends GLEventListener {
    void drawLine(Primitive primitive);
    void drawRandomLine();
    void drawTriangle(Primitive primitive);
    void drawRandomTriangle();

    void rotLeft(float deg);
    void rotRight(float deg);
    void scale(int direction);
    void moveForward(float dist);
    void moveBackward(float dist);
    void moveLeft(float dist);
    void moveRight(float dist);
    void clear();

    default float safeRnd() {
        return random.nextFloat() * 2 - 1;
    }
    Random random = new Random();
}
