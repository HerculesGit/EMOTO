package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;      // primeira versão do App
    public static String NOME_BD = "E_MOTO";
    public static String TABELA_MOTOTAXI = "mototaxis";
    public static String TABELA_CLIENTE = "clientes";

    /**
     * Contexto
     * nome do banco de dados
     * factory - definir cursores
     * version - versão do banco de dados criado
     *
     * */
    public BDHelper(Context context) {

        super(context, NOME_BD,null, VERSION);
    }


    // criar a primeira vez o banco de dados
    /**
     * Assim que o usuário instalar o App este método será chamado
     *
     * */
    @Override
    public void onCreate(SQLiteDatabase bancoDados) {

        // criando primeira tabela
        String sqlMototaxi = "CREATE TABLE IF NOT EXISTS "+TABELA_MOTOTAXI+
                // incrementar automaticamente
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome VARCHAR NOT NULL, sobrenome VARCHAR NOT NULL," +
                " rg VARCHAR NOT NULL, cpf VARCHAR NOT NULL, telefone CHAR(11) NOT NULL, email VARCHAR NOT NULL, senha VARCHAR NOT NULL," +
                " dinheiro DOUBLE(6)," +
                " marca VARCHAR NOT NULL, modelo VARCHAR NOT NULL, placa VARCHAR NOT NULL," +
                " disponivel INT(1), qtdviagens INT(3), qtdencomendas INT(3) );";

        String sqlCliente = "CREATE TABLE IF NOT EXISTS "+TABELA_CLIENTE+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " cpf VARCHAR NOT NULL, telefone CHAR(11) NOT NULL );";

        try {
            bancoDados.execSQL(sqlMototaxi);
            Log.i("INFO DB","Sucesso ao cirar a tabela mototaxis ");

            bancoDados.execSQL(sqlCliente);
            Log.i("INFO DB","Sucesso ao cirar a tabela clientes ");
        } catch (Exception e){
            Log.i("INFO DB","Erro ao cirar a tabela "+ e.getMessage());
        }

        /*
        private String nome, sobrenome, telefone;
        private int qtdEncomendas, qtdViagens;
        */

        /*"CREATE TABLE IF NOT EXISTS mototaxi(nome VARCHAR, sobrenome VARCHAR," +
                            " rg VARCHAR, cpf VARCHAR, telefone CHAR(11), email VARCHAR," +
                            " senha VARCHAR, dinheiro DOUBLE(6), marca VARCHAR, modelo VARCHAR," +
                            " placa VARCHAR, disponivel INT(1), qtdviagens INT(3), qtdencomendas INT(3) )"
            );
        */
    }

    /**
     * Utilizado quando for fazer outra versão do app
     * Criar tabelas, ou atualizar as tabelas que existem...
     * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
