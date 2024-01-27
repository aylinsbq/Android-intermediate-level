package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.data.Providers.HoroscopoProvider
import com.example.horoscapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(horoscopoProvider: HoroscopoProvider):ViewModel(){
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope
    init {
        _horoscope.value = horoscopoProvider.getHoroscopo()
    }
}