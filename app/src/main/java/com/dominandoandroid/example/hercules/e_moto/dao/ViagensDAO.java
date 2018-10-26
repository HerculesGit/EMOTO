package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.model.Viagens;

import java.util.ArrayList;
import java.util.List;



/**
 * @author hercules silva
 * */
public class ViagensDAO implements IViagensDAO {

    private BDHelper db;
    private SQLiteDatabase escreve;         // escrever dados na tabela/ salvar
    private SQLiteDatabase ler;             // ler as tabelas


    public ViagensDAO (Context context){
        db = new BDHelper(context);
        escreve = db.getWritableDatabase();     // permite salvar no banco de dados
        ler = db.getReadableDatabase();         // permite ler os dados de uma tabela
    }

    //@Override
    public boolean salvar(Viagens viagens) {

        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put("nomeCliente",viagens.getNomeCliente());
        cv.put("idMototaxista",viagens.getIdMototaxista());
        cv.put("numero",viagens.getNumero());
        cv.put("tipoCorrida",viagens.getTipoCorrida());
        cv.put("tipoVelocidade",viagens.getTipoVelocidade());
        cv.put("valorCorrida",viagens.getTipoCorrida());
        cv.put("origem",viagens.getOrigem());
        cv.put("destino",viagens.getDestino());
        cv.put("data",viagens.getData());

        try{
            escreve.insert(
                    // nome da tabela
                    BDHelper.TABELA_VIAGENS,
                    null,
                    cv);

            Log.i("INFO", "Exito ao salvar VIAGEM");

        } catch (Exception e){
            Log.i("INFO", "Erro ai salvar VIAGEM: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    @Deprecated
    //@Override
    public boolean atualizar(Viagens viagens) {
        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put("nomeCliente",viagens.getNomeCliente());
        cv.put("idMototaxista",viagens.getIdMototaxista());
        cv.put("numero",viagens.getNumero());
        cv.put("tipoCorrida",viagens.getTipoCorrida());
        cv.put("tipoVelocidade",viagens.getTipoVelocidade());
        cv.put("valorCorrida",viagens.getTipoCorrida());
        cv.put("origem",viagens.getOrigem());
        cv.put("destino",viagens.getDestino());
        cv.put("data",viagens.getData());

        try{
            String[] argumentos = {
                    String.valueOf(viagens.getIdMototaxista())};

            escreve.update(
                    BDHelper.TABELA_VIAGENS,
                    cv,
                    // clausula where - caracter coringa
                    "idMototaxista=?",

                    // argumentos
                    argumentos
            );

            Log.i("INFO", "Exito ao atualizar Viagem");

        } catch (Exception e){
            Log.i("Erro", "Erro ao atualizar Viagem: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    //@Override
    public boolean deletar(Viagens viagem) {
        return false;
    }


    public Viagens listarDe(int idMototaxista) {

        String sql = "SELECT * FROM " + BDHelper.TABELA_VIAGENS+";";
        Cursor cursor = ler.rawQuery(sql, null);

        Viagens viagens = new Viagens();
        while (cursor.moveToNext()) {
            int idConsulta = cursor.getInt(cursor.getColumnIndex("idMototaxista"));

            if (idConsulta == idMototaxista) {
                String nomeCliente = cursor.getString(cursor.getColumnIndex("nomeCliente"));
                String numero = cursor.getString(cursor.getColumnIndex("numero"));
                String tipoCorrida = cursor.getString(cursor.getColumnIndex("tipoCorrida"));
                String tipoVelocidade = cursor.getString(cursor.getColumnIndex("tipoVelocidade"));
                double valorCorrida = cursor.getDouble(cursor.getColumnIndex("valorCorrida"));
                String origem = cursor.getString(cursor.getColumnIndex("origem"));
                String destino = cursor.getString(cursor.getColumnIndex("destino"));
                String data = cursor.getString(cursor.getColumnIndex("data"));

                viagens.setIdMototaxista(idMototaxista);
                viagens.setNomeCliente(nomeCliente);
                viagens.setNumero(numero);
                viagens.setTipoCorrida(tipoCorrida);
                viagens.setTipoVelocidade(tipoVelocidade);
                viagens.setValorCorrida(valorCorrida);
                viagens.setOrigem(origem);
                viagens.setDestino(destino);
                viagens.setData(data);

                return viagens;
            }

        }
        return viagens;
    }


    //@Override
    public List<Viagens> listar() {
        List<Viagens> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + BDHelper.TABELA_VIAGENS+";";
        Cursor cursor = ler.rawQuery(sql, null);

        while(cursor.moveToNext()){

            Viagens viagens = new Viagens();

            int idMototaxista = cursor.getInt(cursor.getColumnIndex("idMototaxista"));
            String nomeCliente = cursor.getString(cursor.getColumnIndex("nomeCliente"));
            String numero = cursor.getString(cursor.getColumnIndex("numero"));
            String tipoCorrida = cursor.getString(cursor.getColumnIndex("tipoCorrida"));
            String tipoVelocidade = cursor.getString(cursor.getColumnIndex("tipoVelocidade"));
            double valorCorrida = cursor.getDouble(cursor.getColumnIndex("valorCorrida"));
            String origem = cursor.getString(cursor.getColumnIndex("origem"));
            String destino = cursor.getString(cursor.getColumnIndex("destino"));
            String data = cursor.getString(cursor.getColumnIndex("data"));

            viagens.setIdMototaxista(idMototaxista);
            viagens.setNomeCliente(nomeCliente);
            viagens.setNumero(numero);
            viagens.setTipoCorrida(tipoCorrida);
            viagens.setTipoVelocidade(tipoVelocidade);
            viagens.setValorCorrida(valorCorrida);
            viagens.setOrigem(origem);
            viagens.setDestino(destino);
            viagens.setData(data);

            lista.add(viagens);
        }

        return lista;
    }
}
