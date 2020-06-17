package ads.db.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class ConvidadosController {

    // JSON example : Pessoa : {"cpf":"00000000000","nome":"Rafael"}
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/convidado")
    public boolean creatItemEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigoEvento, 
        @RequestBody Pessoa pessoa
            ) {
        boolean resultado = false;

        System.out.println(pessoa.toString());
        if (!cpf.equalsIgnoreCase("default")) {
            Pessoa autenticaConvidado = new PessoaDAO().read(pessoa.getCpf());
            if (autenticaConvidado != null && autenticaConvidado.getNome().equalsIgnoreCase(pessoa.getNome())) {
                resultado = new ConvidadosDAO().create(new Convidado(false, pessoa.getCpf(), codigoEvento, false));
            } else {
                boolean resultadoCadastroPessoa = UtilEvento.cadastrarPessoa(pessoa);
                if (resultadoCadastroPessoa) {
                    resultado = new ConvidadosDAO().create(new Convidado(false, pessoa.getCpf(), codigoEvento, false));
                }
            }
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/convidado/{cpfPessoa}/remover")
    public boolean deleteItemEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigoEvento, 
        @PathVariable String cpfPessoa
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("default")) {
            resultado = new ConvidadosDAO().delete(codigoEvento, cpfPessoa);
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/convidado/{cpfPessoa}/confirmar/{confirmacao}")
    public boolean confirmarEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigoEvento, 
        @PathVariable String cpfPessoa, 
        @PathVariable Boolean confirmacao
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("default")) {
            resultado = new ConvidadosDAO()
                    .update_confirmacao(new Convidado(confirmacao, cpfPessoa, codigoEvento, false));
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/convidado/{cpfPessoa}/item/{nomeItem}")
    public boolean confirmarItem(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigoEvento, 
        @PathVariable String cpfPessoa, 
        @PathVariable String nomeItem
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("default")) {
            Item item = new ItemEventoController().CarregarItem(nomeItem);
            if (item != null) {
                resultado = new ConvidadosDAO()
                        .update_item(new Convidado(item.getCodigoItem(), cpfPessoa, codigoEvento, false));
            }
        }
        return resultado;
    }
}