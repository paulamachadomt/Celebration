package ads.db.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {

        // --> redireciona para a página home, (remove cookies do evento);

        return "/";
    }
    
}