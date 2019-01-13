package ru.dansstuff.simpleopengl.primitives;

import java.util.Random;

public class OpenGLColor {
    private float r, g, b;
    private static Random random = new Random();

    public OpenGLColor(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getR() { return r; }
    public float getG() { return g; }
    public float getB() { return b; }
    public void setR(float r) { this.r = r; }
    public void setG(float g)  {this.g = g; }
    public void setB(float b) { this.b = b; }

    public static final OpenGLColor RED = new OpenGLColor(1, 0, 0);
    public static final OpenGLColor GREEN = new OpenGLColor(0, 1, 0);
    public static final OpenGLColor BLUE = new OpenGLColor(0, 0, 1);
    public static final OpenGLColor WHITE = new OpenGLColor(1, 1, 1);

    public static final OpenGLColor[] COLORS = {
            RED, GREEN, BLUE
    };

    public static OpenGLColor getRandomColor() {
        return COLORS[random.nextInt(COLORS.length)];
    }
}

