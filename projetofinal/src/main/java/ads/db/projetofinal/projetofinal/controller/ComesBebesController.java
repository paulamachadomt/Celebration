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

        ComesBebesDAO cadastro = new ComesBebesDAO();

        int codigo = cadastro.cadastrarItem(nomeItem);

        return nomeItem + "\n\n codigo do item: " + codigo;
    }

    @GetMapping("/pesquisaCodigoItem")
    public String selectCodigo(Integer codigoItem) {

        ComesBebesDAO pesquisaCodigo = new ComesBebesDAO();

        ComesBebes item = pesquisaCodigo.selectCodigoItem(codigoItem);

        return item == null ? "código não encontrada" : item.getNomeItem() + item.getCodigoItem();

    }

    @GetMapping("/pesquisaNomeItem")
    public String selectNomeItem(String nomeItem) {

        ComesBebesDAO pesquisaNome = new ComesBebesDAO();

        ArrayList<ComesBebes> listaItens = pesquisaNome.selectNomeItem(nomeItem);

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

        ComesBebesDAO update = new ComesBebesDAO();

        boolean resultado = update.updateItem(item);

        item = update.selectCodigoItem(codigoItem);

        return item.getNomeItem() + "\n\n  " + resultado;
    }

    @GetMapping("/deleteItem")
    public String deletaItem(int codigoItem) {

        ComesBebesDAO delete = new ComesBebesDAO();

        boolean resultado = delete.deleteItem(codigoItem);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar.";
    }

}