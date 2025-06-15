package com.example.rmai2425_projects_astromap.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

@Entity(tableName = "suncev_sustav")
data class SuncevSustavInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val naslov: String,
    val kratkiOpis: String,
    val detaljanOpis: String,
    val kategorija: String
)

@Entity(tableName = "planeti")
data class Planet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val kratkiOpis: String,
    val detaljanOpis: String,
    val povrsinskaTemperaturaDan: String,
    val povrsinskaTemperaturaNoc: String,
    val promjer: Double,
    val imaMjesec: Boolean
)

@Entity(tableName = "mjeseci")
data class Mjesec(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val planetId: Int,
    val kratkiOpis: String,
    val detaljanOpis: String,
    val velicina: Double,
    val zanimljivosti: String
)

@Entity(tableName = "sunca")
data class Sunce(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val kratkiOpis: String,
    val detaljanOpis: String,
    val povrsinskaTemperatura: String,
    val temperaturaJezgre: String,
    val promjer: Double,
    val masa: Double,
    val sastav: String
)

@Entity(tableName = "asteroidi")
data class Asteroid(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val kratkiOpis: String,
    val detaljanOpis: String,
    val smjestaj: String,
    val sastav: String
)

@Entity(tableName = "kometi")
data class Komet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val kratkiOpis: String,
    val orbitalniPeriod: Int,
    val posljednjiPerihel: String,
    val sljedeciPerihel: String,
    val velicinaJezgre: Double
)

@Entity(tableName = "objekti")
data class ObjektSuncevogSustava(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val tip: String,
    val smjestaj: String,
    val opis: String,
    val zanimljivosti: String,
    val velicina: String,
    val sastav: String
)

@Entity(tableName = "planeti_mjeseci")
data class VezaIzmeduPlanetaMjeseca(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val planetId: Int,
    val moonId: Int
)

@Entity(tableName = "zvijezdja")
data class Zvijezdje(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imeHr: String,
    val imeLat: String,
    val pozicija: String,
    val znacaj: String,
    val svjetleZvijezde: String
)

@Entity(tableName = "kviz_pitanja")
data class KvizPitanje(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val kategorija: String,
    val pitanje: String,
    val tocniOdgovor: String,
    val netocniOdgovor1: String,
    val netocniOdgovor2: String,
    val netocniOdgovor3: String
)

@Entity(tableName = "korisnici")
data class Korisnik(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val email: String,
    val password: String,
    val datumRegistracije: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)

@Entity(tableName = "dovrseni_moduli")
data class DovrseniModul(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val modulId: String,
    val datumDovrsenja: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)

@Entity(tableName = "kviz_rezultati")
data class KvizRezultat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val kvizId: String,
    val najboljiRezultat: Int,
    val datum: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)

@Entity(tableName = "highscorevi")
data class HighScore(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val score: Int,
    val datum: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
)
