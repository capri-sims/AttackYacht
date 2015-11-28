/*
********************************************************************************
*** GameOverActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** Handles game play elements that occurs on the player's turn such as
*** interactions b/t players (attacks), tracking the status of the game, and
*** informing players of the status of the game.
********************************************************************************
*** Date:
*** 11/21/15
********************************************************************************
*** Change Log:
*** 11/21/15 - CS - Class created
*** 11/21/15 - CS - Created onCreate
*** 11/27/15 - ZC - Created & designed setVictory
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameOverActivity extends AppCompatActivity {

    // Class-wide variable

    // Holds status of which player won; True, player won; False, play lost
    private static Boolean victory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        //three buttons
    }

/*
********************************************************************************
*** setVictory
*** Group 5
********************************************************************************
*** Purpose:
*** Alters victory so victory status can be displayed
*** Inputs:
*** Boolean newVictory
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/25/15
********************************************************************************
*/
    public static void setVictory (Boolean newVictory)
    {
        victory = newVictory;
    }

}
