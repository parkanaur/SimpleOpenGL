package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import ru.dansstuff.simpleopengl.math.Vec3;

public class Box implements Primitive {
    public final Vec3 center;
    public final float length;
    public final OpenGLColor color;

    public Box(Vec3 center, float length, OpenGLColor color) {
        this.center = center;
        this.length = length;
        this.color = color;
    }

    public void draw (GL2 gl) {
        float size = length / 2;
        gl.glPushMatrix();
        gl.glTranslatef(center.x, center.y, center.z);
        gl.glBegin( gl.GL_QUADS );
        // Top face
        gl.glColor3f(   0.0f, 1.0f,  0.0f );  // Green
        gl.glVertex3d(  size, size, -size );  // Top-right of top face
        gl.glVertex3f( -size, size, -size );  // Top-left of top face
        gl.glVertex3f( -size, size,  size );  // Bottom-left of top face
        gl.glVertex3f(  size, size,  size );  // Bottom-right of top face

        // Bottom face
        gl.glColor3f(   1.0f,  0.5f,  0.0f ); // Orange
        gl.glVertex3f(  size, -size, -size ); // Top-right of bottom face
        gl.glVertex3f( -size, -size, -size ); // Top-left of bottom face
        gl.glVertex3f( -size, -size,  size ); // Bottom-left of bottom face
        gl.glVertex3f(  size, -size,  size ); // Bottom-right of bottom face

        // Front face
        gl.glColor3f(   1.0f,  0.0f, 0.0f );  // Red
        gl.glVertex3f(  size,  size, size );  // Top-Right of front face
        gl.glVertex3f( -size,  size, size );  // Top-left of front face
        gl.glVertex3f( -size, -size, size );  // Bottom-left of front face
        gl.glVertex3f(  size, -size, size );  // Bottom-right of front face

        // Back face
        gl.glColor3f(   1.0f,  1.0f,  0.0f ); // Yellow
        gl.glVertex3f(  size, -size, -size ); // Bottom-Left of back face
        gl.glVertex3f( -size, -size, -size ); // Bottom-Right of back face
        gl.glVertex3f( -size,  size, -size ); // Top-Right of back face
        gl.glVertex3f(  size,  size, -size ); // Top-Left of back face

        // Left face
        gl.glColor3f(   0.0f,  0.0f,  1.0f);  // Blue
        gl.glVertex3f( -size,  size,  size);  // Top-Right of left face
        gl.glVertex3f( -size,  size, -size);  // Top-Left of left face
        gl.glVertex3f( -size, -size, -size);  // Bottom-Left of left face
        gl.glVertex3f( -size, -size,  size);  // Bottom-Right of left face

        // Right face
        gl.glColor3f(   1.0f,  0.0f,  1.0f);  // Magenta
        gl.glVertex3f(  size,  size,  size);  // Top-Right of left face
        gl.glVertex3f(  size,  size, -size);  // Top-Left of left face
        gl.glVertex3f(  size, -size, -size);  // Bottom-Left of left face
        gl.glVertex3f(  size, -size,  size);  // Bottom-Right of left face

        gl.glEnd();
        gl.glPopMatrix();
    }
}
