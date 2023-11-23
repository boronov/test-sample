package ru.appsmile.test.hotel.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.appsmile.test.hotel.R
import ru.appsmile.test.hotel.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment

        binding.toolbar.setupWithNavController(navHostFragment.navController)

        // Switch back button icon to custom
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_hotel) { // top level destination
                binding.toolbar.navigationIcon = null
            } else {
                binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_navigation_back)
            }
        }
    }
}