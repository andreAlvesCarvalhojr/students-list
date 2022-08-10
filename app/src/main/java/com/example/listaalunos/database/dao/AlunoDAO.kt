package com.example.listaalunos.database.dao

import androidx.room.*
import com.example.listaalunos.model.Aluno

@Dao
interface AlunoDAO {
    @Insert
    fun salva(aluno: Aluno): Long

    @Delete
    fun remove(aluno: Aluno)

    @Query("SELECT * FROM aluno")
    fun todos(): List<Aluno>

    @Update
    fun edita(aluno: Aluno)

    @Update
    fun troca(posicaoInicial: Int, posicaoFinal: Int) {
        TODO("Not yet implemented")
    }
}