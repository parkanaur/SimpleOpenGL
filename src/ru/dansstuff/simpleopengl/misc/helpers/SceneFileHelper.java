package ru.dansstuff.simpleopengl.misc.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import ru.dansstuff.simpleopengl.misc.gson.RuntimeTypeAdapterFactory;
import ru.dansstuff.simpleopengl.objects.*;
import ru.dansstuff.simpleopengl.operations.OpenGLOperation;

import java.io.*;

public final class SceneFileHelper {
    private static RuntimeTypeAdapterFactory<GLObject> rFactory = RuntimeTypeAdapterFactory
            .of(GLObject.class, "type");

    private static RuntimeTypeAdapterFactory<OpenGLOperation> opFactory = RuntimeTypeAdapterFactory
            .of(OpenGLOperation.class, "opType");

    private static Gson gson;

    static {
        for (Class objectType : GLObject.getObjectTypes()) {
            rFactory.registerSubtype(objectType, objectType.getSimpleName());
        }
        for (Class transformType : OpenGLOperation.getObjectTypes()) {
            opFactory.registerSubtype(transformType, transformType.getSimpleName());
        }

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(rFactory)
                .registerTypeAdapterFactory(opFactory)
                .create();
    }

    public static GLObject readScene(File file) throws FileNotFoundException, IllegalStateException {
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
