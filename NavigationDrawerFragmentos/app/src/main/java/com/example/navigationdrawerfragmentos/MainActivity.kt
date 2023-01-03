package com.example.navigationdrawerfragmentos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var bottomAppBar: BottomNavigationView
    lateinit var navigationView: NavigationView
    var fragmentManager = supportFragmentManager

    private val handlerND = NavigationView.OnNavigationItemSelectedListener { item ->
        val f: Fragment
        val imagenPintar: Int
        if (item.itemId == R.id.rock) {
            imagenPintar = R.drawable.roca
            f = Fragmento(imagenPintar)
        } else if (item.itemId == R.id.fino) {
            imagenPintar = R.drawable.fino
            f = FragmentoElaborado()
            val bundle = Bundle()
            bundle.putInt(FragmentoElaborado.IMAGEN,imagenPintar)
            bundle.putString(FragmentoElaborado.MENSAJE,"fino señores")
            f.arguments = bundle
        } else {
            imagenPintar = R.drawable.scuby
            f = Fragmento(imagenPintar)
        }
        // Creamos nuestro fragmento (porción de interfaz)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmento_container, f)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        drawerLayout.closeDrawer(GravityCompat.START)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarBottomNavigation()

        inicializarNavigationDrawer()
    }

    fun inicializarNavigationDrawer(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        // Con este objeto le asignamos el drawer a nuestro toolbar.
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.drawer_abrir, R.string.drawer_cerar)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(handlerND)
    }

    fun inicializarBottomNavigation(){
        bottomAppBar = findViewById(R.id.bottomNavigationView)
        bottomAppBar.inflateMenu(R.menu.menu_bottomnav)
        bottomAppBar.setOnItemSelectedListener { boton ->
            val f: Fragment
            val imagenPintar: Int
            when(boton.itemId){
                R.id.rock -> {
                    imagenPintar = R.drawable.roca
                    f = Fragmento(imagenPintar)
                }
                R.id.fino ->  {
                    imagenPintar = R.drawable.fino
                    f = FragmentoElaborado()
                    // Este fragmento recibe los datos como si fuese un intent
                    val bundle = Bundle()
                    bundle.putInt(FragmentoElaborado.IMAGEN,imagenPintar)
                    bundle.putString(FragmentoElaborado.MENSAJE,"fino señores")
                    f.arguments = bundle
                }
                else -> {
                    imagenPintar = R.drawable.scuby
                    // Este fragmento recibe los datos desde el constructor
                    f = Fragmento(imagenPintar)
                }
            }
            // Iniciamos la transacción
            val fragmentTransaction = fragmentManager.beginTransaction()
            // Con este método le digo
            // Dónde (parte de la interfaz) renderizo el fragmentoç
            // Qué fragmento
            fragmentTransaction.replace(R.id.fragmento_container, f)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            return@setOnItemSelectedListener true
        }
    }
    // Si el usuario pulsa el botón de atrás, y el drawer está abierto, hay que cerrarlo!
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}