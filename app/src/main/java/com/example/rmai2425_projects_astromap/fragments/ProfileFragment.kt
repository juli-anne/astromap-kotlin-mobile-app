package com.example.rmai2425_projects_astromap.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.activities.LoginActivity
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.utils.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {
    private lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userManager = UserManager(requireContext())

        val tvUserName = view.findViewById<TextView>(R.id.tv_username)
        val tvUserEmail = view.findViewById<TextView>(R.id.tv_user_email)
        val tvRegistrationDate = view.findViewById<TextView>(R.id.tv_registration_date)
        val tvRezultatModuli = view.findViewById<TextView>(R.id.tv_rezultat_moduli)
        val tvKvizRezultati = view.findViewById<TextView>(R.id.tv_kviz_rezultati)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)
        val spinnerCategory = view.findViewById<Spinner>(R.id.spinner_category)

        val userId = userManager.getCurrentUserId()
        if (userId == -1) {
            Toast.makeText(requireContext(), "Korisnik nije prijavljen", Toast.LENGTH_SHORT).show()
            return
        }


        val allCategories = resources.getStringArray(R.array.categories)
        val categories = allCategories.filter { it.lowercase() != "svi" }

        fun categoryToPrefix(category: String): String {
            return when (category) {
                "Planeti" -> "planeti"
                "Mjeseci" -> "mjeseci"
                "Asteroidi" -> "asteroidi"
                "Kometi" -> "kometi"
                "Zviježđa" -> "zviježđa"
                "Sunce" -> "sunce"
                "Objekti Sunčevog sustava" -> "objekti"
                else -> ""
            }
        }

        fun loadData(selectedCategory: String) {
            lifecycleScope.launch {
                val database = DatabaseProvider.getDatabase(requireContext())
                try {
                    val moduli = withContext(Dispatchers.IO) {
                        database.dovrseniModulDao().getByUserId(userId)
                    }
                    val kvizovi = withContext(Dispatchers.IO) {
                        database.kvizRezultatDao().getByUserId(userId)
                    }

                    val prefix = categoryToPrefix(selectedCategory).lowercase()

                    val filteredModuli = moduli.filter {
                        it.modulId.trim().lowercase().startsWith(prefix)
                    }

                    val filteredKvizovi = kvizovi.filter {
                        it.kvizId.trim().lowercase().startsWith(prefix)
                    }

                    tvRezultatModuli.text = if (filteredModuli.isNotEmpty()) {
                        filteredModuli.joinToString("\n") {
                            "${it.modulId} (${it.datumDovrsenja})"
                        }
                    } else {
                        "Još nema riješenih modula"
                    }

                    tvKvizRezultati.text = if (filteredKvizovi.isNotEmpty()) {
                        filteredKvizovi.joinToString("\n") {
                            "${it.kvizId}: ${it.najboljiRezultat}/10"
                        }
                    } else {
                        "Još nema riješenih kvizova"
                    }

                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Greška: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        spinnerCategory.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                loadData(categories[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerCategory.setSelection(0)

        lifecycleScope.launch {
            val database = DatabaseProvider.getDatabase(requireContext())
            val user = withContext(Dispatchers.IO) { database.korisnikDao().getById(userId) }
            if (user != null) {
                tvUserName.text = user.ime
                tvUserEmail.text = user.email
                tvRegistrationDate.text = "Registriran: ${user.datumRegistracije}"
            } else {
                Toast.makeText(requireContext(), "Korisnički podaci nisu pronađeni", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogout.setOnClickListener {
            userManager.logout()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}

