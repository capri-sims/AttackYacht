/*
********************************************************************************
*** PlayTheirTurnActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** Handles gameplay elements such as player turns, interactions b/t players
*** (attacks), tracking the status of the game, and informing players of the
*** status of the game.                 OOPS NEED TO REWRITE
********************************************************************************
*** Date:
*** 11/23/15
********************************************************************************
*** Change Log:
*** 11/23/15 - CS - Class created and laid out
*** 11/23/15 - CS - Created onCreate
*** 11/23/15 - CS - Created displayMessage
*** 11/24/15 - ZC - Formatted page for comments & style
*** 11/24/15 - ZC - Created onClickFire
*** 11/24/15 - ZC - Created & designed determineEnd
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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

public class PlayTheirTurnActivity extends AppCompatActivity
{
    // Global variable

        // Turn tracking?
        static boolean first = true;

    // Class-wide variables

        // 2D array tracking opponent player ships & their status
        static private Ship[][] friendlyWaters;

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

        if (first)
        {
            // Get friendlyWaters from the setup screen
            friendlyWaters = SetupActivity.getFriendlyWaters();
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
*** onClickFire
*** Group 5
********************************************************************************
*** Purpose:
*** Allows the user to confirm an attack
*** Inputs:
*** SOME SORT OF EVENT TRIGGER???
*** Outputs:
*** int [] attackCoordinate
********************************************************************************
*** Date
*** 11/24/15
********************************************************************************
*/
// PARAMETER WOULD CONTAIN SOME SORT OF EVENT TRIGGER???
    public int [] onClickFire ()
    {
        // Holds coordinates of attack, value to return
        int [] attackCoordinate = new int [2];

        // Takes user input via touchscreen and converts into into x & y
        // value saved to attackCoordinate

        return attackCoordinate;
    }

/*
********************************************************************************
*** determineEnd
*** Group 5
********************************************************************************
*** Purpose:
*** Traverses both friendlyWaters & enemyWaters to check if the game has ended
*** Inputs:
*** n/a
*** Outputs:
*** Boolean [] endStatus
********************************************************************************
*** Date
*** 11/18/15
********************************************************************************
*/
    public Boolean [] determineEnd ()
    {
        // Represents end status of game, 0 - is game over?, 1 - Did player win?
        // I set 0 to true as checking for if the game needs to continue is easier
        // I set 1 to null as if something went wrong, I'd rather it not tell
        // a player they won / lost if they did not
        Boolean [] endStatus = new Boolean [] {true, null};
//
//        // Traverse enemyWaters to check if the player won
//        for (int indexY1 = 0; indexY1 < enemyWaters.length; indexY1++)
//        {
//            for (int indexX1 = 0; indexX1 < enemyWaters [indexY1].length; indexX1++)
//            {
//                // If any health count is greater than 0, the game continues
//                if (enemyWaters [indexY1][indexX1].health > 0)
//                {
//                    endStatus [0] = false;
//                }
//            }
//        }
//        // If the game ended now, the player has won
//        if (endStatus [0] == true)
//        {
//            endStatus [1] = true;
//        }
//        // If player did not win, check if they lost
//        else
//        {
            // Traverse friendlyWaters to check if player lost
//            for (int indexY2 = 0; indexY2 < friendlyWaters.length; indexY2++)
//            {
//                for (int indexX2 = 0; indexX2 < friendlyWaters [indexY2].length; indexX2++)
//                {
//                    // If any health count is greater than 0, the game continues
//                    if (enemyWaters [indexY2][indexX2].health > 0)
//                    {
//                        endStatus [0] = false;
//                    }
//                }
//            }
//            // If the game ended now, the player has lost
//            if (endStatus [0] == true)
//            {
//                endStatus [1] = false;
//            }
//        }
//
//        return endStatus;
//    }
        return endStatus;
}}
