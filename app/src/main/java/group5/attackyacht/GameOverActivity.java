/*
********************************************************************************
*** GameOverActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** Activates when a game has concluded, informing the player to the result of
*** the game. Allows player to return to menu or play again.
********************************************************************************
*** Date:
*** 11/19/15
********************************************************************************
*** Change Log:
*** 11/19/15 - CS - Created onCreate
*** 11/25/15 - ZC - Created setVictory
*** 11/xx/15 - xx -
***
********************************************************************************
*/

// Project Package
package group5.attackyacht;

// Imported libraries
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    // Class-wide variable

    // Holds status of which player won; True, player won; False, play lost
    private static Boolean victory = null;

    /*
********************************************************************************
*** onCreate
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/19/15
********************************************************************************
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // button to return to main menu.
        Button buttonMenu = (Button)findViewById(R.id.button_menu);
        buttonMenu.setOnClickListener((v) -> {
            Intent intent = new Intent(GameOverActivity.this,MainActivity.class);
            startActivity(intent);
        });

        // button to play again
        Button buttonAgain = (Button) findViewById(R.id.button_again);
        buttonAgain.setOnClickListener((v) -> {
            Intent intent = new Intent(GameOverActivity.this,SetupActivity.class);
            startActivity(intent);
        });

        // button to exit
        Button buttonExit = (Button)findViewById(R.id.button_exit);
        buttonExit.setOnClickListener((v)-> {
            finish();
            System.exit(0);
        });
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

