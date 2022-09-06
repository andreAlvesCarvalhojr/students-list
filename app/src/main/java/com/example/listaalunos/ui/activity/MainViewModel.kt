package com.example.listaalunos.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.listaalunos.database.dao.Repository
import com.example.listaalunos.model.Aluno
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val alunoLiveData = MutableLiveData<List<Aluno>>()
    var mAluno = MutableLiveData<Aluno>()

    fun salva(aluno: Aluno) {
        viewModelScope.launch {
            repository.salva(aluno)
            mAluno.value = aluno
        }
    }

    fun edita(aluno: Aluno) {
        viewModelScope.launch {
            repository.edita(aluno)
            mAluno.value = aluno
        }
    }

    fun remove(aluno: Aluno) {
        viewModelScope.launch {
            repository.remove(aluno)
        }
    }

    fun todos() {
        viewModelScope.launch {
            alunoLiveData.value = repository.todos()
        }
    }

    class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }

}