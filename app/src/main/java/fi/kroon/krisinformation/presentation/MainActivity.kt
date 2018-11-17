package fi.kroon.krisinformation.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import fi.kroon.krisinformation.Application
import fi.kroon.krisinformation.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application[applicationContext].cmp.inject(application)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.mainNavigationFragment)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.mainNavigationFragment).navigateUp() || super.onSupportNavigateUp()
}