package ads.db.projetofinal.projetofinal.controller;

import javax.servlet.http.Cookie;

import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Pessoa;

public class UtilLogin {

    static Cookie getCookie(String cookieName, String valueName) {
        Cookie cookie = new Cookie(cookieName, valueName);
        cookie.setMaxAge(1 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }

    static boolean autenticarLogin(Pessoa pessoa) {
        boolean resultado = false;
        try {
            resultado = new PessoaDAO().read(pessoa.getCpf()).equals(pessoa) ? true : false;
            if (resultado) {
                Util.log("Pessoa autenticada com seucesso: " + pessoa.toString());
            } else {
                Util.log("ERROR: Erro ao autenticar Pessoa.");
            }
        } catch (Exception e) {
            Util.log("ERROR: Pessoa não cadastrada no sistema: \n" + e);
        }
        return resultado;
    }

    static boolean cadastrarPessoa(Pessoa pessoa) {
        boolean resultado = false;
        try {
            resultado = new PessoaDAO().create(pessoa);
            if (resultado) {
                Util.log("SUCCESS: " + "\nSucesso ao cadastrar pessoa " + pessoa.toString());
            } else {
                Util.log("ERROR: " + "\nErro ao cadastrar nova Pessoa. Nome errado.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro ao cadastrar nova Pessoa.");
        }
        return resultado;
    }
}