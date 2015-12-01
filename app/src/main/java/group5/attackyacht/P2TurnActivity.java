/*
********************************************************************************
*** P2TurnActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** Handles game play elements that occurs on the player's turn such as
*** interactions b/t players (attacks), tracking the status of the game, and
*** informing players of the status of the game.
********************************************************************************
*** Date:
*** 11/28/15
********************************************************************************
*** Change Log:
*** 11/23/15 - CS - Class created and laid out
*** 11/23/15 - CS - Created onCreate
*** 11/23/15 - CS - Created displayMessage
*** 11/24/15 - ZC - Formatted page for comments & style
*** 11/24/15 - ZC - Created processAttack
*** 11/27/15 - CS - Created onClick
*** 11/27/15 - CS - Created updateGrid
*** 11/28/15 - #5 - Retooled for local play, all previous changes carried over
***                 from PlayTheirTurnActivity
*** 11/28/15 - CS - Created isItOver
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

public class P2TurnActivity extends AppCompatActivity
{
    // Class-wide variables
    static private int ROW = 7;
    static private int COL = 12;
    static private Ship[][] enemyWaters = P1SetupActivity.getFriendlyWaters();
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
        setContentView(R.layout.activity_p2_turn);

        // Creates the 2D array
        TableLayout table = (TableLayout) findViewById(R.id.watersP2);
        for(int i = 0; i < ROW; i++){ //Initialize enemy waters
            TableRow row = new TableRow(P2TurnActivity.this);
            for(int j = 0; j < COL; j++){
                ImageView image = new ImageView (this);
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

        // Button to accept grid choice and attack
        Button buttonFire = (Button) findViewById(R.id.button_fireP2);
        buttonFire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                processAttack(fireRow, fireCol);
                Intent intent = new Intent(P2TurnActivity.this, P1TurnActivity.class);
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
*** Gets location on grid when clicked and gives feedback to user
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

                // Do not increment fireCol / fireRow directly
                int fc = fireCol;// + 1;
                int fr = fireRow;// + 1;

                // Give user feedback of grid selection
                String text = "Firing at " + fc + ", " + fr;
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
*** Displays a message on screen
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
        // Get context and set duration of message
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        // Actually display the message
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
*** Takes attack selection from user, checks if attack was successful, and if
*** the game has ended
*** Inputs:
*** int posRow, int posCol
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/24/15
********************************************************************************
*/
    public void processAttack (int posRow,int posCol)
    {
        /**
         * WiFi connection would happen here. The player would send their attack coordinates to
         * their opponent, and their game's processAttack would check for hits / game ending and
         * that information would be returned to the player.
         */

        // The attack is checked against the enemyWaters ship layout
        if(((enemyWaters[posRow][posCol]).getType()).equals("water")  || ((enemyWaters[posRow][posCol]).getType()).equals("destroyed"))
        {
            displayMessage("MISS");
        }
        else{
            displayMessage("HIT");
            (enemyWaters[posRow][posCol]).hit();
        }

        // updateGrid ();

        // Handles end of game
        if(isItOver()){
            GameOverActivity.setWinner("Player 2");

            // TODO: isItOver; GameOverActivity does not begin
            // Game does not end when all ships are destroyed, but the code manages
            // to get to this point
            Intent intent = new Intent(P2TurnActivity.this, GameOverActivity.class);
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

//        Intent intent = getIntent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        finish();
//        startActivity(intent);
    }

/*
********************************************************************************
*** isItOver
*** Group 5
********************************************************************************
*** Purpose:
*** Traverses enemyWaters to check for remaining ships, and signals for the
*** game to end accordingly
*** Inputs:
*** n/a
*** Outputs:
*** Boolean false, Boolean true
********************************************************************************
*** Date
*** 11/28/15
********************************************************************************
*/
    private boolean isItOver(){

        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(!(((enemyWaters[i][j]).getType()).equals("water") || !((enemyWaters[i][j]).getType()).equals("destroyed"))){
                    return false;
                }
            }

        }
        return true;
    }
}
