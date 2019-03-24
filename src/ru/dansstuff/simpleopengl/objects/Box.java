package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.*;
import ru.dansstuff.simpleopengl.math.Vec3;

public class Box extends GLObject {
    @Getter @Setter
    private Vec3 center;
    @Getter @Setter
    private float length;
    @Getter @Setter
    private OpenGLColor color;
    @Getter
    private final String type = "Box";

    public Box() {
        center = new Vec3(0, 0, 0);
        length = 1;
        color = OpenGLColor.WHITE;
    }

    public Box(Vec3 center, float length, OpenGLColor color) {
        this.center = center;
        this.length = length;
        this.color = color;
    }

    public void draw (GL2 gl) {
        float size = length / 2;
        //gl.glPushMatrix();
        gl.glTranslatef(center.getX(), center.getY(), center.getZ());
        gl.glBegin( gl.GL_QUADS );
        // Top face
        gl.glColor3f(color.getR(), color.getG(), color.getB());  // Green
        gl.glNormal3f(0, 1, 0);
        gl.glVertex3d(  size, size, -size );  // Top-right of top face
        gl.glNormal3f(0, 1, 0);
        gl.glVertex3f( -size, size, -size );  // Top-left of top face
        gl.glNormal3f(0, 1, 0);
        gl.glVertex3f( -size, size,  size );  // Bottom-left of top face
        gl.glNormal3f(0, 1, 0);
        gl.glVertex3f(  size, size,  size );  // Bottom-right of top face

        // Bottom face
        gl.glNormal3f(0, -1, 0);
        gl.glVertex3f(  size, -size, -size ); // Top-right of bottom face
        gl.glNormal3f(0, -1, 0);
        gl.glVertex3f( -size, -size, -size ); // Top-left of bottom face
        gl.glNormal3f(0, -1, 0);
        gl.glVertex3f( -size, -size,  size ); // Bottom-left of bottom face
        gl.glNormal3f(0, -1, 0);
        gl.glVertex3f(  size, -size,  size ); // Bottom-right of bottom face

        // Front face
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(  size,  size, size );  // Top-Right of front face
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f( -size,  size, size );  // Top-left of front face
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f( -size, -size, size );  // Bottom-left of front face
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(  size, -size, size );  // Bottom-right of front face

        // Back face
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(  size, -size, -size ); // Bottom-Left of back face
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f( -size, -size, -size ); // Bottom-Right of back face
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f( -size,  size, -size ); // Top-Right of back face
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(  size,  size, -size ); // Top-Left of back face

        // Left face
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f( -size,  size,  size);  // Top-Right of left face
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f( -size,  size, -size);  // Top-Left of left face
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f( -size, -size, -size);  // Bottom-Left of left face
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f( -size, -size,  size);  // Bottom-Right of left face

        // Right face
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(  size,  size,  size);  // Top-Right of left face
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(  size,  size, -size);  // Top-Left of left face
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(  size, -size, -size);  // Bottom-Left of left face
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(  size, -size,  size);  // Bottom-Right of left face

        gl.glEnd();
        //gl.glPopMatrix();
    }
}
