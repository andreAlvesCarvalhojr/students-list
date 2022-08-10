package com.example.listaalunos.ui.adapter.listener

import com.example.listaalunos.model.Aluno

interface OnItemClickListener {

    fun onItemClick(aluno: Aluno, posicao: Int)
}