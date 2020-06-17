package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.dao.ItemEventoDAO;
import ads.db.projetofinal.projetofinal.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.ItemEvento;
import ads.db.projetofinal.projetofinal.model.Convidado;

@RestController
public class EventoController {

    // JSON example : EVENTO : {"local":"São José","data":"2020-10-10","nome":"Festa
    // do Kobrasol"}
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento")
    public Evento createEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @RequestBody Evento evento
            ) {
        if (!cpf.equalsIgnoreCase("default")) {
            Integer codigoEvento = UtilEvento.cadastrarEvento(evento); // auto_increment
            evento.setCodigo(codigoEvento);
            boolean resultadoCriadorEvento = UtilEvento
                    .cadastrarConvidadoDono(new Convidado(true, cpf, evento.getCodigo(), true));
            System.out.println(resultadoCriadorEvento);
        }

        return evento;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/evento/{codigo}")
    public Evento readEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigo
            ) {
        Evento evento = null;

        if (!cpf.equalsIgnoreCase("default")) {
            evento = UtilEvento.carregarEvento(codigo);
            if (evento != null) {
                evento.setConvidados(UtilEvento.carregarConvidado(codigo));
                evento.setItens(UtilEvento.carregarItens(codigo));
            }
        }
        return evento;
    }

    // JSON example : EVENTO : {"codigo":"1","local":"São
    // José","data":"2020-10-10","nome":"Festa do Kobrasol","descricao":"Festa
    // pósdemia"}
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigo}/")
    public String updateEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @RequestBody Evento evento, 
        @PathVariable Integer codigo
            ) {
        if (!cpf.equalsIgnoreCase("default")) {
            boolean resultado = UtilEvento.atualizarEvento(evento);
            System.out.println(resultado);

        }
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigo}/delete")
    public String deleteEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigo
            ) {
        boolean resultado = false;

        Evento evento = UtilEvento.carregarEvento(codigo);
        if (evento != null) {
            evento.setRegistroConvidados(UtilEvento.carregarRegistroConvidado(codigo));
            evento.setRegistroItens(UtilEvento.carregarRegistroItens(codigo));
        } else {
            evento = new Evento();
            evento.setRegistroConvidados(new ArrayList<>());
            evento.setRegistroItens(new ArrayList<>());
        }
        for (Convidado registroConvidado : evento.getRegistroConvidados()) {
            new ConvidadosDAO().delete(registroConvidado.getCodigoEvento(), registroConvidado.getCpfPessoa());
        }
        for (ItemEvento registroItem : evento.getRegistroItens()) {
            new ItemEventoDAO().delete(registroItem.getCodigoEvento(), registroItem.getCodigoItem());
        }
        resultado = new EventoDAO().delete(codigo);

        return "" + resultado;
    }
}