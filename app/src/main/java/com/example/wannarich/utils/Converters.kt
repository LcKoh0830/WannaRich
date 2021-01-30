package com.example.wannarich.utils

import androidx.room.TypeConverter
import java.util.*

/**
 * Convert epoch time to date
 */
object Converters {
    @TypeConverter
    @JvmStatic
    fun fromLong(value: Long): Date? = Date(value)

    @TypeConverter
    @JvmStatic
    fun toLong(value: Date?): Long = value?.time ?: 0
}