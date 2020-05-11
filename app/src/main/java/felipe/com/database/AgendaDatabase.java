package felipe.com.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import felipe.com.database.converter.ConversorCalendar;
import felipe.com.database.converter.ConversorTipoTelefone;
import felipe.com.database.dao.AlunoDAO;
import felipe.com.database.dao.TelefoneDAO;
import felipe.com.model.Aluno;
import felipe.com.model.Telefone;

import static felipe.com.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters( { ConversorCalendar.class, ConversorTipoTelefone.class } )
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract AlunoDAO getRoomAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }

}
