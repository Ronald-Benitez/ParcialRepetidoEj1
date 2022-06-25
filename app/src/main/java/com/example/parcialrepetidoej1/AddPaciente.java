package com.example.parcialrepetidoej1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcialrepetidoej1.models.Paciente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddPaciente extends AppCompatActivity {
    EditText name,direction,phone,observation;
    Button btnAdd;
    String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paciente);
        Bundle bundle = getIntent().getExtras();

        name = findViewById(R.id.name);
        direction = findViewById(R.id.direction);
        phone = findViewById(R.id.phone);
        observation = findViewById(R.id.observation);
        btnAdd = findViewById(R.id.btnAdd);

        try{
            if(bundle.getString("id") != null) {

                name.setText(bundle.getString("name"));
                direction.setText(bundle.getString("direcion"));
                phone.setText(bundle.getString("phone"));
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
                Paciente p = verificar();
                if(p!=null){
                    myRef.child("pacientes").child(id).setValue(p);
                    if(btnAdd.getText().toString().equals("Actualizar")){
                        Toast.makeText(AddPaciente.this, "Paciente actualizado", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddPaciente.this, "Paciente agregado", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        direction.setText("");
                        phone.setText("");
                        observation.setText("");
                    }
                }
            }
        });
    }

    public Paciente verificar(){
        try {
            Paciente paciente = new Paciente();
            paciente.setId(id);
            paciente.setName(name.getText().toString());
            paciente.setDirection(direction.getText().toString());
            paciente.setPhone(phone.getText().toString());
            paciente.setObservaciones(observation.getText().toString());
            return paciente;
        }catch (Exception e){
            return null;
        }
    }
}