package ads.db.projetofinal.projetofinal.model.entity;

import lombok.Data;

@Data
public class Item {

    private Integer codigoItem; // auto_increment
    private String nomeItem;

    public Item(Integer codigoItem, String nomeItem) {
        this.codigoItem = codigoItem;
        this.nomeItem = nomeItem;
    }
}