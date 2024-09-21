package org.example.model;

public class Aluno {
    private Long matricula;
    private String nome;
    private Boolean maiorIdade;
    private String telefone;

    public int getId_curso() {
        return id_curso;
    }

    private int id_curso;

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    private String sexo;

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getMaiorIdade() {
        return maiorIdade;
    }

    public void setMaiorIdade(Boolean maiorIdade) {
        this.maiorIdade = maiorIdade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
