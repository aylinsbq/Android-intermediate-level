package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscapp.databinding.ActivityHoscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding :ActivityHoscopeDetailBinding
    private val horoscopoDetailViewModel:HoroscopeDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}