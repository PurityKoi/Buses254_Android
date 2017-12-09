package com.example.user.buses254;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import Common.Constants;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OneWayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OneWayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneWayFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OneWayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneWayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OneWayFragment newInstance(String param1, String param2) {
        OneWayFragment fragment = new OneWayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //  CODE FOR THE LAYOUT

    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter3;
    final Calendar myCalender = Calendar.getInstance();

    EditText etDateOfTravel;
    Spinner spinner;
    Spinner spinDepartFrom;
    Spinner spinTravelTime;
    Button checkAvail;

    String myFormat = "MM/dd/yy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    String departure, destination, time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_one_way, container, false);




        //DESTINATION LOCATIONS SPINNER

        spinner = (Spinner) view.findViewById(R.id.spinner);
        adapter= ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(getActivity(), parent.getItemAtPosition(position)+" As Destination", Toast.LENGTH_LONG).show();
                 destination = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //DEPART FROM LOCATIONS SPINNER

        spinDepartFrom = (Spinner) view.findViewById(R.id.spinDepartFrom);
        adapter3 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.DepartLocations, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDepartFrom.setAdapter(adapter3);
        spinDepartFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // .makeText(getActivity(), parent.getItemAtPosition(position)+" As Departure ", Toast.LENGTH_LONG).show();
                departure = spinDepartFrom.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // TRAVEL TIME SPINNER
        spinTravelTime = (Spinner) view.findViewById(R.id.spinTravelTime);
        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.TravelTime, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTravelTime.setAdapter(adapter2);
        spinTravelTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(getActivity(), parent.getItemAtPosition(position)+" Selected Travel Time", Toast.LENGTH_LONG).show();
                time = spinTravelTime.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //CREATING DATEPICKER DIALOG

        etDateOfTravel= (EditText) view.findViewById(R.id.etDateOfTravel);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR , year);
                myCalender.set(Calendar.MONTH , month);
                myCalender.set(Calendar.DAY_OF_MONTH , dayOfMonth);
                etDateOfTravel.setText(sdf.format(myCalender.getTime()));
            }
        };

        etDateOfTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_DeviceDefault_Dialog,
                        date,
                        myCalender.get(Calendar.YEAR),
                        myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        checkAvail= (Button) view.findViewById(R.id.btCheckAvail);
        //ONCLICK LISTENER FOR RETURN TRIP BUTTON
        checkAvail.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCheckAvail:
                final String url = "http://10.0.3.2/android_php/Oneway%20Trip.php";

                //instantiate the Request Queue
                RequestQueue queue = Volley.newRequestQueue(getActivity());


                // Request a string from the provided url

                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("Sorry")) {
                            Toast.makeText(getActivity(), "Sorry, no available buses. Please Try Different Selection!", Toast.LENGTH_LONG).show();

                        } else {
                            try {
                                JSONArray schedule = new JSONArray(response);

                                Constants.shared().setEndpoint(schedule);
                                Intent in = new Intent(getActivity(), oneWaySchedule.class);
                                startActivity(in);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error instanceof TimeoutError){
                            Toast.makeText(getActivity(), "Time Out Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof NoConnectionError){
                            Toast.makeText(getActivity(), "No Connection Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof AuthFailureError){
                            Toast.makeText(getActivity(), "Authentication Failure Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof NetworkError){
                            Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof ServerError){
                            Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof ParseError){
                            Toast.makeText(getActivity(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String ,String>();
                        params.put("departure",departure );
                        params.put("destination",destination );
                        params.put("date", etDateOfTravel.getText().toString() );
                        params.put("time",time );

                        return params;

                    }
                };

                queue.add(stringRequest1);
                break;

        }
    }

}


