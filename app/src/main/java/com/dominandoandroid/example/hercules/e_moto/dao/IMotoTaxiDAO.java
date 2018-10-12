package com.dominandoandroid.example.hercules.e_moto.dao;

import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;

import java.util.List;

/**
 * @author hercules
 * Inteface com
 * salvar, atualizar, deletar e listar
 * */
public interface IMotoTaxiDAO {

    /**
     * Salvar mototaxi no banco de dados
     * */
    public boolean salvar(MotoTaxi motoTaxi);


    /**
     * Atualizar informacoes do mototaxi pelo objeto passado
     * */
    public boolean atualizar(MotoTaxi motoTaxi);

    /**
     *
     * */
    public boolean deletar(MotoTaxi motoTaxi);

    /**
     *
     * */
    public List<MotoTaxi> listar();

}
