package ru.dansstuff.simpleopengl.math;

import lombok.*;

@NoArgsConstructor
public class Vec4 {
   @Getter @Setter
   private float x, y, z, w;

    public Vec4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
