package com.example.listaalunos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.listaalunos.database.converter.ConversorCalendar
import com.example.listaalunos.database.dao.AlunoDAO
import com.example.listaalunos.model.Aluno

@Database(entities = [Aluno::class], version = 4, exportSchema = false)
@TypeConverters(ConversorCalendar::class)
abstract class AgendaDatabase : RoomDatabase() {

    abstract fun getRoomAlunoDao(): AlunoDAO
//    abstract fun getTelefoneDao(): TelefoneDAO

    companion object {
        fun mInstance(context: Context): AgendaDatabase {
            return Room.databaseBuilder(context, AgendaDatabase::class.java, "agenda.db")
                .addMigrations(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT")
                    }
                }, object : Migration(2, 3) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        // Criar nova tabela com as informações desejadas
                        database.execSQL(
                            "CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                                    "(`nome` TEXT NOT NULL, " +
                                    "`telefone` TEXT NOT NULL, " +
                                    "`email` TEXT NOT NULL, " +
                                    "`id` INTEGER NOT NULL, " +
                                    "PRIMARY KEY(`id`))"
                        )

                        // Copiar dados da tabela antiga para a nova
                        database.execSQL(
                            "INSERT INTO Aluno_novo (id,nome,telefone,email)" +
                                    "SELECT id,nome,telefone,email FROM Aluno"
                        )

                        // Remove tabela antiga
                        database.execSQL("DROP TABLE Aluno")

                        // Renomear a tabela nova com o nome da tabela antiga
                        database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno")
                    }
                }, object : Migration(3, 4) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER")
                    }
                }
//                    , object : Migration(4, 5) {
//                    override fun migrate(database: SupportSQLiteDatabase) {
//                        database.execSQL(
//                            "CREATE TABLE IF NOT EXISTS `Aluno` " +
//                                    "(`nome` TEXT NOT NULL, `telefoneCelular` TEXT NOT NULL, " +
//                                    "`telefoneFixo` TEXT NOT NULL, " +
//                                    "`email` TEXT NOT NULL, " +
//                                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                                    "`momentoDeCadastro` INTEGER NOT NULL)"
//                        )
//
//                        database.execSQL(
//                            "INSERT INTO Aluno_novo (id, nome, telefoneCelular, email, momentoDeCadastro) " +
//                                    "SELECT id, nome, telefone, email, momentoDeCadastro FROM Aluno"
//                        )
//
//                        database.execSQL("DROP TABLE Aluno")
//
//                        database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno")
//                    }
//
//                }
//                    , object : Migration(5, 6) {
//                    override fun migrate(database: SupportSQLiteDatabase) {
//                        database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
//                                "(`nome` TEXT NOT NULL, " +
//                                "`email` TEXT NOT NULL, " +
//                                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                                "`momentoDeCadastro` INTEGER NOT NULL)")
//
//                        database.execSQL(
//                            "INSERT INTO Aluno_novo (id, nome, email, momentoDeCadastro) " +
//                                    "SELECT id, nome, email, momentoDeCadastro FROM Aluno")
//
//
//                        database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` " +
//                                "(`numero` TEXT NOT NULL, " +
//                                "`tipo` TEXT NOT NULL, " +
//                                "`alunoId` INTEGER NOT NULL, " +
//                                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                                "FOREIGN KEY(`alunoId`) REFERENCES `Aluno`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )")
//
//                        database.execSQL(
//                            "INSERT INTO Telefone (numero, alunoId) " +
//                                    "SELECT telefoneCelular, id FROM Aluno")
//
//                        database.execSQL("UPDATE Telefone SET tipo = ?"/*, arrayOf(TipoTelefone.FIXO)*/)
//
//                        database.execSQL("DROP TABLE Aluno")
//
//                        database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno")
//
//                    }
//
//                }
                )
                .build()
        }
    }
}