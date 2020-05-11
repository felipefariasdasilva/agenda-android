package felipe.com.asynctask;

import android.os.AsyncTask;

import felipe.com.database.dao.AlunoDAO;
import felipe.com.model.Aluno;
import felipe.com.ui.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void> {

    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter adapter;
    private final Aluno aluno;

    public RemoveAlunoTask(AlunoDAO alunoDAO, ListaAlunosAdapter adapter, Aluno aluno) {
        this.alunoDAO = alunoDAO;
        this.adapter = adapter;
        this.aluno = aluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.remover(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        adapter.remove(aluno);
    }
}
