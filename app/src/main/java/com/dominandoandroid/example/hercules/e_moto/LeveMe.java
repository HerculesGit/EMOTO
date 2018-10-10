package com.dominandoandroid.example.hercules.e_moto;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LeveMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leve_me);



    }


    public void avancar(View v){
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
