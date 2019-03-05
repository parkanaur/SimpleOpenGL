package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.Getter;

public abstract class GLObject {
    public abstract void draw(GL2 gl);
}
