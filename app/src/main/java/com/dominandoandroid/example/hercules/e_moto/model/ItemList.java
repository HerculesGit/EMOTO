package com.dominandoandroid.example.hercules.e_moto.model;

public class ItemList {
    private String texto;
    private int icone;

    public ItemList(){
        this("", -1);
    }
    public ItemList(String texto, int iconeRid) {
        this.texto = texto;
        this.icone= iconeRid;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }
}
