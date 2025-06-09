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
import com.example.rmai2425_projects_astromap.adapters.ConstellationAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Zvijezdje
import com.example.rmai2425_projects_astromap.utils.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConstellationsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var constellationAdapter: ConstellationAdapter
    private lateinit var userManager: UserManager
    private var constellationList: List<Zvijezdje> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_constellations, container, false)
        userManager = UserManager(requireContext())
        recyclerView = view.findViewById(R.id.recycler_view_constellation)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            loadConstellationsFromDatabase()
        }
    }

    private suspend fun loadConstellationsFromDatabase() {
        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.entitiesDao()

        withContext(Dispatchers.IO) {
            constellationList = dao.getAllZvijezdja()
        }

        constellationAdapter = ConstellationAdapter(
            constellationList,
            userManager.isUserLoggedIn(),
            { constellationName ->
                showCompletionMessage("Naučili ste sve o zviježđu $constellationName!")
            }
        )
        recyclerView.adapter = constellationAdapter
    }

    private fun showCompletionMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
