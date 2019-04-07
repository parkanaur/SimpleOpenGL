package ru.dansstuff.simpleopengl;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.math.Vec4;
import ru.dansstuff.simpleopengl.misc.helpers.SceneFileHelper;
import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Main {
    static Random random = new Random();
    static boolean fileUsed = false;

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        OpenGLTestFrame frame = new OpenGLTestFrame(1600, 900);

        GLObject root;
        if (fileUsed) {
            root = SceneFileHelper.readScene(new File("C:/Users/dan/Desktop/a.json"));
        }
        else {
            root = new DirectionalLight().setColor(OpenGLColor.BLUE).setPos(new Vec4(0, 0, -5, 0));
            for (int i = 0; i < 10; ++i) {
                root.addChild(new Sphere()
                        .setColor(OpenGLColor.getRandomColor())
                        .setRadius(1)
                        .setCenter(new Vec3(-1, 0, 1)));
            }
            System.out.println(SceneFileHelper.getSceneJson(root));
        }

        frame.getCanvas().getViewer().setRoot(root);
        frame.getCanvas().getViewer().setDrawAxis(true);
        frame.getCanvas().getViewer().setEnabled(true);
    }
}