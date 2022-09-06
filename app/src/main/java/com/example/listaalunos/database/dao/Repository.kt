package com.example.listaalunos.database.dao

import android.content.Context
import com.example.listaalunos.database.AgendaDatabase
import com.example.listaalunos.model.Aluno
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(context: Context) : AlunoDAO {

    private val dao = AgendaDatabase.mInstance(context).getRoomAlunoDao()

    override suspend fun salva(aluno: Aluno): Long{
        return withContext(Dispatchers.Default){
            dao.salva(aluno)
        }
    }

    override suspend fun remove(aluno: Aluno) {
        withContext(Dispatchers.Default){
            dao.remove(aluno)
        }
    }

    override suspend fun todos(): List<Aluno> {
        return withContext(Dispatchers.Default){
            dao.todos()
        }
    }

    override suspend fun edita(aluno: Aluno) {
        withContext(Dispatchers.Default){
            dao.edita(aluno)
        }
    }

}