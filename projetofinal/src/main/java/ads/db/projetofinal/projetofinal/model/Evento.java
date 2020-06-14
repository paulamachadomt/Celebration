package ads.db.projetofinal.projetofinal.model;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.Data;

@Data
public class Evento {
    private Integer codigo;
    private Integer senha;
    private String local;
    private LocalDate data;
    private String descricao;
    private String nome;

    public Evento(String local, LocalDate data, String descricao, String nome) {
        this.local = local;
        this.data = data;
        this.descricao = descricao;
        this.nome = nome;
    }

    public Evento(Integer codigo, Integer senha, String local, LocalDate data, String descricao, String nome) {
        this.codigo = codigo;
        this.senha = senha;
        this.local = local;
        this.data = data;
        this.descricao = descricao;
        this.nome = nome;
    }

    // gerar senha de evento 
    public void gerarSenhaEvento(){
        
        // =-->   senha é:      + [codigo]         + [ano]                    + [dia]
        String concatSenha = "" + this.codigo + "" + this.data.getYear() + "" + this.data.getDayOfMonth() + "";

        this.senha = Integer.parseInt(concatSenha);
    }

}