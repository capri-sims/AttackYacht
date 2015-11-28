package group5.attackyacht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // button to return to main menu.
        Button buttonMenu = (Button)findViewById(R.id.button_menu);
        buttonMenu.setOnClickListener((v) -> {
            Intent intent = new Intent(GameOverActivity.this,MainActivity.class);
            startActivity(intent);
        });

        // button to play again
        Button buttonAgain = (Button) findViewById(R.id.button_again);
        buttonAgain.setOnClickListener((v) -> {
            Intent intent = new Intent(GameOverActivity.this,SetupActivity.class);
            startActivity(intent);
        });

        // button to exit
        Button buttonExit = (Button)findViewById(R.id.button_exit);
        buttonExit.setOnClickListener((v)-> {
            @Override
                    public void onClick(View v){
                    finish();
                    System.exit(0);
            }
        });


    }
}

