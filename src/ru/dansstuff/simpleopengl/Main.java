package ru.dansstuff.simpleopengl;

import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.math.Vec4;
import ru.dansstuff.simpleopengl.misc.helpers.SceneFileHelper;
import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            DirectionalLight light = new DirectionalLight();
            light.setPos(new Vec4(0, 0, -10, 0));
            light.setColor(OpenGLColor.WHITE);
            root = light;
            Sphere sphere = new Sphere();
            sphere.setRadius(2);
            sphere.setTextureFile("D:/yoba.png");
            root.addChild(sphere);

            sphere = new Sphere();
            sphere.setRadius(2);
            sphere.setCenter(new Vec3(0, 2, 0));
            sphere.setTextureFile("D:/yoba.png");
            root.addChild(sphere);
        }

        frame.getCanvas().getViewer().setRoot(root);
        frame.getCanvas().getViewer().setDrawAxis(true);
        frame.getCanvas().getViewer().setEnabled(true);
        frame.getCanvas().getViewer().setNeedTextureResolution(true);
    }
}