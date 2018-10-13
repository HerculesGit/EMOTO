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

public class EditarMotoTaxista extends AppCompatActivity {

    private static Context ctx;
    private static  MotoTaxi motoTaxi;
    private Button buttonAlterarDados;
    private static MotoTaxiDAO motoTaxiDAO;
    private static TextInputEditText editTextNome, editTextSobrenome, editTextCpf, editTextRg,
            editTextCidade, editTextTelefone, editTextEmail, editTextSenha,
            inputEditTextMarca, inputEditTextModelo, inputEditTextPlaca;

    private static DadosPessoais dadosPessoais;
    private static Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_moto_taxista);

        ctx = getApplication();
        Bundle dados = getIntent().getExtras();

        motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());
        String cpf = dados.getString("cpf");

        for(MotoTaxi o : motoTaxiDAO.listar()){

            if (o.getDadosPessoais().getCpf().equals(cpf)){
                motoTaxi = o;
                break;
            }
        }


        buttonAlterarDados = findViewById(R.id.buttonAlterarDados);
        editTextNome = findViewById(R.id.dados_nome);
        editTextSobrenome = findViewById(R.id.dados_sobrenome);
        editTextRg = findViewById(R.id.dados_rg);
        editTextCpf = findViewById(R.id.dados_cpf);
        editTextCidade = findViewById(R.id.dados_cidade);
        editTextTelefone = findViewById(R.id.dados_telefone);
        editTextEmail = findViewById(R.id.dados_email);
        editTextSenha = findViewById(R.id.dados_senha);

        inputEditTextMarca = findViewById(R.id.dados_marca);
        inputEditTextModelo = findViewById(R.id.dados_modelo);
        inputEditTextPlaca = findViewById(R.id.dados_placa);

        carregaDadosNosCampos();
    }
    private void carregaDadosNosCampos(){
        editTextNome.setText(motoTaxi.getDadosPessoais().getNome());
        editTextSobrenome.setText(motoTaxi.getDadosPessoais().getSobrenome());
        editTextRg.setText(motoTaxi.getDadosPessoais().getRg());
        editTextCpf.setText(motoTaxi.getDadosPessoais().getCpf());
        editTextCidade.setText(motoTaxi.getDadosPessoais().getCidade());
        editTextTelefone.setText(motoTaxi.getDadosPessoais().getTelefone());
        editTextEmail.setText(motoTaxi.getDadosPessoais().getEmail());
        editTextSenha.setText(motoTaxi.getDadosPessoais().getSenha());
        inputEditTextMarca.setText(motoTaxi.getVeiculo().getMarca());
        inputEditTextModelo.setText(motoTaxi.getVeiculo().getModelo());
        inputEditTextPlaca.setText(motoTaxi.getVeiculo().getPlaca());
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

    public void alterarMotoTaxi(View view){

//        List<MotoTaxi> listaMotoTaxistas = new ArrayList<>();
//
//        listaMotoTaxistas = motoTaxiDAO.listar();

        // validacao dos campos
        if (!camposEstaoVazios()){

            // pergunta se deseja salvar realmente
            desejaAlterar();
        }
    }

    private void desejaAlterar(){

        /**
         * Criar AlertDialog
         * */
        AlertDialog.Builder dialog  = new AlertDialog.Builder(
                this
        );

        // configura titulo e mensagem
        dialog.setTitle("Alterar");
        dialog.setMessage("Deseja Alterar?");

        //configura acoes para botao sim
        dialog.setPositiveButton(
                // sim/ aceito
                "Sim",
                //
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getApplicationContext(),"Foi em Sim",Toast.LENGTH_SHORT).show();

                        motoTaxi = recuperaDados();
                        if (motoTaxiDAO.atualizar(motoTaxi)){
                            Toast.makeText(getApplicationContext(),"Alterado com sucesso ", Toast.LENGTH_LONG).show();

                            // vai para outra tela
                            Intent intent = new Intent(getApplicationContext(), MotoTaxista.class);

                            intent.putExtra("cpf",motoTaxi.getDadosPessoais().getCpf());
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"Erro ao alterar mototaxista", Toast.LENGTH_LONG).show();
                        }
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

}
