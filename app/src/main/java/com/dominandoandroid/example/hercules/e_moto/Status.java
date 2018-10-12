package com.dominandoandroid.example.hercules.e_moto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Status extends AppCompatActivity {

    private Switch buttonSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        buttonSwitch = findViewById(R.id.switchDisponivel);


        buttonSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        if (compoundButton.isChecked()){
                            Toast.makeText(getApplicationContext(),"Disponivel",Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(),"Indisponivel",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );

    }
}
