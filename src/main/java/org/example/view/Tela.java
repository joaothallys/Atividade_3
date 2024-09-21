package org.example.view;

import org.example.dao.AlunoDAO;
import org.example.dao.CursoDAO;
import org.example.model.Aluno;
import org.example.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class Tela {
    public JTextField telefone;
    public JTextField nomeAluno;
    public JButton cadastarAlunoButton;
    public JButton criarTurmaButton;
    public JComboBox curso;
    public JComboBox sexo;
    public JPanel painel;
    public JComboBox maioridade;
    public JButton editarAlunoButton;
    public JButton excluirAlunoButton;
    public JButton updateButton;
    Long id;
    public Tela(){
        CursoDAO cur = new CursoDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        cadastarAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aluno a = new Aluno();
                a.setNome(nomeAluno.getText());
                a.setTelefone(telefone.getText());
                a.setId_curso(Math.toIntExact(cur.findIdbyNome(curso.getSelectedItem().toString())));
                a.setSexo(sexo.getSelectedItem().toString());
                if(maioridade.getSelectedItem().toString().equals("Sim")){
                    a.setMaiorIdade(true);
                }else {
                    a.setMaiorIdade(false);
                }
                alunoDAO.create(a);
            }
        });

        excluirAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aluno a = new Aluno();
                String input = JOptionPane.showInputDialog(null, "Digite o ID do aluno:", "Input", JOptionPane.QUESTION_MESSAGE);
                if (!input.isEmpty()) {
                    id = Long.parseLong(input);
                    Optional<Aluno> aluno = alunoDAO.findById(id);
                    nomeAluno.setText(aluno.get().getNome());
                    telefone.setText(aluno.get().getTelefone());
                    if (aluno.get().getSexo().equals("Masculino")) {
                        sexo.setSelectedIndex(0);
                    } else {
                        sexo.setSelectedIndex(1);
                    }
                    if (aluno.get().getMaiorIdade().equals(true)) {
                        maioridade.setSelectedIndex(0);
                    } else {
                        maioridade.setSelectedIndex(1);
                    }
                    curso.setSelectedIndex(aluno.get().getId_curso());
                }
                JOptionPane.showMessageDialog(null, "Deseja realmente execluir esse aluno?");
                a.setMatricula(Long.parseLong(input));
                alunoDAO.delete(a);
                nomeAluno.setText("");
                telefone.setText("");
                sexo.setSelectedIndex(0);
                sexo.setSelectedIndex(1);
                maioridade.setSelectedIndex(0);
                maioridade.setSelectedIndex(1);
                curso.setSelectedIndex(1);
            }
        });
        criarTurmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Aula03");
                frame.setContentPane(new CriarTurma().painelCadastarTurma);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aluno alunoModificado = new Aluno();
                alunoModificado.setNome(nomeAluno.getText());
                alunoModificado.setTelefone(telefone.getText());
                //if(Math.toIntExact(cur.findIdbyNome(curso.getSelectedItem().toString())) != 1){
                    alunoModificado.setId_curso(Math.toIntExact(cur.findIdbyNome(curso.getSelectedItem().toString())) - 1);
                //}else {
                    //alunoModificado.setId_curso(1);
                //}

                alunoModificado.setSexo(sexo.getSelectedItem().toString());
                alunoModificado.setMatricula(id);
                System.out.println(alunoModificado.getId_curso());
                if(maioridade.getSelectedItem().toString().equals("Sim")){
                    alunoModificado.setMaiorIdade(true);
                }else {
                    alunoModificado.setMaiorIdade(false);
                }
                alunoDAO.update(alunoModificado);
            }
        });

        editarAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Digite o ID do aluno:", "Input", JOptionPane.QUESTION_MESSAGE);
                if(!input.isEmpty()){
                    id = Long.parseLong(input);
                    Optional<Aluno> a = alunoDAO.findById(id);
                    nomeAluno.setText(a.get().getNome());
                    telefone.setText(a.get().getTelefone());
                    if(a.get().getSexo().equals("Masculino")){
                        sexo.setSelectedIndex(0);
                    }else {
                        sexo.setSelectedIndex(1);
                    }
                    if(a.get().getMaiorIdade().equals(true)){
                        maioridade.setSelectedIndex(0);
                    }else {
                        maioridade.setSelectedIndex(1);
                    }
                    curso.setSelectedIndex(a.get().getId_curso());


                }else {
                    JOptionPane.showMessageDialog(null, "É preciso colocar o ID");
                }

            }
        });

        CursoDAO c = new CursoDAO();
        List<Curso> cursos = c.findAll();
        for (int i = 0; i < cursos.size(); i++) {
            curso.addItem(cursos.get(i).getNome());
        }
        sexo.addItem("Masculino");
        sexo.addItem("Feminino");

        maioridade.addItem("Sim");
        maioridade.addItem("Não");
    }
}
