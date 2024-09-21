package org.example.dao;

import org.example.model.Aluno;
import org.example.model.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoDAO {

    Curso create(Curso aluno);
    Curso update(Curso aluno);
    void delete(Curso aluno);
    Optional<Curso> findById(Long id);
    List<Curso> findAll();

    Long findIdbyNome(String nome);
}
