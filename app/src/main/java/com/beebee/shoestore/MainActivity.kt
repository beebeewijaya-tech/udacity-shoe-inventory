package com.beebee.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.beebee.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

		val navController = findNavController(R.id.MainNavHost)

		NavigationUI.setupActionBarWithNavController(this, navController)
	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = this.findNavController(R.id.MainNavHost)
		return navController.navigateUp()
	}
}