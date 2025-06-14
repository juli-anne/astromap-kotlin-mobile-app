package com.example.rmai2425_projects_astromap.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.rmai2425_projects_astromap.database.Korisnik
import com.example.rmai2425_projects_astromap.R
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.utils.UserManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity() {
    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var surnameInputLayout: TextInputLayout
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var confirmPasswordInputLayout: TextInputLayout
    private lateinit var nameEditText: TextInputEditText
    private lateinit var surnameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var confirmPasswordEditText: TextInputEditText
    private lateinit var registerButton: Button
    private lateinit var loginLink: TextView
    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userManager = UserManager(this)

        val toolbar = findViewById<Toolbar>(R.id.register_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        nameInputLayout = findViewById(R.id.name_input_layout)
        surnameInputLayout = findViewById(R.id.surname_input_layout)
        emailInputLayout = findViewById(R.id.email_input_layout)
        passwordInputLayout = findViewById(R.id.password_input_layout)
        confirmPasswordInputLayout = findViewById(R.id.confirm_password_input_layout)
        nameEditText = findViewById(R.id.name_edit_text)
        surnameEditText = findViewById(R.id.surname_edit_text)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text)
        registerButton = findViewById(R.id.register_button)
        loginLink = findViewById(R.id.login_link)
    }

    private fun setupClickListeners() {
        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val surname = surnameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (validateInput(name, surname, email, password, confirmPassword)) {
                performRegistration("$name $surname", email, password)
            }
        }

        loginLink.setOnClickListener {
            finish()
        }
    }

    private fun validateInput(name: String, surname: String, email: String, password: String, confirmPassword: String): Boolean {
        nameInputLayout.error = null
        surnameInputLayout.error = null
        emailInputLayout.error = null
        passwordInputLayout.error = null
        confirmPasswordInputLayout.error = null

        if (name.isEmpty()) {
            nameInputLayout.error = "Ime je obavezno"
            return false
        }

        if (surname.isEmpty()) {
            surnameInputLayout.error = "Prezime je obavezno"
            return false
        }

        if (email.isEmpty()) {
            emailInputLayout.error = "Email je obavezan"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputLayout.error = "Unesite valjan email"
            return false
        }

        if (password.isEmpty()) {
            passwordInputLayout.error = "Lozinka je obavezna"
            return false
        }

        if (password.length < 6) {
            passwordInputLayout.error = "Lozinka mora imati najmanje 6 karaktera"
            return false
        }

        if (password != confirmPassword) {
            confirmPasswordInputLayout.error = "Lozinke se ne poklapaju"
            return false
        }

        return true
    }

    private fun performRegistration(fullName: String, email: String, password: String) {
        lifecycleScope.launch {
            try {
                val database = DatabaseProvider.getDatabase(this@RegisterActivity)
                val existingUser = database.korisnikDao().getByEmail(email)

                if (existingUser != null) {
                    Toast.makeText(this@RegisterActivity, "Korisnik s tim emailom već postoji", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val hashedPassword = hashPassword(password)
                val newUser = Korisnik(ime = fullName, email = email, password = hashedPassword)
                val korisnikId = database.korisnikDao().insert(newUser)
                val userWithId = newUser.copy(id = korisnikId.toInt())

                userManager.saveUserSession(userWithId)
                Toast.makeText(this@RegisterActivity, "Uspješno ste se registrirali!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@RegisterActivity, "Greška pri registraciji: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray())
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
}