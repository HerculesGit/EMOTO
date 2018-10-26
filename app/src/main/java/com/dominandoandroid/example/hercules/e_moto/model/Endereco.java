package com.dominandoandroid.example.hercules.e_moto.model;

import java.io.Serializable;

public class Endereco implements Serializable {
    private int idEndereco;
    private String estado, cidade, rua, numero, bairro;

    public Endereco() {
        this.idEndereco = 0;
        this.estado = "";
        this.cidade = "";
        this.rua = "";
        this.numero = "";
        this.bairro = "";
    }

    public Endereco(int idEndereco, String estado, String cidade, String rua, String numero, String bairro) {
        this.idEndereco = idEndereco;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString(){

        return "estado:"+getEstado()+ " cidade:"+getCidade()
                +" rua: "+getRua()+" número:"+getNumero() + " bairro:"+getBairro();
    }
}
