package com.zhileiz.game.hw2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhileizheng on 2/25/18.
 */

public class DataService {

    Map<String, Integer> cachedMap;
    Activity activity;


    private static DataService single_instance = null;

    private DataService() {

    }

    public static DataService getInstance()
    {
        if (single_instance == null)
            single_instance = new DataService();
        return single_instance;
    }

    public void setActivity(Activity context) {
        activity = context;
        getLeaders();
    }

    public String[] getLeaders() {
        if (cachedMap == null) {
            cachedMap = new LinkedHashMap<String, Integer>();
            SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
            Map<String, ?> data = sharedPref.getAll();
            for (Map.Entry<String, ?> entry : data.entrySet()) {
                Integer i = (Integer) entry.getValue();
                cachedMap.put(entry.getKey(), i);
            }
        }
        cachedMap = sortByValue(cachedMap);
        return parseInputToArray(cachedMap.entrySet().iterator());
    }

    private String[] parseInputToArray(Iterator<Map.Entry<String, Integer>> mapIter) {
        ArrayList<String> contentList = new ArrayList<String>();
        while (mapIter.hasNext()) {
            Map.Entry<String, Integer> entry = mapIter.next();
            contentList.add(entry.getKey() + " : " + entry.getValue() + " wins");
        }
        return contentList.toArray(new String[0]);
    }

    public void putValueForUser(String userName) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (cachedMap.containsKey(userName)) {
            cachedMap.put(userName, cachedMap.get(userName) + 1);
            editor.putInt(userName, sharedPref.getInt(userName,0));
        } else {
            cachedMap.put(userName, 1);
            editor.putInt(userName, 1);
        }
        editor.commit();
    }

    private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
