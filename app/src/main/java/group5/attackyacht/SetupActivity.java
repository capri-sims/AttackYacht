package group5.attackyacht;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class SetupActivity extends AppCompatActivity {

    private Ship[][] friendlyWaters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

//        GridView gridView = (GridView) findViewById(R.id.setupGrid);
//        gridView.setAdapter(new ImageAdapter(this));

        TableLayout table = (TableLayout) findViewById(R.id.shipSetupGrid);
        for (int i = 0; i < 10; i++){
            TableRow row = new TableRow(this);
            for (int j = 0; j < 10; j++){
                ImageView image = new ImageView (this);
                image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.water));
                image.setPadding(0, 0, 0, 0); //padding in each image if needed
                //add here on click event etc for each image...
                //...
                row.addView(image, 100, 100);
            }
            table.addView(row);
        }

    }
}
