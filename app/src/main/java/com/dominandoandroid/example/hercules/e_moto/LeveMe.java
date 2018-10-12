package com.dominandoandroid.example.hercules.e_moto;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LeveMe extends AppCompatActivity {

    private EditText txtCepHorigem, txtCepDestino;
    private Button avancarConfirmacao;
    private TextInputEditText inputEditTextPontoReferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leve_me);

        txtCepHorigem = findViewById(R.id.cepHorigem);
        txtCepDestino = findViewById(R.id.cepDestino);
        avancarConfirmacao = findViewById(R.id.avancarConfirmacao);
        inputEditTextPontoReferencia = findViewById(R.id.meleve_ponto_referencia);

    }


    private boolean camposEstaoVazios(){
        if (txtCepHorigem.getText().length() == 0){
            txtCepHorigem.setError("Informe seu CEP");

            return true;
        }
        if (txtCepDestino.getText().length() == 0){
            txtCepDestino.setError("Informe o CEP destino");

            return true;
        }
        if (inputEditTextPontoReferencia.getText().length() == 0){
            inputEditTextPontoReferencia.setError("Informe uma referência para sua localização");

            return true;
        }

        return false;
    }

    public void avancar(View v){

        if (!camposEstaoVazios()){

            Intent intent = new Intent(getApplicationContext(), ConfirmacaoPiloto.class);
            startActivity(intent);
            finish();
        }


        /*
        Resources res = getResources();
        String[] itensPreferecia = res.getStringArray(R.array.tipo_frete);


        /// /String[] itensPreferecia = getResources().getStringArray(R.string.pl);


        // Cria alertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(
                // this não é o contexdo da aplicação, mas da activity
          this
        );

        // configurar obj dialog
        builder.setTitle("Escolha a preferência");
        //dialog.setMessage("");

        // quando clicar no radioButton
        builder.setSingleChoiceItems(itensPreferecia, -1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(),"Você escolheu a opção de ", Toast.LENGTH_LONG).show();

                        // dispensar
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    */
    }



}
