package com.example.listaalunos.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.listaalunos.R
import com.example.listaalunos.database.AgendaDatabase
import com.example.listaalunos.database.dao.AlunoDAO
import com.example.listaalunos.model.Aluno
import com.example.listaalunos.ui.activity.ConstantesActivity.CHAVE_ALUNO
import com.example.listaalunos.ui.activity.ConstantesActivity.CHAVE_POSICAO
import com.example.listaalunos.ui.activity.ConstantesActivity.POSICAO_INVALIDA

class FormularioAlunoActivity : AppCompatActivity() {

    private var posicaoRecebida: Int = POSICAO_INVALIDA
    private val TITULO_APPBAR_NOVO_ALUNO: String = "Novo Aluno"
    private val TITULO_APPBAR_EDITA_ALUNO: String = "Edita Aluno"
    private lateinit var campoNome: EditText
//    private lateinit var campoTelefoneFixo: EditText
//    private lateinit var campoTelefoneCelular: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var campoEmail: EditText
    private lateinit var alunoDAO: AlunoDAO
//    private lateinit var telefoneDAO: TelefoneDAO
    private lateinit var aluno: Aluno
//    private lateinit var telefoneDoAluno: List<Telefone>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)

        title = TITULO_APPBAR_NOVO_ALUNO
        inicializacaoDosCampos()

        val database: AgendaDatabase = AgendaDatabase.mInstance(this)
        alunoDAO = database.getRoomAlunoDao()
//        telefoneDAO = database.getTelefoneDao()

        val dados = intent
        if (dados.hasExtra(CHAVE_ALUNO)) {
            title = TITULO_APPBAR_EDITA_ALUNO
            aluno = dados.getSerializableExtra(CHAVE_ALUNO) as Aluno
            posicaoRecebida = dados.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA)
            preencheCampo(aluno)
        }
    }

    private fun inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome)
//        campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular)
//        campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo)
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone)
        campoEmail = findViewById(R.id.activity_formulario_aluno_email)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_formulario_aluno_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finalizaFormulario(item)
        return super.onOptionsItemSelected(item)
    }

    private fun finalizaFormulario(item: MenuItem) {
//        val telefoneFixo: Telefone = criaTelefone(campoTelefoneFixo, TipoTelefone.FIXO)
//        val telefoneCelular: Telefone = criaTelefone(campoTelefoneCelular, TipoTelefone.CELULAR)

//        telefoneDoAluno = telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId())

        if (item.itemId == R.id.activity_formulario_alunos_menu_salvar) {
            val alunoCriado: Aluno = preencheAluno()
            salva(alunoCriado)
//            aluno(alunoCriado, telefoneFixo, telefoneCelular)
            finish()
        }
    }

    private fun salva(aluno: Aluno) {
        val resultadoIncersao = Intent()
        resultadoIncersao.putExtra(CHAVE_ALUNO, aluno)
        resultadoIncersao.putExtra(CHAVE_POSICAO, posicaoRecebida)
        setResult(Activity.RESULT_OK, resultadoIncersao)
    }

//    private fun salvaAluno(aluno: Aluno, telefoneFixo: Telefone, telefoneCelular: Telefone) {
//        val alunoId: Long = alunoDAO.salva(aluno)
//        vinculaComTelefone(alunoId, telefoneFixo, telefoneCelular)
////        alunoDAO.edita(aluno)
////        telefoneDAO.salva(telefoneFixo, telefoneCelular)
//        telefoneDAO.atualiza(telefoneFixo, telefoneCelular)
//    }

//    private fun editaAluno(aluno: Aluno, telefoneFixo: Telefone, telefoneCelular: Telefone) {
//        vinculaComTelefone(aluno.getId().toLong(), telefoneFixo, telefoneCelular)
////        atualizaIdsDosTelefones(telefoneFixo, telefoneCelular)
//    }

//    private fun atualizaIdsDosTelefones(telefoneFixo: Telefone, telefoneCelular: Telefone) {
//        for (telefone: Telefone in telefoneDoAluno) {
//            if (telefone.tipo === TipoTelefone.FIXO) {
//                telefoneFixo.id
//            } 2else {
//                telefoneCelular.id
//            }
//        }
//    }

//    private fun criaTelefone(campoTelefoneFixo: EditText, fixo: TipoTelefone): Telefone {
//        val numeroFixo: String = campoTelefoneFixo.text.toString()
//        return Telefone(numeroFixo, fixo, -1)
//    }

    private fun preencheCampo(alunoRecebido: Aluno) {
        campoNome.setText(alunoRecebido.nome)
        campoTelefone.setText(alunoRecebido.telefone)
        campoEmail.setText(alunoRecebido.email)
//        preencheCamposDeTelefone()
    }

//    private fun preencheCamposDeTelefone() {
//        telefoneDoAluno = telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId())
//        for (telefone: Telefone in telefoneDoAluno) {
//            if (telefone.tipo === TipoTelefone.FIXO) {
//                campoTelefoneFixo.setText(telefone.numero)
//            } else {
//                campoTelefoneCelular.setText(telefone.numero)
//            }
//        }
//    }


//    private fun vinculaComTelefone(alunoId: Long, vararg telefones: Telefone) {
//        for (telefone: Telefone in telefones) {
//            telefone.alunoId = alunoId
//        }
//    }


    private fun preencheAluno(): Aluno {
//        val nome: String = campoNome.text.toString()
//        val telefone: String = campoTelefoneCelular.text.toString()
//        val telefoneFixo: String = campoTelefoneFixo.text.toString()
//        val telefone: String = campoTelefone.text.toString()
//        val email: String = campoEmail.text.toString()

        return Aluno(campoNome.text.toString(), campoTelefone.text.toString(), campoEmail.text.toString())
    }
}


