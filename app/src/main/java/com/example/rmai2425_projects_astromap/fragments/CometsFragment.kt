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
import com.example.rmai2425_projects_astromap.adapters.CometAdapter
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.Komet
import com.example.rmai2425_projects_astromap.utils.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CometsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cometAdapter: CometAdapter
    private lateinit var userManager: UserManager
    private var cometList: List<Komet> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comets, container, false)
        userManager = UserManager(requireContext())
        recyclerView = view.findViewById(R.id.recycler_view_comet)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            loadCometsFromDatabase()
        }
    }

    private suspend fun loadCometsFromDatabase() {
        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.entitiesDao()

        withContext(Dispatchers.IO) {
            cometList = dao.getAllKometi()
        }

        cometAdapter = CometAdapter(
            cometList,
            userManager.isUserLoggedIn(),
            { cometName ->
                showCompletionMessage("Naučili ste sve o kometu $cometName!")
            }
        )
        recyclerView.adapter = cometAdapter
    }


    private fun showCompletionMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
