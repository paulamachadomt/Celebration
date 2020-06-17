package ads.db.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.ItemEventoDAO;
import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.ItemEvento;

@RestController
public class ItemEventoController {

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/item/{nomeItem}")
    public boolean creatItemEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigoEvento, 
        @PathVariable String nomeItem
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("default")) {
            Item item = UtilEvento.CarregarItem(nomeItem);
            if (item == null) {
                item = UtilEvento.cadastrarItem(nomeItem);
            }
            ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());
            resultado = UtilEvento.cadastrarItemEvento(itemEvento);
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/item/{codigoItem}/remover")
    public boolean deleteItemEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigoEvento, 
        @PathVariable Integer codigoItem
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("default")) {
            resultado = new ItemEventoDAO().delete(codigoEvento, codigoItem);
        }
        return resultado;
    }

}