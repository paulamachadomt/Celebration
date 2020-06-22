package ads.db.projetofinal.projetofinal.model.entity;

import lombok.Data;

@Data
public class Pessoa {

    private String cpf;
    private String nome;

    public Pessoa(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome.toLowerCase();
    }

}