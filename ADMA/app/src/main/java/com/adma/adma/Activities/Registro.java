package com.adma.adma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.adma.adma.R;
import com.adma.adma.Utils.Utilerias;
import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private TextInputEditText rfc,user,tel,correo,contra,contrac;
    private RequestQueue queue;
    private MaterialButton btnregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        bindU();
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Registro.this,Home.class));
                registro();
            }
        });
    }
    private void registro() {
        JSONObject object= new JSONObject();
        try {
            object.put("rfc",rfc.getText().toString());
            object.put("nom_usuario",user.getText().toString());
            object.put("telefono",tel.getText().toString());
            object.put("correo",correo.getText().toString());
            object.put("contrasena",contrac.getText().toString());
        } catch (JSONException e) {
            e.getMessage();
        }
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, Utilerias.url + "RegistroUsuarios.php", object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.w("VOLLEY ",response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("VOLLEY ",error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }
    private void bindU(){
        queue= Volley.newRequestQueue(getApplicationContext());
        rfc=(TextInputEditText)findViewById(R.id.r_rfc);
        user=(TextInputEditText)findViewById(R.id.r_user);
        tel=(TextInputEditText)findViewById(R.id.r_telefono);
        correo=(TextInputEditText)findViewById(R.id.r_correo);
        contra=(TextInputEditText)findViewById(R.id.r_contras);
        contrac=(TextInputEditText)findViewById(R.id.r_contrasc);
        btnregistro=(MaterialButton)findViewById(R.id.r_Btnregistro);
    }
}
