package org.example.dao;

import org.example.model.Aluno;

import java.util.List;
import java.util.Optional;

public interface IAlunoDAO {
    Aluno create(Aluno aluno);
    Aluno update(Aluno aluno);
    void delete(Aluno aluno);
    Optional<Aluno> findById(Long id);
    List<Aluno> findAll();
    Optional<Aluno> findByNome(String nome);
}
