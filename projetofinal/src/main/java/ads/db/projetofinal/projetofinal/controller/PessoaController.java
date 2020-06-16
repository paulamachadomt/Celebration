package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class PessoaController {

    @GetMapping("/cadastro")
    public String doGet(String nome, String cpf) {

        Pessoa pessoa = new Pessoa(cpf, nome);

        PessoaDAO pessoaDAO = new PessoaDAO();

        boolean resultado = pessoaDAO.createPessoa(pessoa);

        return "ola," + nome + "\n\n" + resultado;
    }

    @GetMapping("/pesquisaCPF")
    public String doGet(String cpf) {

        PessoaDAO pessoaDAO = new PessoaDAO();

        Pessoa pessoa = pessoaDAO.readPessoaByCPF(cpf);

        return pessoa == null ? "cpf não encontrada" : "ola," + pessoa.getNome() + "\n\n" + pessoa.getCpf();

        // TERNARIO --==> (condição) ? [true] : [false]
    }

    @GetMapping("/pesquisaNome")
    public String selectNome(String nome) {

        PessoaDAO pessoaDAO = new PessoaDAO();

        ArrayList<Pessoa> listaNomes = pessoaDAO.readPessoaByName(nome);

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

    @GetMapping("/update")
    public String atualizaNome(String nome, String cpf) {

        Pessoa pessoa = new Pessoa(cpf, nome);

        PessoaDAO pessoaDAO = new PessoaDAO();

        boolean resultado = pessoaDAO.updatePessoa(pessoa);

        // pessoa = pessoaDAO.readPessoaByCPF(cpf);

        return "ola," + pessoa.getNome() + "\n\n  " + resultado;
    }

    @GetMapping("/delete")
    public String deletaPessoa(String cpf) {

        PessoaDAO pessoaDAO = new PessoaDAO();

        boolean resultado = pessoaDAO.deletePessoaByCPF(cpf);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar";
    }

}