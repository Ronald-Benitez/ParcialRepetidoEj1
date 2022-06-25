package com.example.parcialrepetidoej1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.parcialrepetidoej1.adapters.doctorList;
import com.example.parcialrepetidoej1.adapters.pacientesList;
import com.example.parcialrepetidoej1.models.Doctor;
import com.example.parcialrepetidoej1.models.Paciente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctores extends AppCompatActivity {
    RecyclerView listPacientes;
    Button addPaciente;
    ArrayList<Doctor> listDocotoresArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctores);
        listPacientes = findViewById(R.id.listPacientes);
        addPaciente = findViewById(R.id.addPaciente);

        addPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctores.this, addDoctor.class));
            }
        });

        listPacientes.setLayoutManager(new LinearLayoutManager(this));
        listPacientes.setAdapter(new doctorList(listDocotoresArray));
        listPacientes.setHasFixedSize(true);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("doctores");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDocotoresArray.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Doctor p = ds.getValue(Doctor.class);
                    listDocotoresArray.add(p);
                }
                listPacientes.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}