package com.dominandoandroid.example.hercules.e_moto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dominandoandroid.example.hercules.e_moto.model.DadosPessoais;
import com.dominandoandroid.example.hercules.e_moto.model.Endereco;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;
import com.dominandoandroid.example.hercules.e_moto.model.Veiculo;
import com.dominandoandroid.example.hercules.e_moto.model.Viagens;

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

    public long ID_RECENTE_INSERIDO=0;
    private BDHelper db;
    private SQLiteDatabase escreve;         // escrever dados na tabela/ salvar
    private SQLiteDatabase ler;             // ler as tabelas
    private DadosPessoaisDAO dadosPessoaisDAO;
    private EnderecoDAO enderecoDAO;
    private ViagensDAO viagensDAO;
    private VeiculoDAO veiculoDAO;

    public MotoTaxiDAO (Context context){
        db = new BDHelper(context);
        escreve = db.getWritableDatabase();     // permite salvar no banco de dados
        ler = db.getReadableDatabase();         // permite ler os dados de uma tabela

        dadosPessoaisDAO = new DadosPessoaisDAO(context);
        enderecoDAO = new EnderecoDAO(context);
        viagensDAO = new ViagensDAO(context);
        veiculoDAO = new VeiculoDAO(context);
    }

    @Override
    public boolean salvar(MotoTaxi motoTaxi) {

        ContentValues cv = new ContentValues();
        // nome do campo e valor para o campo
        //cv.put("idMototaxista",motoTaxi.getIdMototaxista());
        cv.put("email",motoTaxi.getEmail());
        cv.put("senha",motoTaxi.getSenha());
        cv.put("numeroCelular",motoTaxi.getNumeroCelular());
        cv.put("idDadosPessoais",motoTaxi.getDadosPessoais().getIdDadosPessoais());
        cv.put("idEndereco",motoTaxi.getEndereco().getIdEndereco());
        cv.put("idMoto",motoTaxi.getMoto().getIdVeiculo());
        cv.put("disponibilidade",motoTaxi.getDisponivel());

        try{
            ID_RECENTE_INSERIDO = escreve.insert(
                    // nome da tabela
                    BDHelper.TABELA_MOTOTAXISTA,

                    // serve para (quando não passado null como parâmetro e passado o nome da coluna),
                    // caso o usuário não informe algo, salva como nulo
                    null,

                    //
                    cv
            );
            Log.i("INFO", "Exito ao salvar mototaxista");

            System.out.println("Id recente "+ID_RECENTE_INSERIDO);

        } catch (Exception e){
            Log.i("INFO", "Erro ai salvar mototaxista: "+e.getMessage());
            return false;           // indica se houve problema
        } finally {

        }

        return true;
    }

    @Override
    public boolean atualizar(MotoTaxi motoTaxi) {
        ContentValues cv = new ContentValues();

        // nome do campo e valor para o campo
        cv.put("idMototaxista",motoTaxi.getIdMototaxista());
        cv.put("email",motoTaxi.getEmail());
        cv.put("senha",motoTaxi.getSenha());
        cv.put("numeroCelular",motoTaxi.getNumeroCelular());
        cv.put("idDadosPessoais",motoTaxi.getDadosPessoais().getIdDadosPessoais());
        cv.put("idEndereco",motoTaxi.getEndereco().getIdEndereco());
        cv.put("idMoto",motoTaxi.getMoto().getIdVeiculo());
        cv.put("disponibilidade",motoTaxi.getDisponivel());

        try{

            String[] argumentos = {
              String.valueOf(motoTaxi.getIdMototaxista())
            };
            escreve.update(
                    BDHelper.TABELA_MOTOTAXISTA,
                    cv,

                    // clausula where - caracter coringa
                    "idMototaxista=?",

                    // argumentos
                    argumentos
            );

            Log.i("INFO", "Exito ao atualizar motataxista ");

        } catch (Exception e){
            Log.i("Erro", "Erro ao atualizar mototaxista: "+e.getMessage());
            return false;           // indica se houve problema
        }

        return true;
    }

    @Override
    public boolean deletar(MotoTaxi motoTaxi) {
        return false;
    }

    @Override
    public List<MotoTaxi> listar() {
        List<MotoTaxi> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + BDHelper.TABELA_MOTOTAXISTA+";";
        Cursor cursor = ler.rawQuery(sql, null);

        while(cursor.moveToNext()){

            MotoTaxi mototaxista = new MotoTaxi();

            String email = cursor.getString(cursor.getColumnIndex("email"));
            String senha = cursor.getString(cursor.getColumnIndex("senha"));
            String numeroCelular = cursor.getString(cursor.getColumnIndex("numeroCelular"));

            int idMototaxista = cursor.getInt(cursor.getColumnIndex("idMototaxista"));
            int idDadosPessoais = cursor.getInt(cursor.getColumnIndex("idDadosPessoais"));
            int idEndereco = cursor.getInt(cursor.getColumnIndex("idEndereco"));
            int idVeiculo = cursor.getInt(cursor.getColumnIndex("idMoto"));
            int disponibilidade = cursor.getInt(cursor.getColumnIndex("disponibilidade"));

            // pesquisar dados pelo id
            DadosPessoais dadosPessoais = dadosPessoaisDAO.encontrarDe(idDadosPessoais);

            // pesquisar moto pelo id
            Veiculo veiculo = veiculoDAO.encontrarDe(idVeiculo);

            // pesquisar endereco pelo id
            Endereco endereco = enderecoDAO.encontrarDe(idEndereco);

            // pesquisar viagens pelo id
            Viagens viagens = viagensDAO.listarDe(idMototaxista);

            mototaxista.setEmail(email);
            mototaxista.setSenha(senha);
            mototaxista.setIdMototaxista(idMototaxista);
            mototaxista.setDadosPessoais(dadosPessoais);
            mototaxista.setEndereco(endereco);
            mototaxista.setMoto(veiculo);
            mototaxista.setViagens(viagens);
            mototaxista.setDisponivel(disponibilidade);
            mototaxista.setNumeroCelular(numeroCelular);

            System.out.println("tralala");

            lista.add(mototaxista);
        }
        cursor.close();

        System.out.println("SIZE:" + lista.size());

        return lista;
    }

    /**
     * Atualiza os ids da tabela mototaxita a partir do idMototaxista passado
     * */
    public boolean adicionarIds(int id){
        ContentValues cv = new ContentValues();
        cv.put("idDadosPessoais", id);
        cv.put("idEndereco", id);
        cv.put("idMoto", id);

        try {
            String[] args = {String.valueOf(id)};
            escreve.update(BDHelper.TABELA_MOTOTAXISTA, cv, "idMototaxista=?", args );
            Log.i("INFO", "id's adicionados com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizada id's " + e.getMessage() );
            return false;
        }

        return true;

    }
}
