package com.dominandoandroid.example.hercules.e_moto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
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

    private static MotoTaxiDAO motoTaxiDAO;
    private static Context ctx;

    private static MotoTaxi motoTaxi;
    private static DadosPessoais dadosPessoais;
    private static Veiculo veiculo;


    private static Button btConfirmar;
    private static TextInputEditText editTextNome, editTextSobrenome, editTextCpf, editTextRg,
            editTextCidade, editTextTelefone, editTextEmail, editTextSenha,
            inputEditTextMarca, inputEditTextModelo, inputEditTextPlaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_taxi);

        btConfirmar = findViewById(R.id.buttonConfirmarCadastro);
        editTextNome = findViewById(R.id.dados_nome);
        editTextSobrenome = findViewById(R.id.dados_sobrenome);
        editTextRg = findViewById(R.id.dados_rg);
        editTextCpf = findViewById(R.id.dados_cpf);
        editTextCidade = findViewById(R.id.dados_cidade);
        editTextTelefone = findViewById(R.id.dados_telefone);
        editTextEmail = findViewById(R.id.dados_email);
        editTextSenha = findViewById(R.id.dados_senha);

        inputEditTextMarca = findViewById(R.id.dados_marca);
        inputEditTextModelo= findViewById(R.id.dados_modelo);
        inputEditTextPlaca = findViewById(R.id.dados_placa);

        motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());



    }

    private boolean camposEstaoVazios(){
        if (editTextNome.getText().toString().length() == 0){
            editTextNome.setError("Campo obrigatório");
            return true;
        }
        if (editTextSobrenome.getText().toString().length() == 0){
            editTextSobrenome.setError("Campo obrigatório");
            return true;
        }
        if (editTextRg.getText().toString().length() == 0){
            editTextRg.setError("Campo obrigatório");
            return true;
        }
        if (editTextCpf.getText().toString().length() == 0){
            editTextCpf.setError("Campo obrigatório");
            return true;
        }
        if (editTextCidade.getText().toString().length() == 0){
            editTextCidade.setError("Campo obrigatório");
            return true;
        }
        if (editTextTelefone.getText().toString().length() == 0){
            editTextTelefone.setError("Campo obrigatório");
            return true;
        }
        if (editTextEmail.getText().toString().length() == 0){
            editTextEmail.setError("Campo obrigatório");
            return true;
        }
        if (editTextSenha.getText().toString().length() == 0){
            editTextSenha.setError("Campo obrigatório");
            return true;
        }
        if (inputEditTextMarca.getText().toString().length() == 0){
            inputEditTextMarca.setError("Campo obrigatório");
            return true;
        }
        if (inputEditTextModelo.getText().toString().length() == 0){
            inputEditTextModelo.setError("Campo obrigatório");
            return true;
        }
        if (inputEditTextPlaca.getText().toString().length() == 0){
            inputEditTextPlaca.setError("Campo obrigatório");
            return true;
        }

        return false;
    }

    public void cadastrarMotoTaxi(View view){

        List<MotoTaxi> listaMotoTaxistas = new ArrayList<>();

        listaMotoTaxistas = motoTaxiDAO.listar();
        System.out.println("LISTA");
        for (MotoTaxi m: listaMotoTaxistas){
            System.out.println(m.toString());
        }

        // validacao dos campos
        if (!camposEstaoVazios()){

            // pergunta se deseja salvar realmente
            desejaSalvar();
        }

    }

    private void desejaSalvar(){

        /**
         * Criar AlertDialog
         * */
        AlertDialog.Builder dialog  = new AlertDialog.Builder(
                this
        );

        // configura titulo e mensagem
        dialog.setTitle("Quase lá");
        dialog.setMessage("Deseja Salvar?");

        //configura acoes para botao sim
        dialog.setPositiveButton(
                // sim/ aceito
                "Sim",
                //
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Foi em Sim",Toast.LENGTH_SHORT).show();

                        // vai para outra tela
                        Intent intent = new Intent(getApplicationContext(), MotoTaxista.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        // configura botao nao
        dialog.setNegativeButton(
                // nao/ negacao
                "Não",
                //
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }
        );

        // criar e exibir
        dialog.create();
        dialog.show();
    }

    private static MotoTaxi recuperaDados(){

        motoTaxiDAO = new MotoTaxiDAO(ctx);
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
        dadosPessoais = new DadosPessoais(nome, sobrenome,cpf,rg,telefone,senha,email,cidade);
        veiculo = new Veiculo(marca,modelo,placa);
        motoTaxi = new MotoTaxi(dadosPessoais,true,0,0, 0.0, 0.0, veiculo);

        return motoTaxi;

    }

}
