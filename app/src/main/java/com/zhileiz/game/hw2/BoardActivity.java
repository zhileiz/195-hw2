package com.zhileiz.game.hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BoardActivity extends AppCompatActivity {

    ListView list;
    Button button;
    String[] web = {
            "Java",
            "C++",
            "C#",
            "HTML",
            "CSS"
    } ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        LeaderBoardAdapter listAdapter = new
                LeaderBoardAdapter(BoardActivity.this, web);
        list = (ListView) findViewById(R.id.board);
        list.setAdapter(listAdapter);
    }
}
