package ads.db.projetofinal.projetofinal.business;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.model.Evento;

@RestController
public class EventoControl {
    
    @PostMapping("/cadastroEvento")
    public String doGet(
        @RequestParam("localidade") String local,
        @RequestParam("localDate") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
        String descricao,
        String nome) {

        EventoDAO eventoDAO = new EventoDAO();

        Evento evento = eventoDAO.gerarEvento(new Evento(local, data, descricao, nome));

        return evento.toString();
    }

    @GetMapping("/pesquisaSenhaEvento")
    public String doGet(Integer senha) {

        EventoDAO eventoDAO = new EventoDAO();

        Evento evento = eventoDAO.readEventoBySenha(senha);

        return evento == null ? "Evento não encontrado" : "Evento cadastrado: " + evento.toString();
    }

    @GetMapping("/pesquisaCodEvento")
    public String pesquisaCod(Integer codigo) {

        EventoDAO eventoDAO = new EventoDAO();

        Evento evento = eventoDAO.readEventoByCodigo(codigo);

        return evento == null ? "Evento não encontrado" : "Evento cadastrado: " + evento.toString();
    }

    @PostMapping("/updateEvento")
    public String atualizaEvento(
        Integer codigo,
        Integer senha,
        @RequestParam("localidade") String local,
        @RequestParam("localDate") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
        String descricao,
        String nome) {

        System.out.println(codigo +""+ senha+""+ local+""+ data+""+""+ descricao+""+ nome);

        EventoDAO eventoDAO = new EventoDAO();

        Evento evento = new Evento(codigo, senha, local, data, descricao, nome);
        System.out.println(evento.toString());

        boolean resultado = eventoDAO.updateEvento(evento);

        return "ola," + evento.getNome() + "\n\n  " + resultado;
    }

    @GetMapping("/deleteEvento")
    public String deleteEvento(Integer codigo) {

        EventoDAO eventoDAO = new EventoDAO();

        boolean resultado = eventoDAO.deleteEventoByCodigo(codigo);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar";
    }

}
