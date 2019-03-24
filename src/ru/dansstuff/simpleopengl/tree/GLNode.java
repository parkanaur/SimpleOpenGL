package ru.dansstuff.simpleopengl.tree;

import com.jogamp.opengl.GL2;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dansstuff.simpleopengl.objects.GLObject;

import java.util.ArrayList;
import java.util.List;

public class GLNode {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private GLObject glObject;
    @Getter @Setter
    private List<GLNode> children;

    public GLNode() {
        this.id = "0";
        this.children = new ArrayList<>();
    }

    public GLNode(String id, GLObject glObject) {
        this.id = id;
        this.glObject = glObject;
        this.children = new ArrayList<>();
    }

    public void addChild(GLNode child) {
        children.add(child);
    }

    public void drawTree(GL2 gl) {
        glObject.draw(gl);
        for (GLNode child : children) {
            child.drawTree(gl);
        }
    }

    public void clearChildren() {
        for (GLNode child : children) {
            child.clearChildren();
        }
        children.clear();
    }

    public int getObjectsCount() {
        int count = 1;

        for (GLNode node : children) {
            count += node.getObjectsCount();
        }

        return count;
    }
}
