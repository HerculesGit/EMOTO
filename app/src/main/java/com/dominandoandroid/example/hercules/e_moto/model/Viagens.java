package com.dominandoandroid.example.hercules.e_moto.model;

import java.io.Serializable;

public class Viagens implements Serializable {
    private int idMototaxista;
    private String nomeCliente, numero, tipoCorrida, tipoVelocidade, origem, destino,
            data;
    private double valorCorrida;

    public Viagens(){
        this.idMototaxista = 0;
        this.nomeCliente = "";
        this.numero = "";
        this.tipoCorrida = "";
        this.tipoVelocidade = "";
        this.valorCorrida = 0.0;
        this.origem = "";
        this.destino = "";
        this.data = "";
    }

    public Viagens(int idMototaxista, String nomeCliente, String numero, String tipoCorrida,
                   String tipoVelocidade, double valorCorrida, String origem, String destino, String data) {
        this.idMototaxista = idMototaxista;
        this.nomeCliente = nomeCliente;
        this.numero = numero;
        this.tipoCorrida = tipoCorrida;
        this.tipoVelocidade = tipoVelocidade;
        this.valorCorrida = valorCorrida;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
    }

    public int getIdMototaxista() {
        return idMototaxista;
    }

    public void setIdMototaxista(int idMototaxista) {
        this.idMototaxista = idMototaxista;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }

    public String getTipoVelocidade() {
        return tipoVelocidade;
    }

    public void setTipoVelocidade(String tipoVelocidade) {
        this.tipoVelocidade = tipoVelocidade;
    }

    public double getValorCorrida() {
        return valorCorrida;
    }

    public void setValorCorrida(double valorCorrida) {
        this.valorCorrida = valorCorrida;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "nomeCliente:"+getNomeCliente()+ " numeroTelefone:"+getNumero()+" tipoCorrida:"+getTipoCorrida()
                +" tipoVelocidade:"+getTipoVelocidade()+" valorCorrida:"+getValorCorrida()+" origem:"+getOrigem()
                +" destino:"+getDestino()+" data:"+getData();
    }

}
