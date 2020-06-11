package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class Pessoa {
    private String nome;
    private String cpf;
    
    public Pessoa (String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
}