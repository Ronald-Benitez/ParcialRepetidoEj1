package com.example.parcialrepetidoej1.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcialrepetidoej1.R;
import com.example.parcialrepetidoej1.addDoctor;
import com.example.parcialrepetidoej1.models.Doctor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class doctorList extends RecyclerView.Adapter<doctorList.ViewHolder> {
    ArrayList<Doctor> doctors;

    public doctorList(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvname.setText(doctors.get(position).getName());
        holder.tvoccupation.setText(doctors.get(position).getOccupation());
        holder.tvspeciality.setText(doctors.get(position).getSpeciality());
        holder.tvobservation.setText(doctors.get(position).getObservation());
    }

    @Override
    public int getItemCount() {
        try {
            return doctors.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvspeciality,tvoccupation,tvobservation;
        Button btnDelete,btnEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvspeciality = itemView.findViewById(R.id.tvspeciality);
            tvoccupation = itemView.findViewById(R.id.tvoccupation);
            tvobservation = itemView.findViewById(R.id.tvobservation);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("doctores").child(doctors.get(getAdapterPosition()).getId());
                    ref.removeValue();
                    doctors.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), addDoctor.class);
                    intent.putExtra("id",doctors.get(getAdapterPosition()).getId());
                    intent.putExtra("name",doctors.get(getAdapterPosition()).getName());
                    intent.putExtra("speciality",doctors.get(getAdapterPosition()).getSpeciality());
                    intent.putExtra("occupation",doctors.get(getAdapterPosition()).getOccupation());
                    intent.putExtra("observation",doctors.get(getAdapterPosition()).getObservation());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
