package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.ComesBebesDAO;
import ads.db.projetofinal.projetofinal.model.ComesBebes;

@RestController
public class ComesBebesController {

    @GetMapping("/cadastroItem")
    public String cadastrar(String nomeItem) {

        ComesBebesDAO comesBebesDAO = new ComesBebesDAO();

        int codigo = comesBebesDAO.createItem(nomeItem);

        return nomeItem + "\n\n codigo do item: " + codigo;
    }

    @GetMapping("/pesquisaCodigoItem")
    public String selectCodigo(Integer codigoItem) {

        ComesBebesDAO comesBebesDAO = new ComesBebesDAO();

        ComesBebes item = comesBebesDAO.readItemByCodigo(codigoItem);

        return item == null ? "código não encontrada" : item.getNomeItem() + item.getCodigoItem();

    }

    @GetMapping("/pesquisaNomeItem")
    public String selectNomeItem(String nomeItem) {

        ComesBebesDAO comesBebesDAO = new ComesBebesDAO();

        ArrayList<ComesBebes> listaItens = comesBebesDAO.readItensByNome(nomeItem);

        String retorno = "";

        if (listaItens.isEmpty()) {
            retorno = "Item não encontrado";
        } else {
            for (ComesBebes comesBebes : listaItens) {
                retorno += comesBebes.getNomeItem() + comesBebes.getCodigoItem();
            }
        }
        return retorno;
    }

    @GetMapping("/updateItem")
    public String atualizaNome(String nomeItem, Integer codigoItem) {

        ComesBebes item = new ComesBebes(codigoItem, nomeItem);

        ComesBebesDAO comesBebesDAO = new ComesBebesDAO();

        boolean resultado = comesBebesDAO.updateItem(item);

        item = comesBebesDAO.readItemByCodigo(codigoItem);

        return item.getNomeItem() + "\n\n  " + resultado;
    }

    @GetMapping("/deleteItem")
    public String deletaItem(int codigoItem) {

        ComesBebesDAO comesBebesDAO = new ComesBebesDAO();

        boolean resultado = comesBebesDAO.deleteItemByCodigo(codigoItem);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar.";
    }

}