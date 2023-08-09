package com.mostafiz.cmed.restapitask.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mostafiz.cmed.restapitask.databinding.ActivityMainBinding
import com.mostafiz.cmed.restapitask.model.CharacterModel
import com.mostafiz.cmed.restapitask.remote.NetworkResult
import com.mostafiz.cmed.restapitask.ui.adapters.CharacterAdapter
import com.mostafiz.cmed.restapitask.utils.ClickInterface
import com.mostafiz.cmed.restapitask.viewmodels.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val characterViewModel: CharacterViewModel by viewModels()

    @Inject
    lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.title="Characters"


        initValues()

        getLetterList()
    }

    private fun initValues() {
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        adapter.setItemClick(object : ClickInterface<CharacterModel> {

            override fun onClick(data: CharacterModel) {
                showDetails(data.id)
            }
        })
    }

    private fun showDetails(id: String) {
        val intent = Intent(this@MainActivity, DetailsActivity::class.java).also {
            it.putExtra(
                "id",
                id
            )
        }
        startActivity(intent)
    }


    @SuppressLint("NotifyDataSetChanged")
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
                    adapter.updateModelList(it.data)
                    adapter.notifyDataSetChanged()
                }

            }
        }
    }
}