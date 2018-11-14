package com.dominandoandroid.example.hercules.e_moto.model;

import java.io.Serializable;
import java.util.Arrays;

public class Imagem implements Serializable {
    private int idImagem;
    private String descricao;
    private byte[] dados;

    /**
     * @autor hercules
     * Construtor padrao
     * */
    public Imagem(){
        this.idImagem = 0;
        this.descricao = "";
        this.dados = null;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }


    @Override
    public String toString() {
        return "Imagem{" +
                "idImagem=" + idImagem +
                ", descricao='" + descricao + '\'' +
                ", dados=" + Arrays.toString(dados) +
                '}';
    }
}
