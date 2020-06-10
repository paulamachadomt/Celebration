package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class Pessoa {
    private String nome;
    private String cpf;
    

    public Pessoa (String cpf, String nome) {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public String getNome(){
        return this.nome;
    }

    public String getCpf(){
        return this.cpf;
    }

    
}