package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class ComesBebes {
    private String nomeItem;
    private Integer codigoItem;
    
    public ComesBebes(Integer codigoItem, String nomeItem) {
        this.codigoItem =codigoItem;
        this.nomeItem = nomeItem;
    }
}