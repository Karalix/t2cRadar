package fr.coding_ops.t2cradar.modele;

import android.app.Application;
import android.content.Context;

/**
 * Created by Alix on 26/10/2014.
 */
public class MyApplication extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
