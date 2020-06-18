package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.ItemEvento;
import ads.db.projetofinal.projetofinal.model.Convidado;

@RestController
public class EventoController extends UtilEvento{

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento")
    public Integer createEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @RequestBody Evento evento
            ) {
        if (!cpf.equalsIgnoreCase("default")) {
            Integer codigoEvento = cadastrarEvento(evento); // auto_increment
            evento.setCodigo(codigoEvento);
            boolean resultadoCriadorEvento = cadastrarConvidado(new Convidado(true, cpf, evento.getCodigo(), true));
            System.out.println(resultadoCriadorEvento);
        }
        return evento.getCodigo() == null ? -1 : evento.getCodigo();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/evento/{codigo}")
    public Evento readEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigo,
        HttpServletResponse response
            ) {
        Evento evento = null;
        if (!cpf.equalsIgnoreCase("default")) {
            evento = carregarEvento(codigo);
            if (evento != null) {
                Convidado convidado = null;
                List<Convidado> registroConvidados = carregarRegistroConvidado(codigo);
                for (Convidado convidadoRegistrado : registroConvidados) {
                    if (cpf.equalsIgnoreCase(convidadoRegistrado.getCpfPessoa())) {
                        convidado = convidadoRegistrado;
                    }
                }
                evento.setRegistroConvidados(registroConvidados);
                evento.setConvidados(carregarConvidado(codigo));
                evento.setRegistroItens(carregarRegistroItens(codigo));
                evento.setItens(carregarItens(codigo));
                response.addCookie(getCookie("cookieCodigoEvento", ""+evento.getCodigo()));
                response.addCookie(getCookie("cookieCriadorEvento", ""+convidado.getCriadorEvento()));
            }
        }
        return evento;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigo}/")
    public Boolean updateEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @CookieValue(value = "cookieCodigoEvento") String cookieCodigoEvento,
        @CookieValue(value = "cookieCriadorEvento") String cookieCriadorEvento,
        @RequestBody Evento evento, 
        @PathVariable Integer codigo
            ) {
        boolean resultado = false;

        if (codigo == evento.getCodigo()) {
            resultado = atualizarEvento(evento);
        }
       
        return resultado;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigo}/delete")
    public boolean deleteEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigo
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("default")) {
            Evento evento = carregarEvento(codigo);
            if (evento != null) {
                evento.setRegistroConvidados(carregarRegistroConvidado(codigo));
                evento.setRegistroItens(carregarRegistroItens(codigo));
            } else {
                evento = new Evento();
                evento.setRegistroConvidados(new ArrayList<>());
                evento.setRegistroItens(new ArrayList<>());
            }
            for (Convidado registroConvidado : evento.getRegistroConvidados()) {
                deletarRegistroConvidado(registroConvidado);
            }
            for (ItemEvento registroItem : evento.getRegistroItens()) {
                deletarRegistroItem(registroItem);
            }
            resultado = deletarEvento(codigo);
        }
        return resultado;
    }
}