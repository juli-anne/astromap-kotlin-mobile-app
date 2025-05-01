package com.example.rmai2425_projects_astromap.database

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val ime: String,
    val pozicija: String,
    val znacaj: String,
    val svjetleZvijezde: String
)