package com.adma.adma;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.adma.adma.Activities.Registro;
import com.adma.adma.Utils.Utilerias;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private TextInputEditText correo,pass;
    private MaterialButton btnLogin;
    private TextView tvRegistrarse;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registro.class));
            }
        });
    }
    private void bindUI(){
        queue= Volley.newRequestQueue(getApplicationContext());
        correo=(TextInputEditText)findViewById(R.id.login_tfCorreo);
        pass=(TextInputEditText)findViewById(R.id.login_tfPass);
        btnLogin=(MaterialButton)findViewById(R.id.login_iniciarsesion);
        tvRegistrarse=(TextView)findViewById(R.id.login_registro);
    }
    private void login(){
        String c=correo.getText().toString();
        String pas=pass.getText().toString();
        if(!TextUtils.isEmpty(c)&&!TextUtils.isEmpty(pas)){
            JSONObject object= new JSONObject();
            try {
                object.put("correo",c);
                object.put("contrasena",pas);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, Utilerias.url + "", object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.w("VOLLEY ",response.toString());

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.w("VOLLEY ",error.networkResponse.toString());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }
}
