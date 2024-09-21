package org.example.dao;

import org.example.config.ConnectionFactory;
import org.example.model.Aluno;
import org.example.model.Curso;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO implements IAlunoDAO{


    @Override
    public Aluno create(Aluno aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT into Aluno " +
                    "(nome, telefone, maioridade, sexo, id_curso)" +
                    "values (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getTelefone());
            preparedStatement.setBoolean(3, aluno.getMaiorIdade());
            preparedStatement.setInt(5, aluno.getId_curso());
            preparedStatement.setString(4, aluno.getSexo());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Aluno update(Aluno aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE aluno SET " +
                    "nome = ?, maioridade = ?, id_curso = ?, telefone = ?, sexo = ? " +
                    "WHERE matricula = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setBoolean(2, aluno.getMaiorIdade());
            preparedStatement.setInt(3, aluno.getId_curso());
            preparedStatement.setString(4, aluno.getTelefone());
            preparedStatement.setString(5, aluno.getSexo());
            preparedStatement.setLong(6, aluno.getMatricula());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(Aluno aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM aluno " +
                    "WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, aluno.getMatricula());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao' tentar excluir aluno!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Aluno> findById(Long id) {
        Aluno aluno = new Aluno();
        String query = "SELECT * FROM aluno WHERE matricula = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet= preparedStatement.executeQuery();
            resultSet.next();
            aluno.setMatricula(resultSet.getLong("matricula"));
            aluno.setNome(resultSet.getString("nome"));
            aluno.setTelefone(resultSet.getString("telefone"));
            aluno.setMaiorIdade(resultSet.getBoolean("maioridade"));
            aluno.setId_curso(Integer.parseInt(resultSet.getString("id_curso")));
            aluno.setSexo(resultSet.getString("sexo"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(aluno);
    }

    @Override
    public List<Aluno> findAll() {
        return null;
    }

    @Override
    public Optional<Aluno> findByNome(String nome) {
        return Optional.empty();
    }
}
