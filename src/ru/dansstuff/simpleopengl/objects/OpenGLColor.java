package ru.dansstuff.simpleopengl.objects;

import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.Vec3;

import java.util.Random;

@Builder
@EqualsAndHashCode
public class OpenGLColor {
    @Getter
    private float r, g, b;
    private transient static Random random = new Random();

    public void setB(float b) {
        if (b < 0) {
            throw new IllegalArgumentException("Invalid b: " + b);
        }
        this.b = b;
    }

    public void setG(float g) {
        if (g < 0) {
            throw new IllegalArgumentException("Invalid g: " + g);
        }
        this.g = g;
    }

    public void setR(float r) {
        if (r < 0) {
            throw new IllegalArgumentException("Invalid r: " + r);
        }
        this.r = r;
    }

    public OpenGLColor() {
        this(1, 1, 1);
    }

    public OpenGLColor(Vec3 color) {
        this(color.getX(), color.getY(), color.getZ());
    }

    public OpenGLColor(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static final OpenGLColor RED = new OpenGLColor(1, 0, 0);
    public static final OpenGLColor GREEN = new OpenGLColor(0, 1, 0);
    public static final OpenGLColor BLUE = new OpenGLColor(0, 0, 1);
    public static final OpenGLColor WHITE = new OpenGLColor(1, 1, 1);

    public static final OpenGLColor[] COLORS = {
            RED, GREEN, BLUE, WHITE
    };

    public String toString() {
        return "R " + r + " G " + g + " B " + b;
    }

    public static OpenGLColor getRandomColor() {
        return COLORS[random.nextInt(COLORS.length)];
    }
}

