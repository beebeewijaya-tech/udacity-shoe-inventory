package com.beebee.shoestore.screens.list

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beebee.shoestore.MainViewModel
import com.beebee.shoestore.R
import com.beebee.shoestore.databinding.FragmentListBinding
import com.beebee.shoestore.model.Shoe
import com.beebee.shoestore.utils.token.Token

class ListFragment : Fragment() {
	private val mainViewModel: MainViewModel by activityViewModels()
	private lateinit var binding: FragmentListBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Show Action bar
		(activity as AppCompatActivity?)!!.supportActionBar?.show()
		(activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(false)

		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

		// Initialize View Model
		mainViewModel.listOfShoes.observe(viewLifecycleOwner, Observer { shoes ->
			renderListOfShoes(shoes)
		})

		// Set Menu
		setHasOptionsMenu(true)

		binding.fab.setOnClickListener { findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment()) }
		return binding.root
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.list_menu, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.logout -> logout()
		}
		return super.onOptionsItemSelected(item)
	}

	private fun logout() {
		Token.removeToken(context as Activity)
		findNavController().navigate(ListFragmentDirections.actionListFragmentToSplashFragment())
	}

	private fun renderListOfShoes(shoes: List<Shoe>) {
		val linearView: LinearLayout = binding.shoeLayout

		// Looping shoes list from live data
		for (shoe in shoes) {
			// Create new linear layout progammatically
			val linearLayout = LinearLayout(ContextThemeWrapper(context, R.style.ShoeBackground))
			linearLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

			// Create new textview progammatically
			val nameTextView = TextView(ContextThemeWrapper(context, R.style.ShoeTitle))
			nameTextView.text = shoe.name

			// Create new textview progammatically
			val sizeTextView = TextView(ContextThemeWrapper(context, R.style.ShoeDescription))
			sizeTextView.text = "Size ${shoe.size}"

			// Create new textview progammatically
			val descriptionTextView = TextView(ContextThemeWrapper(context, R.style.ShoeDescription))
			descriptionTextView.text = shoe.description

			// Create new textview progammatically
			val companyTextView = TextView(ContextThemeWrapper(context, R.style.ShoeDescription))
			companyTextView.text = shoe.company

			// Inject textview to linear layout progammatically
			linearLayout.addView(nameTextView)
			linearLayout.addView(companyTextView)
			linearLayout.addView(descriptionTextView)
			linearLayout.addView(sizeTextView)

			// Inject linear layout to main linear layout progammatically
			linearView.addView(linearLayout)
		}
	}
}