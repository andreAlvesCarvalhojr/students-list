package com.example.listaalunos.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.listaalunos.R
import com.example.listaalunos.asynctask.BuscaAlunoTask
import com.example.listaalunos.asynctask.EditaAlunoTask
import com.example.listaalunos.asynctask.SalvaAlunoTask
import com.example.listaalunos.database.AgendaDatabase
import com.example.listaalunos.model.Aluno
import com.example.listaalunos.ui.AlunoItemTouchHelperCallback
import com.example.listaalunos.ui.activity.ConstantesActivity.CHAVE_ALUNO
import com.example.listaalunos.ui.activity.ConstantesActivity.CHAVE_POSICAO
import com.example.listaalunos.ui.activity.ConstantesActivity.POSICAO_INVALIDA
import com.example.listaalunos.ui.activity.ConstantesActivity.REQUISICAO_ALTERA_ALUNO
import com.example.listaalunos.ui.activity.ConstantesActivity.REQUISICAO_INSERE_ALUNO
import com.example.listaalunos.ui.adapter.ListaAlunosAdapter
import com.example.listaalunos.ui.adapter.listener.OnItemClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    //    private lateinit var dao: AlunoDAO
    private val TITULO_APPBAR: String = "Lista de Aluno"
    private lateinit var listaAlunosView: RecyclerView
    private lateinit var botaoNovoAluno: FloatingActionButton
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    private val dao by lazy {
        AgendaDatabase
            .mInstance(this)
            .getRoomAlunoDao()
    }

    private val adapter by lazy {
        ListaAlunosAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = TITULO_APPBAR
        initViews()
        configuraRecycleView()
        configuraFabNovoAluno()

//        activityResultLauncher =
//            ComponentActivity().registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (resultadoInsereAluno(result)) {
//
//                    if (result.resultCode == Activity.RESULT_OK) {
//                        val alunoRecebido: Aluno =
//                            result.data!!.getSerializableExtra(CHAVE_ALUNO) as Aluno
//                        adiciona(alunoRecebido)
//                    }
//                }
//            }

//        activityResultLauncher =
//            ComponentActivity().registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (resultadoAlteraAluno(result)) {
//                    if (result.resultCode == Activity.RESULT_OK) {
//                        val alunoRecebido: Aluno =
//                            result.data!!.getSerializableExtra(CHAVE_ALUNO) as Aluno
//                        val posicaoRecebida: Int =
//                            result.data!!.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA)
//                        if (posicaoRecebida > POSICAO_INVALIDA) {
//                            edita(alunoRecebido, posicaoRecebida)
//                        }
//                    }
//                }
//            }


//        dao = AgendaDatabase.mInstance(this)
//            .getRoomAlunoDao()

//        listaAlunosView = ListaAlunoView(this)
//        val todosAlunos: List<Aluno>? = finalizaFormulario()
    }

//    private fun resultadoAlteraAluno(result: ActivityResult) =
//        result.resultCode == REQUISICAO_ALTERA_ALUNO && temAluno(result)
//
//    private fun resultadoInsereAluno(result: ActivityResult) =
//        result.resultCode == REQUISICAO_INSERE_ALUNO && temAluno(result)
//
//    private fun temAluno(result: ActivityResult) =
//        result.data!!.hasExtra(CHAVE_ALUNO)


    private fun initViews() {
        listaAlunosView = findViewById(R.id.activity_lista_alunos_recycleView)
        botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultadoInsereAluno(requestCode, data)) {

            if (resultCode == Activity.RESULT_OK) {
                val alunoRecebido = data!!.getSerializableExtra(CHAVE_ALUNO) as Aluno
                adiciona(alunoRecebido)
            }
        }
        if (resultadoAlteraAluno(requestCode, data)) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    val alunoRecebido: Aluno = data.getSerializableExtra(CHAVE_ALUNO) as Aluno
                    val posicaoRecebida: Int = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA)
                    if (posicaoRecebida > POSICAO_INVALIDA) {
                        edita(
                            alunoRecebido,
                            posicaoRecebida,
                        )
                    }
                }
            }
        }
    }

    private fun resultadoInsereAluno(requestCode: Int, data: Intent?) =
        requestCode == REQUISICAO_INSERE_ALUNO && temAluno(data)

    private fun resultadoAlteraAluno(requestCode: Int, data: Intent?) =
        requestCode == REQUISICAO_ALTERA_ALUNO && temAluno(data)

    private fun temAluno(data: Intent?): Boolean {
        return (data != null) && data.hasExtra(CHAVE_ALUNO)
    }


    private fun configuraFabNovoAluno() {
        botaoNovoAluno.setOnClickListener {
            abreFormularioAlunoActitvity()
        }
    }

    private fun abreFormularioAlunoActitvity() {
        val aluno = Intent(this, FormularioAlunoActivity::class.java)
        startActivityForResult(aluno, REQUISICAO_INSERE_ALUNO)
    }

    private fun configuraRecycleView() {
        configuraListenerDeCliquePorItem(listaAlunosView)
        configuraItemTouchHelper(listaAlunosView)
    }


    private fun configuraItemTouchHelper(listaNotas: RecyclerView) {
        val itemTouchHelper =
            ItemTouchHelper(AlunoItemTouchHelperCallback(adapter, this))
        itemTouchHelper.attachToRecyclerView(listaNotas)
    }

    private fun configuraListenerDeCliquePorItem(listaDeAluno: RecyclerView) {
        listaDeAluno.adapter = adapter
        adapter.setOnITemClickListener(object : OnItemClickListener {
            override fun onItemClick(aluno: Aluno, posicao: Int) {
                abreFormularioModoEditaAluno(aluno, posicao)
            }
        })
    }

    private fun abreFormularioModoEditaAluno(aluno: Aluno, posicao: Int) {
        val vaiParaFormularioActivity =
            Intent(this@MainActivity, FormularioAlunoActivity::class.java)
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno)
        vaiParaFormularioActivity.putExtra(CHAVE_POSICAO, posicao)
        startActivityForResult(vaiParaFormularioActivity, 2)
    }

    override fun onResume() {
        super.onResume()
        atualiza()
    }

    private fun atualiza() {
        BuscaAlunoTask(dao) { aluno ->
            adapter.atualiza(aluno)
        }.execute()
//        adapter.atualiza(dao.todos())
    }

    private fun adiciona(aluno: Aluno) {
        SalvaAlunoTask(aluno, dao) {
            adapter.adiciona(aluno)
        }.execute()
//        dao.salva(aluno)
//        adapter.adiciona(aluno)
    }

    private fun edita(aluno: Aluno, posicao: Int) {
        EditaAlunoTask(aluno, dao) {
            adapter.edita(aluno, posicao)
        }.execute()
//        dao.edita(aluno)
//        adapter.edita(aluno, posicao)
    }
}