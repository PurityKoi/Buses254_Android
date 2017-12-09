package com.example.user.buses254;

import android.app.DatePickerDialog;
import android.content.Context;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReturnTripFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReturnTripFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReturnTripFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReturnTripFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReturnTripFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReturnTripFragment newInstance(String param1, String param2) {
        ReturnTripFragment fragment = new ReturnTripFragment();
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

    //CODE FOR THE LAYOUT

    ArrayAdapter<CharSequence> adapter1;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter3;
    ArrayAdapter<CharSequence> adapter4;

    final Calendar myCalender = Calendar.getInstance();
    final Calendar myCalender2 = Calendar.getInstance();

    String myFormat = "MM/dd/yy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    //VALUE ASSIGNMENT OF THE FIELDS
    Button  checkAvail ;
     EditText etDeptDate ;
    EditText etReturnDate ;
    Spinner spinRTDept ;
    Spinner spinRTDest ;
    Spinner spinRTDeptTime ;
    Spinner spinRTRetTime ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_return_trip, container, false);

        checkAvail = (Button) view.findViewById(R.id.btCheckAvail2);
        etDeptDate = (EditText) view.findViewById(R.id.etDeptDate);
        etReturnDate = (EditText) view.findViewById(R.id.etReturnDate);
        spinRTDept = (Spinner) view.findViewById(R.id.spinRTDept);
        spinRTDest = (Spinner) view.findViewById(R.id.spinRTDest);
        spinRTDeptTime = (Spinner) view.findViewById(R.id.spinRTDeptTime);
        spinRTRetTime = (Spinner) view.findViewById(R.id.spinRTRetTime);

        //DEPARTURE LOCATIONS SPINNER
        adapter1= ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.locations, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRTDept.setAdapter(adapter1);
        spinRTDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), parent.getItemAtPosition(position)+" As Departure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //DESTINATION LOCATIONS SPINNER
        adapter2= ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.locations, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRTDest.setAdapter(adapter2);
        spinRTDest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), parent.getItemAtPosition(position)+" As Destination", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        // DEPARTURE TRAVEL TIME SPINNER
        adapter3 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.TravelTime, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRTDeptTime.setAdapter(adapter3);
        spinRTDeptTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), parent.getItemAtPosition(position)+" Selected Departure Time", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // DESTINATION TRAVEL TIME SPINNER
        adapter4 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.TravelTime, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRTRetTime.setAdapter(adapter4);
        spinRTRetTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), parent.getItemAtPosition(position)+" Selected Destination Time", Toast.LENGTH_LONG).show();
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
                etDeptDate.setText(sdf.format(myCalender.getTime()));
            }
        };

        etDeptDate.setOnClickListener(new View.OnClickListener() {
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

        //CREATING DESTINATION DATEPICKER DIALOG

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender2.set(Calendar.YEAR , year);
                myCalender2.set(Calendar.MONTH , month);
                myCalender2.set(Calendar.DAY_OF_MONTH , dayOfMonth);
                etReturnDate.setText(sdf.format(myCalender2.getTime()));
            }
        };

        etReturnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog2 = new DatePickerDialog(
                        getActivity(),
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

        checkAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
