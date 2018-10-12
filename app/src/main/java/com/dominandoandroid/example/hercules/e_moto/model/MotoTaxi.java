package com.dominandoandroid.example.hercules.e_moto.model;

public class MotoTaxi {
    private  DadosPessoais dadosPessoais;
    private boolean disponivel;
    private int qtdViagensDiaria;
    private int qtdEncomendas;

    private Double dinheiro;
    private Double valorViagem;
    private Veiculo veiculo;

    public MotoTaxi(DadosPessoais dadosPessoais, boolean disponivel, int qtdViagensDiaria,
                    int qtdEncomendas, Double dinheiro, Double valorViagem, Veiculo veiculo) {
        this.dadosPessoais = dadosPessoais;
        this.disponivel = disponivel;
        this.qtdViagensDiaria = qtdViagensDiaria;
        this.qtdEncomendas = qtdEncomendas;
        this.dinheiro = dinheiro;
        this.valorViagem = valorViagem;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getQtdViagensDiaria() {
        return qtdViagensDiaria;
    }

    public void setQtdViagensDiaria(int qtdViagensDiaria) {
        this.qtdViagensDiaria = qtdViagensDiaria;
    }

    public int getQtdEncomendas() {
        return qtdEncomendas;
    }

    public void setQtdEncomendas(int qtdEncomendas) {
        this.qtdEncomendas = qtdEncomendas;
    }

    public Double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Double getValorViagem() {
        return valorViagem;
    }

    public void setValorViagem(Double valorViagem) {
        this.valorViagem = valorViagem;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "Dados pessoais ["+getDadosPessoais().toString()+"]"+
                " disponivel: "+isDisponivel()+ " viagem hoje:"+getQtdViagensDiaria() +
                " quantidade viagens encomendas:" + getQtdEncomendas() + " dinheiro:"+getDinheiro()+
                " valor viagem:" + getValorViagem()+
                " veiculo:["+getVeiculo().toString()+"]";
    }
}
