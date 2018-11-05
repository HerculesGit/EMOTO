package com.dominandoandroid.example.hercules.e_moto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.adapter.AdapterList;
import com.dominandoandroid.example.hercules.e_moto.model.ItemList;

import java.util.List;

public class ConfiguracoesActivity extends AppCompatActivity {

    private List<ItemList> itens;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        listView = findViewById(R.id.configuracao_lista_alteracoes);

        // adaptador
        AdapterList adapter = new AdapterList(getApplicationContext(), itens);

        listView.setAdapter(adapter);



    }
}
