package com.zhileiz.game.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button toBoardButton;
    EditText player1Text;
    EditText player2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataService.getInstance().setActivity(this);

        goButton = (Button) findViewById(R.id.game_button);
        toBoardButton = (Button) findViewById(R.id.leader_button);
        player1Text = (EditText) findViewById(R.id.player_1_input);
        player2Text = (EditText) findViewById(R.id.player_2_input);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1 = player1Text.getText().toString();
                String player2 = player2Text.getText().toString();
                if (player1.equals("") || player2.equals("")) {

                } else {
                    Intent goIntent = new Intent(MainActivity.this, GameActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("PLAYER_1", player1);
                    extras.putString("PLAYER_2",player2);
                    goIntent.putExtras(extras);
                    startActivity(goIntent);
                }
            }
        });

        toBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaderIntent = new Intent(MainActivity.this, BoardActivity.class);
                startActivity(leaderIntent);
            }
        });
    }



}
