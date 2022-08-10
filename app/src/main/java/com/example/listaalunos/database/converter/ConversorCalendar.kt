package com.example.listaalunos.database.converter

import androidx.room.TypeConverter
import java.util.*

class ConversorCalendar {

    @TypeConverter
    fun paraLong(valor: Calendar): Long {
        return valor.timeInMillis
    }

    @TypeConverter
    fun paraCalendar(valor: Long): Calendar {
        val momentoAtual: Calendar = Calendar.getInstance()
        momentoAtual.timeInMillis = valor
        return momentoAtual
    }
}
