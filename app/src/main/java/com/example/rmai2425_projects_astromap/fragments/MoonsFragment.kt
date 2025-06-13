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
import com.example.rmai2425_projects_astromap.adapters.MoonAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Mjesec
import com.example.rmai2425_projects_astromap.utils.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoonsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var moonAdapter: MoonAdapter
    private lateinit var userManager: UserManager
    private var moonList: List<Mjesec> = listOf()
    private var completedModules: Set<String> = emptySet()
    private var planetIdToNameMap: Map<Int, String> = emptyMap()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_moons, container, false)
        userManager = UserManager(requireContext())
        recyclerView = view.findViewById(R.id.recycler_view_moon)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { loadMoonsFromDatabase() }
    }

    private suspend fun loadMoonsFromDatabase() {
        val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()
        val userId = userManager.getCurrentUserId()
        withContext(Dispatchers.IO) {
            moonList = dao.getAllMjeseci()
            val allPlanets = dao.getAllPlanets()
            planetIdToNameMap = allPlanets.associateBy({ it.id }, { it.ime })
            completedModules = if (userId != -1) dao.getDovrseneModule(userId).map { it.modulId }.toSet() else emptySet()
        }
        moonAdapter = MoonAdapter(
            moonList,
            planetIdToNameMap,
            userManager.isUserLoggedIn(),
            completedModules
        ) { moonName ->
            showCompletionMessage("Naučili ste sve o mjesecu $moonName!")
            lifecycleScope.launch {
                dao.insertDovrseniModul(
                    com.example.rmai2425_projects_astromap.database.DovrseniModul(
                        userId = userId,
                        modulId = moonName
                    )
                )
                moonAdapter.markModuleCompleted(moonName)
            }
        }
        recyclerView.adapter = moonAdapter
    }

    private fun showCompletionMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}