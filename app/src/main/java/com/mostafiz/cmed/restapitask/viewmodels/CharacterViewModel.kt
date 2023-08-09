package com.mostafiz.cmed.restapitask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafiz.cmed.restapitask.model.CharacterModel
import com.mostafiz.cmed.restapitask.model.CharacterResponseModel
import com.mostafiz.cmed.restapitask.remote.NetworkResult
import com.mostafiz.cmed.restapitask.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterRepository):ViewModel() {


    private var _allCharacterResponse=MutableLiveData<NetworkResult<List<CharacterModel>>>()
    val allCharacterResponse: LiveData<NetworkResult<List<CharacterModel>>> =_allCharacterResponse



    fun getAllCharacters(){
        viewModelScope.launch {
            repository.getAllCharacters().collect{
                _allCharacterResponse.postValue(it)
            }
        }
    }



}