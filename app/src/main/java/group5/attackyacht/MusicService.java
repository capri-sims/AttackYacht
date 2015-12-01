/*
********************************************************************************
*** MusicService.java
*** Group 5
********************************************************************************
*** Purpose:
*** Plays music throughout the program
********************************************************************************
*** Date:
*** 12/01/15
********************************************************************************
*** Change Log:
*** 12/01/15 - ZC - Created onBind
*** 12/01/15 - ZC - Created onCreate
*** 12/01/15 - ZC - Created onStartCommand
*** 12/01/15 - ZC - Created onDestroy
*** 11/xx/15 - xx -
***
********************************************************************************
*/

// Project Package
package group5.attackyacht;

// Imported Libraries
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service
{
    // Musicplayer object for use
    MediaPlayer music;

    /*
    ********************************************************************************
    *** onBind
    *** Group 5
    ********************************************************************************
    *** Purpose:
    *** Returns null when the activity switchs, binding the music player
    *** Inputs:
    *** Intent activitySwitchBind
    *** Outputs:
    *** null
    ********************************************************************************
    *** Date
    *** 12/01/15
    ********************************************************************************
    */
    public IBinder onBind (Intent activitySwitchBind)
    {
        return null;
    }

    /*
    ********************************************************************************
    *** onCreate
    *** Group 5
    ********************************************************************************
    *** Purpose:
    *** Handles the settings of for the music player
    *** Inputs:
    *** n/a
    *** Outputs:
    *** n/a
    ********************************************************************************
    *** Date
    *** 12/01/15
    ********************************************************************************
    */
    @Override
    public void onCreate ()
    {
        super.onCreate();

        // Settings for the music player
        //music = MediaPlayer.create(this, R.raw.machinations); //GIVES ERRORS
        music.setLooping(true);
        music.setVolume(100,100);
    }

    /*
********************************************************************************
*** onStartCommand
*** Group 5
********************************************************************************
*** Purpose:
*** Starts the music
*** Inputs:
*** Intent intent, int flags, int startId
*** Outputs:
*** int 1
********************************************************************************
*** Date
*** 12/01/15
********************************************************************************
*/
    public int onStartCommand (Intent intent, int flags, int startId)
    {
        music.start();
        return 1;
    }

    /*
    ********************************************************************************
    *** onDestroy
    *** Group 5
    ********************************************************************************
    *** Purpose:
    *** Handles the settings of for the music player
    *** Inputs:
    *** n/a
    *** Outputs:
    *** n/a
    ********************************************************************************
    *** Date
    *** 12/01/15
    ********************************************************************************
    */
    @Override
    public void onDestroy()
    {
        music.stop();
        music.release();
    }
}