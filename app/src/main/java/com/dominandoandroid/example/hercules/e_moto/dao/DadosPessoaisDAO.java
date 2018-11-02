package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.activity.MainActivity;
import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hercules silva
 * */
public class DadosPessoaisDAO implements IDadosPessoaisDAO {

    private BDHelper db;
    private SQLiteDatabase escreve;         // escrever dados na tabela/ salvar
    private SQLiteDatabase ler;             // ler as tabelas


    public DadosPessoaisDAO (Context context){
        db = new BDHelper(context);
        escreve = db.getWritableDatabase();     // permite salvar no banco de dados
        ler = db.getReadableDatabase();         // permite ler os dados de uma tabela
    }

    //@Override
    public boolean salvar(DadosPessoais dadosPessoais) {

        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put("idDadosPessoais",dadosPessoais.getIdDadosPessoais());
        cv.put("nome",dadosPessoais.getNome());
        cv.put("rg",dadosPessoais.getRg());
        cv.put("cpf",dadosPessoais.getCpf());

        try{
            escreve.insert(
                    // nome da tabela
                    BDHelper.TABELA_DADOS_PESSOAIS,
                    null,
                    cv);

            Log.i("INFO", "Exito ao salvar dados pessoais" + dadosPessoais.getNome() + " " + dadosPessoais.getCpf());

        } catch (Exception e){
            Log.i("INFO", "Erro ai salvar dados pessoais: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    //@Override
    public boolean atualizar(DadosPessoais dadosPessoais) {
        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put("nome",dadosPessoais.getNome());
        cv.put("rg",dadosPessoais.getRg());
        cv.put("cpf",dadosPessoais.getCpf());

        try{

            String[] argumentos = {
                    String.valueOf(dadosPessoais.getIdDadosPessoais())
            };
            escreve.update(
                    BDHelper.TABELA_DADOS_PESSOAIS,
                    cv,

                    // clausula where - caracter coringa
                    "idDadosPessoais=?",

                    // argumentos
                    argumentos
            );

            Log.i("INFO", "Exito ao atualizar dados pessoais");

        } catch (Exception e){
            Log.i("Erro", "Erro ao atualizar dados pessoais: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    //@Override
    public boolean deletar(DadosPessoais dadosPessoais) {
        return false;
    }

    public DadosPessoais encontrarDe(int idDadosPessoais){
        String sql = "SELECT * FROM " + BDHelper.TABELA_DADOS_PESSOAIS+";";
        Cursor cursor = ler.rawQuery(sql, null);

        DadosPessoais dadosPessoais = new DadosPessoais();
        while(cursor.moveToNext()){
            int idDadosPessoaisConsuta = cursor.getInt(cursor.getColumnIndex("idDadosPessoais"));

            if (idDadosPessoaisConsuta == idDadosPessoais){
                String cpf = cursor.getString(cursor.getColumnIndex("cpf"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String rg = cursor.getString(cursor.getColumnIndex("rg"));

                dadosPessoais.setIdDadosPessoais(idDadosPessoaisConsuta);
                dadosPessoais.setNome(nome);
                dadosPessoais.setRg(rg);
                dadosPessoais.setCpf(cpf);

                return  dadosPessoais;
            }
        }
        return dadosPessoais;
    }
    public DadosPessoais encontrarComCpf(String cpf){

        String sql = "SELECT * FROM " + BDHelper.TABELA_DADOS_PESSOAIS+";";
        Cursor cursor = ler.rawQuery(sql, null);

        DadosPessoais dadosPessoais = new DadosPessoais();
        while(cursor.moveToNext()){
            int idDadosPessoaisConsuta = cursor.getInt(cursor.getColumnIndex("idDadosPessoais"));

            String cpfConsulta = cursor.getString(cursor.getColumnIndex("cpf"));
            System.out.println("cpfs encontrado" + cpfConsulta);
            if (cpfConsulta.equals(cpf)){
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String rg = cursor.getString(cursor.getColumnIndex("rg"));

                dadosPessoais.setIdDadosPessoais(idDadosPessoaisConsuta);
                dadosPessoais.setNome(nome);
                dadosPessoais.setRg(rg);
                dadosPessoais.setCpf(cpf);

                return  dadosPessoais;
            }
        }
        return dadosPessoais;
    }


    //@Override
    public List<DadosPessoais> listar() {
        List<DadosPessoais> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + BDHelper.TABELA_DADOS_PESSOAIS+";";
        Cursor cursor = ler.rawQuery(sql, null);

        while(cursor.moveToNext()){
            DadosPessoais dadosPessoais = new DadosPessoais();

            int idDadosPessoais = cursor.getInt(cursor.getColumnIndex("idDadosPessoais"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String rg = cursor.getString(cursor.getColumnIndex("rg"));
            String cpf = cursor.getString(cursor.getColumnIndex("cpf"));

            dadosPessoais.setIdDadosPessoais(idDadosPessoais);
            dadosPessoais.setNome(nome);
            dadosPessoais.setRg(rg);
            dadosPessoais.setCpf(cpf);

            lista.add(dadosPessoais);
        }
        cursor.close();
        return lista;
    }

    public int pegarIDDoCpf(String cpf){
        String sql = "SELECT * FROM " + BDHelper.TABELA_DADOS_PESSOAIS + ";";
        Cursor cursor = ler.rawQuery(sql,null);
        int idDadosPessoais = -1;
        while(cursor.moveToNext()){
            String cpfConsulta = cursor.getString(cursor.getColumnIndex("cpf"));

            System.out.println("cpf's achados " + cpfConsulta + "-"+cpf + "->>"+cursor.getColumnIndex("idDadosPessoais"));

            if (cpfConsulta.equals(cpf)){
                idDadosPessoais = cursor.getInt(cursor.getColumnIndex("idDadosPessoais"));
                break;
            }
        }
        cursor.close();

        String sqlMototaxista = "SELECT * FROM "+BDHelper.TABELA_MOTOTAXISTA+";";
        Cursor c = ler.rawQuery(sqlMototaxista,null);
        while (c.moveToNext()){

            int idDadosPessoaisM = c.getInt(c.getColumnIndex("idDadosPessoais"));
            System.out.println("entrei while" + idDadosPessoaisM + " " + idDadosPessoais);
            if(idDadosPessoais == idDadosPessoaisM){
                System.out.println("encontrou");
                int idMotoTaxista = c.getInt(c.getColumnIndex("idMototaxista"));
                return idMotoTaxista;
            }
        }


        return -1;
    }
}
