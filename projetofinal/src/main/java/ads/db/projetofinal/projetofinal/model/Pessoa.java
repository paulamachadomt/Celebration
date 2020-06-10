package model;

import lombok.Data;

@Data
public class Pessoa {
    private String nome;
    private Integer cpf;
    

    public Pessoa (String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    
}