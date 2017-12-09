package com.example.user.buses254;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin2;
    EditText etEmail, etPassword;
    TextView tvRegHere;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tvRegHere = (TextView) findViewById(R.id.tvRegHere);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin2 = (Button) findViewById(R.id.bLogin2);

           tvRegHere.setOnClickListener(this);
           bLogin2.setOnClickListener(this);

    }

        @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogin2:
                final String url = "http://10.0.3.2/android_php/login%20passenger.php";

                //instantiate the Request Queue
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


                // Request a string from the provided url

                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("Successfully Logged In")) {
                                Intent in = new Intent(Login.this, navDrawer.class);
                                startActivity(in);
                            } else {
                                Toast.makeText(getApplicationContext(), "Email or Password Incorrect. Please Try Again!", Toast.LENGTH_LONG).show();
                                Intent in2 = new Intent(Login.this, Login.class);
                                startActivity(in2);
                            }

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
                        params.put("email",etEmail.getText().toString() );
                        params.put("password",etPassword.getText().toString() );

                        return params;
                        //return super.getParams();
                    }
                };

                queue.add(stringRequest1);


                break;

            case R.id.tvRegHere:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
