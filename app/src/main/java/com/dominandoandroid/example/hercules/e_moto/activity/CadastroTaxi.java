package com.dominandoandroid.example.hercules.e_moto.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.dao.ImagemDAO;
import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;
import com.dominandoandroid.example.hercules.e_moto.model.Endereco;
import com.dominandoandroid.example.hercules.e_moto.model.Imagem;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;
import com.dominandoandroid.example.hercules.e_moto.utilitario.DbBitmapUtility;

import java.io.IOException;

public class CadastroTaxi extends AppCompatActivity {

    private static final int LOAD_IMAGE_RESULT = 1;
    private static final int TIRAR_FOTO = 3;
    private final String MY_IMAGE = "my_image";
    private String[] galleryPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Button btAvancar, btConfirmarImagem;
    private TextInputEditText editTextNome, editTextSobrenome, editTextCpf, editTextRg,
            editTextEstado, editTextCidade, editTextRua, editTextNumero, editTextBairro;

    private MotoTaxi motoTaxi;
    private Dialog myPopupDialog;
    //private Dialog myPopupDialogChosenImage;

    private TextView tvNomeSobreNome;
    private Imagem imagem;
    private ImageView imageViewCamera, imageViewGaleria, iconFoto, profileImage;
    //private Bitmap bitmapPerfil;
    //private Bitmap bitmapBackground;
    private Bitmap bitmapImagemEscolhida;
    //private boolean isPerfil = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //aqui a mágica
        setContentView(R.layout.activity_cadastro_taxi);

        motoTaxi = new MotoTaxi();
        addId();                    // findViewById


        // listener
        btAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validacao dos campos
                //if (!camposEstaoVazios()){

                // pegar os dados das editText's
                //motoTaxi = pegarDasEditText();

                    teste();
                    configAndSaveImage();
                    Intent intencao = new Intent(CadastroTaxi.this, CadastroVeiculo.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mototaxi", motoTaxi);
                    intencao.putExtras(bundle);
                    startActivity(intencao);

                //}
            }
        });

        editTextNome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){ // perdeu foco
                    runThread();
                }
            }
        });

        editTextSobrenome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){ // perdeu foco
                    runThread();
                }
            }
        });

    }


    /**
     * Salvar estado, este metodo eh chamado antes da activity ser re-criada
     * */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        imagem = new Imagem();
        if(bitmapImagemEscolhida !=null){
            imagem.setDados(DbBitmapUtility.getBytes(bitmapImagemEscolhida));
            outState.putSerializable(MY_IMAGE, imagem);
        }

    }
    /**
     * Recuperar estado
     * */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        imagem = (Imagem) savedInstanceState.getSerializable(MY_IMAGE);

        if (imagem !=null){

            // buffer com o array de byte
            byte[] buffer = imagem.getDados();

            // convertendo o array de byte para um bitmap
            Bitmap bitmap = DbBitmapUtility.getImage(buffer);

            setImagePerfil(bitmap);
            bitmapImagemEscolhida = bitmap;
        }
    }

    public void onClickImageIconCamera(View view){
        popupCameraOrGaleria();
    }

    private void setImagePerfil(Bitmap bitmap){
        profileImage.setImageBitmap(bitmap);
    }

    /**
     * Mostra a opcao do usuario selecionar camera ou galeria
     * */
    private void popupCameraOrGaleria(){
        myPopupDialog = new Dialog(CadastroTaxi.this);

        myPopupDialog.setCanceledOnTouchOutside(true);  // se clicar fora do dialog ele fecharah
        myPopupDialog.setContentView(R.layout.popup_photo);
        myPopupDialog.show();
    }

//    public void onClickButtonConfirmar(View view){
//        if (isPerfil) {
//            bitmapPerfil = bitmapImagemEscolhida;
//        } else {
//            bitmapBackground = bitmapImagemEscolhida;
//        }
//    }

    /**
     * Clique na imagem camera ou galeria do popup Dialog
     * */
    public void clickImageCameraGaleria(View view){
        if (view.getId() == R.id.popup_opcao_foto_camera){
            // para camera
            tirarFoto();
            myPopupDialog.dismiss();

        } else if (view.getId() == R.id.popup_opcao_foto_galeria){
            // para galeria
            irPraGaleria();
            myPopupDialog.dismiss();
        }
    }

    private void tirarFoto(){
        // minha intencao e captura com a camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent, TIRAR_FOTO);
        }
    }

    private void irPraGaleria(){
        // intent para iniciar a galeria
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ActivityCompat.requestPermissions(CadastroTaxi.this, galleryPermissions,1);
        startActivityForResult(i,LOAD_IMAGE_RESULT);
    }

//    private void displayPopupImage(Bitmap bitmapImage){
//        myPopupDialogChosenImage = new Dialog(CadastroTaxi.this);
//        myPopupDialogChosenImage.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        myPopupDialogChosenImage.setContentView(R.layout.popup_chosen_image);
//        myPopupDialogChosenImage.setTitle("Imagem Selecionada");
//
//        ImageView v = myPopupDialogChosenImage.findViewById(R.id.popup_chosen_image_image_escolhida);
//        v.setImageBitmap(bitmapImage);
//
//        myPopupDialogChosenImage.show();
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == LOAD_IMAGE_RESULT && data != null){      // carregar da galeria
                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,filePath,null,null,null);
                cursor.moveToFirst();

                String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                cursor.close();

                Bitmap img = BitmapFactory.decodeFile(imagePath);
                setImagePerfil(img);
                bitmapImagemEscolhida = img;
                imagem.setDados(DbBitmapUtility.getBytes(bitmapImagemEscolhida));

            } else if (requestCode == TIRAR_FOTO && data != null) {     // tirar foto

                Bundle dadosRecuperados = data.getExtras();
                Bitmap img = (Bitmap) dadosRecuperados.get("data");
                setImagePerfil(img);
                bitmapImagemEscolhida = img;
                imagem.setDados(DbBitmapUtility.getBytes(bitmapImagemEscolhida));

            }
        }
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) {
//            case 1: {
//
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    Toast.makeText(CadastroTaxi.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }

    /**
     * Verifica se os campos estao vazios
     * */
    private boolean camposEstaoVazios(){
        if (editTextNome.getText().toString().length() == 0){
            editTextNome.setError("Campo obrigatório");
            return true;
        }
        if (editTextSobrenome.getText().toString().length() == 0){
            editTextSobrenome.setError("Campo obrigatório");
            return true;
        }
        if (editTextRg.getText().toString().length() == 0){
            editTextRg.setError("Campo obrigatório");
            return true;
        }
        if (editTextCpf.getText().toString().length() == 0){
            editTextCpf.setError("Campo obrigatório");
            return true;
        }
        if (editTextEstado.getText().toString().length() == 0){
            editTextEstado.setError("Campo obrigatório");
            return true;
        }
        if (editTextCidade.getText().toString().length() == 0){
            editTextCidade.setError("Campo obrigatório");
            return true;
        }
        if (editTextRua.getText().toString().length() == 0){
            editTextRua.setError("Campo obrigatório");
            return true;
        }
        if (editTextNumero.getText().toString().length() == 0){
            editTextNumero.setError("Campo obrigatório");
            return true;
        }
        if (editTextBairro.getText().toString().length() == 0){
            editTextBairro.setError("Campo obrigatório");
            return true;
        }

        return false;
    }


    /**
     * */
    private void runThread(){

        String nome = editTextNome.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();
        if (nome.length() > 0) {
            nome = nome.trim();
            sobrenome = sobrenome.trim();

            tvNomeSobreNome.setText("");
            tvNomeSobreNome.setText(nome +" "+ sobrenome);

        }

    }
//    private void runThread() {
//
//        Thread t1 = new Thread() {
//            public void run() {
//                while (true) {
//                    try {
//                        runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                String nome = editTextNome.getText().toString();
//                                String sobrenome = editTextSobrenome.getText().toString();
//                                System.out.println("Verificando");
//                                if (nome.length() > 0) {
//                                    nome = nome.trim();
//                                    sobrenome = sobrenome.trim();
//
//                                    tvNomeSobreNome.setText("");
//                                    tvNomeSobreNome.setText(nome + sobrenome);
//
//                                }
//                            }
//                        });
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t1.start();
//    }

    /**
     * Recupera das editText
     * */
    private void pegarDasEditText(){

        // pegar dados
        String nome = editTextNome.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();
        String rg = editTextRg.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String estado = editTextEstado.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String rua= editTextRua.getText().toString();
        String numero = editTextNumero.getText().toString();
        String bairro = editTextBairro.getText().toString();

        //String nome, String sobrenome, String cpf, String rg, String telefone, String senha, String email
        DadosPessoais dadosPessoais = new DadosPessoais();

        dadosPessoais.setNome(nome+" "+sobrenome);
        dadosPessoais.setRg(rg);
        dadosPessoais.setCpf(cpf);

        Endereco endereco = new Endereco();
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);

        motoTaxi.setDadosPessoais(dadosPessoais);
        motoTaxi.setEndereco(endereco);

        motoTaxi.setDisponivel(1);

    }

    private void teste(){
        DadosPessoais dadosPessoais = new DadosPessoais();

        dadosPessoais.setNome("Hércules Silva");
        dadosPessoais.setRg("1345");
        dadosPessoais.setCpf("109");
        //dadosPessoais.setNome("Mario Quintana");
        //dadosPessoais.setRg("678");
        //dadosPessoais.setCpf("108");

        Endereco endereco = new Endereco();
        endereco.setEstado("PB");
        endereco.setCidade("Mamanguape");
        endereco.setRua("Rua Teonor monoglorio");
        endereco.setNumero("17D");
        endereco.setBairro("Centro");

        motoTaxi.setDadosPessoais(dadosPessoais);
        motoTaxi.setEndereco(endereco);

        motoTaxi.setDisponivel(1);
    }

    private void addId(){
        btAvancar = findViewById(R.id.bt_cadastro_Avancar);
        editTextNome = findViewById(R.id.cadastro_dados_nome);
        editTextSobrenome = findViewById(R.id.cadastro_dados_sobrenome);
        editTextRg = findViewById(R.id.cadastro_dados_rg);
        editTextCpf = findViewById(R.id.cadastro_dados_cpf);
        editTextEstado = findViewById(R.id.cadastro_dados_estado);
        editTextCidade = findViewById(R.id.cadastro_dados_cidade);
        editTextRua = findViewById(R.id.cadastro_dados_rua);
        editTextNumero = findViewById(R.id.cadastro_dados_numero);
        editTextBairro = findViewById(R.id.cadastro_dados_bairro);

        tvNomeSobreNome = findViewById(R.id.cadastro_nome_sobrenome);

        imageViewCamera = findViewById(R.id.popup_opcao_foto_camera);
        imageViewGaleria = findViewById(R.id.popup_opcao_foto_galeria);
        iconFoto = findViewById(R.id.cadastro_icon_foto);
        profileImage = findViewById(R.id.cadastro_profile_image);

        btConfirmarImagem = findViewById(R.id.popup_chosen_image_bt_confirmar);
    }

    private void configAndSaveImage(){
        imagem.setDados(DbBitmapUtility.getBytes(bitmapImagemEscolhida));
        imagem.setIdImagem(0);
        imagem.setDescricao("perfil");

        ImagemDAO imagemDAO = new ImagemDAO(CadastroTaxi.this);
        imagemDAO.addImage(imagem);
    }
}