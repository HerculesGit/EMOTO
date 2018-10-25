package com.dominandoandroid.example.hercules.e_moto.model;

import java.io.Serializable;

// implementou para transverir de uma activity para outra
public class MotoTaxi implements Serializable {

    private int idMototaxista;
    private String email, senha;
    private Endereco endereco;
    private Veiculo moto;
    private DadosPessoais dadosPessoais;
    private Viagens viagens;
    private int disponivel;

    public MotoTaxi() {
        this.idMototaxista = 0;
        this.email = "";
        this.senha = "";
        this.endereco = new Endereco();
        this.moto = new Veiculo();
        this.dadosPessoais = new DadosPessoais();
        this.disponivel = 0;
        this.viagens = new Viagens();
    }

    public MotoTaxi(int idMototaxista, String email, String senha, Endereco endereco, Veiculo moto, DadosPessoais dadosPessoais, int disponivel) {
        this.idMototaxista = idMototaxista;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.moto = moto;
        this.dadosPessoais = dadosPessoais;
        this.disponivel = disponivel;
    }

    public int getIdMototaxista() {
        return idMototaxista;
    }

    public void setIdMototaxista(int idMototaxista) {
        this.idMototaxista = idMototaxista;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Veiculo getMoto() {
        return moto;
    }

    public void setMoto(Veiculo moto) {
        this.moto = moto;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public Viagens getViagens() {
        return viagens;
    }

    public void setViagens(Viagens viagens) {
        this.viagens = viagens;
    }

    public int getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "idMototaxista: "+getIdMototaxista()+" email:"+getEmail()+ " senha:"+getSenha()+"\nDados pessoais ["+getDadosPessoais().toString()+"]\n"
                +" Endereco["+getEndereco().toString()+"]\n"
                +" Moto["+getMoto().toString()+"]"
                +" disponivel: "+getDisponivel() + " viagens "+getViagens().toString();
    }
}
