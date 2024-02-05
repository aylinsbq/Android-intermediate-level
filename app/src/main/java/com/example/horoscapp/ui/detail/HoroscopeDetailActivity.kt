package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityHoscopeDetailBinding
import com.example.horoscapp.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHoscopeDetailBinding
    private val horoscopoDetailViewModel: HoroscopeDetailViewModel by viewModels()
    private val args: HoroscopeDetailActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopoDetailViewModel.getHoroscope(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBackToHoroscope.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopoDetailViewModel.state.collect {
                    when (it) {
                        is HoroscopeDetailState.Error -> erroState()
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Succes -> succesState(it)
                    }

                }
            }
        }

    }

    private fun loadingState() {
        binding.pbDetail.isVisible = true
    }

    private fun succesState(state: HoroscopeDetailState.Succes) {
        binding.pbDetail.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvDetail.text = state.prediction
        val image = when (state.horoscopeModel){
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius ->R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }
        binding.ivHoroscopeDetail.setImageResource(image)
    }

    private fun erroState() {
        binding.pbDetail.isVisible = false

    }

}