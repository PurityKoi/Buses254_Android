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

public class ReturnTrip extends AppCompatActivity  {
    Button btOneWayTrip ;
    EditText etDeptDate ;
    EditText etReturnDate ;
    Spinner spinRTDept ;
    Spinner spinRTDest ;
    Spinner spinRTDeptTime ;
    Spinner spinRTRetTime ;

    ArrayAdapter<CharSequence> adapter1;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter3;
    ArrayAdapter<CharSequence> adapter4;

    final Calendar myCalender = Calendar.getInstance();
    final Calendar myCalender2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_trip);

        //VALUE ASSIGNMENT OF THE FIELDS
        btOneWayTrip = (Button) findViewById(R.id.btOneWayTrip);
        etDeptDate = (EditText) findViewById(R.id.etDeptDate);
        etReturnDate = (EditText) findViewById(R.id.etReturnDate);
        spinRTDept = (Spinner) findViewById(R.id.spinRTDept);
        spinRTDest = (Spinner) findViewById(R.id.spinRTDest);
        spinRTDeptTime = (Spinner) findViewById(R.id.spinRTDeptTime);
        spinRTRetTime = (Spinner) findViewById(R.id.spinRTRetTime);

        //DEPARTURE LOCATIONS SPINNER
        adapter1= ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRTDept.setAdapter(adapter1);
        spinRTDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" As Departure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //DESTINATION LOCATIONS SPINNER
        adapter2= ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRTDest.setAdapter(adapter2);
        spinRTDest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" As Destination", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        // DEPARTURE TRAVEL TIME SPINNER
        adapter3 = ArrayAdapter.createFromResource(this, R.array.TravelTime, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRTDeptTime.setAdapter(adapter3);
        spinRTDeptTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" Selected Departure Time", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // DESTINATION TRAVEL TIME SPINNER
        adapter4 = ArrayAdapter.createFromResource(this, R.array.TravelTime, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRTRetTime.setAdapter(adapter4);
        spinRTRetTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" Selected Destination Time", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //CREATING DEPARTURE DATEPICKER DIALOG

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

        etDeptDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        ReturnTrip.this,
                        android.R.style.Theme_DeviceDefault_Dialog,
                        date,
                        myCalender.get(Calendar.YEAR),
                        myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //CREATING DESTINATION DATEPICKER DIALOG

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender2.set(Calendar.YEAR , year);
                myCalender2.set(Calendar.MONTH , month);
                myCalender2.set(Calendar.DAY_OF_MONTH , dayOfMonth);
                updateLabel2();
            }
        };

        etReturnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog2 = new DatePickerDialog(
                        ReturnTrip.this,
                        android.R.style.Theme_DeviceDefault_Dialog,
                        date2,
                        myCalender2.get(Calendar.YEAR),
                        myCalender2.get(Calendar.MONTH),
                        myCalender2.get(Calendar.DAY_OF_MONTH));
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog2.show();
            }
        });

        //ONCLICK LISTENER FOR ONW WAY TRIP BUTTON

        btOneWayTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntentOW = new Intent(ReturnTrip.this, OneWayTrip.class);
                startActivity(IntentOW);
            }
        });
    }

    private void updateLabel ()
    {
        String myFormat = "MM/dd/yy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDeptDate.setText(sdf.format(myCalender.getTime()));
    }

    private void updateLabel2 ()
    {
        String myFormat = "MM/dd/yy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etReturnDate.setText(sdf.format(myCalender2.getTime()));
    }
    }
