package com.beebee.shoestore.utils.token

import android.app.Activity
import android.content.Context

const val SHOE_STORE = "SHOE_STORE"
const val TOKEN = "TOKEN"

class Token {
	companion object {
		@JvmStatic
		fun saveUserState(activity: Activity, user: String) {
			val preferences = activity.getSharedPreferences(SHOE_STORE, Context.MODE_PRIVATE)
			preferences.edit().putString(TOKEN, user).apply();
		}

		fun getToken(activity: Activity): String {
			val preferences = activity.getSharedPreferences(SHOE_STORE, Context.MODE_PRIVATE)
			val token = preferences.getString(TOKEN, null) ?: "";
			return token
		}

		fun removeToken(activity: Activity) {
			val preferences = activity.getSharedPreferences(SHOE_STORE, Context.MODE_PRIVATE)
			preferences.edit().clear().apply()
		}
	}
}