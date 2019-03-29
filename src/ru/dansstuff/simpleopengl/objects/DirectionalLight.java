package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.math.*;

@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DirectionalLight extends GLObject {
    @Getter
    private Vec4 color;
    @Getter @Setter
    private Vec4 pos;
    @Getter @Setter
    private int index;

    public DirectionalLight setColor(Vec4 color) {
        this.color = color;
        return this;
    }

    public DirectionalLight setColor(OpenGLColor color) {
        this.color = new Vec4(color.getR(), color.getG(), color.getB(), this.color.getW());
        return this;
    }

    public DirectionalLight() {
        this(new Vec4(1, 1, 1, 1), new Vec4(0, 0, -5, 0), 0);
    }

    public DirectionalLight(Vec4 color, Vec4 pos, int index) {
        this.color = color;
        this.pos = pos;
        this.index = index;
        //this.type = "DirectionalLight";
    }

    @Override
    public void draw(GL2 gl) {
        gl.glLightfv(gl.GL_LIGHT0 + index, gl.GL_DIFFUSE, new float[] { color.getX(), color.getY(), color.getZ(), color.getW() }, index);
        gl.glLightfv(gl.GL_LIGHT0 + index, gl.GL_POSITION, new float[] { pos.getX(), pos.getY(), pos.getZ(), pos.getW() }, index);
    }
}
