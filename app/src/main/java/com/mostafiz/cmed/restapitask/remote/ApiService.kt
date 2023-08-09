package com.mostafiz.cmed.restapitask.remote


import com.mostafiz.cmed.restapitask.model.CharacterModel

import retrofit2.http.*

interface ApiService {
    @GET("/api/characters")
    suspend fun getAllCharacters():List<CharacterModel>

    @GET("/api/character/{id}")
    suspend fun getCharacter(@Path("id") id:String):List<CharacterModel>
}