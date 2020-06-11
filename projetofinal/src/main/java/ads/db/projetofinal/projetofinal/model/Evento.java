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
    private ArrayList<Pessoa> convidados;
    private ArrayList<ComesBebes> comesBebes;
    
    public Evento(String local, LocalDate data, String descricao, String nome) {
        this.local = local;
        this.data = data;
        this.descricao = descricao;
        this.nome = nome;
    }
}