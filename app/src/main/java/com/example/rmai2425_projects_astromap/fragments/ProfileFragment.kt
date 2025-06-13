package com.example.rmai2425_projects_astromap.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.activities.LoginActivity
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.utils.UserManager
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var userManager: UserManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)
        val userId = userManager.getCurrentUserId()
        if (userId == -1) {
            Toast.makeText(requireContext(), "Korisnik nije prijavljen", Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch {
            try {
                val dao = DatabaseProvider.getDatabase(requireContext()).entitiesDao()
                val user = dao.getUserById(userId)
                if (user != null) {
                    tvUserName.text = user.ime
                    tvUserEmail.text = user.email
                    tvRegistrationDate.text = "Registriran: ${user.datumRegistracije}"
                } else {
                    Toast.makeText(requireContext(), "Korisnički podaci nisu pronađeni", Toast.LENGTH_SHORT).show()
                }

                // Prikaz dovršenih modula
                val moduli = dao.getDovrseneModule(userId)
                tvDovrseniModuli.text = if (moduli.isNotEmpty()) {
                    "Riješeni moduli:\n${moduli.joinToString("\n") { it.modulId }}"
                } else {
                    "Još nema riješenih modula"
                }

                // Prikaz rezultata kvizova
                val kvizovi = dao.getKvizRezultate(userId)
                tvKvizRezultati.text = if (kvizovi.isNotEmpty()) {
                    "Rezultati kvizova:\n${kvizovi.joinToString("\n") { "${it.kvizId}: ${it.najboljiRezultat}/10" }}"
                } else {
                    "Još nema riješenih kvizova"
                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Greška: ${e.message}", Toast.LENGTH_SHORT).show()
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