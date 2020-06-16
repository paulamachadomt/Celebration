package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class ListaComesBebes {

    private Integer codigoEvento;
    private Integer codigoComesBebes;
    

    public ListaComesBebes(Integer codigoEvento, Integer codigoComesBebes) {
        this.codigoEvento = codigoEvento;
        this.codigoComesBebes = codigoComesBebes;
    }

}
