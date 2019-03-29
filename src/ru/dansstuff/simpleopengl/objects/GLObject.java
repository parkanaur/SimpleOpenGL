package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Accessors(chain = true)
public abstract class GLObject implements Serializable {
    public abstract void draw(GL2 gl);

    @Getter @Setter
    protected String id = "";
    @Getter @Setter
    protected List<GLObject> children = new ArrayList<>();
    @Getter
    protected String type = getClass().getSimpleName();

    public void addChild(GLObject child) {
        children.add(child);
    }

    public void drawTree(GL2 gl) {
        this.draw(gl);
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
    }
}