package com.zhileiz.game.hw2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LeaderBoardAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private String[] web;

    public LeaderBoardAdapter(Activity context,
                      String[] web) {
        super(context, R.layout.leader_board_adapter, web);
        this.context = context;
        this.web = web;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        web = DataService.getInstance().getLeaders();
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.leader_board_adapter, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.username);
        txtTitle.setText(web[position]);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 150);
        rowView.setLayoutParams(params);
        return rowView;
    }

    @Override
    public int getCount() {
        return DataService.getInstance().getLeaders().length;
    }
}