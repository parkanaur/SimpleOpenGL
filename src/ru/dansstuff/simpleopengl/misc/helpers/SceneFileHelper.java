package ru.dansstuff.simpleopengl.misc.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import ru.dansstuff.simpleopengl.misc.gson.RuntimeTypeAdapterFactory;
import ru.dansstuff.simpleopengl.objects.*;

import java.io.*;

public final class SceneFileHelper {
    private static TypeAdapterFactory rFactory = RuntimeTypeAdapterFactory
            .of(GLObject.class, "type")
            .registerSubtype(Box.class, Box.class.getSimpleName())
            .registerSubtype(Triangle.class, Triangle.class.getSimpleName())
            .registerSubtype(Sphere.class, Sphere.class.getSimpleName())
            .registerSubtype(DirectionalLight.class, DirectionalLight.class.getSimpleName())
            .registerSubtype(Cylinder.class, Cylinder.class.getSimpleName())
            .registerSubtype(Line.class, Line.class.getSimpleName());

    private static Gson gson = new GsonBuilder().registerTypeAdapterFactory(rFactory).create();

    public static GLObject readScene(File file) throws FileNotFoundException {
        return gson.fromJson(new JsonReader(new FileReader(file)), GLObject.class);
    }

    public static String getSceneJson(GLObject root) {
        return gson.toJson(root, GLObject.class);
    }

    public static void writeScene(GLObject root, File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        bw.write(getSceneJson(root));
        bw.close();
    }
}
