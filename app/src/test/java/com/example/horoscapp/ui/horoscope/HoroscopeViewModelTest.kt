package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.data.Providers.HoroscopoProvider
import com.example.horoscapp.motherObject.HoroscopeMotherObject
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest{
    @MockK
    lateinit var horoscopeProvider: HoroscopoProvider
    private lateinit var viewModel: HoroscopeViewModel
    @Before
    fun setUp(){
        MockKAnnotations.init(this, relaxUnitFun = true)

    }
    @Test
    fun `when viewModel is created then horoscopes are loaded`(){
        // rutina every{}
        // corutina Coevery{}
        every{ horoscopeProvider.getHoroscopo()} returns HoroscopeMotherObject.listOfHoroscopeInfo
        viewModel= HoroscopeViewModel(horoscopeProvider)
        val horoscopes = viewModel.horoscope.value
        assertTrue(horoscopes.isNotEmpty())
    }
}