package felipe.com;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<String> alunos = new ArrayList<>(Arrays.asList("felipe", "fran", "jose", "maria", "ana"));

        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);

        listaAlunos.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos));


    }
}
