package com.example.rmai2425_projects_astromap.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.OnBackPressedCallback
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
import com.example.rmai2425_projects_astromap.fragments.*
import com.example.rmai2425_projects_astromap.utils.UserManager
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var userManager: UserManager
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Sunčev sustav"

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.open_nav, R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        drawerLayout.post { toggle.syncState() }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        updateNavigationMenu()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.nav_home)
        }

        lifecycleScope.launch {
            val database = DatabaseProvider.getDatabase(this@MainActivity)
            DatabaseInitializer.initDatabase(database)

            if (intent.getBooleanExtra("restart_game", false)) {
                replaceFragment(GameFragment())
                supportActionBar?.title = "Igra"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateNavigationMenu()
    }

    private fun updateNavigationMenu() {
        val menu = navigationView.menu
        val loginMenuItem = menu.findItem(R.id.nav_login)

        if (userManager.isUserLoggedIn()) {
            loginMenuItem?.title = "Moj profil"
            loginMenuItem?.setIcon(R.drawable.baseline_person_24)
        } else {
            loginMenuItem?.title = "Prijava"
            loginMenuItem?.setIcon(R.drawable.baseline_login_24)
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
            R.id.nav_quiz -> {
                replaceFragment(QuizFragment())
                supportActionBar?.title = "Kvizovi"
            }
            R.id.nav_login -> {
                if (userManager.isUserLoggedIn()) {
                    replaceFragment(ProfileFragment())
                    supportActionBar?.title = "Moj profil"
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
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