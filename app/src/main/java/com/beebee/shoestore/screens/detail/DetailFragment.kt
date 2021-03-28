package com.beebee.shoestore.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beebee.shoestore.MainViewModel
import com.beebee.shoestore.R
import com.beebee.shoestore.databinding.FragmentDetailBinding
import com.beebee.shoestore.model.Shoe

class DetailFragment : Fragment() {
	private lateinit var binding: FragmentDetailBinding
	private val mainViewModel: MainViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
		binding.lifecycleOwner = this

		binding.cancelButton.setOnClickListener { findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListFragment()) }
		binding.mainViewModel = mainViewModel
		mainViewModel.shoeAdded.observe(viewLifecycleOwner, Observer { shoeAdded ->
			if (shoeAdded) {
				findNavController().popBackStack()
				mainViewModel.shoeAddedComplete()
			}
		})
		return binding.root
	}
}