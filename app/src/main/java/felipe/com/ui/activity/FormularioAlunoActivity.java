package felipe.com.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.List;

import felipe.com.R;
import felipe.com.asynctask.BuscaTodosTelefonesDoAlunoTask;
import felipe.com.asynctask.EditaAlunoTask;
import felipe.com.asynctask.SalvaAlunoTask;
import felipe.com.database.AgendaDatabase;
import felipe.com.database.dao.AlunoDAO;
import felipe.com.database.dao.TelefoneDAO;
import felipe.com.model.Aluno;
import felipe.com.model.Telefone;
import felipe.com.model.TipoTelefone;

import static felipe.com.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private AlunoDAO alunoDAO;
    private TelefoneDAO telefoneDAO;
    private Aluno aluno;
    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        alunoDAO = database.getRoomAlunoDAO();
         telefoneDAO = database.getTelefoneDAO();
        inicializarCampos();
        carregarAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregarAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencherCampos();
        }else{
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencherCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        preencherCamposDeTelefone();
    }

    private void preencherCamposDeTelefone() {
        new BuscaTodosTelefonesDoAlunoTask(telefoneDAO, aluno,
            telefones -> {
                this.telefonesDoAluno = telefones;
                for (Telefone telefone: telefonesDoAluno) {
                    if (telefone.getTipo() == TipoTelefone.FIXO){
                        campoTelefoneFixo.setText(telefone.getNumero());
                    }else{
                        campoTelefoneCelular.setText(telefone.getNumero());
                    }
                }
            }).execute();
    }

    private void finalizarFormulario() {
        preencheAluno();

        Telefone telefoneFixo = criarTelefone(campoTelefoneFixo, TipoTelefone.FIXO);
        Telefone telefoneCelular = criarTelefone(campoTelefoneCelular, TipoTelefone.CELULAR);

        if(aluno.temIdValido()){
            editarAluno(telefoneFixo, telefoneCelular);
        }else{
            salvarAluno(telefoneFixo, telefoneCelular);
        }
    }

    private Telefone criarTelefone(EditText campoTelefoneFixo, TipoTelefone fixo) {
        String numeroFixo = campoTelefoneFixo.getText().toString();
        return new Telefone(numeroFixo, fixo);
    }

    private void salvarAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new SalvaAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO,
                this::finish).execute();
    }

    private void editarAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new EditaAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO, telefonesDoAluno,
                this::finish).execute();
    }

    private void inicializarCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);

    }
}

