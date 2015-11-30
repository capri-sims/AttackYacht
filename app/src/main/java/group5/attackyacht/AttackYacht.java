package group5.attackyacht;

import android.app.Application;
import android.content.Context;

/**
 * Created by Capri on 11/30/2015.
 */
public class AttackYacht extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();

        AttackYacht.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }

}
