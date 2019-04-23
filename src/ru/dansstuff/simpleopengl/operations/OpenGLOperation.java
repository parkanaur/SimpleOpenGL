package ru.dansstuff.simpleopengl.operations;

import com.jogamp.opengl.GLAutoDrawable;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public abstract class OpenGLOperation implements Serializable {
    public abstract void doOperation(GLAutoDrawable glAutoDrawable);

    @Getter
    protected static Class frameClass;

    public static Set<Class> getObjectTypes() {
        Set<Class> types = new TreeSet<>(Comparator.comparing(Class::getSimpleName));

        types.addAll(Arrays.asList(OriginBasedRotation.class, Rotation.class, Translation.class));

        return types;
    }
}
