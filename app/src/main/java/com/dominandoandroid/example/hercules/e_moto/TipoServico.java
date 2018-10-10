package com.dominandoandroid.example.hercules.e_moto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TipoServico extends AppCompatActivity {

    private ListView listaServicos;
    private String [] itens = {
            "Me leve", "Minha encomenda"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_servico);

        listaServicos = findViewById(R.id.listaServico);
        int botaoSelecionado = 0;

        // cria adaptador para a lista
        // serve para adaptar os itens para a listview exibir
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                // contexto
                this,
                // o layout - 1 tem menos itens/ menos campos
                android.R.layout.simple_list_item_1,

                // identificador do id do simple_list_item_1
                android.R.id.text1,
                itens
        );

        // definindo adaptador
        listaServicos.setAdapter(adapter);

        // adicionando clique na lista
        listaServicos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    // position - a posição do item clicado
                    // long - id do componente
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        // recuperar o que foi clicado
                        //listaServicos.getItemAtPosition(position);

                        // ir para a tela de LEVE-ME
                        if (position == 0){
                            Toast.makeText(getApplicationContext(),"Clicou em leve-me " + position,Toast.LENGTH_SHORT).show();

                            //
                            Intent intent = new Intent(
                                    getApplicationContext(),
                                    LeveMe.class
                            );
                            startActivity(intent);

                        } else {
                            // ir para a tela de ENCOMENDA
                            Toast.makeText(getApplicationContext(),"Clicou em enconmenda " + position,Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(
                                    getApplicationContext(),
                                    Encomenda.class
                            );
                            startActivity(intent);
                        }

                    } // end onItemClick
                }
        );

    }
}
