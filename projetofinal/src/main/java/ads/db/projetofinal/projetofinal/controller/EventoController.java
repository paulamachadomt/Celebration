package ads.db.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoController {

    @PostMapping("/evento")
    public String createEvento() {

        // Params:
        // Nome_festa, data_festa, Local_festa

        // --> Cria a primeira versão na base de dados, retorna o evento com código ->
        // cadastr como evento da pessoa;
        // --> Cria um cookie com os dados do evento
        // --> direciona para a página de edição do evento {envaminha view}

        return "";
    }

    @GetMapping("/evento")
    public String readEvento() {

        // Carregar dados atualizados do evento,
        // preencher cookies no usuário;
        // e direcionar para a pagina de evento;

        return "resgatei um evento";
    }

    @PutMapping("/evento")
    public String updateEvento() {

        // params:
        // cpf
        // evento(todos os meta-dados)

        // Cookie (faz a leitura do cookie de evento)
        // atualzia o evento por completo na base // nome, local, data, descrição,
        return "";
    }

    @DeleteMapping("/evento")
    public String deleteEvento() {

        // params:
        // códiogo_evento

        // deleta a lista d
        // deleta a lista de convidados e
        // deleta o evento;
        // direciona para a página inicial {index}
        return "";
    }
}