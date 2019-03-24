package ru.dansstuff.simpleopengl.misc.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import ru.dansstuff.simpleopengl.misc.gson.RuntimeTypeAdapterFactory;
import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.tree.GLNode;

import java.io.*;

public final class SceneFileHelper {
    private static TypeAdapterFactory rFactory = RuntimeTypeAdapterFactory
            .of(GLObject.class, "type")
            .registerSubtype(Box.class, "Box")
            .registerSubtype(Triangle.class, "Triangle")
            .registerSubtype(Sphere.class, "Sphere")
            .registerSubtype(DirectionalLight.class, "DirectionalLight")
            .registerSubtype(Cylinder.class, "Cylinder")
            .registerSubtype(Line.class, "Line");

    private static Gson gson = new GsonBuilder().registerTypeAdapterFactory(rFactory).create();

    public static GLNode readScene(File file) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(file));

        return gson.fromJson(reader, GLNode.class);
    }

    public static void writeScene(GLNode root, String filename) throws IOException {
        new FileWriter(filename).write(gson.toJson(root));
    }
}
