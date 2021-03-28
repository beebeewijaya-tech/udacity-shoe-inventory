package com.beebee.shoestore.screens.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.beebee.shoestore.R
import com.beebee.shoestore.databinding.FragmentLoginBinding
import com.beebee.shoestore.utils.token.Token

class LoginFragment : Fragment() {
	private lateinit var binding: FragmentLoginBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

		binding.signInButton.setOnClickListener { login() }

		return binding.root
	}

	private fun login() {
		val email = binding.email.text.toString()
		val password = binding.password.text.toString()

		if (email.isEmpty() || password.isEmpty()) {
			Toast.makeText(context, "Please fill all the form", Toast.LENGTH_LONG).show()
		} else {
			Token.saveUserState(context as Activity, email)
			findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
		}
	}
}