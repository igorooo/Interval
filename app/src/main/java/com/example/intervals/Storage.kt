package com.example.intervals

import com.google.gson.Gson

/**
 * Currently gonna use shared preferences with gson
 * In future need to switch to internal storage
 */
class Storage(val storageKey: String) : StorageInterface<Exercise>{

    private val mGson = Gson()

    override fun saveArray(storageKey: String) {

    }

    override fun getAll(storageKey: String): ArrayList<Exercise> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun append(storageKey: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}