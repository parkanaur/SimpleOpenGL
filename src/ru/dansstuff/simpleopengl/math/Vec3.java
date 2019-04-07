package ru.dansstuff.simpleopengl.math;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Vec3 {
    @Getter @Setter
    private float x, y, z;

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
