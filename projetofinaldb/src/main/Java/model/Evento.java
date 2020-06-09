package model;

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
    private ArrayList<Pessoa> convidados;
    private ArrayList<ComesBebes> comesBebes;
    
    public Evento(String local, LocalDate data, String descricao) {
        this.local = local;
        this.data = data;
        this.descricao = descricao;
        
    }
    
}