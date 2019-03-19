package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import ru.dansstuff.simpleopengl.math.*;

@NoArgsConstructor
public class DirectionalLight extends GLObject {
    @Getter @Setter
    private Vec4 color;
    @Getter @Setter
    private Vec4 pos;
    @Getter
    private int index;
    @Getter
    private final String type = "DirectionalLight";

    public DirectionalLight(Vec4 color, Vec4 pos, int index) {
        this.color = color;
        this.pos = pos;
        this.index = index;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glLightfv(gl.GL_LIGHT0 + index, gl.GL_DIFFUSE, new float[] { color.getX(), color.getY(), color.getZ(), color.getW() }, index);
        gl.glLightfv(gl.GL_LIGHT0 + index, gl.GL_POSITION, new float[] { pos.getX(), pos.getY(), pos.getZ(), pos.getW() }, index);
    }
}
