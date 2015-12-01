/*
********************************************************************************
*** MusicService.java
*** Group 5
********************************************************************************
*** Purpose:
*** Plays music throughout the program
********************************************************************************
*** Date:
*** 11/19/15
********************************************************************************
*** Change Log:
*** 11/xx/15 - xx -
***
********************************************************************************
*/

// Project Package
package group5.attackyacht;

/*
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.IOException;
*/

public class MusicService { //extends Service {
    public MusicService() {
    }

//    private static final String TAG = null;
//    MediaPlayer player;
//
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        player = MediaPlayer.create(this, R.raw.bmusic);
//        player.setLooping(true); // Set looping
//        player.setVolume(100,100);
//
//    }
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        player.start();
//
//        return 1;
//    }
//
//    public void onStart(Intent intent, int startId) {
//        // TODO
//
//
//    }
//
//    public IBinder onUnBind(Intent arg0) {
//        // TODO Auto-generated method stub
//
//        return null;
//    }
//
//    public void onStop() {
//
//    }
//
//    public void onPause() {
//
//    }
//
//    @Override
//    public void onDestroy() {
//        player.stop();
//        player.release();
//    }
//
//    @Override
//    public void onLowMemory() {
//
//    }
}
