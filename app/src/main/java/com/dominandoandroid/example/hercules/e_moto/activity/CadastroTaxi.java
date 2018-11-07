package com.dominandoandroid.example.hercules.e_moto.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;
import com.dominandoandroid.example.hercules.e_moto.model.Endereco;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;

public class CadastroTaxi extends AppCompatActivity {

    private static final int TIRAR_FOTO = 3;

    private Button btAvancar, btSelecionarFotoPerfil, btSelecionarFotoFundo;
    private TextInputEditText editTextNome, editTextSobrenome, editTextCpf, editTextRg,
            editTextEstado, editTextCidade, editTextRua, editTextNumero, editTextBairro;
    //private MotoTaxiDAO motoTaxiDAO;
    private MotoTaxi motoTaxi;
    private Dialog myPopupDialog;
    private ImageView imageViewCamera, imageViewGaleria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //aqui a mágica
        setContentView(R.layout.activity_cadastro_taxi);

        motoTaxi = new MotoTaxi();

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
        btSelecionarFotoPerfil = findViewById(R.id.cadastro_button_selecionar_imagem_perfil);

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

    }

    public void clickImageCameraGaleria(View view){
        if (view.getId() == R.id.popup_opcao_foto_camera){
            // para camera
            tirarFoto();
            myPopupDialog.dismiss();

        } else if (view.getId() == R.id.popup_opcao_foto_galeria){
            // para galeria

        }


    }

    private void tirarFoto(){

        // minha intencao e captura com a camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent, TIRAR_FOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle dadosRecuperados = data.getExtras();
        Bitmap imageBitmap = (Bitmap) dadosRecuperados.get("data");

    }

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

}
/*
    @Deprecated
    public void cadastrarMotoTaxi(View view){

        List<MotoTaxi> listaMotoTaxistas = new ArrayList<>();

        listaMotoTaxistas = motoTaxiDAO.listar();

        // validacao dos campos
        //if (!camposEstaoVazios()){

            // pergunta se deseja salvar realmente
            //desejaSalvar();
        //}

    }
*/
 /*
    @Deprecated
    private void desejaSalvar(){

        /**
         * Criar AlertDialog
         *
        AlertDialog.Builder dialog  = new AlertDialog.Builder(
                this
        );

        // configura titulo e mensagem
        dialog.setTitle("Quase lá");
        dialog.setMessage("Deseja Salvar?");

        //configura acoes para botao sim
        dialog.setPositiveButton(
                // sim/ aceito
                "Sim",
                //
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Foi em Sim",Toast.LENGTH_SHORT).show();

                        // vai para outra tela
                        Intent intent = new Intent(getApplicationContext(), MotoTaxistaActivity.class);

                        motoTaxiDAO = new MotoTaxiDAO(getApplicationContext());

                        // setando dados para a variavel motoTaxi
                        //motoTaxi = pegarDasEditText();

                        // mandar  dados para a a proxima tela
                        intent.putExtra("cpf", motoTaxi.getDadosPessoais().getCpf());
                        startActivity(intent);
                        finish();
                    }
                }
        );

        // configura botao nao
        dialog.setNegativeButton(
                // nao/ negacao
                "Não",
                //
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }
        );

        // criar e exibir
        dialog.create();
        dialog.show();
    }
*/