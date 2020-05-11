package felipe.com.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import felipe.com.asynctask.BuscaAlunosTask;
import felipe.com.asynctask.RemoveAlunoTask;
import felipe.com.database.AgendaDatabase;
import felipe.com.database.dao.AlunoDAO;
import felipe.com.model.Aluno;
import felipe.com.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter adapter;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(context);
        alunoDAO = AgendaDatabase.getInstance(context).getRoomAlunoDAO();
    }

    public void confirmarRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que deseja remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remover(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizarAlunos() {
        new BuscaAlunosTask(alunoDAO, adapter).execute();

    }

    private void remover(Aluno alunoEscolhido) {
        new RemoveAlunoTask(alunoDAO, adapter, alunoEscolhido).execute();

    }

    public void configurarAdapter(final ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }

}
