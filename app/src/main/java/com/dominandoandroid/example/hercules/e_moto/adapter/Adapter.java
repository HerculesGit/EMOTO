package com.dominandoandroid.example.hercules.e_moto.adapter;

/*
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dominandoandroid.example.hercules.recycleview27_26.R;
import com.dominandoandroid.example.hercules.recycleview27_26.activity.model.Filme;

import java.util.List;

// dentro do <> precisamos passar um ViewHolder
// imagine que temos uma listagem de 1000 filmes
// nós não criamos uma listagem com os 1000 ítens de lista diferentes
// Usamos um tamanho fixo (por isso o nome_adaptador.sethasFixedSize(true))
// A partir desse tamanho, quando queremos mostrar mais ítens do que o tamanho fixo
// reaproveitamos a mesma visualização e ir mudando apenas o texto dentro

// Quando descemos a barra e não vemos os ítens na View ele está reciclando e reaproveitando
// Por isso o nome RecyclerView, ele recicla as visualizações para que fique muito mais utilizavel

// Nós precisamos utilizar o Holder porque eles guardar os cada um dados
// antes dos dados serem exibidos na tela

// o Adapter.MyViewHolder foi utilizado para que consigamos utilizar
// a classe interna que foi criada
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{ // MyViewHolder está dentro de Adapter

    private List<Filme> listaFilmes;

    public Adapter(List<Filme> lista){
        this.listaFilmes = lista;
    }



    // esse primeiro que é chamado para que possámos criar nossas visualizações
    //
    @NonNull
    @Override
    // esses MyViewHolder é o objeto que criamos como iner class. O nome foi definido por nós
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // nós precisamos retornar View para a listagem de ítens.
        // esse método será chamado até que seja criado as visualizações que serão exibidas ao usuário
        // A partir de uma quantidade de visualizações, quando descermos arrastando
        // Ele não criará mais visualizações, ele vai reciclar as criadas
        //

        // sendo assim, precisamos pegar o adapter_lista.xml - que foi o que criamos
        // e retornar para cada um para os ítens de lista

        // convertendo o layout .xmlem um objeto do tipo View
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(
                // ítem de lista que queremos - quando colocamos o R irá aparecer vários. Usai-vos o do pacote que estamos
                R.layout.adapter_lista,

                // o próprio parent que é do tipo ViewGroup que está no método acima: onCreateViewHolder
                parent,

                // é um booleano que possibilita adicionar o ítem de lista ao elemento Root
                false

        );// LayoutInflater é utilizada para o que chamamos de inflar um xml/ converter um xml
        // em uma viluzalização
        // esse parent.getContext() recupera o contexto baseado no componente no componete que nossa ítem
        // de lista está dentro.


        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // o método onCreateViewHolder ele não exibe os ítens, ele apenas cria.
        // a exibição é no onBindViewHolder

        // tudo foi configurado no onCreateViewHolder, e quando usamos o holder está tudo pronto
        //holder.titulo.setText("Titulo de teste");
        //holder.genero.setText("Comédia");
        //holder.ano.setText("2017");

        Filme filme = listaFilmes.get(position);
        holder.titulo.setText(filme.getTitulo());
        holder.genero.setText(filme.getGenero());
        holder.ano.setText(filme.getAno());

    }

    @Override // retorna a quantidade de ítens que setá exibido
    public int getItemCount() {
        return listaFilmes.size();// com isso o onBindViewHolder sabe a quantidade de ítens que será exibida
    }

    // classe interna - iner classe
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Atributos que MyViewHolder irá guardar
        TextView
                titulo,
                ano,
                genero;

        public MyViewHolder(View itemView) {
            super(itemView);

            // recuperar os componentes de tela - só conseguimos acessar porque usamos o itemView, que tem os componetes de tela
            titulo = itemView.findViewById(R.id.textTitulo);
            ano = itemView.findViewById(R.id.textAno);
            genero = itemView.findViewById(R.id.textGenero);


        }
    }
}
*/