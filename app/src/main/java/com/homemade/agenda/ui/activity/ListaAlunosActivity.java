package com.homemade.agenda.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.homemade.agenda.R;
import com.homemade.agenda.dao.AlunoDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO dao = new AlunoDAO();

        setTitle("Lista de Alunos");
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);

        listaAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1,
                dao.listarTodos()));
    }
}
