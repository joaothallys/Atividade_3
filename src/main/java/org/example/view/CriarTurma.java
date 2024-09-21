package org.example.view;

import org.example.dao.CursoDAO;
import org.example.model.Aluno;
import org.example.model.Area;
import org.example.model.Curso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class CriarTurma {
    public JTextField nomeCurso;
    public JTextField sigla;
    public JComboBox comboBoxArea;
    public JButton cadastrarTurma;
    public JPanel painelCadastarTurma;
    public JButton excluirTurmaButton;
    public JButton buscarTurmaButton;
    public JButton updateButton;

    Long id;

    Long idMod;
    CriarTurma(){
        CursoDAO cursoDAO = new CursoDAO();
        cadastrarTurma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso c = new Curso();
                c.setNome(nomeCurso.getText());
                c.setSigla(sigla.getText());
                c.setArea(Area.valueOf(comboBoxArea.getSelectedItem().toString()));
                cursoDAO.create(c);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso cursoModificado = new Curso();
                cursoModificado.setNome(nomeCurso.getText());
                cursoModificado.setSigla(sigla.getText());
                cursoModificado.setArea(Area.valueOf(comboBoxArea.getSelectedItem().toString()));
                cursoModificado.setId(idMod);
                cursoDAO.update(cursoModificado);
            }
        });

        buscarTurmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Digite o ID do curso:", "Input", JOptionPane.QUESTION_MESSAGE);
                if(!input.isEmpty()){
                    idMod = Long.parseLong(input);
                    Optional<Curso> a = cursoDAO.findById(idMod);
                    nomeCurso.setText(a.get().getNome());
                    sigla.setText(a.get().getSigla());
                    comboBoxArea.setSelectedIndex(findIndexArea(a.get().getArea().toString()));
                }else {
                    JOptionPane.showMessageDialog(null, "É preciso colocar o ID");
                }
            }
        });
        comboBoxArea.addItem(Area.Exatas);
        comboBoxArea.addItem(Area.Artes);
        comboBoxArea.addItem(Area.Humanas);
        comboBoxArea.addItem(Area.Biológicas);

        excluirTurmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso a = new Curso();
                String input = JOptionPane.showInputDialog(null, "Digite o ID do aluno:", "Input", JOptionPane.QUESTION_MESSAGE);
                if (!input.isEmpty()) {
                    id = Long.parseLong(input);
                    Optional<Curso> curso = cursoDAO.findById(id);
                    nomeCurso.setText(curso.get().getNome());
                    sigla.setText(curso.get().getSigla());
                    comboBoxArea.setSelectedIndex(findIndexArea(curso.get().getArea().toString()));
                }
                JOptionPane.showMessageDialog(null, "Deseja realmente execluir esse aluno?");
                a.setId(Long.parseLong(input));
                cursoDAO.delete(a);
                nomeCurso.setText("");
                sigla.setText("");
                comboBoxArea.setSelectedIndex(0);
            }
        });
    }
    public int findIndexArea(String area){
        int i = 0;
        for (; i < comboBoxArea.getModel().getSize() && !comboBoxArea.getItemAt(i).toString().equals(area); i++);
        return i;
    }
}
