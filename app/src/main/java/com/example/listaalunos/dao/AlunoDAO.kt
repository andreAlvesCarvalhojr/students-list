package com.example.listaalunos.dao

import com.example.listaalunos.model.Aluno

object AlunoDAO : ArrayList<Aluno>() {
    private val alunos = ArrayList<Aluno>()
    private var contadorDeIds: Int = 1

    fun salva(aluno: Aluno) {
        aluno.setId(contadorDeIds)
        alunos.add(aluno)
        atualizaIds()
    }

    private fun atualizaIds() {
        contadorDeIds++
    }

    fun edita(aluno: Aluno, posicao: Int) {
        alunos[posicao] = aluno
    }

    private fun buscaAlunoPeloId(aluno: Aluno): Aluno? {
        for ( a: Aluno in  alunos){
            if (a.getId() == aluno.getId())
                return a
        }
        return  null
    }

    fun todos(): ArrayList<Aluno> {
        return ArrayList(alunos)
    }

//    override fun remove(element: Aluno): Boolean {
//        val alunoDevolvido: Aluno? = buscaAlunoPeloId(element)
//        if(alunoDevolvido != null){
//            alunos.remove(alunoDevolvido)
//        }
//        return alunos.remove(alunoDevolvido)
//    }
}