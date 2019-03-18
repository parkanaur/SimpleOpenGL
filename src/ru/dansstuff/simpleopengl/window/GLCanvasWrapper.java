package ru.dansstuff.simpleopengl.window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.jogamp.opengl.awt.GLCanvas;
import lombok.*;
import ru.dansstuff.simpleopengl.misc.gson.RuntimeTypeAdapterFactory;
import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.tree.GLNode;
import ru.dansstuff.simpleopengl.viewer.OpenGLViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@NoArgsConstructor
public class GLCanvasWrapper {
    @Getter @Setter
    private OpenGLViewer viewer;
    @Getter @Setter
    private GLCanvas glCanvas;

    public GLCanvasWrapper(int width, int height) {
        glCanvas = new GLCanvas();
        glCanvas.setSize(width, height);

        viewer = new OpenGLViewer(glCanvas);
        glCanvas.addGLEventListener(viewer);
    }

    public GLNode parseTree(File file) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(file));

        TypeAdapterFactory rFactory = RuntimeTypeAdapterFactory
                .of(GLObject.class, "type")
                .registerSubtype(Box.class, "Box")
                .registerSubtype(Triangle.class, "Triangle")
                .registerSubtype(Sphere.class, "Sphere")
                .registerSubtype(Line.class, "Line");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(rFactory).create();

        return gson.fromJson(reader, GLNode.class);
    }

    public void clear() {
        viewer.clear();
    }
}
