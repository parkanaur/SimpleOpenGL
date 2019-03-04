package ru.dansstuff.simpleopengl;

import com.google.gson.Gson;
import lombok.var;
import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.objects.Box;
import ru.dansstuff.simpleopengl.objects.OpenGLColor;
import ru.dansstuff.simpleopengl.tree.GLNode;

public class Main {
    public static void main(String[] args) {
        var frame = new OpenGLTestFrame(1600, 900);

        var tree = new GLNode("b1", new Box(new Vec3(1f, 1f, 1f), 2, OpenGLColor.BLUE));
        Gson gson = new Gson();
        System.out.println(gson.toJson(tree));

        frame.getCanvasWrapper().getViewer().setRoot(tree);
    }
}
