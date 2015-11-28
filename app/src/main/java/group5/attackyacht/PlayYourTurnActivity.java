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
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class PlayYourTurnActivity extends AppCompatActivity
{
    // Global variable

    static private int ROW = 7;
    static private int COL = 12;
    //static boolean first = true;
    static private Ship[][] enemyWaters  = new Ship[ROW][COL];
    private static boolean firstRun = true; // Check for first run for initialization purposes
    private ImageView selectedSquare;
    private Ship selectedShip;

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

        TableLayout table = (TableLayout) findViewById(R.id.shipSetupGrid);

        if(firstRun){
            for(int i = 0; i < ROW; i++){ //Initialize enemy waters //will this rewrite everytime???
                TableRow row = new TableRow(this);
                for(int j = 0; j < COL; j++){

                    ImageView image = new ImageView (this);
                    enemyWaters[i][j] = new Ship("water", i, j);
                    image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.water));
                    row.addView(image, 100, 100);
                    image.setOnClickListener(onClick(image, i, j));

                }
                table.addView(row);
            }
            firstRun = false;
        }

        Button buttonFire = (Button) findViewById(R.id.button_fire);
        buttonFire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //send selectedSquare to other phone (via FileTransferService?)
                //get response
                //display message
                //update enemyWaters
                //show updated array

                Intent intent = new Intent(PlayYourTurnActivity.this, PlayTheirTurnActivity.class);
                startActivity(intent);
            }
        });
    }

/*
********************************************************************************
*** onClick
*** Group 5
********************************************************************************
*** Purpose:
*** Inputs:
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/23/15
********************************************************************************
*/
View.OnClickListener onClick(final ImageView im, final int row, final int col) {
    return new View.OnClickListener() {
        public void onClick(View v) {
            selectedSquare = (ImageView) v;
            selectedShip = enemyWaters[row][col];
        }
    };
};



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
    public void processAttack (int attackCoordinates []) //probably better to use either Ship or ImageView
    {
        // SEND attackCoordinates TO OPPONENT
        // PLACEHOLDER VALUE FOR ATTACK RESULTS FROM OPPONENT
        Boolean attackResults [] = new Boolean [] {true, false};

        // Check on result of attack, alter enemyWaters if successful
        if (attackResults [0] == true)
        {

        }
    }

/*
********************************************************************************
*** getVictory
*** Group 5
********************************************************************************
*** Purpose:
*** Returns victory variable, tracks who won the game
*** Inputs:
*** n/a
*** Outputs:
*** Boolean victory
********************************************************************************
*** Date
*** 11/25/15
********************************************************************************
*/
    public void getVictory(){

        //get info from connection service
    }
}
