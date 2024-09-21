package org.example.dao;

import org.example.config.ConnectionFactory;
import org.example.model.Aluno;
import org.example.model.Area;
import org.example.model.Curso;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO implements ICursoDAO{
    @Override
    public Curso create(Curso curso) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT into curso " +
                    "(nome, sigla, area)" +
                    "values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getSigla());
            preparedStatement.setString(3, curso.getArea().toString());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Curso update(Curso curso) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE curso SET " +
                    "nome = ?, sigla = ?, area = ? " +
                    "WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getSigla());
            preparedStatement.setString(3, curso.getArea().toString());
            preparedStatement.setLong(4, curso.getId());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(Curso curso) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM curso " +
                    "WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, curso.getId());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Curso excluido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao' tentar excluir curso!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Curso> findById(Long id) {
        Curso curso = new Curso();
        String query = "SELECT * FROM curso WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet= preparedStatement.executeQuery();
            resultSet.next();
            curso.setId(resultSet.getLong("id"));
            curso.setNome(resultSet.getString("nome"));
            curso.setSigla(resultSet.getString("sigla"));
            curso.setArea(Area.valueOf(resultSet.getString("area")));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(curso);
    }

    @Override
    public List<Curso> findAll() {
        String query = "SELECT * FROM Curso";
        List<Curso> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Curso curso = new Curso();
                curso.setId(rs.getLong("id"));
                curso.setNome(rs.getString("nome"));
                curso.setSigla(rs.getString("sigla"));
                curso.setArea(Area.valueOf(rs.getString("area")));
                lista.add(curso);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Long findIdbyNome(String nome) {
        Curso curso = new Curso();
        String query = "SELECT id FROM Curso WHERE nome = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nome);
            ResultSet resultSet= preparedStatement.executeQuery();
            resultSet.next();
            curso.setId(resultSet.getLong("id"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return curso.getId();
    }
}
