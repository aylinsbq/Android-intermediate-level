package com.example.horoscapp.data.network.response

import com.example.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

//{
//    "date": "2020-01-01",
//    "horoscope": "Hoy será un día asqueroso para ti.",
//    "icon": "https://newastro.vercel.app/static/assets/zodiac-1.png",
//    "id": 10,
//    "sign": "aries"
//}
data class PredictionResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String,
) {
    fun toDomain(): PredictionModel {
        return PredictionModel(
            horoscope = horoscope,
            sign = sign
        )

    }
}