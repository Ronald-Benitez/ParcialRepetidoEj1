package com.example.parcialrepetidoej1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Data extends AppCompatActivity {
    Button btnpaciente,btndoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        btnpaciente=findViewById(R.id.btnpaciente);
        btndoctor=findViewById(R.id.btndoctor);

        btnpaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Data.this,Pacientes.class));
            }
        });

        btndoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Data.this,Doctores.class));
            }
        });
    }
}