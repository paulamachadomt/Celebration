package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.dao.ItemDAO;
import ads.db.projetofinal.projetofinal.dao.ItemEventoDAO;
import ads.db.projetofinal.projetofinal.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.ItemEvento;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class EventoController {

    private void log(String textLog) {
        System.out.println("-------------------------------------------------------\n" + textLog);
    }

    private Integer cadastrarEvento(Evento evento) {
        Integer codigoEvento = -1;
        try {
            codigoEvento = new EventoDAO().create(evento);
            if (codigoEvento >= 1) {
                log("SUCCESS: " + "\nSucesso ao cadastrar novo evento " + evento.toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar novo evento. Problema ao resgatar código.");
            }

        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar novo evento.");
        }
        return codigoEvento;
    }

    private boolean atualizarEvento(Evento evento) {
        boolean ressultado = false;
        try {
            ressultado = new EventoDAO().update(evento);
            if (ressultado) {
                log("SUCCESS: " + "\nSucesso ao atualizar o evento " + evento.toString());
            } else {
                log("ERROR: " + "\nErro ao atualizar o evento.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro  ao atualizar o evento.");
        }
        return ressultado;
    }

    private Evento carregarEvento(Integer codigoEvento) {
        Evento evento = null;
        try {
            evento = new EventoDAO().read(codigoEvento);
            if (evento.getCodigo() >= 1) {
                log("SUCCESS: " + "\nSucesso ao carregar evento " + evento.toString());
            } else {
                log("ERROR: " + "\nErro ao carregar evento. Problema com o código.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao carregar evento.");
        }
        return evento;
    }

    private List<Convidado> carregarRegistroConvidado(Integer codigoEvento) {
        List<Convidado> registroConvidados = new ArrayList<>();
        try {
            registroConvidados = new ConvidadosDAO().readAll(codigoEvento);
            if (!registroConvidados.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao carregar registros convidados " + registroConvidados.toString());
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao carregar registros convidados.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar registros convidados.");
        }
        return registroConvidados;
    }

    private List<Pessoa> carregarConvidado(Integer codigoEvento) {
        List<Pessoa> convidados = new ArrayList<>();
        try {
            List<Convidado> registroConvidados = carregarRegistroConvidado(codigoEvento);
            for (Convidado convidado : registroConvidados) {
                convidados.add(new PessoaDAO().read(convidado.getCpfPessoa()));
            }
            if (!convidados.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao carregar convidados " + convidados.toString());
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao carregar convidados.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar convidados.");
        }
        return convidados;
    }

    private List<ItemEvento> carregarRegistroItens(Integer codigoEvento) {
        List<ItemEvento> registroItens = new ArrayList<>();
        try {
            registroItens = new ItemEventoDAO().readAll(codigoEvento);
            if (!registroItens.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao registros carregar itens " + registroItens.toString());
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao registros carregar itens.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao registros carregar itens.");
        }
        return registroItens;
    }

    private List<Item> carregarItens(Integer codigoEvento) {
        List<Item> itens = new ArrayList<>();
        try {
            List<ItemEvento> registroItens = carregarRegistroItens(codigoEvento);
            for (ItemEvento item : registroItens) {
                itens.add(new ItemDAO().read(item.getCodigoItem()));
            }
            if (!itens.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao carregar itens " + itens.toString());
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao carregar itens.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar itens.");
        }
        return itens;
    }

    private boolean cadastrarConvidadoDono(Convidado convidado) {
        boolean ressultado = false;
        try {
            ressultado = new ConvidadosDAO().create(convidado);
            if (ressultado) {
                log("SUCCESS: " + "\nSucesso ao cadastrar o dono do evento " + convidado.toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar o dono do evento.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar o dono do evento.");
        }
        return ressultado;
    }

    // JSON example : EVENTO : {"local":"São José","data":"2020-10-10","nome":"Festa
    // do Kobrasol"}
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento")
    public Evento createEvento(@CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @RequestBody Evento evento) {

        if (!cpf.equalsIgnoreCase("default")) {
            Integer codigoEvento = cadastrarEvento(evento); // auto_increment
            evento.setCodigo(codigoEvento);
            boolean resultadoCriadorEvento = cadastrarConvidadoDono(new Convidado(true, cpf, evento.getCodigo(), true));
            System.out.println(resultadoCriadorEvento);
        }

        return evento;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/evento/{codigo}")
    public Evento readEvento(@CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @PathVariable Integer codigo) {

        Evento evento = null;

        if (!cpf.equalsIgnoreCase("default")) {
            evento = carregarEvento(codigo);
            if (evento != null) {
                evento.setConvidados(carregarConvidado(codigo));
                evento.setItens(carregarItens(codigo));
            }
        }
        return evento;
    }

    // JSON example : EVENTO : {"codigo":"1","local":"São
    // José","data":"2020-10-10","nome":"Festa do Kobrasol","descricao":"Festa pósdemia"}
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigo}/")
    public String updateEvento(@CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @RequestBody Evento evento, @PathVariable Integer codigo) {

        if (!cpf.equalsIgnoreCase("default")) {
            boolean resultado = atualizarEvento(evento);
            System.out.println(resultado);

        }
        // atualzia o evento por completo na base // nome, local, data, descrição,
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/evento/{codigo}/delete")
    public String deleteEvento(@CookieValue(value = "cpf", defaultValue = "default") String cpf,
            @PathVariable Integer codigo) {

        boolean resultado = false;

        Evento evento = carregarEvento(codigo);
        if (evento != null) {
            evento.setRegistroConvidados(carregarRegistroConvidado(codigo));
            evento.setRegistroItens(carregarRegistroItens(codigo));
        } else {
            evento = new Evento();
            evento.setRegistroConvidados(new ArrayList<>());
            evento.setRegistroItens(new ArrayList<>());
        }
        for (Convidado registroConvidado : evento.getRegistroConvidados()) {
            new ConvidadosDAO().delete(registroConvidado.getCodigoEvento(), registroConvidado.getCpfPessoa());
        }
        for (ItemEvento registroItem : evento.getRegistroItens()) {
            new ItemEventoDAO().delete(registroItem.getCodigoEvento(), registroItem.getCodigoItem());
        }

        resultado = new EventoDAO().delete(codigo);

        return "" + resultado;

    }
}