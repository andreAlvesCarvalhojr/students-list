package com.example.listaalunos.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listaalunos.R
import com.example.listaalunos.model.Aluno
import com.example.listaalunos.ui.adapter.listener.OnItemClickListener
import java.util.*

class ListaAlunosAdapter(
    private var context: Context
) :
    RecyclerView.Adapter<ListaAlunosAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    private var aluno: ArrayList<Aluno> = ArrayList()

    fun setOnITemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    /*View.OnCreateContextMenuListener */ {
        private var nome: TextView
        private var telefone: TextView
        private lateinit var aluno: Aluno

        init {
            nome = view.findViewById(R.id.item_aluno_nome)
            telefone = view.findViewById(R.id.item_aluno_telefone)

            view.setOnClickListener {
                onItemClickListener.onItemClick(aluno, adapterPosition)
            }

//            view.setOnCreateContextMenuListener(this)
        }

        fun vincula(aluno: Aluno) {
            this.aluno = aluno
            preencheCampo(aluno)
        }

        private fun preencheCampo(aluno: Aluno) {
            nome.text = aluno.getNomeCompleto()
//            dao = AgendaDatabase.mInstance(context).getTelefoneDao()
//            val primeiroTelefone: Telefone = dao.buscaPrimeiroTelefoneDoAluno(aluno.getId())
//            if (primeiroTelefone != null) {
//                telefone.text = primeiroTelefone.numero
//            }
        }

//        override fun onCreateContextMenu(
//            menu: ContextMenu,
//            v: View?,
//            menuInfo: ContextMenu.ContextMenuInfo?
//        ) {
//            menu.add(Menu.NONE, R.id.activity_lista_alunos_menu_remover, Menu.NONE, "Remover")
//        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val aluno: Aluno = aluno[position]
        viewHolder.vincula(aluno)
    }

    override fun getItemCount() = aluno.size

    fun getItem(position: Int) = this.aluno[position]

    fun adiciona(alunos: Aluno) {
        aluno.add(alunos)
        notifyDataSetChanged()
    }

    fun edita(aluno: Aluno, posicao: Int) {
        this.aluno[posicao] = aluno
        notifyDataSetChanged()
    }

    fun remove(aluno: Aluno) {
        this.aluno.remove(aluno)
        notifyDataSetChanged()
    }

    fun atualiza(alunos: List<Aluno>) {
        aluno.clear()
        aluno.addAll(alunos)
        notifyDataSetChanged()
    }

    fun troca(posicaoInicial: Int, posicaoFinal: Int) {
        Collections.swap(aluno, posicaoInicial, posicaoFinal)
        notifyItemMoved(posicaoInicial, posicaoFinal)
    }

}
