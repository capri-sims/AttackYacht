package group5.attackyacht;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class PlayYourTurnActivity extends AppCompatActivity {

    static boolean first = true;
    static private Ship[][] enemyWaters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_your_turn);

        if(first){
            for(int i = 0; i < 10; i++){ //Initialize enemy waters //will this rewrite everytime???
                for(int j = 0; j < 10; j++){
                    enemyWaters[i][j] = new Ship("water");
                }
            }
            first = false;
        }



        //add selectable tiles...
        //onclick fire
        //send loc to bluetooth
        //wait for response
        //display message
        //update ship array for hit locations... and destroyed
        //their turn
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
