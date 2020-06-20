package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.ItemEvento;

@RestController
public class ItemEventoController extends UtilEvento {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/eventos/{codigoEvento}/itens")
    public List<Item> creatConvidado(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento
            ) {
        List<Item> response = new ArrayList<>();
        if (UtilCheck.loginIsAuthenticated(cpf) 
        &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
        &&  UtilCheck.convidadoCriadorIsAuthenticated(criador_evento)
            ) {
                List<ItemEvento> registroItens = carregarRegistroItens(codigoEvento);
                for (ItemEvento registroItem : registroItens) {
                    response.add(CarregarItem(registroItem.getCodigoItem()));
                }
            }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/eventos/{codigoEvento}/itens")
    public boolean creatItemEvento(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento, 
        @RequestParam String nomeItem // by form
            ) {
        boolean resultado = false;
        System.out.println(nomeItem);
        if (UtilCheck.loginIsAuthenticated(cpf) 
        &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
        &&  UtilCheck.criadorEventoIsAuthenticated(criador_evento)
            ) {
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
        return resultado;
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/eventos/{codigoEvento}/itens/{nomeItem}")
    public boolean deleteItemEvento(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento, 
        @PathVariable String nomeItem
            ) {
        boolean resultado = false;
        if (UtilCheck.loginIsAuthenticated(cpf) 
        &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
        &&  UtilCheck.criadorEventoIsAuthenticated(criador_evento)
            ) {
                Item item = CarregarItem(nomeItem);
                ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());
                List<ItemEvento> registroItemEvento = carregarRegistroItens(codigoEvento);
                if (registroItemEvento.contains(itemEvento)) {
                    resultado = deletarRegistroItem(itemEvento);
                }
            }
        return resultado;
    }
}