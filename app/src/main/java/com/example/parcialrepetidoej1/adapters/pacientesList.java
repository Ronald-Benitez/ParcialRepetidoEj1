package com.example.parcialrepetidoej1.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcialrepetidoej1.AddPaciente;
import com.example.parcialrepetidoej1.R;
import com.example.parcialrepetidoej1.models.Paciente;
import com.google.android.material.transition.Hold;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class pacientesList extends RecyclerView.Adapter<pacientesList.ViewHolder>{
    ArrayList<Paciente> pacientes;

    public pacientesList(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paciente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvname.setText(pacientes.get(position).getName());
        holder.tvdirecion.setText(pacientes.get(position).getDirection());
        holder.tvphone.setText(pacientes.get(position).getPhone());
        holder.tvobservation.setText(pacientes.get(position).getObservaciones());
    }

    @Override
    public int getItemCount() {
        try{
            return pacientes.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvdirecion,tvphone,tvobservation;
        Button btnDelete,btnEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvdirecion = itemView.findViewById(R.id.tvdirecion);
            tvphone = itemView.findViewById(R.id.tvphone);
            tvobservation = itemView.findViewById(R.id.tvobservation);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("pacientes").child(pacientes.get(getAdapterPosition()).getId());
                    ref.removeValue();
                    pacientes.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), AddPaciente.class);
                    intent.putExtra("id",pacientes.get(getAdapterPosition()).getId());
                    intent.putExtra("name",pacientes.get(getAdapterPosition()).getName());
                    intent.putExtra("direcion",pacientes.get(getAdapterPosition()).getDirection());
                    intent.putExtra("phone",pacientes.get(getAdapterPosition()).getPhone());
                    intent.putExtra("observation",pacientes.get(getAdapterPosition()).getObservaciones());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
