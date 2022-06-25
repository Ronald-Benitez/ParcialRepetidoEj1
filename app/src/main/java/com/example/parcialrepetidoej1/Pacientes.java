package com.example.parcialrepetidoej1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parcialrepetidoej1.adapters.pacientesList;
import com.example.parcialrepetidoej1.models.Paciente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pacientes extends AppCompatActivity {
    RecyclerView listPacientes;
    Button addPaciente;
    ArrayList<Paciente> listPacientesArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);
        listPacientes = findViewById(R.id.listPacientes);
        addPaciente = findViewById(R.id.addPaciente);

        addPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pacientes.this, AddPaciente.class));
            }
        });

        listPacientes.setLayoutManager(new LinearLayoutManager(this));
        listPacientes.setAdapter(new pacientesList(listPacientesArray));
        listPacientes.setHasFixedSize(true);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("pacientes");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPacientesArray.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Paciente p = ds.getValue(Paciente.class);
                    listPacientesArray.add(p);
                }
                listPacientes.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}