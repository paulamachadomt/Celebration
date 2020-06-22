package ads.db.projetofinal.projetofinal.controller.util;

import ads.db.projetofinal.projetofinal.model.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.entity.Pessoa;

public class UtilLogin extends Util {

    public boolean autenticarLogin(Pessoa pessoa) {
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

    public boolean cadastrarPessoa(Pessoa pessoa) {
        boolean resultado = false;
        try {
            resultado = new PessoaDAO().create(pessoa);
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao cadastrar pessoa " + pessoa.toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar nova Pessoa. Nome errado ou já existe CPF.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar nova Pessoa.");
        }
        return resultado;
    }
}