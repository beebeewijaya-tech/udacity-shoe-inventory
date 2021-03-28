package com.beebee.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beebee.shoestore.model.Shoe
import timber.log.Timber

class MainViewModel: ViewModel() {
	private val _listOfShoes = MutableLiveData<MutableList<Shoe>>()
	val listOfShoes: LiveData<MutableList<Shoe>>
		get() = _listOfShoes

	private val _shoeAdded = MutableLiveData<Boolean>()
	val shoeAdded: LiveData<Boolean>
		get() = _shoeAdded

	init {
		_listOfShoes.value = mutableListOf(
			Shoe(
				"Nike El Matador",
				"Super Shoes with an Extra layer on the sole",
				"38",
				"Nike Inc."
			),
			Shoe(
				"Nike El Matador",
				"Super Shoes with an Extra layer on the sole",
				"38",
				"Nike Inc."
			)
		)
	}

	fun saveShoe(shoe: Shoe) {
		val listShoes = _listOfShoes.value ?: mutableListOf()
		listShoes?.add(shoe)
		Timber.i("listShoes ${listShoes.toString()}")
		_listOfShoes.postValue(listShoes)
		_shoeAdded.value = true
	}

	fun shoeAddedComplete() {
		_shoeAdded.value = false
	}
}