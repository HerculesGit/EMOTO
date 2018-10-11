package com.dominandoandroid.example.hercules.e_moto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);

        // adicionando o click no botão - quando o usuário clicar no botão
        buttonLogin.setOnClickListener(

                // new em uma interface -> classe anônima
                new View.OnClickListener() {

                    // ações para quando o usuário clicar no botão
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(
                                getApplicationContext(),

                                // activity que queremos ir
                                //TipoServico.class
                                ConfirmacaoPiloto.class

                        );
                        startActivity(intent);


                    }
                }
        );

    }

    private void cliquesBotoes(){

    }


}
