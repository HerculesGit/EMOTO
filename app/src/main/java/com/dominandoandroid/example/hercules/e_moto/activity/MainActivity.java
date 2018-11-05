package com.dominandoandroid.example.hercules.e_moto.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.dao.DadosPessoaisDAO;
import com.dominandoandroid.example.hercules.e_moto.dao.MotoTaxiDAO;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String ARQUIVO_PREFERENCIA_LOGIN = "loginPreferencial";

    private String numero = "";
    private String senha = "";
    private MotoTaxi motoTaxi;
    private Button buttonLogin;
    private EditText editTextTelefone, editTextSenha;
    private TextView textBtRegistre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        buttonLogin = findViewById(R.id.buttonLogar);
        textBtRegistre = findViewById(R.id.id_registre_se);

        editTextTelefone = findViewById(R.id.id_txt_login_telefone);
        editTextSenha = findViewById(R.id.id_txt_login_senha);

        recuperarPreferencia();

        // click bt login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // colocar os valores nas variaveis
                numero = editTextTelefone.getText().toString();
                senha = editTextSenha.getText().toString();

                // verificar se os campos nao estao vazios
                if(!camposEstaoVazios()){

                    if (validaDados()) { // os dados estiverem OK

                        salvarPreferencia();
                        // .class eh a activity que desejamos ir
                        Intent intencao = new Intent(MainActivity.this, HomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mototaxi", motoTaxi);
                        intencao.putExtras(bundle);

                        startActivity(intencao);
                        finish();                   // finalizar activity

                    } else {    // senao
                        Toast.makeText(getApplicationContext(), "Telefone ou senha inválido(s)", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        /**
         * Ir para a tela de cadastro do mototaxista
         * */
        textBtRegistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click txt registre-se
                Intent intent = new Intent(getApplicationContext(), CadastroTaxi.class);
                startActivity(intent);
            }
        });



        // nao apagar esta linha. Codigos comentados daqui e colocado la embaixo

        // teste
        MotoTaxiDAO  motoTaxiDAO = new MotoTaxiDAO(MainActivity.this);
        List<MotoTaxi> lista = motoTaxiDAO.listar();

        //System.out.println("helloooo");
        //for (MotoTaxi m: lista){
        //    System.out.println("->"+m.toString());
        //}

        // teste
        DadosPessoaisDAO dadosPessoaisDAO = new DadosPessoaisDAO(getApplicationContext());
        int id = dadosPessoaisDAO.pegarIDDoCpf("109");

        System.out.println("Olha o id"+id);
        //dadosPessoaisDAO.listar();


    }

    /**
     * Verifica se o mototaxi estah cadastrado
     * */
    private boolean validaDados(){

        MotoTaxiDAO  motoTaxiDAO = new MotoTaxiDAO(MainActivity.this);
        List<MotoTaxi> lista = motoTaxiDAO.listar();

        for(int i=0;i<lista.size();i++){
            System.out.println("Encontratos> "+lista.get(i).toString());
        }

        for (MotoTaxi m: lista){
            if (m.getNumeroCelular().equals(numero)
                    && m.getSenha().equals(senha)){

                motoTaxi = m;
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se tem campos vazios
     * */
    private boolean camposEstaoVazios(){
        if (editTextTelefone.getText().length() == 0){
            editTextTelefone.setError("Campo obrigatório");
            return true;
        } else if(editTextSenha.getText().length() == 0){
            editTextSenha.setError("Campo obrigatório");
            return true;
        }
        return false;
    }


    private void recuperarPreferencia(){
        MotoTaxiDAO  motoTaxiDAO = new MotoTaxiDAO(MainActivity.this);
        List<MotoTaxi> lista = motoTaxiDAO.listar();

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA_LOGIN,0 );

        if (preferences.contains("telefone")) {

            // jogar os valores nas variaveis
            numero = preferences.getString("telefone", "");
            senha = preferences.getString("senha", "");

            if (validaDados()) {
                Intent intencao = new Intent(MainActivity.this, ConfiguracoesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mototaxi", motoTaxi);
                intencao.putExtras(bundle);

                startActivity(intencao);
                finish();                   // finalizar activity
                return;
            }

        }
    }

    private void salvarPreferencia(){
        // name -> nome do arquivo |mode:0 modo privado com 0 so nosso app pode mudar no arquivo
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA_LOGIN,0 );
        SharedPreferences.Editor editor = preferences.edit(); // para poder editar,

        // com o objeto editor podemos editar o arquivo de preferencia
        String telefone = this.editTextTelefone.getText().toString();
        String senha = this.editTextSenha.getText().toString();

        editor.putString("telefone", telefone); //chave ; valor
        editor.putString("senha", senha);

        editor.commit(); // salvar
    }

}

//db.createTable();



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