package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import ru.dansstuff.simpleopengl.math.*;

public class DirectionalLight implements GLObject {
    public float[] color;
    public float[] pos;
    private int index;

    public DirectionalLight(Vec4 color, Vec4 pos, int index) {
        this.color = new float[] { color.x, color.y, color.z, color.w };
        this.pos = new float[] { pos.x, pos.y, pos.z, pos.w };
        this.index = index;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glLightfv(gl.GL_LIGHT0 + index, gl.GL_DIFFUSE, color, 0);
        gl.glLightfv(gl.GL_LIGHT0 + index, gl.GL_POSITION, pos, 0);
    }
}
