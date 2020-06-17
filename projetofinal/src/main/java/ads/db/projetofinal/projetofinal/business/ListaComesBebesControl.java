package ads.db.projetofinal.projetofinal.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.model.ItemEvento;
import ads.db.projetofinal.projetofinal.dao.ItemEventoDAO;
import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.dao.ItemDAO;

@RestController
public class ListaComesBebesControl {

    @GetMapping("/createItemEvento")
    public String createItemEvento(String nomeItem, Integer codigoEvento) {

        Item item = new ItemDAO().read(nomeItem);

        if (item == null) {
            int codigoItemEvento = new ItemDAO().create_getCodigo(nomeItem);
            item = new Item(codigoItemEvento, nomeItem);
        }

        ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());

        boolean resultado = new ItemEventoDAO().create(itemEvento);

        Evento evento = new EventoDAO().read(codigoEvento);

        return "Foi registrado no evento: " + evento.toString() + " o item comesBebes: " + item.toString()
                + "  || " + itemEvento.toString();
    }

    @GetMapping("/readAllItemEvento")
    public String readAllItensEvento(Integer codigoEvento) {
        List<Item> itens = new ArrayList<>();

        List<ItemEvento> itensEvento = new ItemEventoDAO().readAll();
    
        for (ItemEvento itemEvento : itensEvento) {
            Item item = new ItemDAO().read(itemEvento.getCodigoItem());
            itens.add(item);
        }
    
        return itens.toString();
    }

    @GetMapping("/deletaItemEvento")
    public String deletaItemEvento(Integer codigoEvento, Integer codigoItem) {

        boolean resultado = new ItemEventoDAO().delete(codigoEvento, codigoItem); 

        return resultado ? "codigoComesBebes deletado com sucesso" : "falha ao deletar";
    }

}