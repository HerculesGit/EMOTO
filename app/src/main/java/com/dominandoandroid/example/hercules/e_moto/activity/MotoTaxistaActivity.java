
package com.dominandoandroid.example.hercules.e_moto.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.dao.MotoTaxiDAO;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;

import java.util.List;

public class MotoTaxistaActivity extends AppCompatActivity {

    private MotoTaxi taxista;
    private MotoTaxiDAO motoTaxiDAO;
    private Button btEditar, btExcluirConta;
    private TextView textNome, textSobrenome, textAtivo, textDinheiro, qtdViagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moto_taxista);

        btEditar = findViewById(R.id.id_bt_editar);
        btExcluirConta = findViewById(R.id.id_bt_apagar);

        textNome = findViewById(R.id.txt_nome);
        textSobrenome = findViewById(R.id.txt_sobrenome);
        textAtivo = findViewById(R.id.txtAtivo);
        textDinheiro = findViewById(R.id.textDinheiro);
        qtdViagens = findViewById(R.id.txtQtdViagens);

/*
        // Recuperar todos os dados enviados - é como recuperar um array de dados
        Bundle dados = getIntent().getExtras();

        // pegar dados de
        String cpf = dados.getString("cpf");
        //String telefone = dados.getString("telefone");

        //
        motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());
        List<MotoTaxi> lista = motoTaxiDAO.listar();
        for(MotoTaxi m: lista){
            if (m.getDadosPessoais().getCpf().equals(cpf)){

                taxista = m;
                textNome.setText(m.getDadosPessoais().getNome());
                textSobrenome.setText(m.getDadosPessoais().getSobrenome());
                textDinheiro.setText(String.valueOf(m.getDinheiro()));
                qtdViagens.setText(String.valueOf(m.getQtdViagensDiaria()));
                if (m.isDisponivel()){
                    textAtivo.setText("Sim");
                }else {
                    textAtivo.setText("Não");
                }
                break;
            }
        }
    }


    public void clickBotao(View view) {

        // editar
        if (view.getId() == btEditar.getId()) {

            Intent intent = new Intent(getApplicationContext(), EditarMotoTaxista.class);

            //System.out.println("Objeto " + taxista.toString());
            intent.putExtra("cpf", taxista.getDadosPessoais().getCpf());
            startActivity(intent);
        } else {
            //motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());

            //System.out.println("Apertou em Excuir cnta");
            Toast.makeText(getApplicationContext(), "Apagado com sucesso", Toast.LENGTH_LONG).show();
            finish();


            //motoTaxiDAO.deletar(new MotoTaxi());
        }

    }
*/
}}

