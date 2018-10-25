package com.dominandoandroid.example.hercules.e_moto.model;

public class DadosPessoais {

    private int idDadosPessoais;
    private String nome, sobrenome, cpf, rg;

    public DadosPessoais(){
        this.idDadosPessoais = 0;
        this.nome = "";
        this.sobrenome = "";
        this.cpf = "";
        this.rg = "";
    }

    public DadosPessoais(String nome, String sobrenome) {
        this.idDadosPessoais = 0;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = "";
        this.rg = "";
    }

    public DadosPessoais(int idDadosPessoais, String nome, String sobrenome, String cpf, String rg) {
        this.idDadosPessoais = idDadosPessoais;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public int getIdDadosPessoais() {
        return idDadosPessoais;
    }

    public void setIdDadosPessoais(int idDadosPessoais) {
        this.idDadosPessoais = idDadosPessoais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public String toString(){
        return "nome:"+getNome() +" sobrenome:"+ getSobrenome() +
                " cpf:"+ getCpf() + " rg:" +getRg();
    }

}
