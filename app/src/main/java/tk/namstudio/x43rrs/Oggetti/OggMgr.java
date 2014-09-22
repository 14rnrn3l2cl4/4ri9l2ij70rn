package tk.namstudio.x43rrs.Oggetti;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import tk.namstudio.x43rrs.Core.Main;
import tk.namstudio.x43rrs.R;

public class OggMgr
{
    //Textures
    public static int[] TEX_ICON = new int[1];
    public static int[] TEX_TOM = new int[1];

    private static void LoadTextures()
    {
        TEX_ICON[0] = loadTexture(Main.c, R.drawable.ic_launcher, true);
        TEX_TOM[0] = loadTexture(Main.c, R.drawable.tom, true);
    }
    private static int loadTexture(final Context context, final int resourceId, boolean clamp)
    {
        return loadTexture(context, resourceId, clamp, clamp);
    }
    private static int loadTexture(final Context context, final int resourceId, boolean clampS, boolean clampT)
    {
        final int[] textureHandle = new int[1];
        GLES20.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] == 0)
            throw new RuntimeException("Error loading texture");

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

        if(clampS)GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        if(clampT)GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);


        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();

        return textureHandle[0];
    }


    //Buffers
    public static FloatBuffer vertexBuffer, texBuffer;
    public static ByteBuffer indexBuffer;

    public static void LoadOnce()
    {
        byte[] indices = {0,1,2, 0,2,3};
        float[] vertCoords = {-1,-1,0,   1,-1,0,   1,1,0,   -1,1,0}, textCoords = {0,1, 1,1, 1,0, 0,0};

        ByteBuffer bb = ByteBuffer.allocateDirect(vertCoords.length*4);bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();vertexBuffer.put(vertCoords);vertexBuffer.position(0);

        bb = ByteBuffer.allocateDirect(textCoords.length*4);bb.order(ByteOrder.nativeOrder());
        texBuffer = bb.asFloatBuffer();texBuffer.put(textCoords);texBuffer.position(0);

        indexBuffer = ByteBuffer.allocateDirect(indices.length);indexBuffer.put(indices);indexBuffer.position(0);
    }
    public static void LoadAll()
    {
        LoadTextures();
    }
}
