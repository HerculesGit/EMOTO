package com.dominandoandroid.example.hercules.e_moto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin, buttonCreate;
    private RadioGroup radioGroup;
    private RadioButton radioUsuario, radioMotoTaxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        radioGroup = findViewById(R.id.radioGroup);
        radioUsuario = findViewById(R.id.radioButtonUsuario);
        radioMotoTaxi = findViewById(R.id.radioButtonMotoTaxi);
        buttonCreate = findViewById(R.id.buttonCreate);


        // adicionando o click no botão - quando o usuário clicar no botão
        buttonLogin.setOnClickListener(
                // new em uma interface -> classe anônima
                new View.OnClickListener() {

                    // ações para quando o usuário clicar no botão
                    @Override
                    public void onClick(View view) {

                        if (radioMotoTaxi.isChecked()){
                            Intent intent =  new Intent(
                                    getApplicationContext(),

                                    // activity que queremos ir
                                    //TipoServico.class
                                    Status.class);

                            startActivity(intent);
                        } else{
                            Intent intent = new Intent(
                                    getApplicationContext(), ConfirmacaoPiloto.class);

                            startActivity(intent);
                        }
                    }
                }
        );

        // Criar usuário
        buttonCreate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), CadastroTaxi.class);
                        startActivity(intent);
                    }
                }
        );


        this.verificaRadioButton();

    }
    /**
     * Verificação do radioButton
     *
     * */
    private void verificaRadioButton(){
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int idClicado) {

                        if(R.id.radioButtonMotoTaxi == idClicado){
                            buttonCreate.setVisibility(View.VISIBLE);
                        } else {
                            buttonCreate.setVisibility(View.GONE);
                        }
                    }
                }
        );
    }

}
