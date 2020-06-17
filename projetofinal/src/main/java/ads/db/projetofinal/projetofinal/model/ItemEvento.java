package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class ItemEvento {

    private Integer codigoEvento;
    private Integer codigoItem;

    public ItemEvento(Integer codigoEvento, Integer codigoItem) {
        this.codigoEvento = codigoEvento;
        this.codigoItem = codigoItem;
    }

}
