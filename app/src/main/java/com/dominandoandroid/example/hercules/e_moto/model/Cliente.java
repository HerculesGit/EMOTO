package com.dominandoandroid.example.hercules.e_moto.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String cpf,telefone;


    public Cliente(String cpf, String telefone) {
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "cpf:"+getCpf() + " telefone:"+getTelefone();
    }
}
