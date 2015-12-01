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
*** 11/27/15 - CS - Created onClick
*** 11/27/15 - CS - Created updateGrid
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
import android.widget.TextView;
import android.widget.Toast;

public class P1TurnActivity extends AppCompatActivity
{
    // Class-wide variables
    static private int ROW = 7;
    static private int COL = 12;
    static private Ship[][] enemyWaters = P2SetupActivity.getFriendlyWaters();
    private int fireCol= 0;
    private int fireRow = 0;


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
        setContentView(R.layout.activity_p1_turn);

        //creates the 2D array
        TableLayout table = (TableLayout) findViewById(R.id.watersP1);
        for(int i = 0; i < ROW; i++) { //Initialize enemy waters
            TableRow row = new TableRow(P1TurnActivity.this);
            for (int j = 0; j < COL; j++) {
                ImageView image = new ImageView(this);
                //------FOR DEBUGGING-----//
//                int imageID;
//                imageID = getResources().getIdentifier(("ship_" + (enemyWaters[i][j]).getType()), "drawable", getPackageName());
//                image.setImageDrawable(ContextCompat.getDrawable(this, imageID));
                image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ship_water));
                row.addView(image, 100, 100);
                image.setOnClickListener(onClick(image, i, j));
            }
            table.addView(row);
        }

        //updateGrid();

        Button buttonFire = (Button) findViewById(R.id.button_fireP1);
        buttonFire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                processAttack(fireRow, fireCol);
                Intent intent = new Intent(P1TurnActivity.this, P2TurnActivity.class);
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
*** Displays the game screen, initializes enemyWaters, and continues game loop
*** Inputs:
*** final ImageView im, final int row, final int col
*** Outputs:
*** View.OnClickListener
********************************************************************************
*** Date
*** 11/27/15
********************************************************************************
*/
View.OnClickListener onClick(final ImageView im, final int row, final int col) {
    return new View.OnClickListener() {
        public void onClick(View v) {
            fireRow = row;
            fireCol = col;
            TextView firePos = (TextView) findViewById(R.id.firePositionP1);
            int fc = fireCol;// + 1; //Needed to avoid incrementing fireRow & Col
            int fr = fireRow;// + 1;
            String text = "Firing at " + fc + ", " + fr; //Needed to avoid crashes
            firePos.setText(text);
        }
    };
}


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
*** Change Log:
*** 11/28/15 - CS - Added the messages
*** 11/28/15 - CS - updateGrid() added
********************************************************************************
*/
    public void processAttack (int posCol, int posRow)
    {
        // TODO: processAttack; SEND attackCoordinates[] TO OPPONENT
        // TODO: processAttack; RECEIVE attackResults[] FROM OPPONENT
        // TODO: processAttack; OVERWRITE PLACEHOLDER W/ attackResults[] FROM OPPONENT
        //Boolean attackResults[] = new Boolean[]{true, false};

        //not saying the miss output //it was backwards
        //TODO: ERROR IS HERE - ARRAY OUT OF BOUNDS
        if(((enemyWaters[posRow][posCol]).getType()).equals("water")  || ((enemyWaters[posRow][posCol]).getType()).equals("destroyed"))
        {
            displayMessage("MISS");
        }
        else{
            displayMessage("HIT");
            (enemyWaters[posRow][posCol]).hit();
        }


        // Check on result of attack, alter enemyWaters if successful
//        if (attackResults[0] == true) {
//            //Ship.hit();
//
//            // Display attack was successful
//            displayMessage ("We sunk an enemy ship!");
//        } else {
//            // Display attack was unsuccessful
//            displayMessage ("Oh no! Our attack missed!");
//        }

        //updateGrid();

        // Check if game is over, call gameOverActivity if it is
//        if (attackResults[1] == true) {
//            // Set player as winner
//            GameOverActivity.setVictory(true);
//
//            //get info from connection service
//        }
        if(isItOver()){
            GameOverActivity.setWinner("Player 1");
            Intent intent = new Intent(P1TurnActivity.this, GameOverActivity.class);
            startActivity(intent);
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
*** 11/27/15
********************************************************************************
*/
    private void updateGrid(){ //will this override the onClickListeners???

//        TableLayout table = (TableLayout) findViewById(R.id.enemyWaters);
//        for (int i = 0; i < ROW; i++){
//            TableRow row = new TableRow(this);
//            for (int j = 0; j < COL; j++){
//                ImageView image = new ImageView (this);
//                int imageID = getResources().getIdentifier(("ship_" + (enemyWaters[i][j]).getType()), "drawable", getPackageName());
//                image.setImageDrawable(ContextCompat.getDrawable(this, imageID));
//                image.setOnClickListener(onClick(image, i, j));
//                row.addView(image, 100, 100);
//            }
//            table.addView(row);
//        }

        Intent intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }

    private boolean isItOver(){

        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(!(((enemyWaters[i][j]).getType()).equals("water") || ((enemyWaters[i][j]).getType()).equals("destroyed"))){
                    return false;
                }
            }

        }
        return true;
    }


}
