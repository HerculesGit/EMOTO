package com.dominandoandroid.example.hercules.e_moto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class ConfirmacaoPiloto extends AppCompatActivity {

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_piloto);

        progressBar = findViewById(R.id.progressBar);

    }

    public void carregarProgresso(View view){

        //this.progresso+=10;
        //progressBar.setProgress(progresso);

        // simulação de carregamento
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=100;i++){
                    final int progresso = i;

                    // thread para atualizar a interface; dentro de uma Thread normal não
                    // faz atualização da tela
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progresso);

                            // fazer algo
                            if (progresso == 100){
                                // carregar tela com informações sobre o piloto

                            }
                        }
                    });

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();

    }

}
