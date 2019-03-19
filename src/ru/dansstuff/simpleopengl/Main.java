package ru.dansstuff.simpleopengl;

import com.google.gson.Gson;
import lombok.var;
import ru.dansstuff.simpleopengl.math.Vec3;
import ru.dansstuff.simpleopengl.math.Vec4;
import ru.dansstuff.simpleopengl.objects.Box;
import ru.dansstuff.simpleopengl.objects.DirectionalLight;
import ru.dansstuff.simpleopengl.objects.OpenGLColor;
import ru.dansstuff.simpleopengl.objects.Triangle;
import ru.dansstuff.simpleopengl.tree.GLNode;
import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

public class Main {
    public static void main(String[] args) {
        var frame = new OpenGLTestFrame(1600, 900);

        GLNode root = new GLNode();

        var k = new DirectionalLight();
        k.setColor(new Vec4(1, 1, 1, 1));
        k.setPos(new Vec4(0, 0, -3, 0));
        root.setGlObject(k);

        Gson gson = new Gson();
        System.out.println(gson.toJson(root));
    }
}