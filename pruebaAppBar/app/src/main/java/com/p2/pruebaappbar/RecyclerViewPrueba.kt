package com.p2.pruebaappbar


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.size
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class RecyclerViewPrueba : AppCompatActivity() {

    /* Este Array representa los títulos de las pestañas */
    var listaTabs = ArrayList(Arrays.asList("Suburbia", "NCTH", "TGG", "Sister cities", "Disco Creado", "Disco CAMBIADO!!"))

    lateinit  var toolbar: Toolbar
    var listaDiscos = ArrayList<Disco>()
    lateinit var misTabs: TabLayout
    lateinit var viewPager: ViewPager2
    lateinit var adaptadorFragmento: FragmentPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..1) {
            listaDiscos.add(Disco("TGG", "The Wonder Years", R.drawable.tgg))
            listaDiscos.add(Disco("Suburbia", "The Wonder Years", R.drawable.suburbia))
            listaDiscos.add(Disco("Sister cities", "The Wonder Years", R.drawable.sc))
            listaDiscos.add(Disco("NCTH", "The Wonder Years", R.drawable.ncth))
        }
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewPager = findViewById(R.id.viewPager)
        adaptadorFragmento = FragmentPagerAdapter(this)
        adaptadorFragmento.listaDiscos = listaDiscos
        viewPager.adapter = adaptadorFragmento
        misTabs = findViewById(R.id.tabs)
        misTabs.tabMode = TabLayout.MODE_SCROLLABLE

        // Un TabLayoutMediator enlaza un TabLayout (estructura estática) con el viewPager (gestor
        // de los fragmentos que se visualzian dentro).
        TabLayoutMediator(misTabs, viewPager) { tab, position ->
            tab.text = listaTabs[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mimenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.crear -> {
                listaDiscos.add(0, Disco("Disco Creado", "autor inventado", R.drawable.tgg))
                adaptadorFragmento.notifyItemChanged(listaTabs.indexOf("Disco Creado"),listaDiscos)
                true
            }
            R.id.borrar -> {
                adaptadorFragmento.borrarElemento(viewPager.currentItem)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}