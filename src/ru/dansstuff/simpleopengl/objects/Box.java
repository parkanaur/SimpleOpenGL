package ru.dansstuff.simpleopengl.objects;

import com.jogamp.opengl.GL2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.dansstuff.simpleopengl.math.Vec3;

@RequiredArgsConstructor
public class Box implements GLObject {
    @Getter
    private final Vec3 center;
    @Getter
    private final float length;
    @Getter
    private final OpenGLColor color;
    @Getter
    private final String type = "Box";

    public void draw (GL2 gl) {
        float size = length / 2;
        gl.glPushMatrix();
        gl.glTranslatef(center.x, center.y, center.z);
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
        gl.glColor3f(   1.0f,  0.5f,  0.0f ); // Orange
        gl.glNormal3f(0, -1, 0);
        gl.glVertex3f(  size, -size, -size ); // Top-right of bottom face
        gl.glNormal3f(0, -1, 0);
        gl.glVertex3f( -size, -size, -size ); // Top-left of bottom face
        gl.glNormal3f(0, -1, 0);
        gl.glVertex3f( -size, -size,  size ); // Bottom-left of bottom face
        gl.glNormal3f(0, -1, 0);
        gl.glVertex3f(  size, -size,  size ); // Bottom-right of bottom face

        // Front face
        gl.glColor3f(   1.0f,  0.0f, 0.0f );  // Red
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(  size,  size, size );  // Top-Right of front face
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f( -size,  size, size );  // Top-left of front face
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f( -size, -size, size );  // Bottom-left of front face
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(  size, -size, size );  // Bottom-right of front face

        // Back face
        gl.glColor3f(   1.0f,  1.0f,  0.0f ); // Yellow
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(  size, -size, -size ); // Bottom-Left of back face
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f( -size, -size, -size ); // Bottom-Right of back face
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f( -size,  size, -size ); // Top-Right of back face
        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(  size,  size, -size ); // Top-Left of back face

        // Left face
        gl.glColor3f(   0.0f,  0.0f,  1.0f);  // Blue
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f( -size,  size,  size);  // Top-Right of left face
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f( -size,  size, -size);  // Top-Left of left face
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f( -size, -size, -size);  // Bottom-Left of left face
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f( -size, -size,  size);  // Bottom-Right of left face

        // Right face
        gl.glColor3f(   1.0f,  0.0f,  1.0f);  // Magenta
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(  size,  size,  size);  // Top-Right of left face
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(  size,  size, -size);  // Top-Left of left face
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(  size, -size, -size);  // Bottom-Left of left face
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(  size, -size,  size);  // Bottom-Right of left face

        gl.glEnd();
        gl.glPopMatrix();
    }
}
