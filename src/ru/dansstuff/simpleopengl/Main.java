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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        OpenGLTestFrame frame = new OpenGLTestFrame(1200, 900);

        frame.getCanvas().getViewer().setDrawAxis(true);
        frame.getCanvas().getViewer().setEnabled(true);
    }
}