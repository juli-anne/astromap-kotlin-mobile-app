package com.example.rmai2425_projects_astromap.utils

import androidx.lifecycle.lifecycleScope
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.DovrseniModul
import com.example.rmai2425_projects_astromap.database.KvizRezultat
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Progress(private val context: android.content.Context) {

    fun getDovrseneModule(userId: Int, callback: (List<DovrseniModul>) -> Unit) {
        (context as androidx.lifecycle.LifecycleOwner).lifecycleScope.launch {
            val database = DatabaseProvider.getDatabase(context)
            val moduli = database.dovrseniModulDao().getByUserId(userId)
            callback(moduli)
        }
    }

    fun insertDovrseniModul(userId: Int, modulId: String) {
        (context as androidx.lifecycle.LifecycleOwner).lifecycleScope.launch {
            val database = DatabaseProvider.getDatabase(context)
            val modul = DovrseniModul(
                userId = userId,
                modulId = modulId,
                datumDovrsenja = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            )
            database.dovrseniModulDao().insert(modul)
        }
    }

    fun getKvizRezultate(userId: Int, callback: (List<KvizRezultat>) -> Unit) {
        (context as androidx.lifecycle.LifecycleOwner).lifecycleScope.launch {
            val database = DatabaseProvider.getDatabase(context)
            val rezultati = database.kvizRezultatDao().getByUserId(userId)
            callback(rezultati)
        }
    }

    fun insertKvizRezultat(userId: Int, kvizId: String, rezultat: Int) {
        (context as androidx.lifecycle.LifecycleOwner).lifecycleScope.launch {
            val database = DatabaseProvider.getDatabase(context)
            val kvizRezultat = KvizRezultat(
                userId = userId,
                kvizId = kvizId,
                najboljiRezultat = rezultat,
                datum = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            )
            database.kvizRezultatDao().insert(kvizRezultat)
        }
    }

    fun updateKvizRezultat(userId: Int, kvizId: String, noviRezultat: Int) {
        (context as androidx.lifecycle.LifecycleOwner).lifecycleScope.launch {
            val database = DatabaseProvider.getDatabase(context)
            database.kvizRezultatDao().updateRezultat(userId, kvizId, noviRezultat)
        }
    }

    fun spremiKvizRezultat(kvizId: String, rezultat: Int) {
        val userManager = UserManager(context)
        if (userManager.isUserLoggedIn()) {
            insertKvizRezultat(userManager.getCurrentUserId(), kvizId, rezultat)
        }
    }
}