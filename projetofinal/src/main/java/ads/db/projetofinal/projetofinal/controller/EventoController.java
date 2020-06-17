package ads.db.projetofinal.projetofinal.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class EventoController {

    // JSON : EVENTO : {"codigo":null,"senha":null,"local":"São José","data":"2020-10-10","descricao":null,"nome":"RAFAEL"}
    @RequestMapping(method=RequestMethod.POST, produces="application/json", value="/evento")
    // public Evento createEvento(@RequestBody Evento evento){ 
    public Evento createEvento( 


        @RequestParam(value="local", required=false) String local,
        @RequestParam(value="data", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
        @RequestParam(value="descricao", required=false) String descricao,
        @RequestParam(value="nome", required=false) String nome) {

            Evento evento = new Evento(local, data, nome);
            System.out.println(evento.toString());





        // Params:
        // Nome_festa, data_festa, Local_festa

        // --> Cria a primeira versão na base de dados, retorna o evento com código ->
        // cadastr como evento da pessoa;
        // --> Cria um cookie com os dados do evento
        // --> direciona para a página de edição do evento {envaminha view}

        return evento;
    }

    @RequestMapping(method=RequestMethod.GET, produces="application/json", value="/evento/{codigo}")
    public String readEvento(
        @PathVariable Integer codigo
        ) {

        // Carregar dados atualizados do evento,
        // preencher cookies no usuário;
        // e direcionar para a pagina de evento;

        return "resgatei um evento";
    }

    public String updateEvento() {

        // params:
        // cpf
        // evento(todos os meta-dados)

        // Cookie (faz a leitura do cookie de evento)
        // atualzia o evento por completo na base // nome, local, data, descrição,
        return "";
    }

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