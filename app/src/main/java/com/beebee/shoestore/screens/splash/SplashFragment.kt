package com.beebee.shoestore.screens.splash

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.beebee.shoestore.R
import com.beebee.shoestore.databinding.FragmentSplashBinding
import com.beebee.shoestore.utils.token.Token

class SplashFragment : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Remove action bar
		(activity as AppCompatActivity?)!!.supportActionBar?.hide()

		// Inflate the layout for this fragment
		val binding: FragmentSplashBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

		navigateToOther()
		return binding.root
	}

	private fun navigateToOther() {
		Handler().postDelayed({
			val token = Token.getToken(context as Activity)
			if (token.isEmpty()) {
				findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
			} else {
				findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToListFragment())
			}
		}, 1500)
	}
}