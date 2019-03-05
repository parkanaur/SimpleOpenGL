package ru.dansstuff.simpleopengl.window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.jogamp.opengl.awt.GLCanvas;
import ru.dansstuff.simpleopengl.misc.gson.RuntimeTypeAdapterFactory;
import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.tree.GLNode;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GLCanvasWrapper {
    private final OpenGLViewer viewer;
    private final GLCanvas glCanvas;

    final RuntimeTypeAdapterFactory<GLObject> rFactory = RuntimeTypeAdapterFactory
            .of(GLObject.class, "type")
            .registerSubtype(Box.class, "Box")
            .registerSubtype(Triangle.class, "Triangle")
            .registerSubtype(Line.class, "Line");

    final Gson gson;

    public GLCanvasWrapper(int width, int height) {
        glCanvas = new GLCanvas();
        glCanvas.setSize(width, height);

        viewer = new OpenGLViewer(glCanvas);
        glCanvas.addGLEventListener(viewer);

        gson = new GsonBuilder().registerTypeAdapterFactory(rFactory).create();
    }

    public GLNode parseTree(File file) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(file));

        return gson.fromJson(reader, GLNode.class);
    }

    public OpenGLViewer getViewer() { return viewer; }

    public GLCanvas getGlCanvas() {
        return glCanvas;
    }

    public void clear() {
        viewer.clear();
    }
}
