package com.dominandoandroid.example.hercules.e_moto.model;

import java.io.Serializable;

public class DadosPessoais implements Serializable {

    private int idDadosPessoais;
    private String nome, cpf, rg;

    public DadosPessoais(){
        this.idDadosPessoais = 0;
        this.nome = "";
        this.cpf = "";
        this.rg = "";
    }

    public DadosPessoais(String nome) {
        this.idDadosPessoais = 0;
        this.nome = nome;
        this.cpf = "";
        this.rg = "";
    }

    public DadosPessoais(int idDadosPessoais, String nome, String cpf, String rg) {
        this.idDadosPessoais = idDadosPessoais;
        this.nome = nome;
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
        return "nome:"+getNome() +
                " cpf:"+ getCpf() + " rg:" +getRg();
    }

}
