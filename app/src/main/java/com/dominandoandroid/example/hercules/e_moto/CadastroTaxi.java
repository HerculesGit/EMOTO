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

    private Button btAvancar;
    private TextInputEditText editTextNome, editTextSobrenome, editTextCpf, editTextRg,
            editTextEstado, editTextCidade, editTextRua, editTextNumero, editTextBairro;
    private MotoTaxiDAO motoTaxiDAO;
    private MotoTaxi motoTaxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //aqui a mágica
        setContentView(R.layout.activity_cadastro_taxi);

        btAvancar = findViewById(R.id.bt_cadastro_Avancar);
        editTextNome = findViewById(R.id.cadastro_dados_nome);
        editTextSobrenome = findViewById(R.id.cadastro_dados_sobrenome);
        editTextRg = findViewById(R.id.cadastro_dados_rg);
        editTextCpf = findViewById(R.id.cadastro_dados_cpf);
        editTextEstado = findViewById(R.id.cadastro_dados_estado);
        editTextCidade = findViewById(R.id.cadastro_dados_cidade);
        editTextRua = findViewById(R.id.cadastro_dados_rua);
        editTextNumero = findViewById(R.id.cadastro_dados_numero);
        editTextBairro = findViewById(R.id.cadastro_dados_bairro);

        // listener
        btAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validacao dos campos
                //if (!camposEstaoVazios()){

                // pegar os dados das editText's
                motoTaxi = pegarDasEditText();
                Intent intencao = new Intent(CadastroTaxi.this, CadastroVeiculo.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mototaxi", motoTaxi);
                intencao.putExtras(bundle);
                startActivity(intencao);

                //}
            }
        });


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
        if (editTextEstado.getText().toString().length() == 0){
            editTextEstado.setError("Campo obrigatório");
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
        if (editTextNumero.getText().toString().length() == 0){
            editTextNumero.setError("Campo obrigatório");
            return true;
        }
        if (editTextBairro.getText().toString().length() == 0){
            editTextBairro.setError("Campo obrigatório");
            return true;
        }

        return false;
    }

    public void cadastrarMotoTaxi(View view){

        List<MotoTaxi> listaMotoTaxistas = new ArrayList<>();

        listaMotoTaxistas = motoTaxiDAO.listar();

        // validacao dos campos
        //if (!camposEstaoVazios()){

            // pergunta se deseja salvar realmente
            //desejaSalvar();
        //}

    }

    @Deprecated
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
                        Intent intent = new Intent(getApplicationContext(), MotoTaxistaActivity.class);

                        motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());

                        // setando dados para a variavel motoTaxi
                        motoTaxi = pegarDasEditText();

                        // mandar  dados para a a proxima tela
                        intent.putExtra("cpf", motoTaxi.getDadosPessoais().getCpf());
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

    /**
     * Recupera das editText
     * */
    private MotoTaxi pegarDasEditText(){

        // pegar dados
        String nome = editTextNome.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();
        String rg = editTextRg.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String estado = editTextEstado.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String rua= editTextRua.getText().toString();
        String numero = editTextNumero.getText().toString();
        String bairro = editTextBairro.getText().toString();

        //String nome, String sobrenome, String cpf, String rg, String telefone, String senha, String email
        DadosPessoais dadosPessoais = new DadosPessoais();
        motoTaxi.setDisponivel(1);
        return motoTaxi;

    }

}
