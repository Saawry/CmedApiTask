package com.mostafiz.cmed.restapitask.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mostafiz.cmed.restapitask.databinding.ActivityDetailsBinding
import com.mostafiz.cmed.restapitask.model.CharacterModel
import com.mostafiz.cmed.restapitask.remote.NetworkResult
import com.mostafiz.cmed.restapitask.viewmodels.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailsBinding
    private val characterViewModel: CharacterViewModel by viewModels()

    private var id = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title="Details"
        id = intent.getStringExtra("id").toString()


        getSingleCharacter(id)

    }

    private fun getSingleCharacter(id: String) {
        characterViewModel.getAllCharacters(id)

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

                    showDetails(it.data[0])
                }

            }
        }

    }

    private fun showDetails(characterModel: CharacterModel) {

        binding.tvName.setText(characterModel.name)
        binding.tvGender.setText(characterModel.gender ?: "")
        binding.tvDob.setText(characterModel.dateOfBirth ?: ("" + characterModel.yearOfBirth))

        Glide
            .with(this)
            .load(characterModel.image)
            .centerCrop()
            .into(binding.profileImage)

    }
}