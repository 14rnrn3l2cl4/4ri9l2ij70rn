package tk.namstudio.x43rrs.Core.VectorAngle;


public class V
{
    public float x,y;

    public V (){x = y = 0;}
    public V (float x, float y){this.x = x; this.y = y;}

    public V(float f)
    {
        x = f;
        y = f;
    }
    public V copy()
    {
        return new V(this.x, this.y);
    }
    public boolean uguale(V v2)
    {
        return x == v2.x && y == v2.y;
    }

    public void Set(V v)
    {
        x = v.x;
        y = v.y;
    }
    public void Set(float v)
    {
        x = v;
        y = v;
    }

    public void Normalize()
    {
        float d = 1f/(float)Math.sqrt(x*x + y*y);

        x*=d;
        y*=d;
    }
    public void Mul(float m)
    {
        x*=m;
        y*=m;
    }
    public void Sum(V v)
    {
        x+= v.x;
        y+= v.y;
    }
    public void thisMeno(V v)
    {
        x -= v.x;
        y -= v.y;
    }

    public float Distance(V other)
    {
        return (float)Math.sqrt((x-other.x)*(x-other.x) + (y-other.y)*(y-other.y));
    }
}
