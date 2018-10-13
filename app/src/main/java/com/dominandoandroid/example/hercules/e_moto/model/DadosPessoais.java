package com.dominandoandroid.example.hercules.e_moto.model;

public class DadosPessoais {
    private String nome, sobrenome, cpf, rg, senha, telefone, email;

    public DadosPessoais(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = "";
        this.rg = "";
        this.senha = "";
    }

    public DadosPessoais(String nome, String sobrenome, String cpf, String rg, String telefone, String senha, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.rg = rg;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
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

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "nome:"+getNome() +" sobrenome:"+ getSobrenome() +
                " cpf:"+ getCpf() + " rg:" +getRg() +" telefone:"+getTelefone()+" email: "+getEmail() +" senha:"+getSenha();
    }

}
