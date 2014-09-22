package tk.namstudio.x43rrs.Oggetti;


import javax.microedition.khronos.opengles.GL10;

import tk.namstudio.x43rrs.Core.VectorAngle.A;
import tk.namstudio.x43rrs.Core.VectorAngle.V;

public class Oggetto
{
    protected int hPosition,hTextureCoords;

    //parameters
    public V position, scale;
    public A alpha;
    public boolean transparent;
    protected int[] hTextureRef;


    public Oggetto(V _position, A _aplha, V _scale, int[] _hTextureRef)
    {
        alpha = _aplha;
        position = _position;
        scale = _scale;
        hTextureRef = _hTextureRef;
    }

    protected void TextureTransform(GL10 gl)
    {

    }

    protected void TextureFinalization(GL10 gl)
    {

    }

    public void draw(GL10 gl)
    {
        TextureTransform(gl);

        gl.glPushMatrix();



        gl.glBindTexture(GL10.GL_TEXTURE_2D, hTextureRef[0]);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,  OggMgr.vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0,  OggMgr.texBuffer);



        gl.glTranslatef(position.x, position.y, 0);
        gl.glRotatef(alpha.get(), 0, 0, 1);
        gl.glScalef(scale.x, scale.y, 1);

        gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, OggMgr.indexBuffer);
        gl.glPopMatrix();

        TextureFinalization(gl);
    }
    public void Update(float telapsed)
    {

    }
}
