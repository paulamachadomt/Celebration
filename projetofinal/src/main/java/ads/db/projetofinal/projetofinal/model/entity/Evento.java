package ads.db.projetofinal.projetofinal.model.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Evento {

    private Integer codigo; // auto_increment
    private String local;
    private LocalDate data;
    private String nome;
    private String descricao;

    public Evento() { }

    public Evento(String local, LocalDate data, String nome) {
        this.local = local;
        this.data = data;
        this.nome = nome;
    }

    public Evento(Integer codigo, String local, LocalDate data, String nome, String descricao) {
        this.codigo = codigo;
        this.local = local;
        this.data = data;
        this.nome = nome;
        this.descricao = descricao;
    }
}