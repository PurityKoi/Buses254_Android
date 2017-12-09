package com.example.user.buses254;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;

/**
 * Created by USER on 9/25/2017.
 */

public class BusScheduleOneWay extends AppCompatActivity {

    JSONArray jsonArray;

    public BusScheduleOneWay(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_schedule_one_way);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.busSchedOWRecyclerV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

    }




}
