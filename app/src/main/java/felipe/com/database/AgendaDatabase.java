package felipe.com.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import felipe.com.database.dao.RoomAlunoDAO;
import felipe.com.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract RoomAlunoDAO getRoomAlunoDAO();
}
