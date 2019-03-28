package com.alticast.soorynplayerproject.video;

import android.opengl.GLES20;
import com.alticast.soorynplayer.core.gl.GLUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class DirectVideo
{
    private static final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "attribute vec4 vTexCoordinate;" +
                    "uniform mat4 textureTransform;" +
                    "varying vec2 v_TexCoordinate;" +
                    "void main() {" +
                    "   v_TexCoordinate = (textureTransform * vTexCoordinate).xy;" +
                    "   gl_Position = vPosition;" +
                    "}";

    private static final String fragmentShaderCode =
            "#extension GL_OES_EGL_image_external : require\n" +
                    "precision mediump float;" +
                    "uniform samplerExternalOES texture;" +
                    "varying vec2 v_TexCoordinate;" +
                    "void main () {" +
                    "    vec4 color = texture2D(texture, v_TexCoordinate);" +
                    "    gl_FragColor = color;" +
                    "}";



    private FloatBuffer vertexBuffer, textureVerticesBuffer;
    private ShortBuffer drawListBuffer;
    private final int mProgram;

    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

    // number of coordinates per vertex in this array
    private static final int COORDS_PER_VERTEX = 2;

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    private static float squareSize = 1.0f;
    private static float squareCoords[] = { -squareSize,  squareSize, 0.0f,   // top left
            -squareSize, -squareSize, 0.0f,   // bottom left
            squareSize, -squareSize, 0.0f,   // bottom right
            squareSize,  squareSize, 0.0f }; // top right

    private float textureCoords[] = { 0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 1.0f, 0.0f, 1.0f };



    private int texture;

    public DirectVideo()
    {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        ByteBuffer bb2 = ByteBuffer.allocateDirect(textureCoords.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        textureVerticesBuffer = bb2.asFloatBuffer();
        textureVerticesBuffer.put(textureCoords);
        textureVerticesBuffer.position(0);
        mProgram = GLUtil.createProgram(vertexShaderCode,fragmentShaderCode);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
    }

    public void draw(float[] videoMatrix)
    {
        GLES20.glUseProgram(mProgram);
        int textureParamHandle = GLES20.glGetUniformLocation(mProgram, "texture");
        int textureCoordinateHandle = GLES20.glGetAttribLocation(mProgram, "vTexCoordinate");
        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        int textureTranformHandle = GLES20.glGetUniformLocation(mProgram, "textureTransform");

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 4 * 3, vertexBuffer);

        GLES20.glUniform1i(textureParamHandle, 0);

        GLES20.glEnableVertexAttribArray(textureCoordinateHandle);
        GLES20.glVertexAttribPointer(textureCoordinateHandle, 4, GLES20.GL_FLOAT, false, 0, textureVerticesBuffer);

        GLES20.glUniformMatrix4fv(textureTranformHandle, 1, false, videoMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(textureCoordinateHandle);

    }
}

//    private final String vertexShaderCode =
//            "attribute vec4 vPosition;" +
//                    "attribute vec2 inputTextureCoordinate;" +
//                    "varying vec2 textureCoordinate;" +
//                    "void main()" +
//                    "{"+
//                    "gl_Position = vPosition;"+
//                    "textureCoordinate = inputTextureCoordinate;" +
//                    "}";
//
//    private final String fragmentShaderCode =
//            "#extension GL_OES_EGL_image_external : require\n"+
//                    "precision mediump float;" +
//                    "varying vec2 textureCoordinate;\n" +
//                    "uniform samplerExternalOES s_texture;\n" +
//                    "void main() {" +
//                    "  gl_FragColor = texture2D( s_texture, textureCoordinate );\n" +
//                    "}";
//
//
//    static float squareCoords[] = {
//            -1.0f,  1.0f,
//            -1.0f, -1.0f,
//            1.0f, -1.0f,
//            1.0f,  1.0f,
//    };
//
//    static float textureCoords[] = {
//            0.0f, 1.0f,
//            1.0f, 1.0f,
//            1.0f, 0.0f,
//            0.0f, 0.0f,
//    };
//
//    public void draw()
//    {
//        GLES20.glUseProgram(mProgram);
//
//        // get handle to vertex shader's vPosition member
//        int mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
//
//        // Enable a handle to the triangle vertices
//        GLES20.glEnableVertexAttribArray(mPositionHandle);
//
//        // Prepare the <insert shape here> coordinate data
//        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
//
//        int mTextureCoordHandle = GLES20.glGetAttribLocation(mProgram, "inputTextureCoordinate");
//        GLES20.glEnableVertexAttribArray(mTextureCoordHandle);
//        GLES20.glVertexAttribPointer(mTextureCoordHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, textureVerticesBuffer);
//
//        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
//
//        // Disable vertex array
//        GLES20.glDisableVertexAttribArray(mPositionHandle);
//        GLES20.glDisableVertexAttribArray(mTextureCoordHandle);
//    }