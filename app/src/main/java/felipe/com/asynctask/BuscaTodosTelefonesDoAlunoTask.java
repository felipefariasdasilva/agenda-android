package felipe.com.asynctask;

import android.os.AsyncTask;

import java.util.List;

import felipe.com.database.dao.TelefoneDAO;
import felipe.com.model.Aluno;
import felipe.com.model.Telefone;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {
    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoalunoEncontradoListener listener;

    public BuscaTodosTelefonesDoAlunoTask(TelefoneDAO telefoneDAO, Aluno aluno,
                                          TelefonesDoalunoEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        listener.quandoEncontrados(telefones);
        super.onPostExecute(telefones);
    }

    public interface TelefonesDoalunoEncontradoListener{
        void quandoEncontrados(List<Telefone> telefones);
    }
}
