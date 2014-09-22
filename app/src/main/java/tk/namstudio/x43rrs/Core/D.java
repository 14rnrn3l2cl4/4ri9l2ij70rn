package tk.namstudio.x43rrs.Core;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

import java.util.ArrayList;

import tk.namstudio.x43rrs.Core.VectorAngle.V;
import tk.namstudio.x43rrs.Game;
import tk.namstudio.x43rrs.InputMgr;
import tk.namstudio.x43rrs.Oggetti.OggMgr;
import tk.namstudio.x43rrs.Oggetti.Oggetto;

public class D extends GLSurfaceView implements GLSurfaceView.Renderer
{
    public static int sWidth, sHeight;
    public static ArrayList<Oggetto> drawabili, updatabili;
    public static void add(Oggetto o)
    {
        drawabili.add(o);
    }
    public static void addU(Oggetto o)
    {
        updatabili.add(o);
    }

    Game g;
    InputMgr im;
    public long startTime, thisTime;
    float telapsed;
    boolean begin = false;

    public D()
    {
        super(Main.c);
        drawabili = new ArrayList<Oggetto>();
        updatabili = new ArrayList<Oggetto>();

        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        g = new Game();
    }

    //Draw Part <----------------------------------------------------------------------------

    @Override public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        gl.glClearColor(.4f, .7f, 1, 1);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glDisable(GL10.GL_LIGHTING);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        OggMgr.LoadAll();
        if(!begin)
        {
            begin = true;
            OggMgr.LoadOnce();
            startTime=thisTime=System.nanoTime();
            g.Init();
            im = new InputMgr();
        }

    }


    @Override public void onDrawFrame(GL10 gl)
    {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();


        telapsed = ((thisTime=System.nanoTime()) - startTime)*0.000000001f;
        startTime=thisTime;

        im.Update();

        g.UpdateFirst(gl,telapsed);
        for(int i = drawabili.size()-1; i >= 0; i--)
        {
            Oggetto o = drawabili.get(i);
            o.draw(gl);
            o.Update(telapsed);
        }
        for(Oggetto o : updatabili)
        {
            o.Update(telapsed);
        }
        g.UpdateLast(telapsed);
    }
    @Override public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        sWidth = width; sHeight = height;

        int max, min;
        max = Math.max(sWidth, sHeight);
        min = Math.min(sWidth, sHeight);

        gl.glViewport(max == sHeight? (min-max)/2 : 0, max == sWidth? (min-max)/2 : 0, max,  max);
        gl.glLoadIdentity();
        GLU.gluOrtho2D(gl, 0, sWidth, sHeight, 0);
    }

    @Override public void onResume()
    {
        super.onResume();
        startTime=thisTime=System.nanoTime();
    }

    @Override public boolean onTouchEvent(MotionEvent e)
    {
        im.OnTouch(e);
        return true;
    }

    public static V FromScreenToOGL(V p)
    {
        V q = new V(p.x, p.y);
        q.x = (q.x - (float)sWidth/2f)/(float)sWidth*2f;
        q.y = 1- (q.y + (sWidth - sHeight)/2)/(float)sWidth*2f;

        return q;
    }
}