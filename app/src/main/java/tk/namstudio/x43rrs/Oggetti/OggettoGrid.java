package tk.namstudio.x43rrs.Oggetti;

import javax.microedition.khronos.opengles.GL10;

import tk.namstudio.x43rrs.Core.VectorAngle.A;
import tk.namstudio.x43rrs.Core.VectorAngle.V;

public class OggettoGrid  extends Oggetto
{
    float uno_su_nrTex, uno_su_ncTex, ir_per_uno_su_nrTex, ic_per_uno_su_ncTex;
    int nXcol, nYrow, iXcol, iYrow;

    public int getIndXColTexture()
    {
        return iXcol;
    }
    public int getIndYRowTexture()
    {
        return iYrow;
    }
    public int getXNCol()
    {
        return nXcol;
    }
    public int getYNRow()
    {
        return nYrow;
    }


    public void setIndXColTexture(int i)
    {
        iXcol = i;
        ic_per_uno_su_ncTex = iXcol*uno_su_ncTex;
    }
    public void setIndYRowTexture(int i)
    {
        iYrow = i;
        ir_per_uno_su_nrTex = iYrow*uno_su_nrTex;
    }

    public OggettoGrid(V p, A a, V s, int[] hT, int _nXcol, int _nYrow, int _iXcol, int _iYrow)
    {
        super(p, a, s, hT);

        nXcol = _nXcol;
        nYrow = _nYrow;
        iXcol = _iXcol;
        iYrow = _iYrow;

        uno_su_ncTex = 1f/nXcol;
        uno_su_nrTex = 1f/nYrow;

        ir_per_uno_su_nrTex = iYrow*uno_su_nrTex;
        ic_per_uno_su_ncTex = iXcol*uno_su_ncTex;
    }

    protected void TextureTransform(GL10 gl)
    {
        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glPushMatrix();

        gl.glTranslatef(ic_per_uno_su_ncTex, ir_per_uno_su_nrTex, 0);
        gl.glScalef(uno_su_ncTex, uno_su_nrTex, 1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }
    protected void TextureFinalization(GL10 gl)
    {
        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glPopMatrix();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }
}
