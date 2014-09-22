package tk.namstudio.x43rrs;

import android.view.MotionEvent;

import tk.namstudio.x43rrs.Core.D;
import tk.namstudio.x43rrs.Core.Log.L;
import tk.namstudio.x43rrs.Core.VectorAngle.A;
import tk.namstudio.x43rrs.Core.VectorAngle.V;
import tk.namstudio.x43rrs.Oggetti.OggMgr;
import tk.namstudio.x43rrs.Oggetti.Oggetto;
import tk.namstudio.x43rrs.Tom.Tom;
import tk.namstudio.x43rrs.Tom.TomAnim;


public class InputMgr
{
    Oggetto b1, b2;
    boolean initialized = false;

    public InputMgr()
    {
        float scale = 0.05f;
        b1 = new Oggetto(new V(), new A(), new V(scale), OggMgr.TEX_ICON);
        D.drawabili.add(0,b1);

        b2 = new Oggetto(new V(),  new A(), new V(scale), OggMgr.TEX_ICON);
        D.drawabili.add(0,b2);

    }

    public void Update()
    {
        if(!initialized)
        {
            b1.position.Set(D.FromScreenToOGL(new V(4/5f*D.sWidth, .25f*D.sHeight)));
            b2.position.Set(D.FromScreenToOGL(new V(4/5f*D.sWidth, .75f*D.sHeight)));
            initialized = true;
        }
    }

    public void OnTouch(MotionEvent e)
    {
        if(e.getAction() != MotionEvent.ACTION_DOWN)
            return;

        V v = new V(e.getRawX(),e.getRawY());
        v = D.FromScreenToOGL(v);

        if(v.x < 0)
        {
            L.i("si muoveee");
        }
        else
        {
            if(v.y < 0)
            {
                if(Game.tom.tipo == TomAnim.NON_ANGRY)
                    Game.tom.Angry();
                else
                    Game.tom.NonAngry();
            }
            else
            {
                Game.tom.DaiPugno();
            }
        }
    }
}
