package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.controller.util.UtilCheck;
import ads.db.projetofinal.projetofinal.controller.util.UtilEvento;
import ads.db.projetofinal.projetofinal.model.entity.Convidado;
import ads.db.projetofinal.projetofinal.model.entity.Item;
import ads.db.projetofinal.projetofinal.model.entity.Pessoa;
import ads.db.projetofinal.projetofinal.model.ResponseConvidado;

@RestController
public class ConvidadosController extends UtilEvento {
    
    @RequestMapping(
        method = RequestMethod.GET, 
        produces = "application/json", 
        value = "/eventos/{codigoEvento}/convidados"
        )
    public List<ResponseConvidado> doGet(
            @CookieValue(value = "cpf", defaultValue = "null") String cpf,
            @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
            @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
            @PathVariable Integer codigoEvento
                ) {
                List<ResponseConvidado> response = new ArrayList<>();
                if (UtilCheck.loginIsAuthenticated(cpf) 
                &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
                &&  UtilCheck.convidadoCriadorIsAuthenticated(criador_evento)
                    ) {
                        response = carregarResponseConvidado(codigoEvento);
                    }
                return response;
            }

    @RequestMapping(
        method = RequestMethod.POST, 
        produces = "application/json", 
        value = "/eventos/{codigoEvento}/convidados"
        )
    public boolean doPost(
            @CookieValue(value = "cpf", defaultValue = "null") String cpf,
            @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
            @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
            @PathVariable Integer codigoEvento, 
            @RequestBody Pessoa novaPessoaConvidada
            // @RequestParam String cpfConvidado, // by form
            // @RequestParam String nomeConvidado // by form
                ) {
                // Pessoa pessoa = new Pessoa(cpfConvidado, nomeConvidado);
                boolean resultado = false;
                if (UtilCheck.loginIsAuthenticated(cpf) 
                &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
                &&  UtilCheck.criadorEventoIsAuthenticated(criador_evento)
                    ) {
                        Convidado novoRegistroConvidado = novoConvidado(novaPessoaConvidada.getCpf(), codigoEvento);
                        if (verificaDadosConvidado(novaPessoaConvidada.getCpf())) {
                            if (!verificaRegistroDoConvidadoNoEvento(novoRegistroConvidado, codigoEvento)) {
                                resultado = cadastrarConvidado(novoRegistroConvidado);
                            }
                        } else {
                            if (cadastrarPessoa(novaPessoaConvidada)) {
                                resultado = cadastrarConvidado(novoRegistroConvidado);
                            }
                        } 
                    }
                return resultado;
            }

    @RequestMapping(
        method = RequestMethod.PUT, 
        produces = "application/json", 
        value = "/eventos/{codigoEvento}/convidados/{cpfPessoa}/confirmar/{confirmacao}"
        )
    public boolean doPut(
            @CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
            @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
            @PathVariable Integer codigoEvento, 
            @PathVariable String cpfPessoa, 
            @PathVariable Boolean confirmacao
                ) {
                boolean resultado = false;
                if (UtilCheck.loginIsAuthenticated(cpf) 
                &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
                &&  UtilCheck.cpfIsValid(cpf, cpfPessoa)
                &&  UtilCheck.convidadoIsAuthenticate(criador_evento)
                    ) {
                        Convidado confirmarConvidado = new Convidado(confirmacao, cpfPessoa, codigoEvento, Boolean.parseBoolean(criador_evento));
                        if (verificaRegistroDoConvidadoNoEvento(confirmarConvidado, codigoEvento)){
                            resultado = atualizarConvidadoConfirmacao(confirmarConvidado); 
                        }
                    }
                return resultado;
            }

    @RequestMapping(
        method = RequestMethod.PUT, 
        produces = "application/json", 
        value = "/eventos/{codigoEvento}/convidados/{cpfPessoa}/itens/{nomeItem}"
        )
    public boolean doPut(
            @CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
            @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
            @PathVariable Integer codigoEvento, 
            @PathVariable String cpfPessoa, 
            @PathVariable String nomeItem
                ) {
                boolean resultado = false;
                if (UtilCheck.loginIsAuthenticated(cpf) 
                &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
                &&  UtilCheck.convidadoCriadorIsAuthenticated(criador_evento)
                    ) {
                        Item item = CarregarItem(nomeItem);
                        if (item != null) {
                            if (verificaRegistroDoItemNoEvento(item, codigoEvento)) {
                                Convidado itemConvidado = new Convidado(item.getCodigoItem(), cpfPessoa, codigoEvento, Boolean.parseBoolean(criador_evento));
                                resultado = atualizarConvidadoItem(itemConvidado);
                            }                
                        }
                    }
                return resultado;
            }

    @RequestMapping(
        method = RequestMethod.DELETE, 
        produces = "application/json", 
        value = "/eventos/{codigoEvento}/convidados/{cpfConvidado}"
        )
    public boolean doDelete(
            @CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @CookieValue(value = "codigo_evento", defaultValue = "null") String codigo_evento,
            @CookieValue(value = "criador_evento", defaultValue = "null") String criador_evento,
            @PathVariable Integer codigoEvento, 
            @PathVariable String cpfConvidado
                ) {
                boolean resultado = false;
                if (UtilCheck.loginIsAuthenticated(cpf) 
                &&  UtilCheck.codigoEventoIsAuthenticated(codigo_evento, codigoEvento)
                &&  UtilCheck.criadorEventoIsAuthenticated(criador_evento)
                &&  !UtilCheck.cpfIsValid(cpf, cpfConvidado)
                    ) {
                        Convidado convidadoDeletar = novoConvidado(cpfConvidado, codigoEvento);
                        if (verificaRegistroDoConvidadoNoEvento(convidadoDeletar, codigoEvento)){
                            resultado = deletarRegistroConvidado(convidadoDeletar);
                        }
                    }
                return resultado;
            }
}