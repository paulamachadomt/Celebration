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
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @RequestBody Evento evento
            ) {
        if (!cpf.equalsIgnoreCase("null")) {
            Integer codigoEvento = cadastrarEvento(evento); // auto_increment
            evento.setCodigo(codigoEvento);
            boolean resultadoCriadorEvento = cadastrarConvidado(new Convidado(true, cpf, evento.getCodigo(), true));
        }
        return evento.getCodigo() == null ? -1 : evento.getCodigo();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/evento/{codigoEvento}")
    public Evento readEvento(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @PathVariable Integer codigoEvento,
        HttpServletResponse response
            ) {
        Evento evento = null;
        if (!cpf.equalsIgnoreCase("null")) {
                evento = carregarEvento(codigoEvento);
                if (evento != null) {
                    Convidado convidado = null;
                    List<Convidado> registroConvidados = carregarRegistroConvidado(codigoEvento);
                    for (Convidado convidadoRegistrado : registroConvidados) {
                        if (cpf.equalsIgnoreCase(convidadoRegistrado.getCpfPessoa())) {
                            convidado = convidadoRegistrado;
                            break;
                        } 
                    }
                    if (convidado == null) {
                        evento = new Evento();
                    } else {
                        // evento.setRegistroConvidados(registroConvidados);
                        // evento.setConvidados(carregarConvidado(codigoEvento));
                        // evento.setRegistroItens(carregarRegistroItens(codigoEvento));
                        // evento.setItens(carregarItens(codigoEvento));
                        response.addCookie(getCookie("codigo_evento", ""+evento.getCodigo()));
                        response.addCookie(getCookie("criador_evento", ""+convidado.getCriadorEvento()));
                    }
                }
        }
        return evento;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", value = "/evento/{codigoEvento}/")
    public Boolean updateEvento(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @RequestBody Evento evento, 
        @PathVariable Integer codigoEvento
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigoEvento)) {
            if (criador_evento.equalsIgnoreCase("true")) {
                if (codigoEvento == evento.getCodigo()) {
                    resultado = atualizarEvento(evento);
                }
            }
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/evento/{codigo}")
    public boolean deleteEvento(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigo
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigo)) {
            if (criador_evento.equalsIgnoreCase("true")) {
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
        }
        return resultado;
    }
}
