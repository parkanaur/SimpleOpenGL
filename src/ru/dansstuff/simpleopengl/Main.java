package ru.dansstuff.simpleopengl;

import com.google.gson.Gson;
import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.math.Vec4;
import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.tree.GLNode;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        OpenGLTestFrame frame = new OpenGLTestFrame(1600, 900);

        GLNode root = new GLNode();

        DirectionalLight k = new DirectionalLight();
        k.setColor(new Vec4(1, 1, 1, 1));
        k.setPos(new Vec4(0, 0, -3, 0));
        root.setGlObject(k);

        GLNode s1 = new GLNode();
        s1.setGlObject(new Sphere());
        root.addChild(s1);

        frame.getViewer().requestFocusInWindow();
        frame.getViewer().setRoot(root);
        frame.getViewer().setDrawAxis(true);
        frame.getViewer().setEnabled(true);

    }
}