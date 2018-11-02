package com.dominandoandroid.example.hercules.e_moto.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;
import com.dominandoandroid.example.hercules.e_moto.model.Veiculo;

public class CadastroVeiculo extends AppCompatActivity {
    private MotoTaxi motoTaxi;
    private TextInputEditText inputEditTextMarca, inputEditTextModelo, inputEditTextPlaca;
    private Button btAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //aqui a mágica
        setContentView(R.layout.activity_cadastro_veiculo);

        inputEditTextMarca = findViewById(R.id.cadastro_dados_marca);
        inputEditTextModelo = findViewById(R.id.cadastro_dados_modelo);
        inputEditTextPlaca = findViewById(R.id.cadastro_dados_placa);
        btAvancar = findViewById(R.id.bt_avancar_finalizar);

        // recuperando dados passado da outra tela
        Bundle objetoEnviado = getIntent().getExtras();

        if (objetoEnviado != null){
            motoTaxi = (MotoTaxi) objetoEnviado.getSerializable("mototaxi");
            System.out.println(motoTaxi.toString());
        } else {
            System.out.println("NÃO CONSEGUIU PEGAR OS DADOS PASSADOS");
        }

        btAvancar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // se os campos nao estiverem vazios
                        //if(!camposEstaoVazios()){
                            // pegar os dados das editText's
                            //motoTaxi.setMoto(pegarDasEditText());
                        teste();
                        Intent intencao = new Intent(CadastroVeiculo.this, FinalizarCadastro.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("mototaxi", motoTaxi);
                            intencao.putExtras(bundle);
                            startActivity(intencao);
                        //}

                    }
                }
        );
    }

    private boolean camposEstaoVazios(){
        if (inputEditTextMarca.getText().toString().length() == 0){
            inputEditTextMarca.setError("Campo obrigatório");
            return true;

        } if (inputEditTextModelo.getText().toString().length() == 0){
            inputEditTextModelo.setError("Campo obrigatório");
            return true;

        } if (inputEditTextPlaca.getText().toString().length() == 0){
            inputEditTextPlaca.setError("Campo obrigatório");
            return true;
        }
        return false;
    }

    private Veiculo pegarDasEditText() {
        Veiculo moto = new Veiculo();
        moto.setMarca(inputEditTextMarca.getText().toString());
        moto.setModelo(inputEditTextModelo.getText().toString());
        moto.setPlaca(inputEditTextPlaca.getText().toString());
        return moto;
    }

    private void teste(){
        Veiculo moto = new Veiculo();
        moto.setMarca("Honda");
        moto.setModelo("Fan 150");
        moto.setPlaca("mng1234");

        motoTaxi.setMoto(moto);
    }


}
