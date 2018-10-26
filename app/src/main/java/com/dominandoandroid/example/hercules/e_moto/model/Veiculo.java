package com.dominandoandroid.example.hercules.e_moto.model;

import java.io.Serializable;

public class Veiculo implements Serializable {
    private int idVeiculo;
    private String marca, modelo, placa;

    public Veiculo(){
        this.idVeiculo = 0;
        this.marca = "";
        this.modelo = "";
        this.placa = "";
    }

    public Veiculo(int id,String marca, String modelo, String placa) {
        this.idVeiculo = id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "marca:"+getMarca()+" modelo:"+getModelo()+" placa:"+getPlaca();
    }
}
