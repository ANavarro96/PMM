package com.example.navigationdrawerfragmentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager = getSupportFragmentManager();


    private NavigationView.OnNavigationItemSelectedListener handlerND = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment f;
            int imagenPintar;
            if(item.getItemId() == R.id.rock){
                imagenPintar = R.drawable.roca;
                f = new Fragmento(imagenPintar);
            }
            else if (item.getItemId() == R.id.fino){
                imagenPintar = R.drawable.fino;
                f = new Fragmento_elaborado(imagenPintar," Fino señores");
            }
            else{
                imagenPintar = R.drawable.scuby;
                f = new Fragmento(imagenPintar);
            }
            // Creamos nuestro fragmento (porción de interfaz)

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmento_container, f);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        // Con este objeto le asignamos el drawer a nuestro toolbar.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_abrir, R.string.drawer_cerar);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = findViewById(R.id.navigation_view);
         navigationView.setNavigationItemSelectedListener(handlerND);
    }


    @Override
    // Si el usuario pulsa el botón de atrás, y el drawer está abierto, hay que cerrarlo!
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}