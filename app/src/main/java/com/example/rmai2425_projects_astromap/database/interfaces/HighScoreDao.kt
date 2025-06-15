package com.example.rmai2425_projects_astromap.database.interfaces

import androidx.room.*
import com.example.rmai2425_projects_astromap.database.HighScore

@Dao
interface HighScoreDao {
    @Query("SELECT * FROM highscorevi WHERE userId = :userId ORDER BY score DESC LIMIT 1")
    suspend fun getBestScoreByUserId(userId: Int): HighScore?

    @Query("SELECT MAX(score) FROM highscorevi WHERE userId = :userId")
    suspend fun getHighestScoreByUserId(userId: Int): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(highScore: HighScore)

    @Query("SELECT * FROM highscorevi WHERE userId = :userId ORDER BY score DESC")
    suspend fun getAllScoresByUserId(userId: Int): List<HighScore>
}