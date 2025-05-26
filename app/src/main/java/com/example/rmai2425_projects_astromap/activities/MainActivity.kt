package com.example.rmai2425_projects_astromap.activities
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.rmai2425_projects_astromap.R
import kotlinx.coroutines.launch
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.DatabaseInitializer
import com.example.rmai2425_projects_astromap.fragments.AsteroidsFragment
import com.example.rmai2425_projects_astromap.fragments.CometsFragment
import com.example.rmai2425_projects_astromap.fragments.ConstellationsFragment
import com.example.rmai2425_projects_astromap.fragments.GameFragment
import com.example.rmai2425_projects_astromap.fragments.HomeFragment
import com.example.rmai2425_projects_astromap.fragments.MoonsFragment
import com.example.rmai2425_projects_astromap.fragments.ObjectsFragment
import com.example.rmai2425_projects_astromap.fragments.PlanetsFragment
import com.example.rmai2425_projects_astromap.fragments.SunFragment
import com.example.rmai2425_projects_astromap.fragments.QuizFragment
import com.google.android.material.navigation.NavigationView
import androidx.activity.OnBackPressedCallback

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        drawerLayout = findViewById <DrawerLayout> (R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Sunčev sustav"

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)

        drawerLayout.post {
            toggle.syncState()
        }

        onBackPressedDispatcher.addCallback(this, object : androidx.activity.OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })


        if(savedInstanceState == null){
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.nav_home)
        }

        lifecycleScope.launch {
            val dao = DatabaseProvider.getDatabase(this@MainActivity).entitiesDao()
            DatabaseInitializer.initDatabase(dao)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                replaceFragment(HomeFragment())
                supportActionBar?.title = "Početna"
            }
            R.id.nav_planets -> {
                replaceFragment(PlanetsFragment())
                supportActionBar?.title = "Planeti"
            }
            R.id.nav_sun -> {
                replaceFragment(SunFragment())
                supportActionBar?.title = "Sunce"
            }
            R.id.nav_moons -> {
                replaceFragment(MoonsFragment())
                supportActionBar?.title = "Mjeseci"
            }
            R.id.nav_asteroids -> {
                replaceFragment(AsteroidsFragment())
                supportActionBar?.title = "Asteroidi"
            }
            R.id.nav_comets -> {
                replaceFragment(CometsFragment())
                supportActionBar?.title = "Kometi"
            }
            R.id.nav_objects -> {
                replaceFragment(ObjectsFragment())
                supportActionBar?.title = "Objekti Sunčevog sustava"
            }
            R.id.nav_constellations -> {
                replaceFragment(ConstellationsFragment())
                supportActionBar?.title = "Zviježđa"
            }
            R.id.nav_game -> {
                replaceFragment(GameFragment())
                supportActionBar?.title = "Igra"
            }
            R.id.navquiz -> {
                replaceFragment(QuizFragment())
                supportActionBar?.title = "Kvizovi"
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}