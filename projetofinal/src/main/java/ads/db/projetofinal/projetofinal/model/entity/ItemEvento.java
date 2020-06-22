package ads.db.projetofinal.projetofinal.model.entity;

import lombok.Data;

@Data
public class ItemEvento {

    private Integer codigoEvento;
    private Integer codigoItem;

    public ItemEvento(Integer codigoEvento, Integer codigoItem) {
        this.codigoEvento = codigoEvento;
        this.codigoItem = codigoItem;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemEvento other = (ItemEvento) obj;
        if (codigoEvento == null) {
            if (other.codigoEvento != null)
                return false;
        } else if (!codigoEvento.equals(other.codigoEvento))
            return false;
        if (codigoItem == null) {
            if (other.codigoItem != null)
                return false;
        } else if (!codigoItem.equals(other.codigoItem))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoEvento == null) ? 0 : codigoEvento.hashCode());
        result = prime * result + ((codigoItem == null) ? 0 : codigoItem.hashCode());
        return result;
    }

    // @Override
    // public boolean equals(Object obj) {
    //     return (this.codigoEvento.equals(((ItemEvento) obj).codigoEvento)
    //             && this.codigoItem.equals(((ItemEvento) obj).codigoItem));
    // }

}
