package com.dominandoandroid.example.hercules.e_moto;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Encomenda extends AppCompatActivity {

    private RadioGroup radioGroupItens;
    private RadioButton radioButtonLevar, radioButtonBuscar;
    private String msg;

    private TextInputEditText cepEncomenda, cepDestino, referenciaEncomenda, referenciaDestino;
    private EditText comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encomenda);

        radioGroupItens = findViewById(R.id.radioGroup);
        cepEncomenda = findViewById(R.id.cep_da_encomenda);
        cepDestino = findViewById(R.id.cep_do_destino);
        referenciaEncomenda = findViewById(R.id.referencia_da_encomenda);
        referenciaDestino = findViewById(R.id.referencia_do_destino);
        comentarios = findViewById(R.id.txtComentarios);

        msg = "Levar";
        verificaRadioButton();
    }

    /**
     * Verificação do radioButton
     *
     * */
    private void verificaRadioButton(){
        radioGroupItens.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int idClicado) {
                        if(R.id.radioButtonLevar == idClicado){
                            msg = "Levar";
                            //System.out.println(idClicado  +" levar " + R.id.radioButtonLevar);

                        } else {
                            msg = "Buscar";
                            //System.out.println(idClicado  +" buscar " + R.id.radioButtonBuscar);
                        }
                    }
                }
        );
    }

    private boolean camposEstaoVazios(){
        if (cepEncomenda.getText().length() == 0){
            cepEncomenda.setError("Informe o CEP da Encomenda");
            return true;
        }

        if (cepDestino.getText().length() == 0){
            cepDestino.setError("Informe o CEP para onde vai a encomenda");
            return true;
        }

        if (referenciaEncomenda.getText().length() == 0){
            referenciaEncomenda.setError("Informe um ponto de referencia da encomenda");
            return true;
        }
        if (referenciaDestino.getText().length() == 0){
            referenciaDestino.setError("Informe um ponto de referencia do destinatário");
            return true;
        }


        return false;
    }

    public void avancaTipoEntrega(View view) {

        // Se nenhum campo estiver vazio
        if (!camposEstaoVazios()){
            Resources res = getResources();
            final String[] itensPreferecia = res.getStringArray(R.array.tipo_frete);

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
                        public void onClick(DialogInterface dialogInterface, int itemClicado) {

                            Toast.makeText(getApplicationContext(),"Você escolheu "+ msg+ " "+ itensPreferecia[itemClicado]+
                                            " para sua encomenda."
                                    , Toast.LENGTH_LONG).show();

                            // dispensar
                            dialogInterface.dismiss();

                            //
                            Intent intent = new Intent(getApplicationContext(), ConfirmacaoPiloto.class);
                            startActivity(intent);
                            finish();


                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

}
