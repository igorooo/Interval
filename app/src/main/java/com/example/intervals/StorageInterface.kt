package com.example.intervals

interface StorageInterface<T> {

    fun getAll(storageKey : String): ArrayList<T>
    fun append(exercise: Exercise, storageKey : String)
    fun saveArray(arrayList: ArrayList<T>, storageKey: String)
}