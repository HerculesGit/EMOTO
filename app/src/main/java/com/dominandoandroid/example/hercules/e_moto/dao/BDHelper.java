package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;

public class BDHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;                      // primeira versão do App
    public static String NOME_BD = "emoto.db";
    public static String TABELA_MOTOTAXISTA = "mototaxista";
    public static String TABELA_DADOS_PESSOAIS = "dadospessoais";
    public static String TABELA_MOTO = "moto";
    public static String TABELA_ENDERECO= "endereco";
    //public static String TABELA_CLIENTES = "clientes";
    public static String TABELA_VIAGENS = "viagens";

    public static String TABELA_IMAGEM = "imagem";
    public static String IMAGEM_ID = "idImagens";
    public static String IMAGEM_DADOS= "imagem_dados";
    public static String IMAGEM_DESCRICAO= "imagem_descricao";
    public static String IMAGEM_IDMOTOTAXISTA = "idMototaxista";

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

        String sqlFoto = "CREATE TABLE IF NOT EXISTS "+ TABELA_IMAGEM
                +" ("+IMAGEM_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IMAGEM_DADOS + " BLOB NOT NULL, "
                + IMAGEM_DESCRICAO + " TEXT NOT NULL,"
                + IMAGEM_IDMOTOTAXISTA + " INTEGER NOT NULL,"
                + " FOREIGN KEY ("+IMAGEM_IDMOTOTAXISTA+") REFERENCES "+BDHelper.TABELA_MOTOTAXISTA+"(idMototaxista))";

        String sqlDadosPessoais = "CREATE TABLE IF NOT EXISTS "+TABELA_DADOS_PESSOAIS
                //+" (idDadosPessoais INTEGER PRIMARY KEY AUTOINCREMENT,"
                +" (idDadosPessoais INTEGER PRIMARY KEY,"
                +" nome VARCHAR(80) NOT NULL,"
                +" rg VARCHAR NOT NULL UNIQUE,"
                +" cpf VARCHAR NOT NULL UNIQUE)";

        String sqlEndereco = "CREATE TABLE IF NOT EXISTS "+TABELA_ENDERECO
                //+" (idEndereco INTEGER PRIMARY KEY AUTOINCREMENT,"
                +" (idEndereco INTEGER PRIMARY KEY,"
                +" estado VARCHAR NOT NULL,"
                +" cidade VARCHAR NOT NULL,"
                +" rua VARCHAR NOT NULL,"
                +" numero VARCHAR NOT NULL,"
                +" bairro VARCHAR NOT NULL)";

        String sqlMoto = "CREATE TABLE IF NOT EXISTS "+TABELA_MOTO
                //+" (idMoto INTEGER PRIMARY KEY AUTOINCREMENT,"
                +" (idMoto INTEGER PRIMARY KEY,"
                +" marca VARCHAR NOT NULL,"
                +" modelo VARCHAR NOT NULL,"
                +" placa VARCHAR NOT NULL)";

        String sqlMotoTaxista = "CREATE TABLE IF NOT EXISTS "+TABELA_MOTOTAXISTA
                +" (idMototaxista INTEGER PRIMARY KEY AUTOINCREMENT,"
                +" email VARCHAR NOT NULL UNIQUE,"
                +" senha VARCHAR NOT NULL,"
                + "numeroCelular VARCHAR NOT NULL UNIQUE,"
                +" idEndereco INTEGER,"
                +" idMoto INTEGER,"
                +" idDadosPessoais INTEGER,"
                +" disponibilidade INT(1)," // 0-false 1-true

                +" FOREIGN KEY (idEndereco) REFERENCES "+TABELA_ENDERECO+"(idEndereco),"
                +" FOREIGN KEY (idDadosPessoais) REFERENCES "+TABELA_DADOS_PESSOAIS+"(idDadosPessoais),"
                +" FOREIGN KEY (idMoto) REFERENCES "+TABELA_MOTO+"(idMoto))";

        String sqlViagens = // Todas as viagens que o mototaxista ja vez
                "CREATE TABLE IF NOT EXISTS "+TABELA_VIAGENS
                +" (nomeCliente VARCHAR(80) NOT NULL, "
                        +" idMototaxista INTEGER NOT NULL,"
                +" numero VARCHAR NOT NULL,"
                +" tipoCorrida VARCHAR NOT NULL," // leveme encomenda
                +" tipoVelocidade VARCHAR NOT NULL," // normal, rapido, express
                +" valorCorrida DOUBLE(6),"
                +" origem VARCHAR NOT NULL,"
                +" destino VARCHAR NOT NULL,"
                +" data VARCHAR NOT NULL,"
                +" FOREIGN KEY (idMototaxista) REFERENCES "+TABELA_MOTOTAXISTA+"(idMototaxista))";

        try{
            db.execSQL(sqlDadosPessoais);
            Log.i("INFO", "Sucesso ao criar "+TABELA_DADOS_PESSOAIS);

            db.execSQL(sqlFoto);
            Log.i("INFO", "Sucessoao criar "+ TABELA_IMAGEM);

            db.execSQL(sqlEndereco);
            Log.i("INFO", "Sucesso ao criar "+TABELA_ENDERECO);

            db.execSQL(sqlMoto);
            Log.i("INFO", "Sucesso ao criar "+TABELA_MOTO);

            db.execSQL(sqlMotoTaxista);
            Log.i("INFO", "Sucesso ao criar "+TABELA_MOTOTAXISTA);

            db.execSQL(sqlViagens);
            Log.i("INFO", "Sucesso ao criar "+TABELA_VIAGENS);


        } catch (Exception e){
            Log.i("ERROR","Error "+e.getMessage());
            e.printStackTrace();

        } finally {
            // fechar banco independente do que aconteca
            //db.close();
        }
    }

    /**
     * Utilizado quando for fazer outra versão do app
     * Criar tabelas, ou atualizar as tabelas que existem...
     * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVesion) {

        // Lógica para atualizar DB

    }
}
