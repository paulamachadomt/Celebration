package ads.db.projetofinal.projetofinal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.dao.ItemDAO;
import ads.db.projetofinal.projetofinal.dao.ItemEventoDAO;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.ItemEvento;

@RestController
public class ItemEventoController {
    
    public void log(String textLog) {
        System.out.println("-------------------------------------------------------\n" + textLog);
    }

    public boolean cadastrarItemEvento(ItemEvento itemEvento) {
        boolean resultado = false;
        try {
            resultado = new ItemEventoDAO().create(itemEvento);
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao cadastrar novo item ao evento" + itemEvento.toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar novo item ao evento. Problema ao resgatar por código.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar novo item evento.");
        }
        return resultado;
    }

    public Item cadastrarItem(String nomeItem) {
        Item item = null;
        try {
            int codigoItem = new ItemDAO().create_getCodigo(nomeItem);
            if (codigoItem >= 1) {
                item =  new Item(codigoItem, nomeItem);
                log("SUCCESS: " + "\nSucesso ao cadastrar novo item " + new Item(codigoItem, nomeItem).toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar novo item. Problema ao resgatar código.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar novo item.");
        }
        return item;
    }

    public Item CarregarItem(String nomeItem) {
        Item item = null;
        try {
            item = new ItemDAO().read(nomeItem);
            if (item.getCodigoItem() >= 1) {
                log("SUCCESS: " + "\nSucesso ao carregar evento " + item.toString());
            } else {
                log("ERROR: " + "\nErro ao carregar item. Problema com o nome.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao carregar item.");
        }
        return item;
    }

    public Item CarregarItem(Integer codigoItem) {
        Item item = null;
        try {
            item = new ItemDAO().read(codigoItem);
            if (item.getCodigoItem() >= 1) {
                log("SUCCESS: " + "\nSucesso ao carregar item " + item.toString());
            } else {
                log("ERROR: " + "\nErro ao carregar item. Problema com o código.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao carregar item.");
        }
        return item;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/item/{nomeItem}")
    public boolean creatItemEvento(@CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @PathVariable Integer codigoEvento, @PathVariable String nomeItem) {

        boolean resultado = false;

        if (!cpf.equalsIgnoreCase("default")) {
            Item item = CarregarItem(nomeItem);

            if (item == null) {
                item = cadastrarItem(nomeItem);
            }

            ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());

            resultado = cadastrarItemEvento(itemEvento); 
        } 
        return resultado;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/item/{codigoItem}/remover")
    public boolean deleteItemEvento(@CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @PathVariable Integer codigoEvento, @PathVariable Integer codigoItem) {

        boolean resultado = false;

        if (!cpf.equalsIgnoreCase("default")) {
            
            resultado = new ItemEventoDAO().delete(codigoEvento, codigoItem);
        }

        return resultado;
    }

}