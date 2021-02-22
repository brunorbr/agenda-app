package com.homemade.agenda;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> insultosGratuitos = new ArrayList<>(
                Arrays.asList("Ei Bolsonaro,", "vai se fudeeeeeeee", "eeeeeeeee", "eeeeeeeeee"));

        ListView listaAlunos = findViewById(R.id.activity_main_lista_de_alunos);

        listaAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1,
                insultosGratuitos));
    }
}
