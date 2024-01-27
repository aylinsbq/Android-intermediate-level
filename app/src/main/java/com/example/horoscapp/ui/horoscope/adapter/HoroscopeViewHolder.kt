package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo: HoroscopeInfo, onitemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvHoroscope.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.text=context.getText(horoscopeInfo.name)
        binding.itemHoroscopo.setOnClickListener{
            startAnimation(binding.ivHoroscope, newLambda = {onitemSelected(horoscopeInfo)})
            //onitemSelected(horoscopeInfo)
        }



    }

    fun startAnimation(view: View, newLambda: () -> Unit){
        view.animate().apply {
            duration=500
            interpolator= LinearInterpolator()
            rotationBy(360f)
            withEndAction { newLambda() }
            start()
        }

    }
}