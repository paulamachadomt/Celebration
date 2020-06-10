package ads.db.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class SpringController {

    @GetMapping("/back")
    public String doGet(String nome, String cpf) {

        Pessoa pessoa = new Pessoa(cpf, nome);

        PessoaDAO cadastro = new PessoaDAO();

        boolean resultado = cadastro.cadastrarPessoa(pessoa);

        return "ola," + nome + "\n\n" + resultado;
    }

    

}