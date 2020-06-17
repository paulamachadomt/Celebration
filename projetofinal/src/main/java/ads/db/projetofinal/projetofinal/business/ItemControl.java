package ads.db.projetofinal.projetofinal.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.ItemDAO;
import ads.db.projetofinal.projetofinal.model.Item;

@RestController
public class ItemControl {

    @GetMapping("/CreateItem")
    public String CreateItem(String nomeItem) {

        ItemDAO itemDAO = new ItemDAO();

        int codigo = itemDAO.create_getCodigo(nomeItem);

        return nomeItem + "\n\n codigo do item: " + codigo;
    }

    @GetMapping("/pesquisaItem")
    public String pesquisaItem(Integer codigoItem) {

        Item item = new ItemDAO().read(codigoItem);

        return item == null ? "código não encontrada" : item.getNomeItem() + item.getCodigoItem();

    }

    @GetMapping("/pesquisaAllItens")
    public String pesquisaAllItens(String nomeItem) {

        String retorno = "";

        List<Item> itens = new ItemDAO().readAll(nomeItem);

        if (itens.isEmpty()) {
            retorno = "Item não encontrado";
        } else {
            for (Item item : itens) {
                retorno += item.getNomeItem() + item.getCodigoItem();
            }
        }
        return retorno;
    }

    @GetMapping("/updateItem")
    public String updateItem(String nomeItem, Integer codigoItem) {

        Item item = new Item(codigoItem, nomeItem);

        boolean resultado = new ItemDAO().update(item);

        item = new ItemDAO().read(codigoItem);

        return item.getNomeItem() + "\n\n  " + resultado;
    }

    @GetMapping("/deleteItem")
    public String deletaItem(int codigoItem) {

        boolean resultado = new ItemDAO().delete(codigoItem);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar.";
    }

}