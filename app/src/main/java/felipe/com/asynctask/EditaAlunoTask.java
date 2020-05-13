package felipe.com.asynctask;

import java.util.List;

import felipe.com.database.dao.AlunoDAO;
import felipe.com.database.dao.TelefoneDAO;
import felipe.com.model.Aluno;
import felipe.com.model.Telefone;
import felipe.com.model.TipoTelefone;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;
    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(AlunoDAO alunoDAO, Aluno aluno, Telefone telefoneFixo,
                          Telefone telefoneCelular, TelefoneDAO telefoneDAO,
                          List<Telefone> telefonesDoAluno, FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vincularAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizarIdDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualizar(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizarIdDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno ) {
            if(telefone.getTipo() == TipoTelefone.FIXO){
                telefoneFixo.setId(telefone.getId());
            }else{
                telefoneCelular.setId(telefone.getId());
            }
        }
    }
}
