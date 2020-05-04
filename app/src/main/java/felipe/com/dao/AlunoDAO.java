package felipe.com.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import felipe.com.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizarIds();
    }

    private void atualizarIds() {
        contadorDeIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscarAlunoPeloId(aluno);

        if(alunoEncontrado != null){
            int posicaoAluno= alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscarAlunoPeloId(Aluno aluno) {

        for (Aluno a: alunos) {
            if(a.getId() == aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remover(Aluno aluno) {
        Aluno alunoDevolvido = buscarAlunoPeloId(aluno);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}
