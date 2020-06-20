package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.ItemEvento;
import ads.db.projetofinal.projetofinal.model.Pessoa;
import ads.db.projetofinal.projetofinal.model.ResponseConvidado;

@RestController
public class ConvidadosController extends UtilEvento{
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/eventos/{codigoEvento}/convidados")
    public List<ResponseConvidado> creatConvidado(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento
            ) {
        List<ResponseConvidado> response = new ArrayList<>();
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigoEvento)) {
            if (!criador_evento.equalsIgnoreCase("null")) {
                response = carregarResponseConvidado(codigoEvento);
            }
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/eventos/{codigoEvento}/convidados")
    public boolean creatConvidado(
        @CookieValue(value = "cpf", defaultValue = "null") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento, 
        @RequestParam String cpfConvidado, // by form
        @RequestParam String nomeConvidado // by form
            ) {
        Pessoa pessoa = new Pessoa(cpfConvidado, nomeConvidado);
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigoEvento)) {
            if (criador_evento.equalsIgnoreCase("true")) {
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
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", value = "/eventos/{codigoEvento}/convidados/{cpfPessoa}/confirmar/{confirmacao}")
    public boolean confirmarEvento(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento, 
        @PathVariable String cpfPessoa, 
        @PathVariable Boolean confirmacao
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigoEvento)) {
            if (cpf.equalsIgnoreCase(cpfPessoa) && criador_evento.equalsIgnoreCase("false")) {
                    Convidado confirmarConvidado = new Convidado(confirmacao, cpfPessoa, codigoEvento, Boolean.parseBoolean(criador_evento));
                    List<Convidado> convidadosEvento = carregarRegistroConvidado(codigoEvento);
                    if (convidadosEvento.contains(confirmarConvidado)){
                        resultado = atualizarConvidadoConfirmacao(confirmarConvidado); 
                }
            }
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", value = "/eventos/{codigoEvento}/convidados/{cpfPessoa}/itens/{nomeItem}")
    public boolean confirmarItem(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento, 
        @PathVariable String cpfPessoa, 
        @PathVariable String nomeItem
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigoEvento)) {
            if (!criador_evento.equalsIgnoreCase("null")) {
                Item item = CarregarItem(nomeItem);
                if (item != null) {
                    ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());
                    List<ItemEvento> itensEvento = carregarRegistroItens(codigoEvento);
                    if (itensEvento.contains(itemEvento)) {
                        resultado = atualizarConvidadoItem(new Convidado(item.getCodigoItem(), cpfPessoa, codigoEvento, Boolean.parseBoolean(criador_evento)));
                    }                
                }
            }
        }
        return resultado;
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/eventos/{codigoEvento}/convidados/{cpfPessoa}")
    public boolean deleteConvidado(
        @CookieValue(value = "cpf", defaultValue = "default") String cpf,
        @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
        @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
        @PathVariable Integer codigoEvento, 
        @PathVariable String cpfPessoa
            ) {
        boolean resultado = false;
        if (!cpf.equalsIgnoreCase("null") && codigo_evento.equalsIgnoreCase(""+codigoEvento)) {
            if (criador_evento.equalsIgnoreCase("true")) {
                if(!cpf.equalsIgnoreCase(cpfPessoa)){
                    Convidado convidadoDeletar = new Convidado(false, cpfPessoa, codigoEvento, false);
                    List<Convidado> convidadosEvento = carregarRegistroConvidado(codigoEvento);
                    if (convidadosEvento.contains(convidadoDeletar)){
                        resultado = deletarRegistroConvidado(convidadoDeletar);
                    }
                }
            }
        }
        return resultado;
    }
}