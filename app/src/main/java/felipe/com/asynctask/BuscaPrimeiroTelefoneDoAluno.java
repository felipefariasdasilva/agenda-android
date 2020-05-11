package felipe.com.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import felipe.com.database.dao.TelefoneDAO;
import felipe.com.model.Telefone;

public class BuscaPrimeiroTelefoneDoAluno extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO telefoneDAO;
    private final TextView campoTelefone;
    private final int alunoId;

    public BuscaPrimeiroTelefoneDoAluno(TelefoneDAO telefoneDAO, TextView campoTelefone, int alunoId) {
        this.telefoneDAO = telefoneDAO;
        this.campoTelefone = campoTelefone;
        this.alunoId = alunoId;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDAO.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        campoTelefone.setText(primeiroTelefone.getNumero());
    }
}
