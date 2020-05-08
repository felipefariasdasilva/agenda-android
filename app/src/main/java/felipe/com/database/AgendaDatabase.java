package felipe.com.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import felipe.com.database.dao.AlunoDAO;
import felipe.com.model.Aluno;

@Database(entities = {Aluno.class}, version = 3, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract AlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL(
                                "ALTER TABLE aluno ADD COLUMN sobrenome TEXT"
                        );
                    }
                }, new Migration(2, 3) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        // CRIAR NOVA TABELA COM AS INFORMAÇÕES DESEJADAS
                        database.execSQL(
                                "CREATE TABLE IF NOT EXISTS `Aluno_novo`" +
                                " (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                "`nome` TEXT, " +
                                "`telefone` TEXT, " +
                                "`email` TEXT)"
                        );

                        // COPIAR DADOS DA TABELA ANTIGA PARA A NOVA
                        database.execSQL(
                                "INSERT INTO Aluno_novo(id, nome, telefone, email)" +
                                "SELECT id, nome, telefone, email " +
                                "FROM Aluno"
                        );

                        // REMOVE TABELA ANTIGA
                        database.execSQL(
                                "DROP TABLE Aluno"
                        );
                        // RENOMEAR A TABELA NOVA COM O NOME DA TABELA ANTIGA
                        database.execSQL(
                                "ALTER TABLE Aluno_novo RENAME TO Aluno"
                        );
                    }
                })
                .build();
    }
}
