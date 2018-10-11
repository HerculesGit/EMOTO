package com.dominandoandroid.example.hercules.e_moto;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Encomenda extends AppCompatActivity {
    private RadioGroup radioGroupItens;
    private RadioButton radioButtonLevar, radioButtonBuscar;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encomenda);

        radioGroupItens = findViewById(R.id.radioGroup);
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
                            System.out.println(idClicado  +" levar " + R.id.radioButtonLevar);

                        } else {
                            msg = "Buscar";
                            System.out.println(idClicado  +" buscar " + R.id.radioButtonBuscar);
                        }
                    }
                }
        );
    }

    public void avancaTipoFrete(View view) {
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


                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();



    }

}
