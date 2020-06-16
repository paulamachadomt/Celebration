package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class ComesBebes {
    
    private Integer codigoItem;
    private String nomeItem;
    
    
    public ComesBebes(Integer codigoItem, String nomeItem) {
        this.codigoItem = codigoItem;
        this.nomeItem = nomeItem;
    }

}