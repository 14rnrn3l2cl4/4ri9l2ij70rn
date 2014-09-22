package tk.namstudio.x43rrs.Core;


import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

public class Main extends Activity
{
    private D g;
    public static Context c;

    @Override public void onCreate(Bundle b)
    {
        super.onCreate(b);
        c = getApplicationContext();
        g = new D();
        setContentView(g);
    }

    @Override protected void onPause()
    {
        super.onPause();
        g.onPause();
    }

    @Override protected void onResume()
    {
        super.onResume();

        g.onResume();
    }
}
