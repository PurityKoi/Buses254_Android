package com.example.user.buses254;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {

    final String TAG = this.getClass().getSimpleName();
    Button bRegister;
    EditText etFname, etLname, etNationalId, etPhoneNo, etEmail, etRegPass, etConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

                etFname= (EditText) findViewById(R.id.etFname);
                etLname = (EditText) findViewById(R.id.etLname);
                etNationalId = (EditText) findViewById(R.id.etNationalId);
                etPhoneNo = (EditText) findViewById(R.id.etPhoneNo);
                etEmail = (EditText) findViewById(R.id.etEmail);
                etRegPass = (EditText) findViewById(R.id.etRegPass);
                etConfirmPass = (EditText) findViewById(R.id.etConfirmPass);
                bRegister = (Button) findViewById(R.id.bRegister);

            bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bRegister:

               final String url = "http://10.0.3.2/android_php/insert%20passenger.php";

                //instantiate the Request Queue
                 RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


                // Request a string from the provided url
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(Register.this, Login.class);
                            startActivity(in);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error instanceof TimeoutError){
                            Toast.makeText(getApplicationContext(), "Time Out Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof NoConnectionError){
                            Toast.makeText(getApplicationContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof AuthFailureError){
                            Toast.makeText(getApplicationContext(), "Authentication Failure Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof NetworkError){
                            Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof ServerError){
                            Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                        }
                        else if (error instanceof ParseError){
                            Toast.makeText(getApplicationContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String ,String>();
                        params.put("f_name",etFname.getText().toString() );
                        params.put("l_name",etLname.getText().toString() );
                        params.put("idno",etNationalId.getText().toString() );
                        params.put("phnno",etPhoneNo.getText().toString() );
                        params.put("email",etEmail.getText().toString() );
                        params.put("password",etRegPass.getText().toString() );

                            return params;
                                    //return super.getParams();
                    }
                };

                //Add the request to the RequestQueue
               //SingletonRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(stringRequest1);

        queue.add(stringRequest1);
                break;
        }
    }
}
