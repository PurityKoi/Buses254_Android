package com.example.user.buses254;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.OneWayScheduleAdapter;
import Model.oneWayScheduleModel;

import static Common.Constants.endpoint;

/**
 * Created by USER on 10/31/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class oneWaySchedule extends AppCompatActivity {


    RecyclerView busSchedOWRecyclerV;
    List<oneWayScheduleModel> scheduleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_schedule_one_way);


        busSchedOWRecyclerV = (RecyclerView) findViewById(R.id.busSchedOWRecyclerV);
        busSchedOWRecyclerV.setLayoutManager(new LinearLayoutManager(this));
        OneWayScheduleAdapter adapter = new OneWayScheduleAdapter(this, scheduleList);
        busSchedOWRecyclerV.setAdapter(adapter);



        for (int i = 0; i < endpoint.length(); i++) {

            try {

                JSONObject object = endpoint.getJSONObject(i);
                oneWayScheduleModel schedule_data = new oneWayScheduleModel(object.getInt("bus_id"), object.getString("county_of_departure"),
                        object.getString("county_of_arrival"), object.getString("date_of_travel"), object.getString("time_of_travel"), object.getInt("travel_cost"));

                scheduleList.add(schedule_data);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}

