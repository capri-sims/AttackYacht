package group5.attackyacht;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class FriendlyWatersActivity extends AppCompatActivity {

    static private int ROW = 7;
    static private int COL = 12;
    static private Ship[][] friendlyWaters;
    static private boolean p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendly_waters);

        if(p1){
            friendlyWaters = P2TurnActivity.getEnemyWaters();
        }
        else{
            friendlyWaters = P1TurnActivity.getEnemyWaters();
        }

        TableLayout table = (TableLayout) findViewById(R.id.watersF);
        for(int i = 0; i < ROW; i++) { //Initialize friendly waters
            TableRow row = new TableRow(FriendlyWatersActivity.this);
            for (int j = 0; j < COL; j++) {
                ImageView image = new ImageView(this);
                //------FOR DEBUGGING-----//
                int imageID;
                imageID = getResources().getIdentifier(("ship_" + (friendlyWaters[i][j]).getType()), "drawable", getPackageName());
                image.setImageDrawable(ContextCompat.getDrawable(this, imageID));
                //image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ship_water));
                row.addView(image, 100, 100);
            }
            table.addView(row);
        }

        Button buttonReturn = (Button) findViewById(R.id.button_return);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent;
                if(p1){
                    intent = new Intent(FriendlyWatersActivity.this, P1TurnActivity.class);
                }
                else {
                    intent = new Intent(FriendlyWatersActivity.this, P2TurnActivity.class);
                }
                startActivity(intent);
            }});
    }

    public static void setP1(boolean p){
        p1 = p;
    }
}
