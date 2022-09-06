package com.example.listaalunos.asynctask

//import android.os.AsyncTask
//import com.example.listaalunos.database.dao.AlunoDAO
//import com.example.listaalunos.model.Aluno
//
//class BuscaAlunoTask(
//    private val dao: AlunoDAO,
//    private val alunosEncontradas: (notas: List<Aluno>) -> Unit )
//    : AsyncTask<Unit, Unit, List<Aluno>>() {
//
////    var result : List<Aluno>? = null
//
//    override fun doInBackground(vararg params: Unit?): List<Aluno> {
//        return dao.todos()
//    }
//
//    override fun onPostExecute(result: List<Aluno>?) {
//        result?.let { aluno ->
//            alunosEncontradas(aluno)
//        }
//    }
//}