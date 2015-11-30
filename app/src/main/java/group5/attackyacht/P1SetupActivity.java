/*
********************************************************************************
*** SetupActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** Players choose where they wish to place their ships pre-game, which then
*** carries over to PlayYourTurnActivity & PlayTheirTurnActivity
********************************************************************************
*** Date:
*** 11/19/15
********************************************************************************
*** Change Log:
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

public class P1SetupActivity extends AppCompatActivity {

    static private int ROW = 7;
    static private int COL = 12;
    static private Ship[][] friendlyWaters = new Ship[ROW][COL];
    //private ConnectActivity connection = new ConnectActivity();


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
        setContentView(R.layout.activity_p1_setup);

        TableLayout table = (TableLayout) findViewById(R.id.shipSetupGridP1);
        for (int i = 0; i < ROW; i++){
            TableRow row = new TableRow(this);
            for (int j = 0; j < COL; j++){
                ImageView image = new ImageView (this);
                friendlyWaters[i][j] = new Ship("water", i, j);
                image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ship_water));
                //image.setBackgroundColor(Color.BLUE);
                //image.setId(i + j);
                row.addView(image, 100, 100);
                image.setOnClickListener(onClick(image, i, j));
            }
            table.addView(row);
        }

        Button buttonReady = (Button) findViewById(R.id.button_readyP1);
        buttonReady.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Determine who goes first
//                boolean firstTurn = determineFirst();
//                Intent goToYourTurn = new Intent(P1SetupActivity.this, P1TurnActivity.class);
//                Intent goToTheirTurn = new Intent(P1SetupActivity.this, P2TurnActivity.class);
//
//                if (firstTurn)
//                {
//                    startActivity (goToYourTurn);
//                }
//                else
//                {
//                    startActivity (goToTheirTurn);
//                }


                //TODO: Show dialog box for next player
                Intent intent = new Intent(P1SetupActivity.this, P2SetupActivity.class);
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
***
*** Inputs:
*** final ImageView im, final int row, final int col
*** Outputs:
*** View.OnClickListener
********************************************************************************
*** Date
*** 11/23/15
********************************************************************************
*/
    View.OnClickListener onClick(final ImageView im, final int row, final int col)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                //ColorDrawable drawable = (ColorDrawable) v.getBackground();

                ImageView im = (ImageView) v;


                String type = (friendlyWaters[row][col]).getType();
                String left, right, above, below;

                if(type.equalsIgnoreCase("water")){
                    //if (drawable.getColor() == Color.BLUE) {
                    //v.setBackgroundColor(Color.GRAY);

                    if(row != 0){ below = (friendlyWaters[row - 1][col]).getType(); }
                    else{ below = "water"; }

                    if(row != ROW-1){ above = (friendlyWaters[row + 1][col]).getType(); }
                    else{ above = "water"; }

                    if(col != 0){ left = (friendlyWaters[row][col - 1]).getType(); }
                    else{ left = "water"; }

                    if(col != COL-1){ right = (friendlyWaters[row][col + 1]).getType(); }
                    else{ right = "water"; }

                    boolean b = false, a = false, l = false, r = false;

                    if(!(below.equalsIgnoreCase("water"))){
                        (friendlyWaters[row-1][col]).setType("top");
                        (friendlyWaters[row][col]).setType("bottom");
                        b = true;
                        im.setImageDrawable(ContextCompat.getDrawable(P1SetupActivity.this, R.drawable.ship_bottom));
                        displayMessage("Below!");
                    }
                    if(!(above.equalsIgnoreCase("water"))){
                        (friendlyWaters[row+1][col]).setType("bottom");
                        (friendlyWaters[row][col]).setType("top");
                        a = true;
                        im.setImageDrawable(ContextCompat.getDrawable(P1SetupActivity.this, R.drawable.ship_top));
                        displayMessage("Above!");
                    }
                    if(!(left.equalsIgnoreCase("water"))){
                        (friendlyWaters[row][col-1]).setType("left");
                        (friendlyWaters[row][col]).setType("right");
                        l = true;
                        im.setImageDrawable(ContextCompat.getDrawable(P1SetupActivity.this, R.drawable.ship_right));
                        displayMessage("Left!");
                    }
                    if(!(right.equalsIgnoreCase("water"))){
                        (friendlyWaters[row][col+1]).setType("right");
                        (friendlyWaters[row][col]).setType("left");
                        r = true;
                        im.setImageDrawable(ContextCompat.getDrawable(P1SetupActivity.this, R.drawable.ship_left));
                        displayMessage("Right!");
                    }
                    if(b & a){
                        (friendlyWaters[row][col]).setType("middle_v");
                        im.setImageDrawable(ContextCompat.getDrawable(P1SetupActivity.this, R.drawable.ship_middle_v));
                    }
                    if(l & r){
                        (friendlyWaters[row][col]).setType("middle_h");
                        im.setImageDrawable(ContextCompat.getDrawable(P1SetupActivity.this, R.drawable.ship_middle_h));
                    }
                    if((!b) & (!a) & (!l) & (!r)){
                        (friendlyWaters[row][col]).setType("one");
                        im.setImageDrawable(ContextCompat.getDrawable(P1SetupActivity.this, R.drawable.ship_one));
                    }
                }
                else {
                    //v.setBackgroundColor(Color.BLUE);
                    (friendlyWaters[row][col]).setType("water");
                    im.setImageDrawable(ContextCompat.getDrawable(P1SetupActivity.this, R.drawable.ship_water));
                }
            }
        };
    }

/*
********************************************************************************
*** determineFirst - DON"T NEED ANYMORE
*** Group 5
********************************************************************************
*** Purpose:
*** Determines which of the two players will make the first move
*** Inputs:
*** n/a
*** Outputs:
*** Boolean firstTurn
********************************************************************************
*** Date
*** 11/27/15
********************************************************************************
*/
//    public Boolean determineFirst ()
//    {
//        // True, player goes first; False, opponent goes first
//        Boolean firstTurn = null;
//
//
//        // Generate an int between 0 & 5, compare w/ opponent, larger number goes
//        // first. Repeat on equal value.
//        Random randGenerator = new Random ();
//
//        // Loop until the firstTurn is determined
//        while (firstTurn == null)
//        {
//            displayMessage("In determineFirst");
//            int yourRandValue = 3;//randGenerator.nextInt(5);
//            //TODO: send yourRandValue
//            //connection.sendInfo(String.valueOf(yourRandValue));
//
//
//
//            // SEND / RECEIVE # TO / FROM OPPONENT
//            // PLACEHOLDER FOR VALUE TO BE RECEIVED
//            //TODO : get theirRandValue
//            int theirRandValue = 2;
//            //int theirRandValue = Integer.parseInt(connection.getInfo());
//
//            if (yourRandValue > theirRandValue)
//            {
//                firstTurn = true;
//            }
//            else if (yourRandValue < theirRandValue)
//            {
//                firstTurn = false;
//            }
//
//        }
//        //TODO: Needs to communicate with other phone (via fileTrasferServie?)
//        //connection.sendInfo(String.valueOf(firstTurn);
//
//        return firstTurn;
//
//
//    }

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
*** getFriendlyWaters
*** Group 5
********************************************************************************
*** Purpose:
*** Returns friendlyWaters
*** Inputs:
*** n/a
*** Outputs:
*** ship [][] friendlyWaters
********************************************************************************
*** Date
*** 11/23/15
********************************************************************************
*/
    static public Ship[][] getFriendlyWaters() {
        return friendlyWaters;
    }
}
