package com.jet2.assignment.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MediaTypeConverter {

    /**
     * Convert a list of Media object to a Json
     */
    /**
     * Convert a list of Media object to a Json
     */
    @TypeConverter
    fun fromString(value: String?): List<MediaEntity> {
        val listType: Type = object : TypeToken<List<MediaEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    /**
     * Convert a json to a list of Media objects
     */
    @TypeConverter
    fun fromArrayList(list: List<MediaEntity>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}