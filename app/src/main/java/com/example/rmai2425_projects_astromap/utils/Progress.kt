package com.example.rmai2425_projects_astromap.utils

import com.example.rmai2425_projects_astromap.database.DovrseniModul
import com.example.rmai2425_projects_astromap.database.KvizRezultat
import com.example.rmai2425_projects_astromap.database.EntitiesDao

class Progress(
    private val dao: EntitiesDao,
    private val userManager: UserManager
) {

    suspend fun oznaciModulDovrsen(modulId: String) {
        val userId = userManager.getCurrentUserId()
        if (userId != -1) {
            val dovrseni = dao.getDovrseneModule(userId)
            val postoji = dovrseni.any { it.modulId == modulId }
            if (!postoji) {
                dao.insertDovrseniModul(
                    DovrseniModul(
                        userId = userId,
                        modulId = modulId
                    )
                )
            }
        }
    }

    suspend fun spremiKvizRezultat(kvizId: String, rezultat: Int) {
        val userId = userManager.getCurrentUserId()
        if (userId != -1) {
            val sviRezultati = dao.getKvizRezultate(userId)
            val postojeci = sviRezultati.firstOrNull { it.kvizId == kvizId }
            if (postojeci == null || rezultat > postojeci.najboljiRezultat) {
                if (postojeci != null) {
                    dao.updateKvizRezultat(userId, kvizId, rezultat)
                } else {
                    dao.insertKvizRezultat(
                        KvizRezultat(
                            userId = userId,
                            kvizId = kvizId,
                            najboljiRezultat = rezultat
                        )
                    )
                }
            }
        }
    }

    suspend fun getDovrseneModule(userId: Int) = dao.getDovrseneModule(userId)
    suspend fun getKvizRezultate(userId: Int) = dao.getKvizRezultate(userId)
}