package com.dominandoandroid.example.hercules.e_moto;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dominandoandroid.example.hercules.e_moto.dao.MotoTaxiDAO;
import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;
import com.dominandoandroid.example.hercules.e_moto.model.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class CadastroTaxi extends AppCompatActivity {

    private MotoTaxiDAO motoTaxiDAO;

    private MotoTaxi motoTaxi;
    private DadosPessoais dadosPessoais;
    private Veiculo veiculo;


    private Button btConfirmar;
    private TextInputEditText editTextNome, editTextSobrenome, editTextCpf, editTextRg,
            editTextCidade, editTextTelefone, editTextEmail, editTextSenha,
            inputEditTextMarca, inputEditTextModelo, inputEditTextPlaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_taxi);

        btConfirmar = findViewById(R.id.buttonConfirmarCadastro);
        editTextNome = findViewById(R.id.dados_nome);
        editTextSobrenome = findViewById(R.id.dados_nome);
        editTextRg = findViewById(R.id.dados_nome);
        editTextCpf = findViewById(R.id.dados_nome);
        editTextCidade = findViewById(R.id.dados_nome);
        editTextTelefone = findViewById(R.id.dados_nome);
        editTextEmail = findViewById(R.id.dados_nome);
        editTextSenha = findViewById(R.id.dados_nome);

        inputEditTextMarca = findViewById(R.id.dados_nome);
        inputEditTextModelo= findViewById(R.id.dados_nome);
        inputEditTextPlaca = findViewById(R.id.dados_nome);

        onClickConfirmar();
    }

    private void verificaCampos(){

    }

    private void onClickConfirmar(){
        btConfirmar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getApplicationContext(),"Cadastrado com sucesso "+editTextNome.getText().toString(),Toast.LENGTH_LONG).show();
                        // perguntar se quer confirmar

                        // verifica campos

                        // recuperar dados
                        motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());
                        // pegar dados
                        String nome = editTextNome.getText().toString();
                        String sobrenome = editTextSobrenome.getText().toString();
                        String rg = editTextRg.getText().toString();
                        String cpf = editTextCpf.getText().toString();
                        String cidade = editTextCidade.getText().toString();
                        String telefone = editTextTelefone.getText().toString();
                        String email = editTextEmail.getText().toString();
                        String senha = editTextSenha.getText().toString();
                        String marca = inputEditTextMarca.getText().toString();
                        String modelo = inputEditTextModelo.getText().toString();
                        String placa = inputEditTextPlaca.getText().toString();

                        //String nome, String sobrenome, String cpf, String rg, String telefone, String senha, String email
                        dadosPessoais = new DadosPessoais(nome, sobrenome,cpf,rg,telefone,senha,email);
                        veiculo = new Veiculo(marca,modelo,placa);
                        motoTaxi = new MotoTaxi(dadosPessoais,true,0,0, 0.0, 0.0, veiculo);

                        //motoTaxiDAO.salvar(null);

                        //System.out.println(motoTaxi.toString());
                        // / salvar dados
                        //boolean salvo = motoTaxiDAO.salvar(
                        //        motoTaxi
                        //);


                        /*if (salvo){
                            Toast.makeText(getApplicationContext(),"Cadastrado com sucesso ["+nome +"]",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Erro ao cadastar mototaxi ["+nome+"]",Toast.LENGTH_LONG).show();
                        }*/

                        // abrir tela para cadastrar Veiculo
                        //Intent intent = new Intent(getApplicationContext(), CadastroVeiculo.class);
                        //startActivity(intent);


                        /*
                        List<MotoTaxi> listaMotoTaxistas = new ArrayList<>();

                        listaMotoTaxistas = motoTaxiDAO.listar();

                        System.out.println("LISTA");
                        for (MotoTaxi m: listaMotoTaxistas){
                            System.out.println(m.toString());
                        }
                        */


                    }
                }
        );
    }

    private void recuperaDados(){
        motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());
        // pegar dados
        String nome = editTextNome.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();
        String rg = editTextRg.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();
        String marca = inputEditTextMarca.getText().toString();
        String modelo = inputEditTextModelo.getText().toString();
        String placa = inputEditTextPlaca.getText().toString();

        //String nome, String sobrenome, String cpf, String rg, String telefone, String senha, String email
        dadosPessoais = new DadosPessoais(nome, sobrenome,cpf,rg,telefone,senha,email);
        veiculo = new Veiculo(marca,modelo,placa);
        motoTaxi = new MotoTaxi(dadosPessoais,true,0,0, 0.0, 0.0, veiculo);

    }

}
