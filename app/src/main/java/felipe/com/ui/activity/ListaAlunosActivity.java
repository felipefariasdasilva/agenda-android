package felipe.com.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import felipe.com.R;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        setTitle("Lista de Alunos");

        List<String> alunos = new ArrayList<>(Arrays.asList("felipe", "fran", "jose", "maria", "ana"));

        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);

        listaAlunos.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos));


    }
}
