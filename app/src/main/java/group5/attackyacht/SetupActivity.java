package group5.attackyacht;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class SetupActivity extends AppCompatActivity {

    static private int ROW = 7;
    static private int COL = 12;
    static private Ship[][] friendlyWaters = new Ship[ROW][COL];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        //TODO: Change table to 7 * 12 ??? for better formatting on a phone
        TableLayout table = (TableLayout) findViewById(R.id.shipSetupGrid);
        for (int i = 0; i < ROW; i++){
            TableRow row = new TableRow(this);
            for (int j = 0; j < COL; j++){
                ImageView image = new ImageView (this);
                friendlyWaters[i][j] = new Ship("water", i, j);
                image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.water));
                //image.setBackgroundColor(Color.BLUE);
                //image.setPadding(0, 0, 0, 0); //padding in each image if needed
                image.setId(i + j);
                //add here on click event etc for each image...
                image.setOnClickListener(onClick(image, i, j));
                //...
                row.addView(image, 100, 100);
            }
            table.addView(row);
        }

        Button buttonReady = (Button) findViewById(R.id.button_ready);
        buttonReady.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //determine who goes first
                //from... bluetooth?
                //if you go first - gameplay1Activity
                //else - gameplay2Activity

                Intent intent = new Intent(SetupActivity.this, PlayTheirTurnActivity.class);
                startActivity(intent);
            }
        });

    }

    View.OnClickListener onClick(final ImageView im, final int row, final int col)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                //ColorDrawable drawable = (ColorDrawable) v.getBackground();
                //int row = Integer.parseInt((String.valueOf(v.getId())).substring(0, 1)); //i
                //int col = Integer.parseInt((String.valueOf(v.getId())).substring(1)); //j


                //ImageView im = (ImageView) findViewById(v.getId());
                ImageView im = (ImageView) v;

                String type = (friendlyWaters[row][col]).getType();

                if(type.equalsIgnoreCase("water")){ //problem line? no
                    //if (drawable.getColor() == Color.BLUE) {
                    //v.setBackgroundColor(Color.GRAY); //TODO: change to images...
                    im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.ship_one)); //??? NOT WORKING
                    //friendlyWaters[row][col].setType("ship_one");

                } else {
                    //v.setBackgroundColor(Color.BLUE);
                    im.setImageDrawable(ContextCompat.getDrawable(SetupActivity.this, R.drawable.water)); //???
                    //friendlyWaters[row][col].setType("water");
                }
            }
        };
    };

    static public Ship[][] getFriendlyWaters(){
        return friendlyWaters;
    }
}
