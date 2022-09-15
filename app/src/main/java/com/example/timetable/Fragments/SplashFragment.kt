package com.example.timetable.Fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timetable.R
import com.example.timetable.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    lateinit var handler: Handler
    var time = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        handler = Handler(requireActivity().mainLooper)

        handler.postDelayed(runnable, 100)

        return binding.root
    }

    private val runnable: Runnable = object : Runnable {
        override fun run() {
            if (time != 100) {
                time += 20
                binding.progress.progress = time
                handler.postDelayed(this, 700)
            }else{
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }
}