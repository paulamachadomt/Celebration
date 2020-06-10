package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class SpringController {

    @GetMapping("/cadastro")
    public String doGet(String nome, String cpf) {

        Pessoa pessoa = new Pessoa(cpf, nome);

        PessoaDAO cadastro = new PessoaDAO();

        boolean resultado = cadastro.cadastrarPessoa(pessoa);

        return "ola," + nome + "\n\n" + resultado;
    }

    @GetMapping("/pesquisaCPF")
    public String doGet(String cpf) {

        PessoaDAO pesquisa = new PessoaDAO();

        Pessoa pessoa = pesquisa.selectCPFPessoa(cpf);

        return pessoa == null ? "cpf não encontrada" : "ola," + pessoa.getNome() + "\n\n" + pessoa.getCpf();

        // TERNARIO --==> (condição) ? [true] : [false]
    }

    @GetMapping("/pesquisaNome")
    public String selectNome(String nome) {

        PessoaDAO pesquisa = new PessoaDAO();

        ArrayList<Pessoa> listaNomes = pesquisa.selectNomePessoa(nome);

        String retorno = "";

        if (listaNomes.isEmpty()) {
            retorno = "Nome não encontrado";
        } else {
            for (Pessoa pessoa : listaNomes) {
                retorno += "ola," + pessoa.getNome() + "\n\n" + pessoa.getCpf();
            }
        }
        return retorno;
    }

}