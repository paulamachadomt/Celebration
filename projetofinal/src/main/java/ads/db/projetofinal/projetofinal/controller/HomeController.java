package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/home")
    public List<Evento> getHome(
        @CookieValue(value = "nome", defaultValue = "default") String nome,
        @CookieValue(value = "cpf", defaultValue = "default") String cpf
            ) {
        List<Evento> eventos = new ArrayList<>();
        Pessoa pessoa = new Pessoa(cpf, nome.toLowerCase());
        if (UtilLogin.autenticarLogin(pessoa)) {
            for (Convidado eventoConvidados : UtilEvento.eteventosConvidado(new Pessoa(cpf, nome))) {
                eventos.add(new EventoDAO().read(eventoConvidados.getCodigoEvento()));
            }
        }
        return eventos;
    }

    // JSON : example : { "nome":"nome","cpf":"00000000000"}
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/home/login")
    public List<Evento> login(
        @RequestBody Pessoa pessoa, 
        HttpServletResponse response
            ) {
        boolean getCookie = false;
        if (UtilLogin.autenticarLogin(pessoa)) {
            getCookie = true;
        } else if (UtilLogin.cadastrarPessoa(pessoa)) {
            getCookie = true;
        }
        if (getCookie) {
            response.addCookie(UtilLogin.getCookie("nome", pessoa.getNome().toLowerCase()));
            response.addCookie(UtilLogin.getCookie("cpf", pessoa.getCpf()));

        }
        return getHome(pessoa.getNome().toLowerCase(), pessoa.getCpf());
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/home/logout")
    public List<Evento> logout(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        HttpServletResponse response
            ) {
        response.addCookie(UtilLogin.getCookie("nome", "default"));
        response.addCookie(UtilLogin.getCookie("cpf", "default"));
        return getHome("default", "default");
    }
}