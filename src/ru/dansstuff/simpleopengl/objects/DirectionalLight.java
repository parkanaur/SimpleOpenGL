package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import ru.dansstuff.simpleopengl.math.*;

@NoArgsConstructor
public class DirectionalLight extends GLObject {
    @Getter @Setter
    private float[] color;
    @Getter @Setter
    private float[] pos;
    @Getter
    private int index;

    public DirectionalLight(Vec4 color, Vec4 pos, int index) {
        this.color = new float[] { color.getX(), color.getY(), color.getZ(), color.getW() };
        this.pos = new float[] { pos.getX(), pos.getY(), pos.getZ(), pos.getW() };
        this.index = index;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glLightfv(gl.GL_LIGHT0 + index, gl.GL_DIFFUSE, color, 0);
        gl.glLightfv(gl.GL_LIGHT0 + index, gl.GL_POSITION, pos, 0);
    }
}
