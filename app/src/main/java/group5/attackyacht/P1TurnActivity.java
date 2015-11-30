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
import android.widget.Toast;

public class P1TurnActivity extends AppCompatActivity
{
    // Class-wide variables

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

        if(firstRun){
            TableLayout table = (TableLayout) findViewById(R.id.enemyWaters);
            for(int i = 0; i < ROW; i++){ //Initialize enemy waters //will this rewrite everytime???
                TableRow row = new TableRow(P1TurnActivity.this);
                for(int j = 0; j < COL; j++){

                    ImageView image = new ImageView (this);
                    enemyWaters[i][j] = new Ship("water", i, j);
                    image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ship_water));
                    row.addView(image, 100, 100);
                    image.setOnClickListener(onClick(image, i, j));

                }
                table.addView(row);
            }
            firstRun = false;
        }

        updateGrid();

        Button buttonFire = (Button) findViewById(R.id.button_fire);
        buttonFire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // TODO: onClick; The following comments on implementation
                //send selectedSquare to other phone (via FileTransferService?)
                //get response
                //display message
                //update enemyWaters
                //show updated array
                
                // processAttack should handle sending data to the other phone, getting the
                // response, displaying the message, and updating enemyWaters, but it'd like the
                // row / col data from selectedSquare to do so

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
            selectedSquare = (ImageView) v;
            selectedShip = enemyWaters[row][col];
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
    public void processAttack (int attackCoordinates []) //probably better to use either Ship or ImageView
    {
        // TODO: processAttack; SEND attackCoordinates[] TO OPPONENT
        // TODO: processAttack; RECEIVE attackResults[] FROM OPPONENT
        // TODO: processAttack; OVERWRITE PLACEHOLDER W/ attackResults[] FROM OPPONENT
        Boolean attackResults[] = new Boolean[]{true, false};

        // Check on result of attack, alter enemyWaters if successful
        if (attackResults[0] == true) {
            Ship.hit();

            // Display attack was successful
            displayMessage ("We sunk an enemy ship!");
        } else {
            // Display attack was unsuccessful
            displayMessage ("Oh no! Our attack missed!");
        }

        updateGrid();

        // Check if game is over, call gameOverActivity if it is
        if (attackResults[1] == true) {
            // Set player as winner
            GameOverActivity.setVictory(true);

            //get info from connection service
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
}
