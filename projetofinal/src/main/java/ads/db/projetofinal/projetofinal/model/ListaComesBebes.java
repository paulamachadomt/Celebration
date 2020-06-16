package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class ListaComesBebes {

    Integer codigoEvento;
    Integer codigoComesBebes;
    

    public ListaComesBebes(Integer codigoEvento, Integer codigoComesBebes) {
        this.codigoEvento = codigoEvento;
        this.codigoComesBebes = codigoComesBebes;
    }

}
