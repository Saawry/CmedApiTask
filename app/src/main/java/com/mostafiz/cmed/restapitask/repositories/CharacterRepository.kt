package com.mostafiz.cmed.restapitask.repositories



import com.mostafiz.cmed.restapitask.remote.ApiService
import com.mostafiz.cmed.restapitask.remote.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class CharacterRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun getAllCharacters()= flow {
        emit(NetworkResult.Loading(true))
        val response=apiService.getAllCharacters()
        emit(NetworkResult.Success(response))
    }.catch{e->
        emit(NetworkResult.Failure( e.message?: "Unknown Error"))
    }


    suspend fun getCharacter(id:String)= flow {
        emit(NetworkResult.Loading(true))
        val response=apiService.getCharacter(id)
        emit(NetworkResult.Success(response))
    }.catch{e->
        emit(NetworkResult.Failure( e.message?: "Unknown Error"))
    }

}