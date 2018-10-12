package com.dominandoandroid.example.hercules.e_moto.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;
import com.dominandoandroid.example.hercules.e_moto.model.Veiculo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hercules
 * DAO Data Access Object / design pattern
 *
 * CRUD - Create, Read, Updade, Delete
 *
 * */
public class MotoTaxiDAO implements IMotoTaxiDAO {

    private BDHelper db;
    private SQLiteDatabase escreve;         // escrever dados na tabela/ salvar
    private SQLiteDatabase ler;             // ler as tabelas


    public MotoTaxiDAO (Context context){
        db = new BDHelper(context);
        escreve = db.getWritableDatabase();     // permite salvar no banco de dados
        ler = db.getReadableDatabase();         // permite ler os dados de uma tabela
    }

    @Override
    public boolean salvar(MotoTaxi motoTaxi) {


        /*(DadosPessoais dadosPessoais, boolean disponivel, int qtdViagensDiaria,
                    int qtdEncomendas, Double dinheiro, Double valorViagem, Veiculo veiculo) {
        this.dadosPessoais = dadosPessoais;*/

        /*public DadosPessoais(String nome, String sobrenome, String cpf, String rg, String telefone, String senha, String email) {*/
        motoTaxi = new MotoTaxi(new DadosPessoais("Hercules", "Silva",
                "109","333","83999999999","herco123","email@email")
                , true, 0, 0, 0.0,
                0.0,
                new Veiculo("Honda", "fan 150", "mno4545")

        );
        System.out.println(motoTaxi.toString());

        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put("nome",motoTaxi.getDadosPessoais().getNome());
        cv.put("sobrenome",motoTaxi.getDadosPessoais().getSobrenome());
        cv.put("rg",motoTaxi.getDadosPessoais().getRg());
        cv.put("cpf",motoTaxi.getDadosPessoais().getCpf());
        cv.put("telefone",motoTaxi.getDadosPessoais().getTelefone());
        cv.put("email",motoTaxi.getDadosPessoais().getEmail());
        cv.put("senha",motoTaxi.getDadosPessoais().getSenha());
        cv.put("dinheiro",motoTaxi.getDinheiro());
        cv.put("marca",motoTaxi.getVeiculo().getMarca());
        cv.put("modelo",motoTaxi.getVeiculo().getModelo());
        cv.put("placa",motoTaxi.getVeiculo().getPlaca());

        int disponivel = 0;             // nao disponivel
        if (motoTaxi.isDisponivel()){
            disponivel = 1;             // disponivel
        }
        cv.put("disponivel", disponivel);
        cv.put("qtdviagens",motoTaxi.getQtdViagensDiaria());
        cv.put("qtdencomendas",motoTaxi.getQtdEncomendas());

        try{
            escreve.insert(
                    // nome da tabela
                    BDHelper.TABELA_MOTOTAXI,

                    // serve para (quando não passado null como parâmetro e passado o nome da coluna),
                    // caso o usuário não informe algo, salva como nulo
                    null,

                    //
                    cv
            );
            Log.i("INFO", "Exito ao savar usuário ");

        } catch (Exception e){
            Log.i("INFO", "Erro ai salvar usuário mototaxi: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    @Override
    public boolean atualizar(MotoTaxi motoTaxi) {
        return false;
    }

    @Override
    public boolean deletar(MotoTaxi motoTaxi) {
        return false;
    }

    @Override
    public List<MotoTaxi> listar() {
        List<MotoTaxi> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + BDHelper.TABELA_MOTOTAXI;
        Cursor cursor = ler.rawQuery(sql, null);

        while(cursor.moveToNext()){

            MotoTaxi mototaxista= new MotoTaxi();

            long id = cursor.getLong(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String sobrenome = cursor.getString(cursor.getColumnIndex("sobrenome"));
            String rg = cursor.getString(cursor.getColumnIndex("rg"));
            String cpf = cursor.getString(cursor.getColumnIndex("cpf"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String senha = cursor.getString(cursor.getColumnIndex("senha"));
            String marca = cursor.getString(cursor.getColumnIndex("marca"));
            String modelo = cursor.getString(cursor.getColumnIndex("modelo"));
            String placa = cursor.getString(cursor.getColumnIndex("placa"));

            Double dinheiro = cursor.getDouble(cursor.getColumnIndex("dinheiro"));

            DadosPessoais dados = new DadosPessoais(nome,sobrenome);
            dados.setRg(rg);
            dados.setCpf(cpf);
            dados.setSenha(senha);
            dados.setTelefone(telefone);
            dados.setEmail(email);
            dados.setSenha(senha);

            mototaxista.setId(id);
            mototaxista.setVeiculo(new Veiculo(marca,modelo,placa));

            mototaxista.setDinheiro(dinheiro);
            mototaxista.setDadosPessoais(
                    dados
            );
            lista.add(mototaxista);
        }

        return lista;
    }
}
