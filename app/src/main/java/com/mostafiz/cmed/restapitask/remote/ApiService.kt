package com.mostafiz.cmed.restapitask.remote


import com.mostafiz.cmed.restapitask.model.CharacterResponseModel
import retrofit2.http.*

interface ApiService {
    @GET("/api/characters")
    suspend fun getAllCharacters():CharacterResponseModel
}