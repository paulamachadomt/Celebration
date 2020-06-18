package ads.db.projetofinal.projetofinal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.ItemEvento;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class ConvidadosController extends UtilEvento{

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/convidado")
    public boolean creatConvidado(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigoEvento, 
        @RequestBody Pessoa pessoa
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("default")) {
            Convidado novoConvidado = new Convidado(false, pessoa.getCpf(), codigoEvento, false);
            Pessoa dadosNovoConvidado = carregarPessoa(pessoa.getCpf());
            if (dadosNovoConvidado != null) {
                List<Convidado> convidadosEvento = carregarRegistroConvidado(codigoEvento);
                if (!convidadosEvento.contains(novoConvidado)) {
                    resultado = cadastrarConvidado(novoConvidado);
                }
            } else {
                boolean resultadoCadastroPessoa = cadastrarPessoa(pessoa);
                if (resultadoCadastroPessoa) {
                    resultado = cadastrarConvidado(novoConvidado);
                }
            }
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigoEvento}/convidado/{cpfPessoa}/remover")
    public boolean deleteConvidado(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @PathVariable Integer codigoEvento, 
        @PathVariable String cpfPessoa
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("default")) {
            if(!cpf.equalsIgnoreCase(cpfPessoa)){
                Convidado convidadoDeletar = new Convidado(false, cpfPessoa, codigoEvento, false);
                List<Convidado> convidadosEvento = carregarRegistroConvidado(codigoEvento);
                if (convidadosEvento.contains(convidadoDeletar)){
                    resultado = deletarRegistroConvidado(convidadoDeletar);
                }
            }
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
            if(!cpf.equalsIgnoreCase(cpfPessoa)){
                Convidado confirmarConvidado = new Convidado(confirmacao, cpfPessoa, codigoEvento, false);
                List<Convidado> convidadosEvento = carregarRegistroConvidado(codigoEvento);
                if (convidadosEvento.contains(confirmarConvidado)){
                    resultado = atualizarConvidado(confirmarConvidado); 
                }
            }
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
            Item item = CarregarItem(nomeItem);
            if (item != null) {
                ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());
                List<ItemEvento> itensEvento = carregarRegistroItens(codigoEvento);
                if (itensEvento.contains(itemEvento)) {
                    resultado = new ConvidadosDAO()
                    .update_item(new Convidado(item.getCodigoItem(), cpfPessoa, codigoEvento, false));
                }                
            }
        }
        return resultado;
    }
}