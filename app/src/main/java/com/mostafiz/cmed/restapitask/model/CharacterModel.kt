package com.mostafiz.cmed.restapitask.model
import com.beust.klaxon.*

data class CharacterModel (
    val id: String,
    val name: String,
    @Json(name = "alternate_names")
    val alternateNames: List<String>?,
    val species: String,
    val gender: String?,
    val house: String,
    val dateOfBirth: String?,
    val yearOfBirth: Long?,
    val wizard: Boolean,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val wand: Wand,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    @Json(name = "alternate_actors")
    val alternateActors: List<String>,
    val alive: Boolean,
    val image: String
)


data class Wand (
    val wood: String,
    val core: String,
    val length: Double? = null
)

