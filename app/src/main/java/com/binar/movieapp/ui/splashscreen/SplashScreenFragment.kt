package com.binar.movieapp.ui.splashscreen

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.binar.movieapp.R

class SplashScreenFragment : Fragment() {

//    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("DATA_INFO", AppCompatActivity.MODE_PRIVATE)

        val email = sharedPreferences.getString("email", null)



        if (email != null){
            findNavController().navigate(R.id.action_splashScreenFragment_to_nav_movie)
        } else {
            Handler(Looper.myLooper()!!).postDelayed({
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }, 2000)
        }

//        Handler(Looper.myLooper()!!).postDelayed({
//            findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
//        }, 2000)
        return view
    }
}