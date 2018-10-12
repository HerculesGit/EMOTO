package com.dominandoandroid.example.hercules.e_moto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dominandoandroid.example.hercules.e_moto.dao.MotoTaxiDAO;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;

public class MotoTaxista extends AppCompatActivity {

    private MotoTaxiDAO motoTaxiDAO;
    private Button btEditar, btExcluirConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moto_taxista);

        btEditar = findViewById(R.id.id_bt_editar);
        btExcluirConta = findViewById(R.id.id_bt_apagar);

    }


    public void clickBotao(View view) {
        Toast.makeText(getApplicationContext(), "Chegueeei", Toast.LENGTH_LONG).show();
        // editar
        if (view.getId() == btEditar.getId()) {
            Intent intent = new Intent(getApplicationContext(), EditarMotoTaxista.class);
            startActivity(intent);
        } else {
            //motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());

            System.out.println("Apertou em Excuir cnta");
            Toast.makeText(getApplicationContext(), "Apagado com sucesso", Toast.LENGTH_LONG).show();
            finish();


            //motoTaxiDAO.deletar(new MotoTaxi());
        }

    }

}

