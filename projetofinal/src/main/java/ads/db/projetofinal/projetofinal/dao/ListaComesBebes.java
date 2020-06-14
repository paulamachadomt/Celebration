package ads.db.projetofinal.projetofinal.dao;

import lombok.Data;

@Data
public class ListaComesBebes {

    Integer codigoComesBebes;
    Integer codigoEvento;

    public ListaComesBebes(Integer codigoComesBebes, Integer codigoEvento) {
        this.codigoEvento = codigoEvento;
        this.codigoComesBebes = codigoComesBebes;
    }

}
