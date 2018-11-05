package com.dominandoandroid.example.hercules.e_moto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.model.ItemList;

import java.util.List;

public class AdapterList extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context context;
    private List<ItemList>  itens;

    public AdapterList (Context context, List<ItemList> itens){
        this.context = context;
        this.itens = itens;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;

        //se a view estiver nula (nunca criada), inflamos o layout nela.
        if (view == null) {
            //infla o layout para podermos pegar as views
            view = mInflater.inflate(R.layout.item_list, null);

            //cria um item de suporte para nao precisarmos sempre
            //inflar as mesmas informacoes
            itemHolder = new ItemSuporte();
            itemHolder.textView = view.findViewById(R.id.item_list_texto);
            itemHolder.imageView = view.findViewById(R.id.item_list_icone);

            //define os itens na view;
            view.setTag(itemHolder);
        } else {
            //se a view ja existe pega os itens.
            itemHolder = (ItemSuporte) view.getTag();
        }

        //pega os dados da lista
        //e define os valores nos itens.
        ItemList item = itens.get(position);
        itemHolder.textView.setText(item.getTexto());
        itemHolder.imageView.setImageResource(item.getIcone());

        //retorna a view com as informações
        return view;
    }

    /**
     * Classe de suporte
     * */
    private class ItemSuporte{
        ImageView imageView;
        TextView textView;
    }

}
