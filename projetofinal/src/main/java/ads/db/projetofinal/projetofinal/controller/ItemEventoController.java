package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.controller.util.UtilCheck;
import ads.db.projetofinal.projetofinal.controller.util.UtilEvento;
import ads.db.projetofinal.projetofinal.model.entity.Item;
import ads.db.projetofinal.projetofinal.model.entity.ItemEvento;

@RestController
public class ItemEventoController extends UtilEvento {

    @RequestMapping(
        method = RequestMethod.GET, 
        produces = "application/json", 
        value = "/eventos/{codigoEvento}/itens"
        )
    public List<Item> doGet(
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
                        response = carregaItensDoEvento(codigoEvento);
                    }
                return response;
            }

    @RequestMapping(
        method = RequestMethod.POST, 
        produces = "application/json", 
        value = "/eventos/{codigoEvento}/itens"
        )
    public boolean doPost(
            @CookieValue(value = "cpf", defaultValue = "null") String cpf,
            @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
            @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
            @PathVariable Integer codigoEvento, 
            @RequestBody Item item
            // @RequestParam String nomeItem // by form
                ) {
        boolean resultado = false;
        if (UtilCheck.loginIsAuthenticated(cpf) 
        &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
        &&  UtilCheck.criadorEventoIsAuthenticated(criador_evento)
            ) {
                item = verificaOuCadastraItem(item.getNomeItem());
                if (!verificaRegistroDoItemNoEvento(item, codigoEvento)) {
                    resultado = cadastrarItemEvento(new ItemEvento(codigoEvento, item.getCodigoItem()));
                }
            }
        return resultado;
    }

    @RequestMapping(
        method = RequestMethod.DELETE, 
        produces = "application/json", 
        value = "/eventos/{codigoEvento}/itens/{nomeItem}"
        )
    public boolean doDelete(
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
                        if (verificaRegistroDoItemNoEvento(item, codigoEvento)) { 
                            resultado = deletarRegistroItem(new ItemEvento(codigoEvento, item.getCodigoItem()));
                        } 
                    }
                return resultado;
            }
}