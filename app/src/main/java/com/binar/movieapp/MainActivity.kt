package com.binar.movieapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.binar.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()


        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_movie, R.id.nav_favorite
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.apply {
            navBottom.setupWithNavController(navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment) {
                nav_bottom.visibility = View.GONE

            } else if (destination.id == R.id.registerFragment) {
                nav_bottom.visibility = View.GONE

            } else if (destination.id == R.id.splashScreenFragment) {
                nav_bottom.visibility = View.GONE
            } else if (destination.id == R.id.profileFragment){
                nav_bottom.visibility = View.GONE
            } else {
                nav_bottom.visibility = View.VISIBLE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}