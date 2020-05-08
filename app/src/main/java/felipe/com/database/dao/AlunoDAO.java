package felipe.com.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import felipe.com.model.Aluno;

@Dao
public interface AlunoDAO {

    @Insert
    void salva(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> todos();

    @Delete
    void remover(Aluno aluno);

    @Update
    void edita(Aluno aluno);
}
