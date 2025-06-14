package com.example.rmai2425_projects_astromap.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.adapters.AsteroidAdapter
import com.example.rmai2425_projects_astromap.database.Asteroid
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.DovrseniModul
import com.example.rmai2425_projects_astromap.utils.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AsteroidsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var asteroidAdapter: AsteroidAdapter
    private lateinit var userManager: UserManager
    private var asteroidList: List<Asteroid> = listOf()
    private var completedModules: Set<String> = emptySet()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_asteroids, container, false)
        userManager = UserManager(requireContext())
        recyclerView = view.findViewById(R.id.recycler_view_asteroid)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            loadAsteroidsFromDatabase()
        }
    }

    private suspend fun loadAsteroidsFromDatabase() {
        val database = DatabaseProvider.getDatabase(requireContext())
        val userId = userManager.getCurrentUserId()

        withContext(Dispatchers.IO) {
            asteroidList = database.asteroidDao().getAll()
            completedModules = if (userId != -1) {
                database.dovrseniModulDao().getByUserId(userId).map { it.modulId }.toSet()
            } else {
                emptySet()
            }
        }

        asteroidAdapter = AsteroidAdapter(
            asteroidList,
            userManager.isUserLoggedIn(),
            completedModules
        ) { asteroidName ->
            showCompletionMessage("Naučili ste sve o asteroidu $asteroidName!")
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    database.dovrseniModulDao().insert(
                        DovrseniModul(
                            userId = userId,
                            modulId = asteroidName,
                            datumDovrsenja = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                        )
                    )
                }
                asteroidAdapter.markModuleCompleted(asteroidName)
            }
        }
        recyclerView.adapter = asteroidAdapter
    }

    private fun showCompletionMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}