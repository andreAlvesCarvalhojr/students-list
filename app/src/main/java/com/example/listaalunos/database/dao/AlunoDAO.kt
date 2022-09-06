package com.example.listaalunos.database.dao

import androidx.room.*
import com.example.listaalunos.model.Aluno

@Dao
interface AlunoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(aluno: Aluno): Long

    @Delete
    suspend fun remove(aluno: Aluno)

    @Query("SELECT * FROM aluno")
    suspend fun todos(): List<Aluno>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun edita(aluno: Aluno)

//    @Update
//    fun troca(posicaoInicial: Int, posicaoFinal: Int)
}