package com.example.listaalunos.asynctask

import android.os.AsyncTask
import com.example.listaalunos.database.dao.AlunoDAO
import com.example.listaalunos.model.Aluno

class SalvaAlunoTask(
    private val aluno: Aluno,
    private val dao: AlunoDAO,
    private val alunosEncontradas: (aluno: Aluno) -> Unit
) : AsyncTask<Void, Void, Aluno>() {
    override fun doInBackground(vararg params: Void?): Aluno? {
        dao.salva(aluno)
        return null
    }

    override fun onPostExecute(result: Aluno?) {
        result?.let { aluno ->
            alunosEncontradas(aluno)
        }

    }

}
