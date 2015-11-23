package group5.attackyacht;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

public class PlayTheirTurnActivity extends AppCompatActivity {

    static boolean first = true;
    static private Ship[][] friendlyWaters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_their_turn);

        if (first) {
            friendlyWaters = SetupActivity.getFriendlyWaters(); //Get friendlyWaters from the setup screen
            first = false;
        }
        //will first stay false?


        //wait to get hit
        //from bluetooth
        //update ship array
        //display message
        //determine end
        //open your turn

    }

    private void displayMessage(String message){

        Context context = getApplicationContext();
        //CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
