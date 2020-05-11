package felipe.com.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import felipe.com.model.TipoTelefone;

import static felipe.com.model.TipoTelefone.FIXO;

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

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                        "CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                            "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`nome` TEXT, " +
                            "`telefoneFixo` TEXT, " +
                            "`telefoneCelular` TEXT, " +
                            "`email` TEXT, " +
                            "`momentoDeCadastro` INTEGER)"
            );

            database.execSQL(
                    "INSERT INTO Aluno_novo(id, nome, telefoneFixo, email, momentoDeCadastro)" +
                            "SELECT id, nome, telefone, email, momentoDeCadastro " +
                            "FROM Aluno"
            );

            database.execSQL(
                    "DROP TABLE Aluno"
            );

            database.execSQL(
                    "ALTER TABLE Aluno_novo RENAME TO Aluno"
            );

        }
    };

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                            "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`nome` TEXT, " +
                            "`email` TEXT, " +
                            "`momentoDeCadastro` INTEGER)"
            );

            database.execSQL(
                    "INSERT INTO Aluno_novo(id, nome, email, momentoDeCadastro)" +
                            "SELECT id, nome, email, momentoDeCadastro " +
                            "FROM Aluno"
            );

            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `Telefone` " +
                            "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`numero` TEXT, " +
                            "`tipo` TEXT, " +
                            "`alunoId` INTEGER NOT NULL)"
            );

            database.execSQL(
                    "INSERT INTO Telefone(numero, alunoId)" +
                            "SELECT telefoneFixo, id " +
                            "FROM Aluno"
            );

            database.execSQL(
                    "UPDATE Telefone " +
                            "SET tipo = ?", new TipoTelefone[] {FIXO}
            );

            database.execSQL(
                    "DROP TABLE Aluno"
            );

            database.execSQL(
                    "ALTER TABLE Aluno_novo RENAME TO Aluno"
            );
        }
    };

    public static final Migration[] TODAS_MIGRATIONS = {

            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4,
            MIGRATION_4_5,
            MIGRATION_5_6

    };

}
