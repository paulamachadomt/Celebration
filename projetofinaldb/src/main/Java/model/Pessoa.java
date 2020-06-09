package model;

import lombok.Data;

@Data
public class Pessoa {
    private String nome;
    private String cpf;
    private String escolhaItem;

    public Pessoa (String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    
}