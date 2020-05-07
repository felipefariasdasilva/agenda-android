package felipe.com;

import android.app.Application;

import androidx.room.Room;

import felipe.com.dao.AlunoDAO;
import felipe.com.database.AgendaDatabase;
import felipe.com.database.dao.RoomAlunoDAO;
import felipe.com.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criarAlunosDeTeste();
    }

    private void criarAlunosDeTeste() {
        AgendaDatabase database = Room.databaseBuilder(this, AgendaDatabase.class, "agenda.db").build();
        RoomAlunoDAO roomAlunoDAO = database.getRoomAlunoDAO();
        roomAlunoDAO.salva(new Aluno("felipe", "13 9 8228 0395", "felipe@gmail.com"));
        roomAlunoDAO.salva(new Aluno("fran", "13 9 8121 2868", "fran@gmail.com"));
    }
}
