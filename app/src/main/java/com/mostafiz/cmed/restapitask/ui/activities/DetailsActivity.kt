package com.mostafiz.cmed.restapitask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mostafiz.cmed.restapitask.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}