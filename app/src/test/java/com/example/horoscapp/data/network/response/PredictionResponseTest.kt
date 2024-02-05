package com.example.horoscapp.data.network.response

import com.example.horoscapp.motherObject.HoroscopeMotherObject
import io.kotlintest.shouldBe
import org.junit.Assert.*
import org.junit.Test
import kotlin.math.sign

class PredictionResponseTest {
    @Test
    fun `toDomain Should return a correct Prediction Model`() {
        //given
        val predictionResponse = HoroscopeMotherObject.anyResponse.copy(sign="libra")

        //when
        val predictionModel = predictionResponse.toDomain()

        //then
        predictionModel.sign shouldBe  predictionResponse.sign
        predictionModel.horoscope shouldBe predictionResponse.horoscope

    }
}