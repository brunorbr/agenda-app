package com.homemade.agenda.dao;

import com.homemade.agenda.model.Aluno;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPorId(aluno);
        if(alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPorId(Aluno aluno) {
        for(Aluno a : alunos){
            if(a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }
}
