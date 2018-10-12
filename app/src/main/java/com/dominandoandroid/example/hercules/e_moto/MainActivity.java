package com.dominandoandroid.example.hercules.e_moto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dominandoandroid.example.hercules.e_moto.dao.BDHelper;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin, buttonCreate;
    private RadioGroup radioGroup;
    private RadioButton radioUsuario, radioMotoTaxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        radioGroup = findViewById(R.id.radioGroup);
        radioUsuario = findViewById(R.id.radioButtonUsuario);
        radioMotoTaxi = findViewById(R.id.radioButtonMotoTaxi);
        buttonCreate = findViewById(R.id.buttonCreate);


        this.verificaRadioButton();

        BDHelper db = new BDHelper(getApplicationContext());
        //db.createTable();

        // adicionando o click no botão - quando o usuário clicar no botão
        buttonLogin.setOnClickListener(
                // new em uma interface -> classe anônima
                new View.OnClickListener() {

                    // ações para quando o usuário clicar no botão
                    @Override
                    public void onClick(View view) {

                        if (radioMotoTaxi.isChecked()){
                            Intent intent =  new Intent(
                                    getApplicationContext(),

                                    // activity que queremos ir
                                    //TipoServico.class
                                    Status.class);

                            startActivity(intent);
                        } else{
                            Intent intent = new Intent(
                                    getApplicationContext(), ConfirmacaoPiloto.class);

                            startActivity(intent);
                        }
                    }
                }
        );

        // Criar usuário
        buttonCreate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), CadastroTaxi.class);
                        startActivity(intent);
                    }
                }
        );


        /*
        BDHelper db = new BDHelper(this);
        //db.onCreate(null);



        // banco de dados
        SQLiteDatabase bancoDados = openOrCreateDatabase(

                "mototaxi",      //nome
                MODE_PRIVATE,           // apenas o
                null
        );
        try {
            bancoDados.execSQL(
                    "CREATE TABLE IF NOT EXISTS mototaxi(nome VARCHAR, sobrenome VARCHAR," +
                            " rg VARCHAR, cpf VARCHAR, telefone CHAR(11), email VARCHAR," +
                            " senha VARCHAR, dinheiro DOUBLE(6), marca VARCHAR, modelo VARCHAR," +
                            " placa VARCHAR, disponivel INT(1), qtdviagens INT(3), qtdencomendas INT(3) )");
            Log.i("Showww", "google" );
        } catch ( Exception e){
            Log.i("ERRO", e.getMessage() );
        }*/
/*
 private  DadosPessoais dadosPessoais;
    private boolean disponivel;
    private int qtdViagensDiaria;
    private int qtdEncomendas;

    private Double dinheiro;
    private Double valorViagem;
    private Veiculo veiculo;
* */
/*
        try{


            // criar tabela
            bancoDados.execSQL(
                    "CREATE TABLE IF NOT EXISTS mototaxi(nome VARCHAR, sobrenome VARCHAR," +
                            " rg VARCHAR, cpf VARCHAR, telefone CHAR(11), email VARCHAR," +
                            " senha VARCHAR, dinheiro DOUBLE(6), marca VARCHAR, modelo VARCHAR," +
                            " placa VARCHAR, disponivel INT(1), qtdviagens INT(3), qtdencomendas INT(3) )"
            );


            // inserir dados/registros
            bancoDados.execSQL("INSERT INTO mototaxi(nome, sobrenome, rg, cpf, telefone," +
                    " email, senha," +
                    " dinheiro," +
                    " marca, modelo, placa," +
                    " disponivel, qtdviagens, qtdencomendas) " +
                    " VALUES ('Hércules', 'Silva', '1111', '1092222', '83999999999', 'email@email.com', '123senha'," +
                    " 0.00,'honda', 'fan 150','mno4545', " +
                    " 0, 0, 0) ");

            // inserir dados/registros
            bancoDados.execSQL("INSERT INTO mototaxi(nome, sobrenome, rg, cpf, telefone," +
                    " email, senha," +
                    " dinheiro," +
                    " marca, modelo, placa," +
                    " disponivel, qtdviagens, qtdencomendas) " +
                    " VALUES ('Maria', 'Souza', '1111', '1092222', '83999999999', 'email@email.com', '123senha'," +
                    " 0.00,'honda', 'fan 150','mno4545', " +
                    " 0, 0, 0) ");


            // recuperar pessoas - percorrer um a um
            String consulta = "SELECT nome, sobrenome FROM mototaxi";

            // filtros
            //consulta = "SELECT nome, sobrenome FROM mototaxi WHERE nome='Maria'";
            //consulta = "SELECT nome, sobrenome FROM mototaxi WHERE nome='Maria' AND sobrenome='Souza'"; // poderia ter vários ANDs

            /*consulta = "SELECT nome, sobrenome FROM mototaxi " +
                    "WHERE nome='Maria' OR idade>=20 ";*/

            /*consulta = "SELECT nome, sobrenome FROM mototaxi " +
                    "WHERE nome IN('Maria', 'Hércules')";*/

            // entre o intervalo - ex: produtos maior que 30,00 e maior que 4
            /*consulta = "SELECT nome, sobrenome FROM mototaxi " +
                    "WHERE idade BETWEEN 30 AND 50 ";*/

            // LIKE ou seja, como. O nome é como "Hercules"
            /*consulta = "SELECT nome, sobrenome FROM mototaxi " +
                    "WHERE nome LIKE 'Hér%'";*/ // usando a % seria, qualquer coisa após o informado

            // LIKE ou seja, como. O nome é como "Hercules"
            /*consulta = "SELECT nome, sobrenome FROM mototaxi " +
                    "WHERE nome LIKE '%Hér%'";*/ // usando a % seria, qualquer coisa antes ou após o informado

            // SELECT * FROM nome_tabela

            // pegando da tela/ componentes
           /* String filtro = "mar";
            consulta = "SELECT nome, sobrenome FROM mototaxi " +
                    "WHERE nome LIKE '%"+filtro+"%' ";*/


           // Atualizar registro
           /* String update = " UPDATE mototaxi SET nome = 'Joao'" +
                    // WHERE usado para limitar, senao colocaria Joao em todos os nomes
                    " WHERE nome='Maria' ";
            bancoDados.execSQL(update);


            // Alterando estrututa da do banco de dados
                // deletando tabela
            //bancoDados.execSQL("DROP TABLE mototax");


            // Deletar registro
            //bancoDados.execSQL("DELETE FROM mototaxi WHERE nome='Hércules'");


            // Apagar todos os dados de uma tabela
            //bancoDados.execSQL("DELETE FROM mototax");


            Cursor cursor = bancoDados.rawQuery(consulta,null);

            int indiceNome = cursor.getColumnIndex("nome");         // retorna o indice da coluna nome
            int indiceSobrenome = cursor.getColumnIndex("sobrenome");    // retorna o indice da coluna sobrenome

            // mover o cursor para o primeiro ítem : usar isso porque o rawQuery deixa ele na última linha
            cursor.moveToFirst();

            // quando for null, significa que chegou ao final
            while (cursor != null){

                String nome = cursor.getString(indiceNome);
                String sobrenome = cursor.getString(indiceSobrenome);

                Log.i("RESULTADO - nome ",nome + " "+ sobrenome);
                cursor.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
*/
    }
    /**
     * Verificação do radioButton
     *
     * */
    private void verificaRadioButton(){
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int idClicado) {

                        if(R.id.radioButtonMotoTaxi == idClicado){
                            buttonCreate.setVisibility(View.VISIBLE);
                        } else {
                            buttonCreate.setVisibility(View.GONE);
                        }
                    }
                }
        );
    }

}
