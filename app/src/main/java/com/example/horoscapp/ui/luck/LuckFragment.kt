package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import com.example.horoscapp.ui.core.listeners.OnSwipeTouchListener
import com.example.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val currentLuck = randomCardProvider.getLucky()
        currentLuck?.let { luck ->
            binding.tvLucky.text = getString(luck.text)
            binding.ivLuckyCard.setImageResource(luck.image)
            binding.tvShare.setOnClickListener {
                shareResult(getString(luck.text))
            }
        }
    }

    private fun shareResult(prediction: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }
        val shareIntent: Intent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        //binding.ivRoulete.setOnClickListener {spinRoulete()}
        binding.ivRoulete.setOnTouchListener(
        object :OnSwipeTouchListener(requireContext()){
            override fun onSwipeRight() {
                spinRoulete()
            }

            override fun onSwipeLeft() {
                spinRoulete()
            }
        })
    }

    private fun spinRoulete() {
        val random = Random.nextInt(360, 1440)
        val animator =
            ObjectAnimator.ofFloat(binding.ivRoulete, View.ROTATION, 0f, random.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.ivCardBackSmall.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                growUp()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        binding.ivCardBackSmall.startAnimation(slideUpAnimation)
    }

    private fun growUp() {
        val growUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow_up)
        growUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.ivCardBackSmall.isVisible = false
                showPremonitionView()
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })
        binding.ivCardBackSmall.startAnimation(growUpAnimation)
    }

    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200
        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000
        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })
        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }

}