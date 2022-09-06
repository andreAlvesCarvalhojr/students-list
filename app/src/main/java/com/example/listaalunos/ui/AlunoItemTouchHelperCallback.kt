package com.example.listaalunos.ui

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.listaalunos.database.AgendaDatabase
import com.example.listaalunos.database.dao.AlunoDAO
import com.example.listaalunos.model.Aluno
import com.example.listaalunos.ui.adapter.ListaAlunosAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AlunoItemTouchHelperCallback(private val adapter: ListaAlunosAdapter, val context: Context) :
    ItemTouchHelper.Callback() {

    private val dao: AlunoDAO = AgendaDatabase.mInstance(context)
        .getRoomAlunoDao()

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val marcacoesDeDeslize = ItemTouchHelper.RIGHT
        val marcacoesDeArraste = ItemTouchHelper.RIGHT
        return makeMovementFlags(marcacoesDeArraste, marcacoesDeDeslize)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
//        val posicaoInicial = viewHolder.adapterPosition
//        val posicaoFinal = target.adapterPosition
//        trocaNotas(posicaoInicial, posicaoFinal)
//        return true
//    }
//
//    private fun trocaNotas(posicaoInicial: Int, posicaoFinal: Int) {
//        dao.troca(posicaoInicial, posicaoFinal)
//        adapter.troca(posicaoInicial, posicaoFinal)
//    }
        TODO()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val posicaoDaNotaDeslizada = viewHolder.adapterPosition
        removeAluno(posicaoDaNotaDeslizada)
    }

    private fun removeAluno(posicao: Int) {
        val alunoEscolhido: Aluno = adapter.getItem(posicao)
//        RemoveAlunoTask(alunoEscolhido, dao) {
//            adapter.remove(alunoEscolhido)
//        }

        CoroutineScope(Dispatchers.IO).launch {
            dao.remove(alunoEscolhido)
            withContext(Dispatchers.Main) {
                adapter.remove(alunoEscolhido)
            }
        }
    }
}