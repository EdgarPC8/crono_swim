package com.example.cronoswim.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cronoswim.R;
import com.example.cronoswim.fragmentos.PrimerFragment;
import com.example.cronoswim.fragmentos.SegundoFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarMetrosFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarNadadorFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarPruebasFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarTiemposFragment;
import com.example.cronoswim.fragmentos.cronometro.CronoFragment;
import com.example.cronoswim.fragmentos.cronometro.EstadisticasCronoFragment;
import com.example.cronoswim.fragmentos.registrar.RegistrarMetrosFragment;
import com.example.cronoswim.fragmentos.registrar.RegistrarNadadorFragment;
import com.example.cronoswim.fragmentos.registrar.RegistrarPruebasFragment;



public class SegundoActivity extends AppCompatActivity {
    Fragment primerFragment,segundoFragment;
    Button btnPrimer,btnSegundo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        Bundle bundle = getIntent().getExtras();
        btnPrimer=(Button) findViewById(R.id.btnPrimer);
        btnSegundo=(Button) findViewById(R.id.btnSegundo);
        if (bundle!=null){
            int name= bundle.getInt("clave");
            int valor_registro_consulta= bundle.getInt("clave2");
            switch (name) {
                case 0:
                    primerFragment= new RegistrarNadadorFragment();
                    segundoFragment= new ConsultarNadadorFragment();
                    break;
                case 1:
                    primerFragment= new RegistrarPruebasFragment();
                    segundoFragment= new ConsultarPruebasFragment();
                    break;
                case 2:
                    primerFragment= new RegistrarMetrosFragment();
                    segundoFragment= new ConsultarMetrosFragment();
                    break;
                case 3:
                    primerFragment= new RegistrarPruebasFragment();
                    segundoFragment= new ConsultarTiemposFragment();
                    break;
                case 20:
                    primerFragment= new CronoFragment();
                    segundoFragment= new EstadisticasCronoFragment();
                    break;
            }

            switch (valor_registro_consulta) {
                case 0:
                    getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, primerFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, segundoFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(segundoFragment).show(primerFragment).commit();

                    break;
                case 1:
                    getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, segundoFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, primerFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(primerFragment).show(segundoFragment).commit();
                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, primerFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, segundoFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(segundoFragment).show(primerFragment).commit();
                    btnPrimer.setText("Cronometros");
                    btnSegundo.setText("Estadisticas");
                    break;
                case 3:
                    getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, segundoFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, primerFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(primerFragment).show(segundoFragment).commit();
                    break;
            }
        }
    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPrimer:
                getSupportFragmentManager().beginTransaction().hide(segundoFragment).show(primerFragment).commit();
                break;
            case R.id.btnSegundo:
                getSupportFragmentManager().beginTransaction().hide(primerFragment).show(segundoFragment).commit();
                break;
        }
    }
}