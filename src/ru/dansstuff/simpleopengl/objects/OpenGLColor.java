package ru.dansstuff.simpleopengl.objects;

import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.Vec3;

import java.util.Random;

@Builder
@Accessors(chain = true)
@EqualsAndHashCode
public class OpenGLColor {
    @Getter @Setter
    private float r, g, b;
    private transient static Random random = new Random();

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

    public static OpenGLColor getRandomColor() {
        return COLORS[random.nextInt(COLORS.length)];
    }
}

