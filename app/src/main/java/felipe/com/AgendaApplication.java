package felipe.com;

import android.app.Application;

import felipe.com.dao.AlunoDAO;
import felipe.com.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO alunoDAO = new AlunoDAO();
        criarAlunosDeTeste(alunoDAO);
    }

    private void criarAlunosDeTeste(AlunoDAO alunoDAO) {
        alunoDAO.salva(new Aluno("felipe", "13 9 8228 0395", "felipe@gmail.com"));
        alunoDAO.salva(new Aluno("fran", "13 9 8121 2868", "fran@gmail.com"));
    }
}
