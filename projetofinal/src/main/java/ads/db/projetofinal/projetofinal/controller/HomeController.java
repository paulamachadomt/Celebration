package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class HomeController extends UtilLogin {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/home")
    public List<Evento> getHome(
        @CookieValue(value = "nome", defaultValue = "default") String nome,
        @CookieValue(value = "cpf", defaultValue = "default") String cpf
            ) {
        List<Evento> eventos = new ArrayList<>();
        Pessoa pessoa = new Pessoa(cpf, nome.toLowerCase());
        UtilEvento util = new UtilEvento();
        if (autenticarLogin(pessoa)) {
            for (Convidado eventoConvidados : util.carregaEventosConvidado(new Pessoa(cpf, nome))) {
                eventos.add(util.carregarEvento(eventoConvidados.getCodigoEvento())); 
            }
        }
        return eventos;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/home/login")
    public List<Evento> login(
        @RequestBody Pessoa pessoa, 
        HttpServletResponse response
            ) {
        boolean getCookie = false;
        if (autenticarLogin(pessoa)) {
            getCookie = true;
        } else if (cadastrarPessoa(pessoa)) {
            getCookie = true;
        }
        if (getCookie) {
            response.addCookie(getCookie("nome", pessoa.getNome().toLowerCase()));
            response.addCookie(getCookie("cpf", pessoa.getCpf()));
        }
        return getHome(pessoa.getNome().toLowerCase(), pessoa.getCpf());
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/home/logout")
    public List<Evento> logout(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        HttpServletResponse response
            ) {
        response.addCookie(getCookie("nome", "default"));
        response.addCookie(getCookie("cpf", "default"));
        return getHome("default", "default");
    }
}