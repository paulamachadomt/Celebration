package ads.db.projetofinal.projetofinal.controller;

import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Pessoa;

public class UtilLogin extends Util {

    boolean autenticarLogin(Pessoa pessoa) {
        boolean resultado = false;
        try {
            resultado = new PessoaDAO().read(pessoa.getCpf()).equals(pessoa) ? true : false;
            if (resultado) {
                log("Pessoa autenticada com seucesso: " + pessoa.toString());
            } else {
                log("ERROR: Erro ao autenticar Pessoa.");
            }
        } catch (Exception e) {
            log("ERROR: Pessoa não cadastrada no sistema: \n" + e);
        }
        return resultado;
    }

    boolean cadastrarPessoa(Pessoa pessoa) {
        boolean resultado = false;
        try {
            resultado = new PessoaDAO().create(pessoa);
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao cadastrar pessoa " + pessoa.toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar nova Pessoa. Nome errado.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar nova Pessoa.");
        }
        return resultado;
    }
}