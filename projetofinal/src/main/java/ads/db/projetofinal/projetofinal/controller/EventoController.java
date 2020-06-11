package ads.db.projetofinal.projetofinal.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.model.Evento;

public class EventoController {
    
    @PostMapping("/cadastroEvento")
    public String doGet(
        String local,  
        @RequestParam("localDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
        LocalDate localDate, 
        String descricao, 
        String nome
        ) {

        Evento evento = new Evento(local, localDate, descricao, nome);

        // metodo que gera senhas

        evento.setSenha(01);

        EventoDAO cadastro = new EventoDAO();

        boolean resultado = cadastro.cadastroEvento(evento);

        return evento.getNome() + "\n\n" + resultado;
    }

    @GetMapping("/pesquisaCodEvento")
    public String doGet(Integer codigo) {

        EventoDAO pesquisa = new EventoDAO();

        Evento evento = pesquisa.selectCodEvento(codigo);

        return evento == null ? "Evento não encontrado" : "Evento cadastrado: "+ evento.getNome();

        // TERNARIO --==> (condição) ? [true] : [false]
    }

    // @GetMapping("/pesquisaNomeEvento")
    // public String selectNome(String nome) {

    //     PessoaDAO pesquisa = new PessoaDAO();

    //     ArrayList<Pessoa> listaNomes = pesquisa.selectNomePessoa(nome);

    //     String retorno = "";

    //     if (listaNomes.isEmpty()) {
    //         retorno = "Nome não encontrado";
    //     } else {
    //         for (Pessoa pessoa : listaNomes) {
    //             retorno += "Olá, " + pessoa.getNome() + "\n\n" + pessoa.getCpf();
    //         }
    //     }
    //     return retorno;
    }

    // @GetMapping("/update")
    // public String atualizaNome(String nome, String cpf) {

    //     Pessoa pessoa = new Pessoa(cpf, nome);

    //     PessoaDAO update = new PessoaDAO();

    //     boolean resultado = update.updatePessoa(pessoa);

    //     pessoa = update.selectCPFPessoa(cpf);

    //     return "ola," + pessoa.getNome() + "\n\n  " + resultado;
    // }

    @GetMapping("/deleteEvento")
    public String deleteEvento(Integer codigo) {

        EventoDAO delete = new EventoDAO();

        boolean resultado = delete.deleteEvento(codigo);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar";
    }
}
