package model;

import lombok.Data;

@Data
public class ComesBebes {
    private String nomeItem;
    private Integer codigoItem;
    
    public ComesBebes(String nomeItem, Integer codigoItem) {
        this.nomeItem = nomeItem;
        this.codigoItem = codigoItem;
    }

}