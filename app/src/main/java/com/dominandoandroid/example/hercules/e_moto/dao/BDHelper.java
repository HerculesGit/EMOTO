package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;

public class BDHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;                      // primeira versão do App
    public static String NOME_BD = "emoto.db";

    public static final String TABELA_MOTOTAXISTA = "mototaxista";
    public static String TABELA_DADOS_PESSOAIS = "dadospessoais";
    public static String TABELA_MOTO = "moto";
    public static String TABELA_ENDERECO= "endereco";
    public static String TABELA_VIAGENS = "viagens";

    // tabela mototaxi
    public static final String MOTOTAXISTA_ID = "idMototaxista";
    public static final String MOTOTAXISTA_EMAIL = "email";
    public static final String MOTOTAXISTA_SENHA = "senha";
    public static final String MOTOTAXISTA_NUMERO_CELULAR = "numeroCelular";
    public static final String MOTOTAXISTA_ID_DADOS_PESSOAIS = "idDadosPessoais";
    public static final String MOTOTAXISTA_ID_ENDERECO = "idEndereco";
    public static final String MOTOTAXISTA_ID_MOTO = "idMoto";
    public static final String MOTOTAXISTA_ID_IMAGEM = "idImagem";
    public static final String MOTOTAXISTA_DISPONIBILIDADE = "disponivel";

    // tabela dados pessoais
    public static final String DADOS_PESSOAIS_ID = "idDadosPessoais";
    public static final String DADOS_PESSOAIS_NOME = "nome";
    public static final String DADOS_PESSOAIS_RG = "rg";
    public static final String DADOS_PESSOAIS_CPF = "cpf";

    // tabela endereco
    public static final String ENDERECO_ID = "idEndereco";
    public static final String ENDERECO_ESTADO = "estado";
    public static final String ENDERECO_CIDADE = "cidade";
    public static final String ENDERECO_RUA = "rua";
    public static final String ENDERECO_NUMERO = "numero";
    public static final String ENDERECO_BAIRRO = "bairro";

    // viagens
    public static final String VIAGENS_BAIRRO_NOME_CLIENTE= "nomeCliente";
    public static final String VIAGENS_ID_MOTO_TAXISTA = "idMotoTaxista";
    public static final String VIAGENS_NUMERO = "numero";
    public static final String VIAGENS_TIPO_CORRIDA = "tipoCorrida";
    public static final String VIAGENS_TIPO_VELOCIDADE = "tipoVelocidade";
    public static final String VIAGENS_VALOR_CORRIDA= "valorCorrida";
    public static final String VIAGENS_ORIGEM = "origem";
    public static final String VIAGENS_DESTINO= "destino";
    public static final String VIAGENS_DATA= "data";

    // tabela imagem
    public static final String TABELA_IMAGEM = "imagem";
    public static final String IMAGEM_ID = "idImagens";
    public static final String IMAGEM_DADOS= "imagem_dados";
    public static final String IMAGEM_DESCRICAO= "imagem_descricao";

    // moto
    public static final String MOTO_ID = "idmoto";
    public static final String MOTO_MARCA = "marca";
    public static final String MOTO_MODELO= "modelo";
    public static final String MOTO_PLACA= "placa";



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

        createDataBase(db);

    }

    /**
     * Utilizado quando for fazer outra versão do app
     * Criar tabelas, ou atualizar as tabelas que existem...
     * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVesion) {

//        // Lógica para atualizar DB
//        if(newVesion != oldVersion){
//            db.execSQL("DROP TABLE IF EXISTS " + TABELA_MOTOTAXISTA);
//            db.execSQL("DROP TABLE IF EXISTS " + TABELA_DADOS_PESSOAIS);
//            db.execSQL("DROP TABLE IF EXISTS " + TABELA_ENDERECO);
//            db.execSQL("DROP TABLE IF EXISTS " + TABELA_IMAGEM);
//            db.execSQL("DROP TABLE IF EXISTS " + TABELA_MOTO);
//            db.execSQL("DROP TABLE IF EXISTS " + TABELA_IMAGEM);
//            db.execSQL("DROP TABLE IF EXISTS " + TABELA_VIAGENS);
//
//            onCreate(db);
//        }

    }

    private void createDataBase(SQLiteDatabase db){
        String sqlFoto = "CREATE TABLE IF NOT EXISTS "+ TABELA_IMAGEM
                +" ("+IMAGEM_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IMAGEM_DADOS + " BLOB NOT NULL, "
                + IMAGEM_DESCRICAO + " TEXT NOT NULL)";

        String sqlDadosPessoais = "CREATE TABLE IF NOT EXISTS "+TABELA_DADOS_PESSOAIS
                +" ("+DADOS_PESSOAIS_ID+" INTEGER PRIMARY KEY, "
                +DADOS_PESSOAIS_NOME+" VARCHAR(80) NOT NULL, "
                +DADOS_PESSOAIS_RG+" VARCHAR NOT NULL UNIQUE, "
                +DADOS_PESSOAIS_CPF+" VARCHAR NOT NULL UNIQUE)";

        String sqlEndereco = "CREATE TABLE IF NOT EXISTS "+TABELA_ENDERECO
                +" ("+ENDERECO_ID+" INTEGER PRIMARY KEY, "
                +ENDERECO_ESTADO+" VARCHAR NOT NULL, "
                +ENDERECO_CIDADE+" VARCHAR NOT NULL, "
                +ENDERECO_RUA+" VARCHAR NOT NULL, "
                +ENDERECO_NUMERO+" VARCHAR NOT NULL, "
                +ENDERECO_BAIRRO+" VARCHAR NOT NULL)";

        String sqlVeiculo = "CREATE TABLE IF NOT EXISTS "+TABELA_MOTO
                +" ("+MOTO_ID+" INTEGER PRIMARY KEY, "
                +MOTO_MARCA+" VARCHAR NOT NULL, "
                +MOTO_MODELO+" VARCHAR NOT NULL, "
                +MOTO_PLACA+" VARCHAR NOT NULL)";

        String sqlMotoTaxista = "CREATE TABLE IF NOT EXISTS "+TABELA_MOTOTAXISTA
                +"("+MOTOTAXISTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +MOTOTAXISTA_EMAIL+" VARCHAR NOT NULL UNIQUE, "
                +MOTOTAXISTA_SENHA+" VARCHAR NOT NULL, "
                +MOTOTAXISTA_NUMERO_CELULAR+" VARCHAR NOT NULL UNIQUE, "
                +MOTOTAXISTA_ID_ENDERECO+" INTEGER, "
                +MOTOTAXISTA_ID_MOTO+" INTEGER, "
                +MOTOTAXISTA_ID_DADOS_PESSOAIS +" INTEGER, "
                +MOTOTAXISTA_ID_IMAGEM +" INTEGER, "
                +MOTOTAXISTA_DISPONIBILIDADE +" INT(1)," // 0-false 1-true

                +" FOREIGN KEY ("+MOTOTAXISTA_ID_ENDERECO+") REFERENCES "+TABELA_ENDERECO+"("+ENDERECO_ID+"),"
                +" FOREIGN KEY ("+MOTOTAXISTA_ID_DADOS_PESSOAIS+") REFERENCES "+TABELA_DADOS_PESSOAIS+"("+DADOS_PESSOAIS_ID+"),"
                +" FOREIGN KEY ("+MOTOTAXISTA_ID_MOTO+") REFERENCES "+TABELA_MOTO+"("+MOTO_ID+"),"
                +" FOREIGN KEY ("+MOTOTAXISTA_ID_IMAGEM+") REFERENCES "+TABELA_IMAGEM +"("+IMAGEM_ID+"))";

        String sqlViagens = // Todas as viagens que o mototaxista ja vez
                "CREATE TABLE IF NOT EXISTS "+TABELA_VIAGENS
                        + "("+VIAGENS_BAIRRO_NOME_CLIENTE+" VARCHAR(80) NOT NULL, "
                        +VIAGENS_ID_MOTO_TAXISTA+" INTEGER NOT NULL, "
                        +VIAGENS_NUMERO+" VARCHAR NOT NULL, "
                        +VIAGENS_TIPO_CORRIDA+" VARCHAR NOT NULL, " // leveme encomenda
                        +VIAGENS_TIPO_VELOCIDADE+" VARCHAR NOT NULL, " // normal, rapido, express
                        +VIAGENS_VALOR_CORRIDA+" DOUBLE(6), "
                        +VIAGENS_ORIGEM+" VARCHAR NOT NULL, "
                        +VIAGENS_DESTINO+" VARCHAR NOT NULL, "
                        +VIAGENS_DATA+" VARCHAR NOT NULL, "
                        +" FOREIGN KEY ("+VIAGENS_ID_MOTO_TAXISTA+") REFERENCES "+TABELA_MOTOTAXISTA+"("+MOTOTAXISTA_ID+"))";

        try{
            db.execSQL(sqlDadosPessoais);
            Log.i("INFO", "Sucesso ao criar "+TABELA_DADOS_PESSOAIS);

            db.execSQL(sqlFoto);
            Log.i("INFO", "Sucessoao criar "+ TABELA_IMAGEM);

            db.execSQL(sqlEndereco);
            Log.i("INFO", "Sucesso ao criar "+TABELA_ENDERECO);

            db.execSQL(sqlVeiculo);
            Log.i("INFO", "Sucesso ao criar "+TABELA_MOTO);

            db.execSQL(sqlMotoTaxista);
            Log.i("INFO", "Sucesso ao criar "+TABELA_MOTOTAXISTA);

            db.execSQL(sqlViagens);
            Log.i("INFO", "Sucesso ao criar "+TABELA_VIAGENS);


        } catch (Exception e){
            Log.i("ERROR","Error "+e.getMessage());
            e.printStackTrace();

        }
    }
}
