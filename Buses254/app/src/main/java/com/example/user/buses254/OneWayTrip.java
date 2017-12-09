package com.example.user.buses254;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OneWayTrip extends AppCompatActivity implements View.OnClickListener
{

    Button ReturnTrip ;
    EditText  etDateOfTravel;
    Spinner spinner;
    Spinner spinTravelTime;
    Spinner spinDepartFrom;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter3;

    final Calendar myCalender = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_one_way_trip);

        //DESTINATION LOCATIONS SPINNER
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter= ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" As Destination", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //DEPART FROM LOCATIONS SPINNER

        spinDepartFrom = (Spinner) findViewById(R.id.spinDepartFrom);
        adapter3 = ArrayAdapter.createFromResource(this, R.array.DepartLocations, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDepartFrom.setAdapter(adapter3);
        spinDepartFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" As Departure ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // TRAVEL TIME SPINNER
        spinTravelTime = (Spinner) findViewById(R.id.spinTravelTime);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.TravelTime, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTravelTime.setAdapter(adapter2);
        spinTravelTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" Selected Travel Time", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //CREATING DATEPICKER DIALOG

        etDateOfTravel= (EditText) findViewById(R.id.etDateOfTravel);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR , year);
                myCalender.set(Calendar.MONTH , month);
                myCalender.set(Calendar.DAY_OF_MONTH , dayOfMonth);
                updateLabel();
            }
        };

        etDateOfTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DatePickerDialog dialog = new DatePickerDialog(
                     OneWayTrip.this,
                     android.R.style.Theme_DeviceDefault_Dialog,
                     date,
                     myCalender.get(Calendar.YEAR),
                     myCalender.get(Calendar.MONTH),
                     myCalender.get(Calendar.DAY_OF_MONTH));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        ReturnTrip = (Button) findViewById(R.id.ReturnTrip);
        //ONCLICK LISTENER FOR RETURN TRIP BUTTON
        ReturnTrip.setOnClickListener(this);

    }
    private void updateLabel ()
    {
        String myFormat = "MM/dd/yy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDateOfTravel.setText(sdf.format(myCalender.getTime()));
    }

    //IMPLEMENTATION OF ONCLICK LISTENER FOR RETURN TRIP BUTTON

    @Override
    public void onClick(View v) {
        Intent IntentRT = new Intent(OneWayTrip.this, ReturnTrip.class) ;
        startActivity(IntentRT);
    }

}
