package felipe.com.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import felipe.com.R;
import felipe.com.dao.AlunoDAO;
import felipe.com.model.Aluno;

import static felipe.com.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configurarLista();

        alunoDAO.salva(new Aluno("felipe", "13 9 8228 0395", "felipe@gmail.com"));
        alunoDAO.salva(new Aluno("fran", "13 9 8121 2868", "fran@gmail.com"));
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFormularioAlunoActivity();
            }
        });
    }

    private void abrirFormularioAlunoActivity() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarAlunos();

    }

    private void atualizarAlunos() {
        adapter.clear();
        adapter.addAll(alunoDAO.todos());
    }

    private void configurarLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configurarAdapter(listaAlunos);
        configurarListenerDeClickPorItem(listaAlunos);
        configurarListenerDeClickLongoPorItem(listaAlunos);

    }

    private void configurarListenerDeClickLongoPorItem(ListView listaAlunos) {
        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                remover(alunoEscolhido);
                return true;
            }
        });
    }

    private void remover(Aluno alunoEscolhido) {
        alunoDAO.remover(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }

    private void configurarListenerDeClickPorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                abrirFormularioModoInserirAluno(alunoEscolhido);
            }
        });
    }

    private void abrirFormularioModoInserirAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configurarAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);
    }
}
