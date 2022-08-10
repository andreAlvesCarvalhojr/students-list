package com.example.listaalunos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.DateFormat.getDateInstance
import java.util.*

@Entity
class Aluno(val nome: String,
            val telefone: String,
            val email: String) : Serializable {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    @ColumnInfo
    private var momentoDeCadastro: Calendar = Calendar.getInstance()

    @JvmName("getMomentoDeCadastro1")
    fun getMomentoDeCadastro(): Calendar {
        return momentoDeCadastro
    }

    @JvmName("setMomentoDeCadastro1")
    fun setMomentoDeCadastro(momentoDeCadastro: Calendar?) {
        this.momentoDeCadastro = momentoDeCadastro!!
    }


    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    override fun toString(): String {
        return nome
    }

    fun getNomeCompleto(): String {
        return nome
    }

    // Implementa data
    private fun dataFormatada(): String {
        val formatador = getDateInstance()
        return formatador.format(momentoDeCadastro.time)
    }

}



