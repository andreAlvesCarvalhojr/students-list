package com.example.listaalunos.ui

import android.content.Context
import android.widget.AdapterView.AdapterContextMenuInfo
import com.example.listaalunos.database.AgendaDatabase


class ListaAlunoView(var context: Context) {

    private lateinit var mItem: AdapterContextMenuInfo

//    private var adapter: ListaAlunosAdapter = ListaAlunosAdapter(AlunoDAO.todos(), context)

    private val dao: com.example.listaalunos.database.dao.AlunoDAO = AgendaDatabase.mInstance(context)
        .getRoomAlunoDao()

//    var positiveButtonClick = { dialog: DialogInterface, which: Int ->
//        Toast.makeText(context, "YES", Toast.LENGTH_SHORT).show()
//    }
//
//    var negativeButtonClick = { dialog: DialogInterface, which: Int ->
//        Toast.makeText(context, "NO", Toast.LENGTH_SHORT).show()
//    }
//
//    var neutralButtonClick = { dialog: DialogInterface, which: Int ->
//        Toast.makeText(context, "Talvez", Toast.LENGTH_SHORT).show()
//    }

//    fun confirmaRemocao(item: MenuItem) {
//        AlertDialog
//            .Builder(context)
//            .setTitle("Remover Aluno")
//            .setMessage("Tem certeza que quer remover aluno?")
//            .setPositiveButton("Sim") { _, _ ->
//                //ERROR - Item.menuInfo retornando null
//                val menuItem: AdapterView.AdapterContextMenuInfo= item.menuInfo as AdapterView.AdapterContextMenuInfo
//                    val alunoEscolhido: Aluno =
//                        adapter.getItem(menuItem.position)
//                    remove(alunoEscolhido)
//            }
//            .setNegativeButton("Não", null)
//            .show()


//        val builder = androidx.appcompat.app.AlertDialog.Builder(context);
//        builder.setTitle("Remover Aluno")
//        builder.setMessage("Tem certeza que quer remover aluno?")
//        builder.setPositiveButton("OK", {dialog : DialogInterface, which: Int ->
//            val menuItem = AdapterView.AdapterContextMenuInfo = item.menuInfo as
//        })
//        builder.setNegativeButton(android.R.string.no, negativeButtonClick)
//        builder.setNeutralButton("Talvez", neutralButtonClick)
//        builder.show()


//        AlertDialog
//            .Builder(context)
//            .setTitle("Confirm Delete")
//            .setMessage("Do you want to delete this blank?")
//            .setPositiveButton("Ok") { _, _ ->
//                mItem = item.menuInfo as AdapterContextMenuInfo
//                val alunoEscolhido: Aluno = adapter.getItem(mItem.position)
//                remove(alunoEscolhido)
//            }
//            .setNeutralButton("Cancel", null)
//            .create()
//            .show()

//    }

//        AdapterView.OnItemLongClickListener { _, _, position, _ ->
//            val alunoEscolhido: Aluno = adapter.getItem(position)
//            dao.remove(alunoEscolhido)
//            adapter.remove(alunoEscolhido)
//            true
//        }

//    private fun remove(aluno: Aluno) {
//        dao.remove(aluno)
//        adapter.remove(aluno)
//    }

//    fun atualizaAlunos() {
//        adapter.atualiza(dao.todos())
//    }
}
