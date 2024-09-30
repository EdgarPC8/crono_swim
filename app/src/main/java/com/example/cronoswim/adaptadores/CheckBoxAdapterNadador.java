package com.example.cronoswim.adaptadores;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cronoswim.R;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.entidades.Nadador;

import java.util.ArrayList;

public class CheckBoxAdapterNadador extends RecyclerView.Adapter<CheckBoxAdapterNadador.ViewHolder> {
    static ArrayList<Nadador> listaAdapterNadador;
    RecyclerView recyclerView;

    public CheckBoxAdapterNadador(ArrayList<Nadador> listaAdapterNadador ) {
        this.listaAdapterNadador = listaAdapterNadador;
    }

    public ArrayList<Nadador> getListaAdapterNadador() {
        return listaAdapterNadador;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crono_items_checkboxs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Nadador nadador = listaAdapterNadador.get(position);
        String texto = (position + 1) + ".- " + nadador.getNadador() + " " + nadador.getCedula();
        holder.checkBox.setText(texto);
        holder.checkBox.setChecked(nadador.isChecked());
    }

    @Override
    public int getItemCount() {
        return listaAdapterNadador.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_box);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getAdapterPosition();
                    Nadador nadador = listaAdapterNadador.get(position);
                    nadador.setChecked(isChecked);
                }
            });
        }
    }
}


