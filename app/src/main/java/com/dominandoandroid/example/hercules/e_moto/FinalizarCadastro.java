package com.dominandoandroid.example.hercules.e_moto;

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
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;

public class FinalizarCadastro extends AppCompatActivity {
    private MotoTaxi motoTaxi;
    private TextInputEditText operadora, numero, email, senha;
    private Button btConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_finalizar_cadastro);

        operadora = findViewById(R.id.edit_operadora);
        numero = findViewById(R.id.edit_numero);
        email = findViewById(R.id.edit_email);
        senha = findViewById(R.id.edit_senha);
        btConfirmar = findViewById(R.id.bt_finalizar_cadastro);

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desejaSalvar();
            }
        });

        // recuperando dados passado da outra tela
        Bundle objetoEnviado = getIntent().getExtras();

        if (objetoEnviado != null){
            motoTaxi = (MotoTaxi) objetoEnviado.getSerializable("mototaxi");
            System.out.println("TELA FINALIZAR \n"+motoTaxi.toString());

        } else{
            System.out.println("Não recuperou os dados TELA FINALIZAR");
        }
    }

    private void pegarDasEditText() {
        motoTaxi.setNumeroCelular(operadora.getText().toString() + numero.getText().toString());
        motoTaxi.setEmail(email.getText().toString());
        motoTaxi.setSenha(senha.getText().toString());
    }

    private void desejaSalvar(){

        //Criar AlertDialog
        AlertDialog.Builder dialog  = new AlertDialog.Builder(this);

        // configura titulo e mensagem
        dialog.setTitle("Finalização");
        dialog.setMessage("Cadastrar?");

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
                        Intent intencao = new Intent(getApplicationContext(), HomeActivity.class);

                        MotoTaxiDAO motoTaxiDAO = new MotoTaxiDAO(getApplicationContext()); // carreganod banco

                        //pegarDasEditText();                 // setando dados para a variavel motoTaxi
                        teste();
                        motoTaxiDAO.salvar(motoTaxi);       // salvando no banco de dados

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mototaxi", motoTaxi);
                        intencao.putExtras(bundle);

                        startActivity(intencao);
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

    private void teste(){
        //motoTaxi.setNumeroCelular("83"+"99999999");
        //motoTaxi.setEmail("hercules@gmail.com");
        motoTaxi.setNumeroCelular("83"+"88888888");
        motoTaxi.setEmail("mario@gmail.com");
        motoTaxi.setSenha("123");

    }
}
