package ads.db.projetofinal.projetofinal.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.controller.util.UtilLogin;
import ads.db.projetofinal.projetofinal.model.entity.Pessoa;

@RestController
public class AutenticacaoController extends UtilLogin {

    @RequestMapping(
        method = RequestMethod.POST, 
        produces = "application/json", 
        value = "/login"
        )
    public Boolean doPost(
            @RequestBody Pessoa pessoa, // JSON: {"cpf":"01010101012", "nome":"rafael"}
            // @RequestParam String cpf, // by simple form   
            // @RequestParam String nome, // by simple form  
            HttpServletResponse response
                ) {
                // Pessoa pessoa = new Pessoa(cpf, nome.toLowerCase());
                pessoa.setNome(pessoa.getNome().toLowerCase());
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
                return getCookie;
            }

    @RequestMapping(
        method = RequestMethod.POST, 
        produces = "application/json", 
        value = "/logout")
    public Boolean doPost(
            @CookieValue(value = "cpf", defaultValue = "null") String cpf,
            HttpServletResponse response
                ) {
                response.addCookie(killCookie("nome", "default"));    // remove cookie autenticação user
                response.addCookie(killCookie("cpf", "default"));     // remove cookie autenticação user
                response.addCookie(killCookie("codigo_evento", ""));  // remove cookie autentica acesso ao evento 
                response.addCookie(killCookie("criador_evento", "")); // remove cookie autentica acesso a edição de evento                  
                return true;
            }
}