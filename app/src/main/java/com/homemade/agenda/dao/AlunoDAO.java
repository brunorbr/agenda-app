package com.homemade.agenda.dao;

import com.homemade.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salva(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }
}
