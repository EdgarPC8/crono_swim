package com.example.cronoswim.adaptadores;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cronoswim.R;
import com.example.cronoswim.data_bases.SentenciasSql;
import com.example.cronoswim.entidades.Cronometros;
import com.example.cronoswim.funciones.Cronometro;
import java.util.ArrayList;

//public class ListaAdapterCronometro extends RecyclerView.Adapter<ListaAdapterCronometro.RegistroViewHolder> {
//    ArrayList<Cronometros> ListaAdapterCronometro;
//    private OnItemClickListener mListener;
//    Context context;
//    Handler handler;
//    public interface OnItemClickListener{
//        void onItemClick(View v,int position,int id,Cronometros entidadCrono);
//    }
//    //este es el constructor
//    public ListaAdapterCronometro(ArrayList<Cronometros> listaRegistros, Context context){
//        this.ListaAdapterCronometro = listaRegistros;
//        this.context = context;
//        handler = new Handler() ;
//    }
//    @NonNull
//    @Override
//    public ListaAdapterCronometro.RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crono_items,null,false) ;
//        return new ListaAdapterCronometro.RegistroViewHolder(view);
//    }
//    @Override
//    public void onBindViewHolder(RegistroViewHolder holder, int position) {
//        String vueltas=ListaAdapterCronometro.get(position).getVuelta();
////        if (vueltas!=null){
////            String[] arreglo = vueltas.split("\n");
////            if (arreglo.length>1){
////                ArrayList<String>lista=new ArrayList<>();
////                for(String elemento : arreglo){
////                    lista.add(elemento);
////                }
////                crono.setVuelta(lista);
////            }
////        }
//        holder.crono.setId(ListaAdapterCronometro.get(position).getId());
//        holder.crono.putTextTiempo(ListaAdapterCronometro.get(position).getTiempo());
//        holder.viewNombre.setText(ListaAdapterCronometro.get(position).getNombre());
//        holder.viewPrueba.setText(ListaAdapterCronometro.get(position).getPrueba());
//        holder.viewMetros.setText(ListaAdapterCronometro.get(position).getMetros());
//    }
//
//    @Override
//    public int getItemCount() {
//        return ListaAdapterCronometro.size();
//    }
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
//        this.mListener=onItemClickListener;
//    }
//    public class RegistroViewHolder extends RecyclerView.ViewHolder  {
//        public TextView viewTiempo;
//        TextView viewNombre,viewVelocidad,viewPorcentaje,viewMetros,viewPrueba;
//        Button start, reset;
//        public Cronometro crono = new Cronometro(handler,context);
//        public RegistroViewHolder(@NonNull View itemView) {
//            super(itemView);
//            viewNombre= itemView.findViewById(R.id.viewNombre);
//            viewTiempo= itemView.findViewById(R.id.viewTiempo);
//            viewVelocidad= itemView.findViewById(R.id.viewVelocidad);
//            viewPorcentaje= itemView.findViewById(R.id.viewPorcentaje);
//            viewMetros= itemView.findViewById(R.id.viewMetros);
//            viewPrueba= itemView.findViewById(R.id.viewPrueba);
//            start= (Button) itemView.findViewById(R.id.btnStart);
//            reset= (Button) itemView.findViewById(R.id.btnReset);
//            System.out.println("holaa");
//
//            crono.setViewsAndButtons(viewTiempo,viewNombre,viewVelocidad,viewMetros,start,reset);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Cronometros entidadCrono = new Cronometros();
//                    String[] name = viewNombre.getText().toString().split(" ");
//
//                    entidadCrono.setVuelta(crono.obtenerVueltas());
//                    entidadCrono.setPrueba(viewPrueba.getText().toString());
//                    entidadCrono.setMetros(viewMetros.getText().toString());
//                    entidadCrono.setNombre(name[0]+" "+name[1]);
//                    entidadCrono.setTiempo(viewTiempo.getText().toString());
//                    entidadCrono.setCedula(name[2]);
//                    mListener.onItemClick(itemView,getAdapterPosition(), crono.getId(),entidadCrono);
//                }
//            });
//        }
//    }
//
//}



//public class ListaAdapterCronometro extends RecyclerView.Adapter<ListaAdapterCronometro.RegistroViewHolder> {
//    ArrayList<Cronometro> ListaAdapterCronometro;
//    Context context;
//    public interface OnItemClickListener{
//        void onItemClick(View v,int position,int id,Cronometros entidadCrono);
//    }
//    //este es el constructor
//    public ListaAdapterCronometro(ArrayList<Cronometro> listaRegistros, Context context){
//        this.ListaAdapterCronometro = listaRegistros;
//        this.context = context;
//    }
//    @NonNull
//    @Override
//    public ListaAdapterCronometro.RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crono_items,null,false) ;
//        return new ListaAdapterCronometro.RegistroViewHolder(view);
//    }
//    @Override
//    public void onBindViewHolder(RegistroViewHolder holder,int position) {
//        Cronometro crono = ListaAdapterCronometro.get(position);
//        crono.setViewsAndButtons(holder.viewTiempo,holder.viewNombre,holder.viewVelocidad,holder.viewMetros,holder.start,holder.reset);
//    }
//
//    @Override
//    public int getItemCount() {
//        return ListaAdapterCronometro.size();
//    }
////    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
////        this.mListener=onItemClickListener;
////    }
//    public class RegistroViewHolder extends RecyclerView.ViewHolder  {
//        public TextView viewTiempo;
//        TextView viewNombre,viewVelocidad,viewPorcentaje,viewMetros,viewPrueba;
//        Button start, reset;
//        public RegistroViewHolder(@NonNull View itemView) {
//            super(itemView);
//            viewNombre= itemView.findViewById(R.id.viewNombre);
//            viewTiempo= itemView.findViewById(R.id.viewTiempo);
//            viewVelocidad= itemView.findViewById(R.id.viewVelocidad);
//            viewPorcentaje= itemView.findViewById(R.id.viewPorcentaje);
//            viewMetros= itemView.findViewById(R.id.viewMetros);
//            viewPrueba= itemView.findViewById(R.id.viewPrueba);
//            start= (Button) itemView.findViewById(R.id.btnStart);
//            reset= (Button) itemView.findViewById(R.id.btnReset);
//
//
////            itemView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Cronometros entidadCrono = new Cronometros();
////                    String[] name = viewNombre.getText().toString().split(" ");
////
////                    entidadCrono.setVuelta(crono.obtenerVueltas());
////                    entidadCrono.setPrueba(viewPrueba.getText().toString());
////                    entidadCrono.setMetros(viewMetros.getText().toString());
////                    entidadCrono.setNombre(name[0]+" "+name[1]);
////                    entidadCrono.setTiempo(viewTiempo.getText().toString());
////                    entidadCrono.setCedula(name[2]);
////                    mListener.onItemClick(itemView,getAdapterPosition(), crono.getId(),entidadCrono);
////                }
////            });
//        }
//    }
//
//}

public class ListaAdapterCronometro extends RecyclerView.Adapter<ListaAdapterCronometro.RegistroViewHolder> {

    private ArrayList<Cronometro> listaRegistros;
    private Context context;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(View v,int position,Cronometros entidadCrono);
    }
        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mListener=onItemClickListener;
    }

    public ListaAdapterCronometro(ArrayList<Cronometro> listaRegistros, Context context) {
        this.listaRegistros = listaRegistros;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position; // Devuelve un valor único para cada posición
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crono_items, parent, false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
//            Cronometro crono = listaRegistros.get(position);
//            if (!crono.isViewsAndButtonsSet()) {
//                crono.setViewsAndButtons(holder.viewTiempo, holder.viewNombre, holder.viewVelocidad, holder.viewMetros, holder.start, holder.reset);
//                crono.setViewsAndButtonsSet(true);
//                holder.crono=crono;
//            }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.onItemClick(holder.itemView,holder.getAdapterPosition(),crono.cronometro);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listaRegistros.size();
    }
        public class RegistroViewHolder extends RecyclerView.ViewHolder  {
        public TextView viewTiempo;
        public TextView viewNombre,viewVelocidad,viewPorcentaje,viewMetros,viewPrueba;
        public Button start, reset;
        public Cronometro crono;
        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre= itemView.findViewById(R.id.viewNombre);
            viewTiempo= itemView.findViewById(R.id.viewTiempo);
            viewVelocidad= itemView.findViewById(R.id.viewVelocidad);
            viewPorcentaje= itemView.findViewById(R.id.viewPorcentaje);
            viewMetros= itemView.findViewById(R.id.viewMetros);
            viewPrueba= itemView.findViewById(R.id.viewPrueba);
            start= (Button) itemView.findViewById(R.id.btnStart);
            reset= (Button) itemView.findViewById(R.id.btnReset);
        }
    }

}















