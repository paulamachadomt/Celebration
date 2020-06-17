package ads.db.projetofinal.projetofinal.business;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class PessoaControl {

    @GetMapping("/createPessoa")
    public String createPessoa(String nome, String cpf) {

        Pessoa pessoa = new Pessoa(cpf, nome);

        PessoaDAO pessoaDAO = new PessoaDAO();

        boolean resultado = pessoaDAO.create(pessoa);

        return "ola," + nome + "\n\n" + resultado;
    }

    @GetMapping("/pesquisaPessoaCPF")
    public String pesquisaPessoaCPF(String cpf) {

        PessoaDAO pessoaDAO = new PessoaDAO();

        Pessoa pessoa = pessoaDAO.read(cpf);

        return pessoa == null ? "cpf não encontrada" : "ola," + pessoa.getNome() + "\n\n" + pessoa.getCpf();

    }

    @GetMapping("/pesquisaNome")
    public String pesquisaNome(String nome) {

        PessoaDAO pessoaDAO = new PessoaDAO();

        ArrayList<Pessoa> listaNomes = pessoaDAO.read_ByNome(nome);

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

    @GetMapping("/updatePessoa")
    public String updatePessoa(String nome, String cpf) {

        Pessoa pessoa = new Pessoa(cpf, nome);

        PessoaDAO pessoaDAO = new PessoaDAO();

        boolean resultado = pessoaDAO.update(pessoa);

        // pessoa = pessoaDAO.readPessoaByCPF(cpf);

        return "ola," + pessoa.getNome() + "\n\n  " + resultado;
    }

    @GetMapping("/deletePessoa")
    public String deletaPessoa(String cpf) {

        PessoaDAO pessoaDAO = new PessoaDAO();

        boolean resultado = pessoaDAO.delete(cpf);

        return resultado == true ? "Deletado com sucesso." : "Erro ao deletar";
    }

}