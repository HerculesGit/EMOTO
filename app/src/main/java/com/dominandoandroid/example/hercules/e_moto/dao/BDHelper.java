package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;                      // primeira versão do App
    public static String NOME_BD = "emoto";
    public static String TABELA_MOTOTAXI = "mototaxis";
    public static String TABELA_CLIENTE = "clientes";

    private Context context;
    private SQLiteDatabase db;

    /**
     * Contexto
     * nome do banco de dados
     * factory - definir cursores
     * version - versão do banco de dados criado
     * */
    public BDHelper(Context context) {
        super(context, NOME_BD,null, VERSION);
        Log.i("CONSTRUTOR", "ENTROU");

        this.context = context;

        db = getReadableDatabase();

    }


    // criar a primeira vez o banco de dados
    /**
     * Assim que o usuário instalar o App este método será chamado
     *
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // criando primeira tabela
        String sqlMototaxi = "CREATE TABLE IF NOT EXISTS "+TABELA_MOTOTAXI+
                // incrementar automaticamente
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome VARCHAR NOT NULL, sobrenome VARCHAR NOT NULL," +
                " rg VARCHAR NOT NULL, cpf VARCHAR NOT NULL, telefone VARCHAR(11) NOT NULL, email VARCHAR NOT NULL, senha VARCHAR NOT NULL," +
                " dinheiro DOUBLE(6)," +
                " cidade VARCHAR, "+
                " marca VARCHAR NOT NULL, modelo VARCHAR NOT NULL, placa VARCHAR NOT NULL," +
                " disponivel INT(1), qtdviagens INT(3), qtdencomendas INT(3) )";

        String sqlCliente = "CREATE TABLE IF NOT EXISTS "+TABELA_CLIENTE+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " cpf VARCHAR NOT NULL, telefone VARCHAR(11) NOT NULL )";

        try{
            db.execSQL(sqlMototaxi);
            Log.i("INFO", "Sucesso ao criar chupa "+TABELA_MOTOTAXI);

            db.execSQL(sqlCliente);
            Log.i("INFO", "Sucesso ao criar chupa "+TABELA_CLIENTE);

        } catch (Exception e){
            Log.i("ERROR","Error "+e.getMessage());
            e.printStackTrace();

        } finally {
            // fechar banco independente do que aconteca
            //db.close();
        }


/*
        // criando primeira tabela
        String sqlMototaxi = "CREATE TABLE IF NOT EXISTS "+TABELA_MOTOTAXI+
                // incrementar automaticamente
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome VARCHAR NOT NULL, sobrenome VARCHAR NOT NULL," +
                " rg VARCHAR NOT NULL, cpf VARCHAR NOT NULL, telefone VARCHAR(11) NOT NULL, email VARCHAR NOT NULL, senha VARCHAR NOT NULL," +
                " dinheiro DOUBLE(6)," +
                " marca VARCHAR NOT NULL, modelo VARCHAR NOT NULL, placa VARCHAR NOT NULL," +
                " disponivel INT(1), qtdviagens INT(3), qtdencomendas INT(3) )";

        String sqlCliente = "CREATE TABLE IF NOT EXISTS "+TABELA_CLIENTE+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " cpf VARCHAR NOT NULL, telefone CHAR(11) NOT NULL );";

        try {
            db.execSQL(sqlMototaxi);
            Log.i("INFO DB","Sucesso ao criar a tabela mototaxis ");
            db.execSQL(sqlCliente);
            Log.i("INFO DB","Sucesso ao criar a tabela clientes ");
        } catch (Exception e){
            Log.i("INFO DB","Erro ao criar a tabela "+ e.getMessage());
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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVesion) {

        // Lógica para atualizar DB

    }

    public boolean createTable(){
        openBD();

        String createTable = "CREATE TABLE IF NOT EXISTS teste"+
                " (nome TEXT, idade INTEGER, CASADA TEXT)";

        try{
            db.execSQL(createTable);
            Log.i("INFO", "Sucesso ao criar tabela teste");

            return true;
        } catch (Exception e){
            e.printStackTrace();

            return false;
        } finally {
            // fechar banco independente do que aconteca
            db.close();
        }
    }


    /**
     * Checkar se tá aberto, e abrir caso necessário
     * */
    private void openBD(){
        if (!db.isOpen()){
            db = context.openOrCreateDatabase(NOME_BD,

                    // ler e escrever
                    SQLiteDatabase.OPEN_READWRITE,

                    // cursor
                    null
            );
        }
    }


}
