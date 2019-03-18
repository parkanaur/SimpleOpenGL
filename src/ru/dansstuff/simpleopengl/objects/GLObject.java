package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;

import java.io.Serializable;

public abstract class GLObject implements Serializable {
    public abstract void draw(GL2 gl);
}
