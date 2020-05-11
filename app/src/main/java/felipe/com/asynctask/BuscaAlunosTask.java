package felipe.com.asynctask;

import android.os.AsyncTask;

import java.util.List;

import felipe.com.database.dao.AlunoDAO;
import felipe.com.model.Aluno;
import felipe.com.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {

    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunosTask(AlunoDAO alunoDAO, ListaAlunosAdapter adapter) {

        this.alunoDAO = alunoDAO;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return alunoDAO.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualizar( todosAlunos);
    }
}
