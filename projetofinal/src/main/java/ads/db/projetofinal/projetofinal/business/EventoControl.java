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
    
    @PostMapping("/createEvento")
    public String createEvento(
        @RequestParam("localidade") String local,
        @RequestParam("localDate") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
        String descricao,
        String nome) {

        EventoDAO eventoDAO = new EventoDAO();

        Evento evento = eventoDAO.create_getEvento(new Evento(local, data, nome));

        return evento.toString();
    }

    @GetMapping("/pesquisaCodEvento")
    public String pesquisaCodEvento(Integer codigo) {

        EventoDAO eventoDAO = new EventoDAO();

        Evento evento = eventoDAO.read(codigo);

        return evento == null ? "Evento não encontrado" : "Evento cadastrado: " + evento.toString();
    }

    @PostMapping("/updateEvento")
    public String updateEvento(
        Integer codigo,
        Integer senha,
        @RequestParam("localidade") String local,
        @RequestParam("localDate") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
        String descricao,
        String nome) {

        EventoDAO eventoDAO = new EventoDAO();

        Evento evento = new Evento(codigo, local, data, descricao, nome);

        boolean resultado = eventoDAO.update(evento);

        return "ola," + evento.getNome() + "\n\n  " + resultado;
    }

    @GetMapping("/deleteEvento")
    public String deleteEvento(Integer codigo) {

        EventoDAO eventoDAO = new EventoDAO();

        boolean resultado = eventoDAO.delete(codigo);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar";
    }

}
