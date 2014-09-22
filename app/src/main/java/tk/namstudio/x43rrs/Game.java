package tk.namstudio.x43rrs;

import javax.microedition.khronos.opengles.GL10;

import tk.namstudio.x43rrs.Core.VectorAngle.V;
import tk.namstudio.x43rrs.Tom.Tom;

public class Game
{
    public static Tom tom;

    public void Init()
    {
        tom = new Tom(new V(-0.57f,-0.38f), new V(0.16f));
    }

    public void UpdateFirst(GL10 gl, float telapsed)
    {

        tom.Update(telapsed);
    }

    public void UpdateLast(float telapsed)
    {

    }
}
