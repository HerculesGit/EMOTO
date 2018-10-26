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
import com.dominandoandroid.example.hercules.e_moto.model.Endereco;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;
import com.dominandoandroid.example.hercules.e_moto.model.Veiculo;

public class EditarMotoTaxista extends AppCompatActivity {

    private MotoTaxi motoTaxi;
    private Button buttonAlterarDados;
    private MotoTaxiDAO motoTaxiDAO;
    private TextInputEditText editTextNome, editTextSobrenome, editTextCpf, editTextRg,
            editTextEstado, editTextCidade, editTextRua, editTextNumero, editTextBairro, editTextTelefone, editTextEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_moto_taxista);
        getSupportActionBar().hide();

        // recuperando dados passado da outra tela
        Bundle objetoEnviado = getIntent().getExtras();

        if (objetoEnviado != null){
            motoTaxi = (MotoTaxi) objetoEnviado.getSerializable("mototaxi");
        }

        buttonAlterarDados = findViewById(R.id.buttonAlterarDados);
        editTextNome = findViewById(R.id.editar_dados_nome);
        editTextSobrenome = findViewById(R.id.editar_dados_sobrenome);
        editTextRg = findViewById(R.id.editar_dados_rg);
        editTextCpf = findViewById(R.id.editar_dados_cpf);
        editTextEstado= findViewById(R.id.editar_dados_estado);
        editTextCidade = findViewById(R.id.editar_dados_cidade);
        editTextRua = findViewById(R.id.editar_dados_rua);
        editTextNumero = findViewById(R.id.editar_dados_numero);
        editTextBairro = findViewById(R.id.editar_dados_bairro);
        editTextTelefone = findViewById(R.id.editar_dados_telefone);
        editTextEmail = findViewById(R.id.editar_dados_email);

        carregaDadosNosCampos();
    }

    /**
     * Recupera as informacoes mototaxi e coloca nos editTexts
     * */
    private void carregaDadosNosCampos(){

        // alterar dasdos
        editTextNome.setText(motoTaxi.getDadosPessoais().getNome());
        String nomeCompleto = motoTaxi.getDadosPessoais().getNome();
        for (int i=0;i<nomeCompleto.length();i++){
            if(nomeCompleto.charAt(i) == ' '){ // encontrou o espaco
                editTextSobrenome.setText(nomeCompleto.substring(i));
                break;
            }
        }
        editTextRg.setText(motoTaxi.getDadosPessoais().getRg());
        editTextCpf.setText(motoTaxi.getDadosPessoais().getCpf());

        // localizacao
        editTextEstado.setText(motoTaxi.getEndereco().getEstado());
        editTextCidade.setText(motoTaxi.getEndereco().getCidade());
        editTextRua.setText(motoTaxi.getEndereco().getRua());
        editTextNumero.setText(motoTaxi.getEndereco().getNumero());
        editTextBairro.setText(motoTaxi.getEndereco().getBairro());


        // dados acesso
        editTextTelefone.setText(motoTaxi.getNumeroCelular());
        editTextEmail.setText(motoTaxi.getEmail());

    }


    /**
     * Recupera os dados das texts e seta no mototaxi
     * */
    private void recuperaDados(){
        // pegar dados
        String nome = editTextNome.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();
        String rg = editTextRg.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String estado = editTextEstado.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String rua = editTextRua.getText().toString();
        String numero = editTextNumero.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String email = editTextEmail.getText().toString();

        motoTaxi.getDadosPessoais().setNome(nome+sobrenome);
        motoTaxi.getDadosPessoais().setCpf(cpf);
        motoTaxi.getDadosPessoais().setRg(rg);

        motoTaxi.getEndereco().setEstado(estado);
        motoTaxi.getEndereco().setCidade(cidade);
        motoTaxi.getEndereco().setRua(rua);
        motoTaxi.getEndereco().setNumero(numero);
        motoTaxi.getEndereco().setBairro(bairro);

        motoTaxi.setNumeroCelular(telefone);
        motoTaxi.setEmail(email);
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
        if (editTextRua.getText().toString().length() == 0){
            editTextRua.setError("Campo obrigatório");
            return true;
        }
        if (editTextBairro.getText().toString().length() == 0){
            editTextBairro.setError("Campo obrigatório");
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

        return false;
    }

    public void alterarMotoTaxi(View view){
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

                        recuperaDados();    // colocando os dados ds text's
                        if (motoTaxiDAO.atualizar(motoTaxi)){
                            Toast.makeText(getApplicationContext(),"Alterado com sucesso ", Toast.LENGTH_SHORT).show();

                            // .class eh a activity que desejamos ir
                            Intent intencao = new Intent(getApplicationContext(), HomeActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("mototaxi", motoTaxi);
                            intencao.putExtras(bundle);

                            startActivity(intencao);
                            finish();                   // finalizar activity

                        } else {
                            Toast.makeText(getApplicationContext(),"Erro ao alterar mototaxista", Toast.LENGTH_SHORT).show();
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
