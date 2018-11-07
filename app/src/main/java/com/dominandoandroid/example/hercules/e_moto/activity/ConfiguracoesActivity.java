package com.dominandoandroid.example.hercules.e_moto.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.dao.DbBitmapUtility;
import com.dominandoandroid.example.hercules.e_moto.dao.ImagemDAO;
import com.dominandoandroid.example.hercules.e_moto.model.Imagem;

public class ConfiguracoesActivity extends AppCompatActivity {

    private Imagem imagem;
    private ImageView imageView;
    private ImagemDAO imagemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        imagemDAO = new ImagemDAO(getApplicationContext());
        imageView = findViewById(R.id.configuracao_imagem_perfil);

        // imagem a partir de um drawable
        //imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.girlperfil2));



        //Para criar um Drawable a partir de um Bitmap use:
        //Drawable drawable = new BitmapDrawable(getResources(), bitmap);


    }

    public void clickImage(View view){

        imagem = new Imagem();
        imagem.setIdMototaxista(1);

        if (view.getId() == R.id.configuracao_icone_perfil_button){
            // perfil
            imagem.setDescricao("perfil");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girlperfil2);

            // conversao de bitmap para array de byte
            imagem.setDados(DbBitmapUtility.getBytes(bitmap));

            // buffer com o array de byte
            byte[] buffer = imagem.getDados();

            // convertendo o array de byte para um bitmap
            imageView.setImageBitmap(DbBitmapUtility.getImage(buffer));


        } else if (view.getId() == R.id.configuracao_icone_background_button){
            // background
            //imagem.setDescricao("background");
            // carregar imagem

            
            imagem = imagemDAO.listar(1).get(0);

            byte[] buffer = imagem.getDados();

            imageView.setImageBitmap(DbBitmapUtility.getImage(buffer));

        }
    }

    public void clickButtonAlterar(View view){
        imagem.setIdMototaxista(1);
        imagem.setDescricao("perfil");

        System.out.println("Clicou em ClickButtonAlterar");

        imagemDAO.addImage(imagem);

    }

    /**
     * Salvar estado
     * */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("my_image", imagem);


    }

    /**
     * Recuperar estado
     * */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        imagem = (Imagem) savedInstanceState.getSerializable("my_image");

        // buffer com o array de byte
        byte[] buffer = imagem.getDados();

        // convertendo o array de byte para um bitmap
        imageView.setImageBitmap(DbBitmapUtility.getImage(buffer));

    }
}
