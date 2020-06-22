package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class HomeController extends UtilLogin {

    @RequestMapping(
        method = RequestMethod.GET, 
        produces = "application/json", 
        value = "/eventos"
        )
    public List<Evento> getHome(
            @CookieValue(value = "nome", defaultValue = "null") String nome,
            @CookieValue(value = "cpf", defaultValue = "null") String cpf, 
            HttpServletResponse response
                ) {
                Pessoa pessoa = new Pessoa(cpf, nome.toLowerCase());
                List<Evento> eventos = new ArrayList<>();
                if (autenticarLogin(pessoa)) {
                    eventos = new UtilEvento().carregaEventosConvidado(pessoa);
                }
                response.addCookie(killCookie("codigo_evento", ""));  // remove cookie autentica acesso ao evento 
                response.addCookie(killCookie("criador_evento", "")); // remove cookie autentica acesso a edição de evento                                                 
                return eventos;
            }

    @RequestMapping(
        method = RequestMethod.POST, 
        produces = "application/json", 
        value = "/login"
        )
    public Boolean login(
            @RequestParam String cpf, // by simple form   
            @RequestParam String nome, // by simple form  
            HttpServletResponse response
                ) {
                Pessoa pessoa = new Pessoa(cpf, nome.toLowerCase());
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
                // return getHome(pessoa.getNome().toLowerCase(), pessoa.getCpf(), response);
                return getCookie;
            }

    @RequestMapping(
        method = RequestMethod.POST, 
        produces = "application/json", 
        value = "/logout")
    public Boolean logout(
            @CookieValue(value = "cpf", defaultValue = "null") String cpf,
            HttpServletResponse response
                ) {
                response.addCookie(killCookie("nome", "default"));
                response.addCookie(killCookie("cpf", "default"));                
                return true;
            }
}