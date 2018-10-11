package com.dominandoandroid.example.hercules.e_moto;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Random;

public class ConfirmacaoPiloto extends AppCompatActivity {

    private ConstraintLayout layoutCarregamento, layoutInformacoesPiloto;
    private ProgressBar progressBar;
    private int progresso = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_piloto);

        getSupportActionBar().hide();

        layoutCarregamento = findViewById(R.id.layout_carregamento);
        //layoutInformacoesPiloto = findViewById(R.id.layout_carregamento_piloto);

        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarProgresso();
    }

    public void carregarProgresso(){

        //this.progresso+=10;
        //progressBar.setProgress(progresso);

        // simulação de carregamento
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random gerador = new Random();

                for(int i=0;i<=100;i++){
                    progresso = i;

                    // thread para atualizar a interface; dentro de uma Thread normal não
                    // faz atualização da tela
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progresso);

                            // fazer algo
                            if (progresso == 100){
                                // carregar tela com informações sobre o piloto
                                layoutCarregamento.setVisibility(View.GONE);

                                //layoutInformacoesPiloto.setVisibility(View.VISIBLE);


                            }
                        }
                    });

                    try {
                        if (progresso > 50){
                            Thread.sleep( gerador.nextInt(60));
                        } else {
                            Thread.sleep( gerador.nextInt(160));
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();

    }

}
