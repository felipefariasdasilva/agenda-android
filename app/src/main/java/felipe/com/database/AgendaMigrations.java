package felipe.com.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                    "ALTER TABLE Aluno ADD COLUMN sobrenome TEXT"
            );
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
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
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                    "ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER"
            );
        }
    };

    public static final Migration[] TODAS_MIGRATIONS = {

            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4

    };

}
