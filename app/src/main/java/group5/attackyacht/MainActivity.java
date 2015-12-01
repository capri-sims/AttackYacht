/*
********************************************************************************
*** MainActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** Main navigation for player that appears on start up
********************************************************************************
*** Date:
*** 11/17/15
********************************************************************************
*** Change Log:
*** 11/17/15 - CS - Created onCreate
*** 11/xx/15 - xx -
***
********************************************************************************
*/

// Project Package
package group5.attackyacht;

// Imported libraries
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

/*
********************************************************************************
*** onCreate
*** Group 5
********************************************************************************
*** Purpose:
*** Displays the main menu and gives the player options to start a game or
*** alter their settings
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NOT WORKING
//        Intent music = new Intent(this, MusicService.class);
//        startService(music);

        //BUTTONS//
        Button buttonLocal = (Button) findViewById(R.id.button_local);
        buttonLocal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,P1SetupActivity.class);
                startActivity(intent);
            }
        });

        Button buttonWifi = (Button) findViewById(R.id.button_wifi);
        buttonWifi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WifiActivity.class);
                startActivity(intent);
            }
        });

        Button buttonOptions = (Button) findViewById(R.id.button_menu_options);
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(intent);
            }
        });
        //////
        Button button_local = (Button) findViewById(R.id.button_local);
        button_local.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, P1SetupActivity.class);
                startActivity(intent);
            }
        });

    }
}
