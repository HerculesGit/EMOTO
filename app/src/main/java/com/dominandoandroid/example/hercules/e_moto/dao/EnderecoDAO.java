package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.model.Endereco;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hercules silva
 * */
public class EnderecoDAO implements IEnderecoDAO {

    private BDHelper db;
    private SQLiteDatabase escreve;         // escrever dados na tabela/ salvar
    private SQLiteDatabase ler;             // ler as tabelas


    public EnderecoDAO (Context context){
        db = new BDHelper(context);
        escreve = db.getWritableDatabase();     // permite salvar no banco de dados
        ler = db.getReadableDatabase();         // permite ler os dados de uma tabela
    }

    //@Override
    public boolean salvar(Endereco endereco) {

        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put("idEndereco",endereco.getIdEndereco());
        cv.put("estado",endereco.getEstado());
        cv.put("cidade",endereco.getCidade());
        cv.put("rua",endereco.getRua());
        cv.put("numero",endereco.getNumero());
        cv.put("bairro",endereco.getBairro());

        try{
            escreve.insert(
                    // nome da tabela
                    BDHelper.TABELA_ENDERECO,
                    null,
                    cv);

            Log.i("INFO", "Exito ao salvar Endereco");

        } catch (Exception e){
            Log.i("INFO", "Erro ai salvar Endereco: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    //@Override
    public boolean atualizar(Endereco endereco) {
        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put("estado",endereco.getEstado());
        cv.put("cidade",endereco.getCidade());
        cv.put("rua",endereco.getRua());
        cv.put("numero",endereco.getNumero());
        cv.put("bairro",endereco.getBairro());

        try{
            String[] argumentos = {
                    String.valueOf(endereco.getIdEndereco())};

            escreve.update(
                    BDHelper.TABELA_ENDERECO,
                    cv,
                    // clausula where - caracter coringa
                    "idEndereco=?",

                    // argumentos
                    argumentos
            );

            Log.i("INFO", "Exito ao atualizar Endereco");

        } catch (Exception e){
            Log.i("Erro", "Erro ao atualizar Endereco: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    //@Override
    public boolean deletar(Endereco endereco) {
        return false;
    }

    public Endereco encontrarDe(int idEndereco){

        String sql = "SELECT * FROM " + BDHelper.TABELA_ENDERECO+";";
        Cursor cursor = ler.rawQuery(sql, null);

        Endereco endereco = new Endereco();
        while(cursor.moveToNext()){
            int idEnderecoConsulta = cursor.getInt(cursor.getColumnIndex(BDHelper.ENDERECO_ID));

            if (idEndereco == idEnderecoConsulta){

                String estado = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_ESTADO));
                String cidade = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_CIDADE));
                String rua = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_RUA));
                String numero = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_NUMERO));
                String bairro = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_BAIRRO));

                endereco.setIdEndereco(idEndereco);
                endereco.setEstado(estado);
                endereco.setCidade(cidade);
                endereco.setRua(rua);
                endereco.setNumero(numero);
                endereco.setBairro(bairro);

                return  endereco;
            }
        }
        return endereco;
    }

    //@Override
    public List<Endereco> listar() {
        List<Endereco> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + BDHelper.TABELA_ENDERECO+";";
        Cursor cursor = ler.rawQuery(sql, null);

        while(cursor.moveToNext()){

            Endereco endereco = new Endereco();

            int idEndereco = cursor.getInt(cursor.getColumnIndex(BDHelper.ENDERECO_ID));
            String estado = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_ESTADO));
            String cidade = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_CIDADE));
            String rua = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_RUA));
            String numero = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_NUMERO));
            String bairro = cursor.getString(cursor.getColumnIndex(BDHelper.ENDERECO_BAIRRO));

            endereco.setIdEndereco(idEndereco);
            endereco.setEstado(estado);
            endereco.setCidade(cidade);
            endereco.setRua(rua);
            endereco.setNumero(numero);
            endereco.setBairro(bairro);

            lista.add(endereco);
        }

        return lista;
    }
}
