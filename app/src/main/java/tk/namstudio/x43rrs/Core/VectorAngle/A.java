package tk.namstudio.x43rrs.Core.VectorAngle;



public class A
{
    public static final float _2_PI = 2*(float)Math.PI;
    public static final float _PI = (float)Math.PI;
    public static final float _PI_2 = (float)Math.PI/2f;
    public static final float _PI_4 = (float)Math.PI/4f;
    public static final float _3_PI_4 = 3f*(float)Math.PI/4f;
    public static final float _5_PI_4 = 5f*(float)Math.PI/4f;
    public static final float _7_PI_4 = 7f*(float)Math.PI/4f;

    public static final float _PI_180 = (float)Math.PI/180f;
    private float alpha;
    public A(){alpha = 0;}
    public A(float a){alpha = a;}


    public void add(float a)
    {
        alpha += a;
        if(a < 0 || a >= _2_PI)
            alpha = alpha - (int)(alpha/_2_PI)*_2_PI;
    }
    public void set(float a)
    {
        if(a < 0 || a >= _2_PI)
            alpha = a - (int)(a/_2_PI)*_2_PI;
        else
            alpha = a;
    }
    public float get()
    {
        return alpha;
    }

    public static A fromTwoPoints(V pDa, V pA)
    {
        if(pDa.uguale(pA))
            return new A();

        V tmp = new V(pA.x - pDa.x, pA.y - pDa.y);

        float dist = 1f/(float)Math.sqrt(tmp.x*tmp.x + tmp.y*tmp.y);

        tmp.x *= dist;

        float alpha = (float) Math.acos(tmp.x);

        if(tmp.y < 0)
            alpha = _2_PI - alpha;

        return new A(alpha);
    }
    public static float ffromTwoPoints(V pDa, V pA)
    {
        if(pDa.uguale(pA))
            return 0;

        V tmp = new V(pA.x - pDa.x, pA.y - pDa.y);

        float dist = 1f/(float)Math.sqrt(tmp.x*tmp.x + tmp.y*tmp.y);

        tmp.x *= dist;

        float alpha = (float) Math.acos(tmp.x);

        if(tmp.y < 0)
            alpha = _2_PI - alpha;

        return alpha;
    }
}
