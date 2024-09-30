package com.example.cronoswim.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cronoswim.R;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.entidades.Nadador;

import java.util.ArrayList;
public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.ViewHolder> {
    ArrayList<Cronometros> ListaAdapterCronometro;
    public CheckBoxAdapter(ArrayList<Cronometros> ListaAdapterCronometro) {
        this.ListaAdapterCronometro = ListaAdapterCronometro;
    }

    public ArrayList<Cronometros> getListaAdapterNadador() {
        return ListaAdapterCronometro;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crono_items_checkboxs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cronometros cronometros = ListaAdapterCronometro.get(position);
        String texto="No "+(position+1)+"\n"+ListaAdapterCronometro.get(position).getNombre();
        holder.checkBox.setText(texto);
        holder.checkBox.setChecked(cronometros.isChecked());

    }

    @Override
    public int getItemCount() {
        return ListaAdapterCronometro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_box);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getAdapterPosition();
                    Cronometros cronometros = ListaAdapterCronometro.get(position);
                    cronometros.setChecked(isChecked);
                }
            });
        }
    }
}

