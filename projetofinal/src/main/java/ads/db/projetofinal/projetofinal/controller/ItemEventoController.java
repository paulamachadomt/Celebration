package ads.db.projetofinal.projetofinal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.ItemEvento;

@RestController
public class ItemEventoController extends UtilEvento {

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/item/{nomeItem}")
    public boolean creatItemEvento(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento, 
        @PathVariable String nomeItem
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigoEvento)) {
            if (criador_evento.equalsIgnoreCase("true")) {
                Item item = CarregarItem(nomeItem);
                if (item == null) {
                    item = cadastrarItem(nomeItem);
                }
                ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());
                List<ItemEvento> registroItemEvento = carregarRegistroItens(codigoEvento);
                if (!registroItemEvento.contains(itemEvento)) {
                    resultado = cadastrarItemEvento(itemEvento);
                }
            }
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/item/{nomeItem}/remover")
    public boolean deleteItemEvento(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento, 
        @PathVariable String nomeItem
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigoEvento)) {
            if (criador_evento.equalsIgnoreCase("true")) {
                Item item = CarregarItem(nomeItem);
                ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());
                List<ItemEvento> registroItemEvento = carregarRegistroItens(codigoEvento);
                if (registroItemEvento.contains(itemEvento)) {
                    resultado = deletarRegistroItem(itemEvento);
                }
            }
        }
        return resultado;
    }
}