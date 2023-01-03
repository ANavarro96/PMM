package com.p2.pruebaappbar


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class RecyclerViewPrueba : AppCompatActivity() {
    lateinit  var toolbar: Toolbar
    var listaDiscos = ArrayList<Disco>()
    lateinit var misTabs: TabLayout
    lateinit var viewPager: ViewPager
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
        adaptadorFragmento = FragmentPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        adaptadorFragmento.listaDiscos = listaDiscos
        viewPager.setAdapter(adaptadorFragmento)
        misTabs = findViewById(R.id.tabs)
        misTabs.setTabMode(TabLayout.MODE_SCROLLABLE)
        misTabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mimenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.crear -> {
                listaDiscos.add(0, Disco("Disco Creado", "autor inventado", R.drawable.tgg))
                adaptadorFragmento.notifyDataSetChanged()
                true
            }
            R.id.modificar -> {
                val posicionAzar = Random().nextInt(listaDiscos.size)
                listaDiscos[posicionAzar] = Disco("Disco CAMBIADO!!", "autor CAMBIADO!!", R.drawable.ncth)
                //adaptadorDisco.notifyItemChanged(posicionAzar);
                adaptadorFragmento.notifyDataSetChanged()
                true
            }
            R.id.borrar -> {
                if (listaDiscos.size > 1) {
                    listaDiscos.removeAt(0)
                    //adaptadorDisco.notifyItemRemoved(0);
                    adaptadorFragmento.notifyDataSetChanged()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}