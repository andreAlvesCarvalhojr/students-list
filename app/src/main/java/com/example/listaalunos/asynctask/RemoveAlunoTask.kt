package com.example.listaalunos.asynctask

//import android.os.AsyncTask
//import com.example.listaalunos.database.dao.AlunoDAO
//import com.example.listaalunos.model.Aluno
//
//class RemoveAlunoTask(
//    private val aluno: Aluno,
//    private val dao: AlunoDAO,
//    private val alunosEncontradas: (notas: Aluno) -> Unit
//) : AsyncTask<Void, Void, Aluno>() {
//
//    @Deprecated("Deprecated in Java")
//    override fun doInBackground(vararg params: Void?): Aluno? {
//        dao.remove(aluno)
//        return null
//    }
//
//    @Deprecated("Deprecated in Java")
//    override fun onPostExecute(result: Aluno?) {
//        super.onPostExecute(result)
//        alunosEncontradas
//
////        result?.let { aluno ->
////            alunosEncontradas(aluno)
////        }
//    }
//}