package felipe.com.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import felipe.com.R;
import felipe.com.asynctask.BuscaPrimeiroTelefoneDoAlunoTask;
import felipe.com.database.AgendaDatabase;
import felipe.com.database.dao.TelefoneDAO;
import felipe.com.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO telefoneDAO;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        telefoneDAO = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criarView(parent);
        Aluno alunoDevolvido = alunos.get(position);
        vincular(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincular(View viewCriada, Aluno alunoDevolvido) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNome());

        final TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);

        new BuscaPrimeiroTelefoneDoAlunoTask(telefoneDAO, alunoDevolvido.getId(),
                telefoneEncontrado ->
                        telefone.setText( telefoneEncontrado.getNumero() ) )
                .execute();
    }

    private View criarView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false);
    }

    public void atualizar(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
