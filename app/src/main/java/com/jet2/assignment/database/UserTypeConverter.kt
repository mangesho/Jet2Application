package com.jet2.assignment.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class UserTypeConverter {


    /**
     * Convert a list of User object to a Json
     */
    @TypeConverter
    fun fromString(value: String?): List<UserEntity> {
        val listType: Type = object : TypeToken<List<UserEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    /**
     * Convert a json to a list of User objects
     */
    @TypeConverter
    fun fromArrayList(list: List<UserEntity>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}