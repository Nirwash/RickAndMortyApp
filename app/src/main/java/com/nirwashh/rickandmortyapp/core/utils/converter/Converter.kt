package com.nirwashh.rickandmortyapp.core.utils.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nirwashh.rickandmortyapp.characters.data.model.Location


object Converter {
    @JvmStatic
    @TypeConverter
    fun listToString(list: List<String>): String = Gson().toJson(list)

    @JvmStatic
    @TypeConverter
    fun stringToList(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @JvmStatic
    @TypeConverter
    fun locationToString(location: Location): String {
        return Gson().toJson(location)
    }

    @JvmStatic
    @TypeConverter
    fun stringToLocation(json: String): Location {
        val listType = object : TypeToken<Location>() {}.type
        return Gson().fromJson(json, listType)
    }
}