package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import lombok.*;
import lombok.experimental.Accessors;
import ru.dansstuff.simpleopengl.operations.OpenGLOperation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public abstract class GLObject implements Serializable {
    public abstract void draw(GL2 gl);
    public abstract void update();

    @Getter @Setter
    protected String id = "";
    @Getter @Setter
    protected List<GLObject> children = new ArrayList<>();
    @Getter
    protected String type = getClass().getSimpleName();
    @Getter @Setter
    protected String textureFile;
    @Getter @Setter
    protected Texture texture;
    @Getter @Setter
    protected List<OpenGLOperation> transforms = new ArrayList<>();

    @Getter
    protected static Class frameClass;

    public GLObject getChildren(int index) {
        return children.get(index);
    }

    public void setChildren(int index, GLObject child) {
        children.add(index, child);
    }

    public void resolveTexturesForTree(Map<String, Texture> textureMap) throws IOException {
        resolveTexture(textureMap);
        for (GLObject child : children) {
            child.resolveTexturesForTree(textureMap);
        }
    }

    private Texture getTextureFromFile(File file) throws IOException {
        if (file.exists()) {
            return TextureIO.newTexture(file, true);
        }
        throw new FileNotFoundException("Could not find file: " + file.getAbsolutePath());
    }

    public void resolveTexture(Map<String, Texture> textureMap) throws IOException {
        if (textureFile == null) {
            return;
        }

        if (textureMap.containsKey(textureFile)) {
            texture = textureMap.get(textureFile);
            return;
        }

        texture = getTextureFromFile(new File(textureFile));
        textureMap.put(textureFile, texture);
    }

    public void addChild(GLObject child) {
        children.add(child);
    }

    public void addTransform(OpenGLOperation transform) {
        transforms.add(transform);
    }

    public void drawTree(GL2 gl) {
        this.draw(gl);
        this.update();
        for (GLObject child : children) {
            child.drawTree(gl);
        }
    }

    public int getObjectsCount() {
        int count = 1;

        for (GLObject child : children) {
            count += child.getObjectsCount();
        }

        return count;
    }

    public List<GLObject> getTreeAsList() {
        List<GLObject> childrenList = new ArrayList<>();
        childrenList.add(this);

        for (GLObject child : children) {
            childrenList.addAll(child.getTreeAsList());
        }

        return childrenList;
    }

    public void clear() {
        for (GLObject child : children) {
            child.clear();
        }
        children.clear();
        transforms.clear();
    }

    public static Set<Class> getObjectTypes() {
        Set<Class> types = new TreeSet<>(Comparator.comparing(Class::getSimpleName));

        types.addAll(Arrays.asList(Box.class, Cylinder.class, DirectionalLight.class,
                Line.class, Sphere.class, Triangle.class, EmptyObject.class));

        return types;
    }

    @Override
    public String toString() {
        return id + ": " + getClass().getSimpleName() + "(" + System.identityHashCode(this) + ")";
    }
}