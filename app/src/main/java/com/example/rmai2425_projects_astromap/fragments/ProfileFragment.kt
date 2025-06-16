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
        val tvDovrseniModuli = view.findViewById<TextView>(R.id.tv_dovrseni_moduli)
        val tvKvizRezultati = view.findViewById<TextView>(R.id.tv_kviz_rezultati)
        val tvHighScore = view.findViewById<TextView>(R.id.tv_high_score)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)
        val spinnerCategory = view.findViewById<Spinner>(R.id.spinner_category)

        val userId = userManager.getCurrentUserId()
        if (userId == -1) {
            Toast.makeText(requireContext(), "Korisnik nije prijavljen", Toast.LENGTH_SHORT).show()
            return
        }

        val categories = listOf("Svi", "Planeti", "Sunce", "Mjeseci", "Asteroidi", "Kometi", "Objekti", "Zvijezđa")

        suspend fun getObjectNamesForCategory(category: String): List<String> {
            val database = DatabaseProvider.getDatabase(requireContext())
            return withContext(Dispatchers.IO) {
                when (category) {
                    "Planeti" -> database.planetDao().getAll().map { it.ime }
                    "Sunce" -> database.sunceDao().getAll().map { it.ime }
                    "Mjeseci" -> database.mjesecDao().getAll().map { it.ime }
                    "Asteroidi" -> database.asteroidDao().getAll().map { it.ime }
                    "Kometi" -> database.kometDao().getAll().map { it.ime }
                    "Objekti" -> database.objektSuncevogSustavaDao().getAll().map { it.ime }
                    "Zvijezđa" -> database.zvijezdjeDao().getAll().map { it.imeHr }
                    else -> emptyList()
                }
            }
        }

        suspend fun getModulesForCategory(
            category: String,
            allModules: List<com.example.rmai2425_projects_astromap.database.DovrseniModul>
        ): List<com.example.rmai2425_projects_astromap.database.DovrseniModul> {
            return if (category == "Svi") {
                allModules
            } else {
                val objectNames = getObjectNamesForCategory(category)
                allModules.filter { modul ->
                    objectNames.any { objectName ->
                        modul.modulId.trim().equals(objectName.trim(), ignoreCase = true)
                    }
                }
            }
        }

        fun loadModuleData(selectedCategory: String) {
            lifecycleScope.launch {
                try {
                    val database = DatabaseProvider.getDatabase(requireContext())
                    val allModules = withContext(Dispatchers.IO) {
                        database.dovrseniModulDao().getByUserId(userId)
                    }
                    val filteredModules = getModulesForCategory(selectedCategory, allModules)

                    withContext(Dispatchers.Main) {
                        tvDovrseniModuli.text = if (filteredModules.isNotEmpty()) {
                            if (selectedCategory == "Svi") {
                                val groupedText = StringBuilder()
                                val planetModules = getModulesForCategory("Planeti", allModules)
                                val sunModules = getModulesForCategory("Sunce", allModules)
                                val moonModules = getModulesForCategory("Mjeseci", allModules)
                                val asteroidModules = getModulesForCategory("Asteroidi", allModules)
                                val cometModules = getModulesForCategory("Kometi", allModules)
                                val objectModules = getModulesForCategory("Objekti", allModules)
                                val constellationModules = getModulesForCategory("Zvijezđa", allModules)

                                if (planetModules.isNotEmpty()) {
                                    groupedText.append("🪐 PLANETI:\n")
                                    planetModules.forEach {
                                        groupedText.append("• ${it.modulId} (${it.datumDovrsenja.substring(0, 10)})\n")
                                    }
                                    groupedText.append("\n")
                                }

                                if (sunModules.isNotEmpty()) {
                                    groupedText.append("☀️ SUNCE:\n")
                                    sunModules.forEach {
                                        groupedText.append("• ${it.modulId} (${it.datumDovrsenja.substring(0, 10)})\n")
                                    }
                                    groupedText.append("\n")
                                }

                                if (moonModules.isNotEmpty()) {
                                    groupedText.append("🌙 MJESECI:\n")
                                    moonModules.forEach {
                                        groupedText.append("• ${it.modulId} (${it.datumDovrsenja.substring(0, 10)})\n")
                                    }
                                    groupedText.append("\n")
                                }

                                if (asteroidModules.isNotEmpty()) {
                                    groupedText.append("☄️ ASTEROIDI:\n")
                                    asteroidModules.forEach {
                                        groupedText.append("• ${it.modulId} (${it.datumDovrsenja.substring(0, 10)})\n")
                                    }
                                    groupedText.append("\n")
                                }

                                if (cometModules.isNotEmpty()) {
                                    groupedText.append("☄️ KOMETI:\n")
                                    cometModules.forEach {
                                        groupedText.append("• ${it.modulId} (${it.datumDovrsenja.substring(0, 10)})\n")
                                    }
                                    groupedText.append("\n")
                                }

                                if (objectModules.isNotEmpty()) {
                                    groupedText.append("🌌 OBJEKTI:\n")
                                    objectModules.forEach {
                                        groupedText.append("• ${it.modulId} (${it.datumDovrsenja.substring(0, 10)})\n")
                                    }
                                    groupedText.append("\n")
                                }

                                if (constellationModules.isNotEmpty()) {
                                    groupedText.append("⭐ ZVIJEZĐA:\n")
                                    constellationModules.forEach {
                                        groupedText.append("• ${it.modulId} (${it.datumDovrsenja.substring(0, 10)})\n")
                                    }
                                }

                                groupedText.toString().trim()
                            } else {
                                val emoji = when(selectedCategory) {
                                    "Planeti" -> "🪐"
                                    "Sunce" -> "☀️"
                                    "Mjeseci" -> "🌙"
                                    "Asteroidi" -> "☄️"
                                    "Kometi" -> "☄️"
                                    "Objekti" -> "🌌"
                                    "Zvijezđa" -> "⭐"
                                    else -> "•"
                                }
                                filteredModules.joinToString("\n") { modul ->
                                    "$emoji ${modul.modulId} (${modul.datumDovrsenja.substring(0, 10)})"
                                }
                            }
                        } else {
                            if (selectedCategory == "Svi") {
                                "Još nema riješenih modula"
                            } else {
                                "Još nema riješenih modula u kategoriji $selectedCategory"
                            }
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Greška pri dohvaćanju modula: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
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
                loadModuleData(categories[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerCategory.setSelection(0)

        lifecycleScope.launch {
            try {
                val database = DatabaseProvider.getDatabase(requireContext())
                val user = withContext(Dispatchers.IO) {
                    database.korisnikDao().getById(userId)
                }
                val kvizovi = withContext(Dispatchers.IO) {
                    database.kvizRezultatDao().getByUserId(userId)
                }
                val highScore = withContext(Dispatchers.IO) {
                    database.highScoreDao().getHighestScoreByUserId(userId)
                }

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        tvUserName.text = user.ime
                        tvUserEmail.text = user.email
                        tvRegistrationDate.text = "Registriran: ${user.datumRegistracije}"
                    } else {
                        Toast.makeText(requireContext(), "Korisnički podaci nisu pronađeni", Toast.LENGTH_SHORT).show()
                    }

                    tvKvizRezultati.text = if (kvizovi.isNotEmpty()) {
                        kvizovi.joinToString("\n") { kviz ->
                            "${kviz.kvizId}: ${kviz.najboljiRezultat}/10"
                        }
                    } else {
                        "Još nema riješenih kvizova"
                    }

                    tvHighScore.text = if (highScore != null && highScore > 0) {
                        "Najbolji rezultat u igri: $highScore"
                    } else {
                        "Još nema odigranih igara"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Greška pri dohvaćanju podataka: ${e.message}", Toast.LENGTH_SHORT).show()
                }
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
