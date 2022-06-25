package com.example.parcialrepetidoej1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcialrepetidoej1.models.Doctor;
import com.example.parcialrepetidoej1.models.Paciente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class addDoctor extends AppCompatActivity {
    EditText name,speciality,occupation,observation;
    Button btnAdd;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        Bundle bundle = getIntent().getExtras();

        name = findViewById(R.id.name);
        speciality = findViewById(R.id.speciality);
        occupation = findViewById(R.id.occupation);
        observation = findViewById(R.id.observation);
        btnAdd = findViewById(R.id.btnAdd);

        try{
            if(bundle.getString("id") != null) {

                name.setText(bundle.getString("name"));
                speciality.setText(bundle.getString("speciality"));
                occupation.setText(bundle.getString("occupation"));
                observation.setText(bundle.getString("observation"));
                id = bundle.getString("id");
                btnAdd.setText("Actualizar");
            }else{
                id = UUID.randomUUID().toString();
            }
        }catch (Exception e){
            id= UUID.randomUUID().toString();
        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Doctor p = verificar();
                if(p!=null){
                    myRef.child("doctores").child(id).setValue(p);
                    if(btnAdd.getText().toString().equals("Actualizar")){
                        Toast.makeText(addDoctor.this, "Doctor actualizado", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(addDoctor.this, "Doctor agregado", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        speciality.setText("");
                        occupation.setText("");
                        observation.setText("");
                    }
                }
            }
        });
    }

    public Doctor verificar(){
        try {
            Doctor doctor = new Doctor();
            doctor.setName(name.getText().toString());
            doctor.setSpeciality(speciality.getText().toString());
            doctor.setOccupation(occupation.getText().toString());
            doctor.setObservation(observation.getText().toString());
            doctor.setId(id);
            return doctor;
        }catch (Exception e){
            return null;
        }
    }
}