package com.dominandoandroid.example.hercules.e_moto.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;
import com.dominandoandroid.example.hercules.e_moto.model.Endereco;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;

import java.io.IOException;

public class CadastroTaxi extends AppCompatActivity {

    private static final int LOAD_IMAGE_RESULT = 1;
    private static final int TIRAR_FOTO = 3;
    private String[] galleryPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Button btAvancar, btSelecionarFotoPerfil, btSelecionarFotoFundo, btConfirmarImagem;
    private ImageView imagemEscolhida;
    private TextInputEditText editTextNome, editTextSobrenome, editTextCpf, editTextRg,
            editTextEstado, editTextCidade, editTextRua, editTextNumero, editTextBairro;

    private MotoTaxi motoTaxi;
    private Dialog myPopupDialog;
    private Dialog myPopupDialogChosenImage;

    private ImageView imageViewCamera, imageViewGaleria;


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
                    Intent intencao = new Intent(CadastroTaxi.this, CadastroVeiculo.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mototaxi", motoTaxi);
                    intencao.putExtras(bundle);
                    startActivity(intencao);

                //}
            }
        });

        btSelecionarFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPopupDialog = new Dialog(CadastroTaxi.this);

                myPopupDialog.setCanceledOnTouchOutside(true);  // se clicar fora do dialog ele fecharah
                myPopupDialog.setContentView(R.layout.popup_photo);
                myPopupDialog.show();
            }
        });

        btSelecionarFotoFundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPopupDialog = new Dialog(CadastroTaxi.this);

                myPopupDialog.setCanceledOnTouchOutside(true);  // se clicar fora do dialog ele fecharah
                myPopupDialog.setContentView(R.layout.popup_photo);
                myPopupDialog.show();

            }
        });

    }

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
        startActivityForResult(i,LOAD_IMAGE_RESULT);
    }

    private void displayPopupImage(Bitmap bitmapImage){
        myPopupDialogChosenImage = new Dialog(CadastroTaxi.this);
        myPopupDialogChosenImage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myPopupDialogChosenImage.setContentView(R.layout.popup_chosen_image);
        myPopupDialogChosenImage.setTitle("Imagem Selecionada");

        ImageView v = myPopupDialogChosenImage.findViewById(R.id.popup_chosen_image_image_escolhida);
        v.setImageBitmap(bitmapImage);

        myPopupDialogChosenImage.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //ActivityCompat.requestPermissions(CadastroTaxi.this, galleryPermissions,1);

        if (resultCode == RESULT_OK){
            if (requestCode == LOAD_IMAGE_RESULT && data != null){      // carregar da galeria
                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(CadastroTaxi.this.getContentResolver(), selectedImage);
                } catch(IOException e){
                    e.printStackTrace();
                }

                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,filePath,null,null,null);
                cursor.moveToFirst();

                String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                cursor.close();
                displayPopupImage(BitmapFactory.decodeFile(imagePath));

            } else if (requestCode == TIRAR_FOTO && data != null) {     // tirar foto

                Bundle dadosRecuperados = data.getExtras();
                Bitmap imageBitmap = (Bitmap) dadosRecuperados.get("data");
                displayPopupImage(imageBitmap);
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

        imageViewCamera = findViewById(R.id.popup_opcao_foto_camera);
        imageViewGaleria = findViewById(R.id.popup_opcao_foto_galeria);
        imagemEscolhida = findViewById(R.id.popup_chosen_image_image_escolhida);

        btSelecionarFotoPerfil = findViewById(R.id.cadastro_button_selecionar_imagem_perfil);
        btConfirmarImagem = findViewById(R.id.popup_chosen_image_bt_confirmar);
    }
}