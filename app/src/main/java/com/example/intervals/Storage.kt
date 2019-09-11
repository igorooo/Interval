package com.example.intervals

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Currently gonna use shared preferences with gson
 * In future need to switch to internal storage
 */
class Storage(val context: Context) : StorageInterface<Exercise>{

    private var SP_KEY = "Storage"
    private var DEFAULT_VAL = ""

    private val mGson = Gson()
    private val mSharedPreferences = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)

    override fun saveArray(arrayList: ArrayList<Exercise>, storageKey: String) {

        val json = mGson.toJson(arrayList)
        val editor = mSharedPreferences.edit()
        editor.putString(storageKey, json)
        editor.apply()
    }

    override fun getAll(storageKey: String): ArrayList<Exercise> {
        val json = mSharedPreferences.getString(storageKey,DEFAULT_VAL)
        val type = object : TypeToken<ArrayList<Exercise>>(){}.type

        var arrayList = mGson.fromJson<ArrayList<Exercise>>(json,type)

        if(arrayList == null){
            arrayList = ArrayList<Exercise>()
        }
        return arrayList
    }

    override fun append(exercise: Exercise, storageKey: String) {

        val arrayList = getAll(storageKey)
        arrayList.add(exercise)
        saveArray(arrayList,storageKey)
    }
}