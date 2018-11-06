package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.model.Imagem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hercules silva
 * */
public class ImagemDAO {

    public static String TABELA_IMAGEM = BDHelper.TABELA_IMAGEM;
    public static String IMAGEM_ID =BDHelper.IMAGEM_ID;
    public static String IMAGEM_DADOS= BDHelper.IMAGEM_DADOS;
    public static String IMAGEM_DESCRICAO= BDHelper.IMAGEM_DESCRICAO;
    public static String IMAGEM_ID_MOTOTAXITA = BDHelper.IMAGEM_IDMOTOTAXISTA;

    private BDHelper db;
    private SQLiteDatabase escreve;         // escrever dados na tabela/ salvar
    private SQLiteDatabase ler;             // ler as tabelas


    public ImagemDAO (Context context){
        db = new BDHelper(context);
        escreve = db.getWritableDatabase();     // permite salvar no banco de dados
        ler = db.getReadableDatabase();         // permite ler os dados de uma tabela
    }

    /**
     * Add image
     * */
    public boolean addImage(Imagem img) {

        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put(IMAGEM_DADOS,img.getDados());
        cv.put(IMAGEM_DESCRICAO, img.getDescricao());
        cv.put(IMAGEM_ID_MOTOTAXITA, img.getIdMototaxista());

        try{
            escreve.insert(
                    // nome da tabela
                    TABELA_IMAGEM,
                    null,
                    cv);

            Log.i("INFO", "Exito ao salvar Imagem");

        } catch (Exception e){
            Log.i("INFO", "Erro ai salvar Imagem: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    //@Override
    public boolean atualizar(Imagem img) {
        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put(IMAGEM_DADOS,img.getDados());
        cv.put(IMAGEM_DESCRICAO, img.getDescricao());
        cv.put(IMAGEM_ID_MOTOTAXITA, img.getIdMototaxista());

        try{
            String[] argumentos = {
                    String.valueOf(img.getIdImagem())};

            escreve.update(
                    BDHelper.TABELA_MOTO,
                    cv,
                    // clausula where - caracter coringa
                    IMAGEM_ID+"=?",

                    // argumentos
                    argumentos
            );

            Log.i("INFO", "Exito ao atualizar Imagem");

        } catch (Exception e){
            Log.i("Erro", "Erro ao atualizar Imagem: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }


    /**
     * Tem que ter obrigatoriamente o id dessa imagem com o id do mototaxista
     * */
    public boolean deletar(Imagem img) {

        return false;
    }

    /**
     * Lista todas as imagens no banco de dados
     * */
    public List<Imagem> listar(){

        List<Imagem> imagens = new ArrayList<Imagem>();
        String sql = "SELECT * FROM " + BDHelper.TABELA_IMAGEM+";";

        try{
            Cursor cursor = ler.rawQuery(sql, null);

            while(cursor.moveToNext()){
                // convert blob to byte array
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(IMAGEM_DADOS));

                Imagem imagem = new Imagem();
                imagem.setIdImagem(cursor.getInt(cursor.getColumnIndex(IMAGEM_ID)));
                imagem.setDescricao(cursor.getString(cursor.getColumnIndex(IMAGEM_DESCRICAO)));
                imagem.setIdMototaxista(cursor.getInt(cursor.getColumnIndex(IMAGEM_ID_MOTOTAXITA)));
                imagem.setDados(blob);

                imagens.add(imagem);
            }
        } catch (SQLException e){
            Log.i("Erro", "Erro ao listar Imagens: "+e.getMessage());
            e.printStackTrace();
        }

        return imagens;
    }


    public Imagem getImage(int idMototaxista, String descricao){
        String sql = "SELECT * FROM " + BDHelper.TABELA_IMAGEM+";";

        try {
            Cursor cursor = ler.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(IMAGEM_ID_MOTOTAXITA));
                String desc = cursor.getString(cursor.getColumnIndex(IMAGEM_DESCRICAO));

                if (id == idMototaxista
                        && desc.equalsIgnoreCase(descricao)) {

                    // convert blob to byte array
                    byte[] blob = cursor.getBlob(cursor.getColumnIndex(IMAGEM_DADOS));

                    Imagem imagem = new Imagem();
                    imagem.setIdImagem(cursor.getInt(cursor.getColumnIndex(IMAGEM_ID)));
                    imagem.setDescricao(cursor.getString(cursor.getColumnIndex(IMAGEM_DESCRICAO)));
                    imagem.setIdMototaxista(cursor.getInt(cursor.getColumnIndex(IMAGEM_ID_MOTOTAXITA)));
                    imagem.setDados(blob);

                    return  imagem;
                }
            }
        } catch (SQLException e) {
            Log.i("Erro", "Erro ao listar Imagens do id"+idMototaxista+": " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param idMototaxista -
     * Lista todas as imagens do mototaxista
     * */
    public List<Imagem> listar(int idMototaxista){
        List<Imagem> imagens = new ArrayList<Imagem>();
        String sql = "SELECT * FROM " + BDHelper.TABELA_IMAGEM+";";

        try {
            Cursor cursor = ler.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(IMAGEM_ID_MOTOTAXITA));
                if (id == idMototaxista) {

                    // convert blob to byte array
                    byte[] blob = cursor.getBlob(cursor.getColumnIndex(IMAGEM_DADOS));

                    Imagem imagem = new Imagem();
                    imagem.setIdImagem(cursor.getInt(cursor.getColumnIndex(IMAGEM_ID)));
                    imagem.setDescricao(cursor.getString(cursor.getColumnIndex(IMAGEM_DESCRICAO)));
                    imagem.setIdMototaxista(cursor.getInt(cursor.getColumnIndex(IMAGEM_ID_MOTOTAXITA)));
                    imagem.setDados(blob);

                    imagens.add(imagem);
                }
            }
        } catch (SQLException e) {
            Log.i("Erro", "Erro ao listar Imagens do id"+idMototaxista+": " + e.getMessage());
            e.printStackTrace();
        }
        return imagens;
    }
}
