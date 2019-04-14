package ru.dansstuff.simpleopengl;

import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

public class Main {
    public static void main(String[] args) {
        int width = 800;
        int height = 600;

        if (args.length == 2) {
            try {
                width = Integer.parseInt(args[0]);
                height = Integer.parseInt(args[1]);
            }
            catch (Exception ex) {
                System.out.println("Invalid width or height parameter");
                return;
            }
        }
        OpenGLTestFrame frame = new OpenGLTestFrame(width, height);
    }
}