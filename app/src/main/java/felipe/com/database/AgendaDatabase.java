package felipe.com.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import felipe.com.database.converter.ConversorCalendar;
import felipe.com.database.dao.AlunoDAO;
import felipe.com.model.Aluno;

import static felipe.com.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters( { ConversorCalendar.class} )
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract AlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
