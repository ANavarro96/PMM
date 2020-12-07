package com.p2.pruebaappbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewPrueba extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<Disco> listaDiscos = new ArrayList<>();
    TabLayout misTabs;
    ViewPager viewPager;
    FragmentPagerAdapter adaptadorFragmento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 2; i++) {
            listaDiscos.add(new Disco("TGG", "The Wonder Years", R.drawable.tgg));
            listaDiscos.add(new Disco("Suburbia", "The Wonder Years", R.drawable.suburbia));
            listaDiscos.add(new Disco("Sister cities", "The Wonder Years", R.drawable.sc));
            listaDiscos.add(new Disco("NCTH", "The Wonder Years", R.drawable.ncth));
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewPager);
        adaptadorFragmento = new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adaptadorFragmento.setListaDiscos(listaDiscos);
        viewPager.setAdapter(adaptadorFragmento);
        misTabs = findViewById(R.id.tabs);
        misTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        misTabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mimenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.crear:
                listaDiscos.add(0,new Disco("Disco Creado","autor inventado",R.drawable.tgg));
                adaptadorFragmento.setListaDiscos(listaDiscos);
                adaptadorFragmento.notifyDataSetChanged();
                return true;

            case R.id.modificar:
                int posicionAzar = new Random().nextInt(listaDiscos.size());
                listaDiscos.set(posicionAzar,new Disco("Disco CAMBIADO!!","autor CAMBIADO!!",R.drawable.ncth));
                //adaptadorDisco.notifyItemChanged(posicionAzar);
                adaptadorFragmento.setListaDiscos(listaDiscos);
                adaptadorFragmento.notifyDataSetChanged();
                return true;
            case R.id.borrar:
                if(listaDiscos.size() > 1) {
                    listaDiscos.remove(0);
                    //adaptadorDisco.notifyItemRemoved(0);
                    adaptadorFragmento.setListaDiscos(listaDiscos);
                    adaptadorFragmento.notifyDataSetChanged();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}