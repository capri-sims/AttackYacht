/*
********************************************************************************
*** PlayYourTurnActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** Handles game play elements that occurs on the player's turn such as
*** interactions b/t players (attacks), tracking the status of the game, and
*** informing players of the status of the game.
********************************************************************************
*** Date:
*** 11/23/15
********************************************************************************
*** Change Log:
*** 11/23/15 - CS - Class created and laid out
*** 11/23/15 - CS - Created onCreate
*** 11/23/15 - CS - Created displayMessage
*** 11/24/15 - ZC - Formatted page for comments & style
*** 11/24/15 - ZC - Created processAttack
*** 11/xx/15 - xx -
***
********************************************************************************
*** Look at this:
*** n/a
********************************************************************************
*/

// Project Package
package group5.attackyacht;

// Imported libraries
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class PlayYourTurnActivity extends AppCompatActivity
{
    // Class-wide variables

    // Check for first run for initialization purposes
    private static boolean firstRun = true;

    // 2D array tracking opponent player ships & their status
    static private Ship[][] enemyWaters;
/*
********************************************************************************
*** onCreate
*** Group 5
********************************************************************************
*** Purpose:
*** Displays the game screen, initializes enemyWaters, and continues game loop
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/23/15
********************************************************************************
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_your_turn);

        if(firstRun){
            for(int i = 0; i < 10; i++){ //Initialize enemy waters //will this rewrite everytime???
                for(int j = 0; j < 10; j++){
                    enemyWaters[i][j] = new Ship("water", i, j);
                }
            }
            firstRun = false;
        }



    //add selectable tiles...
    //onclick fire
    //send loc to bluetooth
    //wait for response
    //display message
    //update ship array for hit locations... and destroyed
    //their turn
    }

/*
********************************************************************************
*** onClickFire
*** Group 5
********************************************************************************
*** Purpose:
*** Displays the game screen, initializes enemyWaters, and continues game loop
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/23/15
********************************************************************************
*/



/*
********************************************************************************
*** displayMessage
*** Group 5
********************************************************************************
*** Purpose:
*** Displays the game screen, initializes enemyWaters, and continues game loop
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/23/15
********************************************************************************
*/
    private void displayMessage(String message){

        Context context = getApplicationContext();
        //CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

/*
********************************************************************************
*** processAttack
*** Group 5
********************************************************************************
*** Purpose:
*** Takes attack selection from user, sends it to opponent, then alters
*** enemyWaters or ends the game to reflect the attack
*** Inputs:
*** int attackCoordinates []
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/25/15
********************************************************************************
*/
    public void processAttack (int attackCoordinates [])
    {
        // SEND attackCoordinates TO OPPONENT
        // PLACEHOLDER VALUE FOR ATTACK RESULTS FROM OPPONENT
        Boolean attackResults [] = new Boolean [] {true, false};

        // Check on result of attack, alter enemyWaters if successful
        if (attackResults [0] == true)
        {
            enemyWaters [attackCoordinates [0]][attackCoordinates [1]].hit();

            // DISPLAY MESSAGE THAT ATTACK WAS SUCCESSFUL
        }
        else
        {
            // DISPLAY MESSAGE THAT ATTACK MISSED
        }

        // Check if game is over, call gameOverActivity if it is
        if (attackResults [1] == true)
        {
            // Set player as winner
            GameOverActivity.setVictory (true);

            // Switch over to gameOverActivity screen
            Intent toGameOver = new Intent (PlayYourTurnActivity.this, GameOverActivity.class);
            startActivity (toGameOver);
        }
    }
}
