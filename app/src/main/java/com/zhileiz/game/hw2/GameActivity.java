package com.zhileiz.game.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

    TextView grid11;
    TextView grid12;
    TextView grid13;
    TextView grid14;
    TextView grid21;
    TextView grid22;
    TextView grid23;
    TextView grid24;
    TextView grid31;
    TextView grid32;
    TextView grid33;
    TextView grid34;
    TextView grid41;
    TextView grid42;
    TextView grid43;
    TextView grid44;
    TextView player1Turn;
    TextView player2Turn;

    String player1Name;
    String player2Name;
    GameState gameManager;

    List<TextView> grids;
    Map<Integer, Integer> viewDict;
    View.OnClickListener gridAction;
    int clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        grids = new ArrayList<TextView>();
        viewDict = new HashMap<Integer, Integer>();
        Intent intent = getIntent();
        player1Name = intent.getExtras().getString("PLAYER_1");
        player2Name = intent.getExtras().getString("PLAYER_2");
        player1Turn = (TextView) findViewById(R.id.player_1_turn);
        player2Turn = (TextView) findViewById(R.id.player_2_turn);
        gameManager = new GameState();
        setGridFunc();
        setGrids();
        addGrids();
        int i = 0;
        for (TextView v : grids) {
            v.setOnClickListener(gridAction);
            viewDict.put(v.getId(), i);
            i ++;
        }
    }


    private void setGrids() {
        grid11 = (TextView) findViewById(R.id.med_card_1);
        grid12= (TextView) findViewById(R.id.med_card_2);
        grid13 = (TextView) findViewById(R.id.med_card_3);
        grid14 = (TextView) findViewById(R.id.med_card_4);
        grid21 = (TextView) findViewById(R.id.med_card_5);
        grid22 = (TextView) findViewById(R.id.med_card_6);
        grid23 = (TextView) findViewById(R.id.med_card_7);
        grid24 = (TextView) findViewById(R.id.med_card_8);
        grid31 = (TextView) findViewById(R.id.med_card_9);
        grid32 = (TextView) findViewById(R.id.med_card_10);
        grid33 = (TextView) findViewById(R.id.med_card_11);
        grid34 = (TextView) findViewById(R.id.med_card_12);
        grid41 = (TextView) findViewById(R.id.med_card_13);
        grid42 = (TextView) findViewById(R.id.med_card_14);
        grid43 = (TextView) findViewById(R.id.med_card_15);
        grid44 = (TextView) findViewById(R.id.med_card_16);
    }

    private void addGrids() {
        grids.add(grid11);
        grids.add(grid12);
        grids.add(grid13);
        grids.add(grid14);
        grids.add(grid21);
        grids.add(grid22);
        grids.add(grid23);
        grids.add(grid24);
        grids.add(grid31);
        grids.add(grid32);
        grids.add(grid33);
        grids.add(grid34);
        grids.add(grid41);
        grids.add(grid42);
        grids.add(grid43);
        grids.add(grid44);
    }

    private void setGridFunc() {
        player2Turn.setText("Pause, " + player1Name + "'s turn");
        player2Turn.setText("Go " + player1Name);
        gridAction = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clock % 2 == 0) {
                    gameManager.setGrid(viewDict.get(view.getId()), 1);
                    view.setBackgroundColor(getResources().getColor(R.color.player_1_color));
                    player1Turn.setText("Pause, " + player2Name + "'s turn");
                    player2Turn.setText("Go " + player2Name);
                } else {
                    gameManager.setGrid(viewDict.get(view.getId()), 2);
                    view.setBackgroundColor(getResources().getColor(R.color.player_2_color));
                    player2Turn.setText("Pause, " + player1Name + "'s turn");
                    player1Turn.setText("Go " + player1Name);
                }
                handleResult(gameManager.getWinner());
                clock ++;
            }
        };
    }

    private void handleResult(int winner) {
        if (winner == 1) {
            this.setTitle(player1Name + " wins!");
            player2Turn.setText(player1Name + " wins!");
            player1Turn.setText(player1Name + " wins!");
            Toast.makeText(GameActivity.this, player1Name + " wins!", Toast.LENGTH_SHORT).show();
            for (TextView tv : grids) {
                tv.setBackgroundColor(getResources().getColor(R.color.player_1_color));
                tv.setOnClickListener(null);
            }
            DataService.getInstance().putValueForUser(player1Name);
            updateUI(true);
        } else if (winner == 2) {
            this.setTitle(player2Name + " wins!");
            player2Turn.setText(player2Name + " wins!");
            player1Turn.setText(player2Name + " wins!");
            Toast.makeText(GameActivity.this, player2Name + " wins!", Toast.LENGTH_SHORT).show();
            for (TextView tv : grids) {
                tv.setBackgroundColor(getResources().getColor(R.color.player_2_color));
                tv.setOnClickListener(null);
            }
            DataService.getInstance().putValueForUser(player2Name);
            updateUI(true);
        } else if (winner == 3) {
            this.setTitle("It's a draw");
            Toast.makeText(GameActivity.this, "DRAW", Toast.LENGTH_SHORT).show();
            for (TextView tv : grids) {
                tv.setBackgroundColor(getResources().getColor(R.color.grey));
                tv.setOnClickListener(null);
            }
            updateUI(false);
        }
    }

    private void updateUI(boolean won) {
        if (won) {
            Intent intent = new Intent(GameActivity.this, BoardActivity.class);
            this.finish();
            startActivity(intent);
        } else {
            this.finish();
        }
    }
}
