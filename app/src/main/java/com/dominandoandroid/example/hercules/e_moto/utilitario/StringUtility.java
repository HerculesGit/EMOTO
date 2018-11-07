package com.dominandoandroid.example.hercules.e_moto.utilitario;

public class StringUtility {

    /**
     * Metodo pega o nome completo e retorna apenas o primeiro nome
     * @autor Hercules
     * @param nomeCompleto - O nome completo eh o nome com sobrenome(s)
     * @return String - nome: apenas o primeiro nome
     *
     * */
    public static String getFirstName(String nomeCompleto){
        for (int i = 0; i < nomeCompleto.length(); i++) {
            if (nomeCompleto.charAt(i) == ' '){
                String firstName = nomeCompleto.substring(0, i);
                return firstName;
            }
        }
        return  nomeCompleto;
    }

    /**
     * Metodo pega o nome completo e retorna o sobrenome
     * @autor Hercules
     * @param nomeCompleto - O nome completo eh o nome com sobrenome(s)
     * @return String - sobrenome - retorna o sobrenome
     *
     * */
    public static String getLastName(String nomeCompleto){
        for (int i = 0; i < nomeCompleto.length(); i++) {
            if (nomeCompleto.charAt(i) == ' '){
                String lastName = nomeCompleto.substring(i);
                return lastName;
            }
        }
        return  nomeCompleto;
    }

    /**
     * Metodo pega o nome completo e retorna o nome e sobrenome
     * @autor Hercules
     * @param nomeCompleto - O nome completo eh o nome com nome e o sobrenome
     * @return String - retorna o nome e sobrenome
     *
     * */
    public static String getFirstAndLastName(String nomeCompleto){
        int count = 0;
        for (int i = 0; i < nomeCompleto.length(); i++) {
            if (nomeCompleto.charAt(i) == ' '){
                count++;

                if (count == 2){
                    String lastName = nomeCompleto.substring(i);
                    return lastName;
                }
            }
        }
        return  nomeCompleto;
    }

    /**
     * Remove os espacos no final e comeco da String
     * */
    //public static String aparar(){

    //}

}
