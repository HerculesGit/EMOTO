package com.dominandoandroid.example.hercules.e_moto.model;

public class Cliente {
    private String nome, sobrenome, telefone;
    private int qtdEncomendas, qtdViagens;

    public Cliente(String nome, String sobrenome, String telefone, int qtdEncomendas, int qtdViagens) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.qtdEncomendas = qtdEncomendas;
        this.qtdViagens = qtdViagens;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getQtdEncomendas() {
        return qtdEncomendas;
    }

    public void setQtdEncomendas(int qtdEncomendas) {
        this.qtdEncomendas = qtdEncomendas;
    }

    public int getQtdViagens() {
        return qtdViagens;
    }

    public void setQtdViagens(int qtdViagens) {
        this.qtdViagens = qtdViagens;
    }

    @Override
    public String toString() {
        return "nome:"+getNome() +" sobrenome:"+ getSobrenome() + " telefone: "+getTelefone() +
                " viagens:" +getQtdViagens()+
                " encomendas:"+getQtdEncomendas();
    }
}
