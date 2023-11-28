package com.mdg.myancare_codetest.Utli

import android.content.Context
import android.content.SharedPreferences
import com.example.example.BeerDataObj
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val gson: Gson = Gson()

    fun saveArrayList(key: String, arrayList: ArrayList<BeerDataObj>) {
        val json = gson.toJson(arrayList)
        sharedPreferences.edit().putString(key, json).apply()
    }

    fun getArrayList(key: String): ArrayList<BeerDataObj> {
        val json = sharedPreferences.getString(key, null)
        val type = object : TypeToken<ArrayList<BeerDataObj>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }

    fun ClearData(key: String) {
        sharedPreferences.edit().remove(key)
    }
}