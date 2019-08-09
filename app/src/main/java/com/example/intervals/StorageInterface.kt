package com.example.intervals

interface StorageInterface<T> {

    fun getAll(storageKey : String): ArrayList<T>
    fun append(storageKey : String)
    fun saveArray(storageKey: String)
}