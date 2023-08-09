package com.mostafiz.cmed.restapitask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.mostafiz.cmed.restapitask.remote.NetworkResult
import com.mostafiz.cmed.restapitask.viewmodels.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLetterList()
    }
    private fun getLetterList() {

        characterViewModel.getAllCharacters()
        characterViewModel.allCharacterResponse.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Failure -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()

                    Log.d("GetData", "getCharacterList error: " + it.errorMessage)
                }

                is NetworkResult.Success -> {
                    Log.d("GetData", "getCharacterList size : " + it.data.size)
                    Log.d("GetData", "getCharacterList data : " + it.data.toString())

                }

            }
        }
    }
}