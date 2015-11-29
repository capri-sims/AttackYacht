/*
********************************************************************************
*** PlayTheirTurnActivity.java
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
*** 11/24/15 - ZC - Created determineEnd
*** 11/26/15 - ZC - Created processAttack
*** 11/29/15 - xx - Created updateGrid
*** 11/xx/15 - xx -
***
********************************************************************************
*/

// Project Package
package group5.attackyacht;


// Imported libraries
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class PlayTheirTurnActivity extends AppCompatActivity
{
    static private int ROW = 7;
    static private int COL = 12;
    static boolean firstRun = true; // Turn tracking? no
    static private Ship[][] friendlyWaters = new Ship[ROW][COL];  // 2D array tracking opponent player ships & their status

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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_their_turn);

        // Only run on first run
        if (firstRun){
            // Get friendlyWaters from the setup screen
            friendlyWaters = SetupActivity.getFriendlyWaters();
            firstRun = false;
        }

        updateGrid();
        // TODO: onCreate; Begin processAttack
        // TODO: onCreate; Being your turn
    }


/*
********************************************************************************
*** displayMessage
*** Group 5
********************************************************************************
*** Purpose:
*** Informs user to the status of their attacks on the enemy
*** Inputs:
*** String message
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/23/15
********************************************************************************
*/
    private void displayMessage(String message)
    {
        Context context = getApplicationContext();
        //CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

/*
********************************************************************************
*** determineEnd
*** Group 5
********************************************************************************
*** Purpose:
*** Traverses friendlyWaters to check if the game has ended
*** Inputs:
*** n/a
*** Outputs:
*** Boolean endStatus
********************************************************************************
*** Date
*** 11/18/15
********************************************************************************
*/
    public Boolean determineEnd ()
    {
        // Represents end status of game: True, game over; False, game continues
        Boolean endStatus = true;

            // Traverse friendlyWaters to check if player lost
            for (int indexY = 0; indexY < friendlyWaters.length; indexY++)
            {
                for (int indexX = 0; indexX < friendlyWaters [indexX].length; indexX++)
                {
                    // If there is anything other than destroyed or water type pieces, the game continues
                    if (friendlyWaters [indexY][indexX].getType () != "destroyed" || friendlyWaters [indexY][indexX].getType () != "water")
                    {
                        endStatus = false;
                    }
                }
            }

        return endStatus;
    }

/*
********************************************************************************
*** processAttack
*** Group 5
********************************************************************************
*** Purpose:
*** Takes attack selection from opponent, alters friendlyWaters to reflect
*** attack, Sends hit and loss confirmation to opponent
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/26/15
********************************************************************************
*/
    public void processAttack ()
    {
        // TODO: processAttack; RECEIVE attackCoordinates FROM OPPONENT
        // TODO: processAttack; OVERWRITE PLACEHOLDER W/ attackCoordinates FROM OPPONENT
        int attackCoordinates [] = new int [] {1, 2};

        // Represent status of attack: 0, if attack was successful; 1, if game is over
        Boolean attackResults [] = new Boolean [2];

        // Check if attack was successful
        attackResults [0] = friendlyWaters [attackCoordinates [0]][attackCoordinates [1]].hit();

        // Game will continue, unless detemineEnd sets value to true
        attackResults [1] = false;

        if (attackResults [0] == true)
        {
            // TODO: processAttack; DISPLAY MESSAGE THAT ATTACK WAS SUCCESSFUL

            // Check if the game ended
            attackResults [1] = determineEnd ();
        }
        else
        {
            // TODO: processAttack; DISPLAY MESSAGE THAT ATTACK MISSED
        }

        // TODO: processAttack; SEND attackResults TO OPPONENT

        // Check if game is over, call gameOverActivity if it is
        if (attackResults [1] == true)
        {
            // Set player as loser
            GameOverActivity.setVictory (false);

            // Switch over to gameOverActivity screen
            Intent toGameOver = new Intent (PlayTheirTurnActivity.this, GameOverActivity.class);
            startActivity (toGameOver);
        }
    }

/*
********************************************************************************
*** updateGrid
*** Group 5
********************************************************************************
*** Purpose:
*** Updates the graphics displayed within the on screen grid
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/29/15
********************************************************************************
*/
    private void updateGrid(){ //will this override the onClickListeners???

        TableLayout table = (TableLayout) findViewById(R.id.friendlyWaters);
        for (int i = 0; i < ROW; i++){
            TableRow row = new TableRow(this);
            for (int j = 0; j < COL; j++){
                ImageView image = new ImageView (this);
                int imageID = getResources().getIdentifier((friendlyWaters[i][j]).getType(), "drawable", getPackageName());
                image.setImageDrawable(ContextCompat.getDrawable(this, imageID));
                row.addView(image, 100, 100);
            }
            table.addView(row);
        }
    }
}
