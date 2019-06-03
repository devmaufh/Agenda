package com.adma.adma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

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

            }
        });
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
