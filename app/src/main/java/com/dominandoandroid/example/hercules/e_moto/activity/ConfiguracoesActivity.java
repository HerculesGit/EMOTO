package com.dominandoandroid.example.hercules.e_moto.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.utilitario.DbBitmapUtility;
import com.dominandoandroid.example.hercules.e_moto.dao.ImagemDAO;
import com.dominandoandroid.example.hercules.e_moto.model.Imagem;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;
import com.dominandoandroid.example.hercules.e_moto.utilitario.StringUtility;

import java.util.List;

public class ConfiguracoesActivity extends AppCompatActivity {

    private Imagem imagem;
    private ImageView imageViewPerfil, imageViewBackground;
    private ImagemDAO imagemDAO;
    private TextView textViewNomeSobrenome, textViewNumeroCel;
    private MotoTaxi motoTaxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        imageViewPerfil = findViewById(R.id.configuracao_imagem_perfil);
        imageViewBackground = findViewById(R.id.configuracao_imagem_background);
        textViewNomeSobrenome = findViewById(R.id.configuracao_nome);
        textViewNumeroCel = findViewById(R.id.configuracao_numero_telefone);

        imagemDAO = new ImagemDAO(getApplicationContext());
        imagem = new Imagem();

        // recuperando objeto
        Bundle objetoEnviado = getIntent().getExtras();
        if (objetoEnviado != null){
            motoTaxi = (MotoTaxi) objetoEnviado.getSerializable("mototaxi");

            setarInformacoesNosComponentes();
            System.out.println("ConfiguracoesActivity" + motoTaxi.toString());
        } else{
            System.out.println("ConfiguracoesActivity NÃO RECUPEROU DADOS");
        }

        //Para criar um Drawable a partir de um Bitmap use:
        //Drawable drawable = new BitmapDrawable(getResources(), bitmap);
    }

    public void clickImage(View view){

        imagem = new Imagem();

        if (view.getId() == R.id.configuracao_icone_perfil_button){
            // perfil
            imagem.setDescricao("perfil");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girlperfil2);

            // conversao de bitmap para array de byte
            imagem.setDados(DbBitmapUtility.getBytes(bitmap));

            // buffer com o array de byte
            byte[] buffer = imagem.getDados();

            // convertendo o array de byte para um bitmap
            imageViewPerfil.setImageBitmap(DbBitmapUtility.getImage(buffer));


        } else if (view.getId() == R.id.configuracao_icone_background_button){
            // background
            //imagem.setDescricao("background");
            // carregar imagem

            
            imagem = imagemDAO.listar(1).get(0);

            byte[] buffer = imagem.getDados();

            imageViewPerfil.setImageBitmap(DbBitmapUtility.getImage(buffer));

        }
    }

    public void clickButtonAlterar(View view){
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
        imageViewPerfil.setImageBitmap(DbBitmapUtility.getImage(buffer));

    }

    private void setarInformacoesNosComponentes(){
        String temp = "";

        temp = StringUtility.getFirstAndLastName(motoTaxi.getDadosPessoais().getNome());
        textViewNomeSobrenome.setText(temp);
        temp = motoTaxi.getNumeroCelular();
        textViewNumeroCel.setText(temp);

        List<Imagem> imagens = imagemDAO.listar(motoTaxi.getIdMototaxista());
        if (imagens.size() > 1){
            for (Imagem img : imagens) {
                if (img.getDescricao().equalsIgnoreCase("perfil")){

                    imageViewPerfil.setImageBitmap(DbBitmapUtility.getImage(img.getDados()));

                    // apenas para carregar a de perfil
                    imagem = img;

                } else if (img.getDescricao().equalsIgnoreCase("background")){
                    imageViewBackground.setImageBitmap(DbBitmapUtility.getImage(img.getDados()));
                }

            }
        }

    }
}
