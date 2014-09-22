package tk.namstudio.x43rrs.Tom;

import tk.namstudio.x43rrs.Core.D;
import tk.namstudio.x43rrs.Core.VectorAngle.A;
import tk.namstudio.x43rrs.Core.VectorAngle.V;
import tk.namstudio.x43rrs.Oggetti.OggMgr;
import tk.namstudio.x43rrs.Oggetti.OggettoGrid;

public class Tom
{


    OggettoGrid dn, md, up;
    final float sogliaC = 0.1f;
    final float sogliaP = 0.07f;
    float counterTimeC = 0;
    float counterTimeP = 0;

    public TomAnim tipo = TomAnim.NON_ANGRY;
    int counterCamminata = 0, counterPugno = 0;

    V position;

    public Tom(V p, V s)
    {
        position = p;
        dn = new OggettoGrid(p, new A(), s, OggMgr.TEX_TOM, 7,2,6,0);
        md = new OggettoGrid(p, new A(), s, OggMgr.TEX_TOM, 7,2,0,1);
        up = new OggettoGrid(p, new A(), s, OggMgr.TEX_TOM, 7,2,6,0);



        D.add(up);
        D.add(md);
        D.add(dn);
    }

    public void SetPosition(V p)
    {
        position = p;
        dn.position = p;
        md.position = p;
        up.position = p;
    }

    public void SetAngle(A p)
    {
        dn.alpha = p;
        md.alpha = p;
        up.alpha = p;
    }

    void DoAnim(float telapsed)
    {
        counterTimeC+=telapsed;
        if(counterTimeC>sogliaC)
        {
            SetPosition(new V(position.x, position.y+ 0.002f*(float)Math.sin(counterCamminata*(4*Math.PI/7))));
            SetAngle(new A(1.5f*(float)Math.sin(counterCamminata*(1*Math.PI/7))));

            counterTimeC -= sogliaC;

            counterCamminata--;
            if(counterCamminata < 0)
                counterCamminata = 6;

            md.setIndXColTexture(counterCamminata);
        }

        if(tipo == TomAnim.ANGRY_CAMMINATA)
        {
            up.setIndXColTexture(4);
            dn.setIndXColTexture(5);
        }
        else if(tipo == TomAnim.NON_ANGRY)
        {
            up.setIndXColTexture(6);
            dn.setIndXColTexture(6);
        }



        if(tipo != TomAnim.ANGRY_PUGNO_DX)
            return;

        counterTimeP+=telapsed;
        if(counterTimeP>sogliaP)
        {
            counterTimeP -= sogliaP;



            counterPugno++;

            if(counterPugno == 5)
            {
                Angry();
                return;
            }

            switch (counterPugno)
            {
                case 0: up.setIndXColTexture(0); dn.setIndXColTexture(0); break;
                case 1: up.setIndXColTexture(1); dn.setIndXColTexture(2); break;
                case 2: up.setIndXColTexture(3); dn.setIndXColTexture(3); break;
                case 3: up.setIndXColTexture(4); dn.setIndXColTexture(5); break;
            }
        }


    }

    public void DaiPugno() { tipo = TomAnim.ANGRY_PUGNO_DX; counterPugno = -1; counterTimeP = 0;}
    public void Angry() { tipo = TomAnim.ANGRY_CAMMINATA;}
    public void NonAngry() { tipo = TomAnim.NON_ANGRY; }

    public void Update(float telapsed)
    {
        DoAnim(telapsed);
    }
}
