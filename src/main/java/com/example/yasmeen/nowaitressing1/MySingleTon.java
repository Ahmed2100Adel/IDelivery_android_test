package com.example.yasmeen.nowaitressing1;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by yasmeen on 12/18/2017.
 */

public class MySingleTon {
    private static MySingleTon Minstance;
    private RequestQueue requestQueue ;
    private static Context mctx ;

    private MySingleTon(Context context)
    {
        mctx = context ;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleTon getMinstance (Context context)
    {
        if(Minstance == null)
        {
            Minstance = new MySingleTon(context);
        }
        return Minstance;
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }
    public <T>void addRequestQueuex(Request<T> request)
    {
        requestQueue.add(request);
    }

}

