package ru.dansstuff.simpleopengl;

import ru.dansstuff.simpleopengl.window.OpenGLTestFrame;

public class Main {
    private static int width = 800;
    private static int height = 600;

    public static void main(String[] args) {
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