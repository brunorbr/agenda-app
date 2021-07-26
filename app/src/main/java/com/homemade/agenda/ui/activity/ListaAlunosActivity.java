package com.homemade.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.homemade.agenda.R;
import com.homemade.agenda.dao.AlunoDAO;
import com.homemade.agenda.model.Aluno;

import java.util.List;

import static com.homemade.agenda.ui.activity.ActivitiesConstants.CHAVE_ALUNO;


public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoSelecionado = adapter.getItem(menuInfo.position);
            removeAluno(alunoSelecionado);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovo = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioInsereAluno();
            }
        });
    }

    private void abreFormularioInsereAluno() {
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.listarTodos());
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaAlunos);
        configuraListenerClickPorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }


    private void removeAluno(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraListenerClickPorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
                abreFormularioEditaAluno(alunoSelecionado);
            }
        });
    }

    private void abreFormularioEditaAluno(Aluno aluno) {
        Intent edicaoAluno = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        edicaoAluno.putExtra(CHAVE_ALUNO, aluno);
        Log.i("id", String.valueOf(aluno.getId()));
        startActivity(edicaoAluno);
    }

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1);
        listaAlunos.setAdapter(adapter);
    }
}
