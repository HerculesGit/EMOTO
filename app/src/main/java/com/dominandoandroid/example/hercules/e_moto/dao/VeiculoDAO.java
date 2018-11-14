package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.model.Veiculo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hercules silva
 * */
public class VeiculoDAO implements IVeiculoDAO {

    private BDHelper db;
    private SQLiteDatabase escreve;         // escrever dados na tabela/ salvar
    private SQLiteDatabase ler;             // ler as tabelas


    public VeiculoDAO (Context context){
        db = new BDHelper(context);
        escreve = db.getWritableDatabase();     // permite salvar no banco de dados
        ler = db.getReadableDatabase();         // permite ler os dados de uma tabela
    }

    //@Override
    public boolean salvar(Veiculo veiculo) {

        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put(BDHelper.MOTO_ID,veiculo.getIdVeiculo());
        cv.put(BDHelper.MOTO_MARCA,veiculo.getMarca());
        cv.put(BDHelper.MOTO_MODELO,veiculo.getModelo());
        cv.put(BDHelper.MOTO_PLACA,veiculo.getPlaca());

        try{
            escreve.insert(
                    // nome da tabela
                    BDHelper.TABELA_MOTO,
                    null,
                    cv);

            Log.i("INFO", "Exito ao salvar Veiculo");

        } catch (Exception e){
            Log.i("INFO", "Erro ai salvar Veiculo: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    //@Override
    public boolean atualizar(Veiculo veiculo) {
        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put(BDHelper.MOTO_MARCA,veiculo.getMarca());
        cv.put(BDHelper.MOTO_MODELO,veiculo.getModelo());
        cv.put(BDHelper.MOTO_PLACA,veiculo.getPlaca());

        try{
            String[] argumentos = {
                    String.valueOf(veiculo.getIdVeiculo())};

            escreve.update(
                    BDHelper.TABELA_MOTO,
                    cv,
                    // clausula where - caracter coringa
                    "idMoto=?",

                    // argumentos
                    argumentos
            );

            Log.i("INFO", "Exito ao atualizar Veiculo");

        } catch (Exception e){
            Log.i("Erro", "Erro ao atualizar Veiculo: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    //@Override
    public boolean deletar(Veiculo veiculo) {
        return false;
    }

    public Veiculo encontrarDe(int idVeiculo){

        String sql = "SELECT * FROM " + BDHelper.TABELA_MOTO+";";
        Cursor cursor = ler.rawQuery(sql, null);

        Veiculo veiculo = new Veiculo();
        while(cursor.moveToNext()){
            int idVeiculoConsulta = cursor.getInt(cursor.getColumnIndex(BDHelper.MOTO_ID));

            if (idVeiculo == idVeiculoConsulta){

                String marca = cursor.getString(cursor.getColumnIndex(BDHelper.MOTO_MARCA));
                String modelo = cursor.getString(cursor.getColumnIndex(BDHelper.MOTO_MODELO));
                String placa = cursor.getString(cursor.getColumnIndex(BDHelper.MOTO_PLACA));

                veiculo.setIdVeiculo(idVeiculoConsulta);
                veiculo.setMarca(marca);
                veiculo.setModelo(modelo);
                veiculo.setPlaca(placa);

                return  veiculo;
            }
        }
        return veiculo;
    }

    //@Override
    public List<Veiculo> listar() {
        List<Veiculo> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + BDHelper.TABELA_MOTO;
        Cursor cursor = ler.rawQuery(sql, null);

        while(cursor.moveToNext()){

            Veiculo veiculo= new Veiculo();

            int idMoto= cursor.getInt(cursor.getColumnIndex("idMoto"));
            String marca = cursor.getString(cursor.getColumnIndex("marca"));
            String modelo = cursor.getString(cursor.getColumnIndex("modelo"));
            String placa = cursor.getString(cursor.getColumnIndex("placa"));

            veiculo.setIdVeiculo(idMoto);
            veiculo.setMarca(marca);
            veiculo.setModelo(modelo);
            veiculo.setPlaca(placa);


            lista.add(veiculo);
        }

        return lista;
    }
}
