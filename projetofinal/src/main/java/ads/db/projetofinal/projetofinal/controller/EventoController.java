package ads.db.projetofinal.projetofinal.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class EventoController {

    // @PostMapping("/localdate")
    // public String testa(
    // @RequestParam("localDate")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    // LocalDate localDate) {

    // System.out.println(localDate);

    // System.out.println("" + localDate.getDayOfMonth()
    // + localDate.getDayOfWeek() + localDate.getMonth() + localDate.getYear());

    // return "" + localDate;
    // }

    @PostMapping("/cadastroEvento")
    public String doGet(
            @RequestParam("localidade") String local,
            @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
            String descricao,
            String nome) {

        String retorno = "falha ao cadastrar evento";

        Evento evento = new Evento(local, data, descricao, nome);

        EventoDAO cadastro = new EventoDAO();

        Integer resultado = cadastro.cadastroEvento(evento);

        if (resultado >= 1) {
            evento.setCodigo(resultado);
            evento.gerarSenhaEvento(resultado, data);
        }

        return evento.toString();
    }

    @GetMapping("/pesquisaCodEvento")
    public String doGet(Integer codigo) {

        EventoDAO pesquisa = new EventoDAO();

        Evento evento = pesquisa.selectCodEvento(codigo);

        return evento == null ? "Evento não encontrado" : "Evento cadastrado: " + evento.getNome();

        // TERNARIO --==> (condição) ? [true] : [false]
    }

    @GetMapping("/pesquisaNomeEvento")
    public String selectNome(String nome) {

        PessoaDAO pesquisa = new PessoaDAO();

        ArrayList<Pessoa> listaNomes = pesquisa.selectNomePessoa(nome);

        String retorno = "";

        if (listaNomes.isEmpty()) {
            retorno = "Nome não encontrado";
        } else {
            for (Pessoa pessoa : listaNomes) {
                retorno += "Olá, " + pessoa.getNome() + "\n\n" + pessoa.getCpf();
            }
        }
        return retorno;
    }

    @GetMapping("/updateEvento")
    public String atualizaNome(String nome, String cpf) {

        Pessoa pessoa = new Pessoa(cpf, nome);

        PessoaDAO update = new PessoaDAO();

        boolean resultado = update.updatePessoa(pessoa);

        pessoa = update.selectCPFPessoa(cpf);

        return "ola," + pessoa.getNome() + "\n\n  " + resultado;
    }

    @GetMapping("/deleteEvento")
    public String deleteEvento(Integer codigo) {

        EventoDAO delete = new EventoDAO();

        boolean resultado = delete.deleteEvento(codigo);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar";
    }

}
