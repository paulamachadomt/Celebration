package ads.db.projetofinal.projetofinal.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @PostMapping("/login")
    public String login(String username, String cpf) {

            //Params:
            //nome, cpf 

            //--> Cookier (informações usuário, listas de festas participantes e criadas), 
            //--> Eventos (Nome, local, data, descrição, proprietario)

            return "";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {

        //Params:
        //cpf

        //--> Destroi cookie, 
        //--> fecha sistema

        return "";
    }

}