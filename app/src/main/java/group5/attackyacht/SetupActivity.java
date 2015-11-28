package group5.attackyacht;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import java.util.Random;

public class SetupActivity extends AppCompatActivity {

    static private int ROW = 7;
    static private int COL = 12;
    static private Ship[][] friendlyWaters = new Ship[ROW][COL];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        TableLayout table = (TableLayout) findViewById(R.id.shipSetupGrid);
        for (int i = 0; i < ROW; i++){
            TableRow row = new TableRow(this);
            for (int j = 0; j < COL; j++){
                ImageView image = new ImageView (this);
                friendlyWaters[i][j] = new Ship("water", i, j);
                image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.water));
                //image.setBackgroundColor(Color.BLUE);
                //image.setId(i + j);
                row.addView(image, 100, 100);
                image.setOnClickListener(onClick(image, i, j));
            }
            table.addView(row);
        }

        Button buttonReady = (Button) findViewById(R.id.button_ready);
        buttonReady.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //determine who goes first
                Boolean firstTurn = determineFirst ();
                Intent goToYourTurn = new Intent(SetupActivity.this, PlayYourTurnActivity.class);
                Intent goToTheirTurn = new Intent(SetupActivity.this, PlayTheirTurnActivity.class);

                if (firstTurn == true)
                {
                    startActivity(goToYourTurn);
                }
                else
                {
                    startActivity(goToTheirTurn);
                }
            }
        });
    }

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
                        friendlyWaters[row-1][col].setType("ship_top");
                        friendlyWaters[row][col].setType("ship_bottom");
                        b = true;
                        im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.ship_bottom));
                    }
                    if(!(above.equalsIgnoreCase("water"))){
                        friendlyWaters[row+1][col].setType("ship_bottom");
                        friendlyWaters[row][col].setType("ship_top");
                        a = true;
                        im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.ship_top));
                    }
                    if(!(left.equalsIgnoreCase("water"))){
                        friendlyWaters[row][col-1].setType("ship_left");
                        friendlyWaters[row][col].setType("ship_right");
                        l = true;
                        im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.ship_right));
                    }
                    if(!(right.equalsIgnoreCase("water"))){
                        friendlyWaters[row][col+1].setType("ship_right");
                        friendlyWaters[row][col].setType("ship_left");
                        r = true;
                        im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.ship_left));
                    }
                    if(b & a){
                        friendlyWaters[row][col].setType("ship_middle_v");
                        im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.ship_middle_v));
                    }
                    if(l & r){
                        friendlyWaters[row][col].setType("ship_middle_h");
                        im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.ship_middle_h));
                    }
                    if((!b) & (!a) & (!l) & (!r)){
                        im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.ship_one)); //NOT SHOWING
                        friendlyWaters[row][col].setType("ship_one");
                    }
                }
                else {
                    //v.setBackgroundColor(Color.BLUE);
                    im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.water)); //???
                    friendlyWaters[row][col].setType("water");

                }
            }
        };
    };

    static public Ship[][] getFriendlyWaters(){
        return friendlyWaters;
    }

/*
********************************************************************************
*** determineFirst
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
    public Boolean determineFirst ()
    {
        // True, player goes first; False, opponent goes first
        Boolean firstTurn = null;

        // Generate an int b/t 0 & 5, compare w/ opponent, larger number goes
        // first. Repeat on equal value.
        Random randGenerator = new Random ();

        // Loop until the firstTurn is determined
        while (firstTurn == null)
        {
            int yourRandValue = randGenerator.nextInt(5);

            // SEND / RECEIVE # TO / FROM OPPONENT
            // PLACEHOLDER FOR VALUE TO BE RECEIVED
            int theirRandValue = 2;

            if (yourRandValue > theirRandValue)
            {
                firstTurn = true;
            }
            else if (yourRandValue < theirRandValue)
            {
                firstTurn = false;
            }
        }

        return firstTurn;

        //TODO: Needs to communicate with other phone (via fileTrasferServie?)
    }
}
