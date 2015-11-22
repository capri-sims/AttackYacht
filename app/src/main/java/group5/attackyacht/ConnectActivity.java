package group5.attackyacht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        //start bluetooth
        //get devices
        //populate listview


        //BUTTONS//
        Button buttonConnect = (Button) findViewById(R.id.button_connect);
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //give bluetooth the selected device
                //if successful

                    Intent intent = new Intent(ConnectActivity.this, SetupActivity.class);
                    startActivity(intent);
                //else... try again
            }
        });
    }

    public void onClickConnect(View v){


    }
}
