package hu.bme.aut.recipebase.persistence.converter

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*

class Converters {
    @TypeConverter
    fun fromLong(value: Long?): BigDecimal? {
        return if (value == null) null else BigDecimal(value).divide(BigDecimal(100))
    }

    @TypeConverter
    fun toLong(bigDecimal: BigDecimal?): Long? {
        return bigDecimal?.multiply(BigDecimal(100))?.toLong()
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}