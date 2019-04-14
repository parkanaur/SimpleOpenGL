package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.windows.EmptyObjectFrame;

public class EmptyObject extends GLObject {
    @Getter @Setter
    public Vec3 center;

    public EmptyObject() {
        this(new Vec3(0, 0, 0));
    }

    public EmptyObject(Vec3 center) {
        this.center = center;
        frameClass = EmptyObjectFrame.class;
    }

    @Override
    public void draw(GL2 gl) {

    }
}
