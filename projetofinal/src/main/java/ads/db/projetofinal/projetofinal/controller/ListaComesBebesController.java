package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.ComesBebesDAO;
import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.model.ListaComesBebes;
import ads.db.projetofinal.projetofinal.dao.ListaComesBebesDAO;
import ads.db.projetofinal.projetofinal.model.ComesBebes;
import ads.db.projetofinal.projetofinal.model.Evento;

@RestController
public class ListaComesBebesController {

    ArrayList<ListaComesBebesDAO> listaComesBebes = new ArrayList<>();

    /**
     * Esse metodo é complicado e deve ser alterado com cuidado. Apenas a criação do
     * Evento evento não é importante.
     */
    @GetMapping("/cadastroComesBebesEvento")
    public String doGet(String nomeItem, Integer codigoEvento) {
        // abstrair para "pesquisar/cadastrar item de festa"
        ComesBebes comesBebes = new ComesBebesDAO().selectNomeItemUnico(nomeItem);
        if (comesBebes == null) {
            comesBebes = new ComesBebes(new ComesBebesDAO().cadastrarItem(nomeItem), nomeItem); 
        }
        ListaComesBebes novoRegistroComesBebesEvento = new ListaComesBebes(comesBebes.getCodigoItem(), codigoEvento);
        // agora cadastra na tabela
        new ListaComesBebesDAO().cadastrarListaComesBebes(novoRegistroComesBebesEvento);

        // Apenas a titulo de organização inicial dos crud e controllers
        Evento evento = new EventoDAO().selectCodEvento(codigoEvento);
        return "Foi registrado no evento: " + evento.toString() + " o item comesBebes: " + comesBebes.toString()
                + "  || " + novoRegistroComesBebesEvento.toString();
    }

    @GetMapping("/listaComesBebesEvento") 
    public String listaComesBebesEvento(Integer codigoEvento) {

        ArrayList<ComesBebes> listaComesBebes = new ArrayList<>();

        ArrayList<ListaComesBebes> listaComesBebesEvento = new ListaComesBebesDAO().selectListaComesBebes();
        for (ListaComesBebes comesBebesEvento : listaComesBebesEvento) {
            listaComesBebes.add(new ComesBebesDAO().selectCodigoItem(comesBebesEvento.getCodigoComesBebes()));
        }

        return listaComesBebes.toString();
    }

    @GetMapping("/deletaComesBebesEvento")
    public String listaComesBebesEvento(Integer codigoEvento, Integer codigoComesBebes) {

        boolean resultado = new ListaComesBebesDAO().deletarListaComesBebes(codigoEvento, codigoComesBebes);

        return resultado ? "codigoComesBebes deletado com sucesso" : "falha ao deletar";
    }

}