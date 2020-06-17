package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class HomeController {

    private void log(String textLog) {
        System.out.println("-------------------------------------------------------\n" + textLog);
    }

    private Cookie getCookie(String cookieName, String valueName) {
        Cookie cookie = new Cookie(cookieName, valueName);
        cookie.setMaxAge(1 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }
    
    /**
     * Verifica se as credenciais estão cadastradas no banco de dados corretamente.
     * 
     * @param pessoa
     * @return true se houver autenticação da Pessoa. E false se houver na
     *         autenticação. Poderá indicar erros de SQL.
     */
    private boolean autenticarLogin(Pessoa pessoa) {
        boolean resultado = false;
        try {
            resultado = new PessoaDAO().read(pessoa.getCpf()).equals(pessoa) ? true : false;
            if (resultado) {
                log("Pessoa autenticada com seucesso: " + pessoa.toString());
            } else {
                log("ERROR: Erro ao autenticar Pessoa.");
            }
        } catch (Exception e) {
            log("ERROR: Pessoa não cadastrada no sistema: \n" + e);
        }
        return resultado;
    }

    /**
     * Cadastra as credenciais Pessoa no banco de dados.
     * 
     * @param pessoa
     * @return true se houver sucesso no cadastro. E false se houver duplicação de
     *         primary.key ou erros de SQL.
     */
    private boolean cadastrarPessoa(Pessoa pessoa) {
        boolean resultado = false;
        try {
            resultado = new PessoaDAO().create(pessoa);
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao cadastrar pessoa " + pessoa.toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar nova Pessoa. Nome errado.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar nova Pessoa.");
        }
        return resultado;
    }

    private List<Convidado> eteventosConvidado(Pessoa pessoa) {
        return new ConvidadosDAO().read(pessoa.getCpf());
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/home")
    public List<Evento> getHome(
        @CookieValue(value = "nome", defaultValue = "default") String nome,
        @CookieValue(value = "cpf", defaultValue = "default") String cpf
        ) {
        List<Evento> eventos = new ArrayList<>();
        Pessoa pessoa = new Pessoa(cpf, nome.toLowerCase());
        if (autenticarLogin(pessoa)) {
            for (Convidado eventoConvidados : eteventosConvidado(new Pessoa(cpf, nome))) {
                eventos.add(new EventoDAO().read(eventoConvidados.getCodigoEvento()));
            }
        }
        // remove cookie evento
        return eventos;
    }

    // JSON : example : { "nome":"nome","cpf":"00000000000"}
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