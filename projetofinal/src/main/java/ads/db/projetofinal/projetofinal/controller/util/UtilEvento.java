package ads.db.projetofinal.projetofinal.controller.util;

import java.util.ArrayList;
import java.util.List;

import ads.db.projetofinal.projetofinal.model.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.model.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.model.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.dao.ResponseDAO;
import ads.db.projetofinal.projetofinal.model.dao.ItemDAO;
import ads.db.projetofinal.projetofinal.model.dao.ItemEventoDAO;
import ads.db.projetofinal.projetofinal.model.entity.Evento;
import ads.db.projetofinal.projetofinal.model.entity.Convidado;
import ads.db.projetofinal.projetofinal.model.entity.Pessoa;
import ads.db.projetofinal.projetofinal.model.ResponseConvidado;
import ads.db.projetofinal.projetofinal.model.entity.Item;
import ads.db.projetofinal.projetofinal.model.entity.ItemEvento;

public class UtilEvento extends Util {

    protected boolean autenticaDonoEvento(Convidado convidado) {
        boolean resultado = false;
        return resultado;
    }

    protected Integer cadastrarEvento_setupCodigo(Evento evento) {
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

    protected boolean atualizarEvento(Evento evento) {
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

    protected List<Convidado> carregaRegistroEventosConvidado(Pessoa pessoa) {
        List<Convidado> eventosConvidado = new ArrayList<>();
        try {
            eventosConvidado = new ConvidadosDAO().read(pessoa.getCpf());
            if (!eventosConvidado.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao carregar registro de eventos convidado" + eventosConvidado);
            } else {
                log("ERROR: " + "\nErro ao carregar registro de eventos convidado.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao carregar registro de eventos convidado.");
        }
        return eventosConvidado;
    }

    protected Evento carregarEvento(Integer codigoEvento) {
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

    protected List<Convidado> carregarRegistroConvidado(Integer codigoEvento) {
        List<Convidado> registroConvidados = new ArrayList<>();
        try {
            registroConvidados = new ConvidadosDAO().readAll(codigoEvento);
            if (!registroConvidados.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao carregar registros convidados " + registroConvidados);
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao carregar registros convidados.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar registros convidados.");
        }
        return registroConvidados;
    }

    protected List<Pessoa> carregarConvidado(Integer codigoEvento) {
        List<Pessoa> convidados = new ArrayList<>();
        try {
            List<Convidado> registroConvidados = carregarRegistroConvidado(codigoEvento);
            for (Convidado convidado : registroConvidados) {
                convidados.add(new PessoaDAO().read(convidado.getCpfPessoa()));
            }
            if (!convidados.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao carregar convidados " + convidados);
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao carregar convidados.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar convidados.");
        }
        return convidados;
    }

    protected List<ResponseConvidado> carregarResponseConvidado(Integer codigoEvento) {
        List<ResponseConvidado> response = new ArrayList<>();
        try {
            List<Convidado> registroConvidados = carregarRegistroConvidado(codigoEvento);
            for (Convidado registroConvidado : registroConvidados) {
                response.add(new ResponseDAO().read(registroConvidado.getCpfPessoa(), codigoEvento));
            }
            if (!response.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao carregar convidados " + response);
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao carregar convidados.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar convidados.");
        }
        return response;
    }

    protected  List<ItemEvento> carregarRegistroItens(Integer codigoEvento) {
        List<ItemEvento> registroItens = new ArrayList<>();
        try {
            registroItens = new ItemEventoDAO().readAll(codigoEvento);
            if (!registroItens.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao registros carregar itens " + registroItens);
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao registros carregar itens.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao registros carregar itens.");
        }
        return registroItens;
    }

    protected List<Item> carregarItens(Integer codigoEvento) {
        List<Item> itens = new ArrayList<>();
        try {
            List<ItemEvento> registroItens = carregarRegistroItens(codigoEvento);
            for (ItemEvento item : registroItens) {
                itens.add(new ItemDAO().read(item.getCodigoItem()));
            }
            if (!itens.isEmpty()) {
                log("SUCCESS: " + "\nSucesso ao carregar itens " + itens);
            } else {
                log("ERROR: " + "\nLista vazia, ou Erro ao carregar itens.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar itens.");
        }
        return itens;
    }

    protected boolean cadastrarConvidado(Convidado convidado) {
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

    protected boolean atualizarConvidadoConfirmacao(Convidado convidado) {
        boolean resultado = false;
        try {
            resultado = new ConvidadosDAO().update_confirmacao(convidado);
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao atualizar convidado " + convidado.toString());
            } else {
                log("ERROR: " + "\nErro ao atualizar convidado.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao atualizar convidado.");
        }
        return resultado;
    }

    protected boolean atualizarConvidadoItem(Convidado convidado) {
        boolean resultado = false;
        try {
            resultado = new ConvidadosDAO().update_item(convidado);
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao atualizar convidado " + convidado.toString());
            } else {
                log("ERROR: " + "\nErro ao atualizar convidado.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao atualizar convidado.");
        }
        return resultado;
    }

    protected boolean cadastrarPessoa(Pessoa pessoa) {
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

    protected Pessoa carregarPessoa(String cpf) {
        Pessoa pessoa = null;
        try {
            pessoa = new PessoaDAO().read(cpf);
            if (pessoa != null) {
                log("SUCCESS: " + "\nSucesso ao carregar pessoa " + pessoa.toString());
            } else {
                log("ERROR: " + "\nErro ao carregar pessoa. Nome errado.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao carregar pessoa.");
        }
        return pessoa;
    }

    protected boolean cadastrarItemEvento(ItemEvento itemEvento) {
        boolean resultado = false;
        try {
            resultado = new ItemEventoDAO().create(itemEvento);
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao cadastrar novo item ao evento" + itemEvento.toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar novo item ao evento. Problema ao resgatar por código.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar novo item evento.");
        }
        return resultado;
    }

    protected Item cadastrarItem(String nomeItem) {
        Item item = null;
        try {
            int codigoItem = new ItemDAO().create_getCodigo(nomeItem);
            if (codigoItem >= 1) {
                item = new Item(codigoItem, nomeItem);
                log("SUCCESS: " + "\nSucesso ao cadastrar novo item " + new Item(codigoItem, nomeItem).toString());
            } else {
                log("ERROR: " + "\nErro ao cadastrar novo item. Problema ao resgatar código.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao cadastrar novo item.");
        }
        return item;
    }

    protected Item CarregarItem(String nomeItem) {
        Item item = null;
        try {
            item = new ItemDAO().read(nomeItem);
            if (item.getCodigoItem() >= 1) {
                log("SUCCESS: " + "\nSucesso ao carregar evento " + item.toString());
            } else {
                log("ERROR: " + "\nErro ao carregar item. Problema com o nome.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao carregar item.");
        }
        return item;
    }

    protected Item CarregarItem(Integer codigoItem) {
        Item item = null;
        try {
            item = new ItemDAO().read(codigoItem);
            if (item.getCodigoItem() >= 1) {
                log("SUCCESS: " + "\nSucesso ao carregar item " + item.toString());
            } else {
                log("ERROR: " + "\nErro ao carregar item. Problema com o código.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao carregar item.");
        }
        return item;
    }

    protected boolean deletarRegistroConvidado(Convidado convidado) {
        boolean resultado = false;
        try {
            resultado = new ConvidadosDAO().delete(convidado.getCodigoEvento(), convidado.getCpfPessoa());
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao remover convidado " + convidado.toString());
            } else {
                log("ERROR: " + "\nErro ao remover convidado. Problema com os códigos.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao remover convidado.");
        }
        return resultado;
    }

    protected boolean deletarRegistroItem(ItemEvento itemEvento) {
        boolean resultado = false;
        try {
            resultado = new ItemEventoDAO().delete(itemEvento.getCodigoEvento(), itemEvento.getCodigoItem());
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao remover item " + itemEvento.toString());
            } else {
                log("ERROR: " + "\nErro ao remover item. Problema com os códigos.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao remover item.");
        }
        return resultado;
    }

    protected boolean deletarEvento(Integer codigoEvento) {
        boolean resultado = false;
        try {
            resultado = new EventoDAO().delete(codigoEvento);
            if (resultado) {
                log("SUCCESS: " + "\nSucesso ao excluir evento" + codigoEvento.toString());
            } else {
                log("ERROR: " + "\nErro ao excluir evento. Problema com o código.");
            }
        } catch (Exception e) {
            log("ERROR: " + e + "\nErro ao excluir evento.");
        }
        return resultado;
    }
    
    /**
     *
     * 
     *  
     */// Ações dos controllers abaixo

     protected List<Evento> carregaEventosConvidado(Pessoa pessoa) {
        List<Evento> eventos = new ArrayList<>();
        List<Convidado> eventosConvidado = carregaRegistroEventosConvidado(pessoa);
        for (Convidado eventoConvidados : eventosConvidado) {
            eventos.add(carregarEvento(eventoConvidados.getCodigoEvento()));
        }
        return eventos;
    }

    @SuppressWarnings(value="all") // para remover o aviso de valor boolean não utilizado -> 'boolean resultadoCriadorEvento'
    protected Evento cadastrarEvento(Evento preEvento, String cpf) {
        Integer codigoEvento = cadastrarEvento_setupCodigo(preEvento);
        preEvento.setCodigo(codigoEvento); // auto_increment
        boolean resultadoCriadorEvento = cadastrarConvidado(new Convidado(true, cpf, preEvento.getCodigo(), true));
        return preEvento;
    }

    protected Convidado verificaECarregaConvidadoDoEvento(Evento evento, String cpf_cookie) {
        Convidado convidado = null;
        if (evento != null) {
            List<Convidado> registroConvidados = carregarRegistroConvidado(evento.getCodigo());
            for (Convidado convidadoRegistrado : registroConvidados) {
                if (cpf_cookie.equalsIgnoreCase(convidadoRegistrado.getCpfPessoa())) {
                    convidado = convidadoRegistrado;
                    break;
                }
            }
        }
        return convidado;
    }
    protected Boolean verificaEDeletaEventoERegistros(Integer codigoEvento){
        Boolean resultado = false;
        Evento evento = carregarEvento(codigoEvento);
        List<Convidado> convidados = null;
        List<ItemEvento> itens = null;
        if (evento != null) {
            convidados = carregarRegistroConvidado(codigoEvento);
            itens = carregarRegistroItens(codigoEvento);
        } else {
            evento = new Evento();
            convidados = new ArrayList<>();
            itens = new ArrayList<>();
        }
        for (Convidado registroConvidado : convidados) {
            deletarRegistroConvidado(registroConvidado);
        }
        for (ItemEvento registroItem : itens) {
            deletarRegistroItem(registroItem);
        }
        resultado = deletarEvento(codigoEvento);
        return resultado;
    }

    protected List<Item> carregaItensDoEvento(Integer codigoEvento){
        List<Item> response = new ArrayList<>();
        List<ItemEvento> registroItens = carregarRegistroItens(codigoEvento);
        for (ItemEvento registroItem : registroItens) {
            response.add(CarregarItem(registroItem.getCodigoItem()));
        }
        return response;
    }

    protected Item verificaOuCadastraItem(String nomeItem){
        Item item = CarregarItem(nomeItem);
        if (item == null) {
            item = cadastrarItem(nomeItem);
        }
        return item;
    }

    protected Boolean verificaRegistroDoItemNoEvento(Item item, Integer codigoEvento){
        Boolean resultado = false;
        ItemEvento itemEvento = new ItemEvento(codigoEvento, item.getCodigoItem());
        List<ItemEvento> registroItemEvento = carregarRegistroItens(codigoEvento);
        if (registroItemEvento.contains(itemEvento)) {
            resultado = true;
        }
        return resultado;
    }

    protected Convidado novoConvidado(String cpfConvidado, Integer codigoEvento){
        boolean convidadoCriador = false;
        boolean convidadoConfirmacao = false;
        return new Convidado(convidadoConfirmacao, cpfConvidado, codigoEvento, convidadoCriador);
    }

    protected Boolean verificaDadosConvidado(String cpfConvidado){
        boolean resultado = false;
        Pessoa dadosNovoConvidado = carregarPessoa(cpfConvidado);
        if (dadosNovoConvidado != null) {
            resultado = true;
        }
        return resultado;
    }

    protected Boolean verificaRegistroDoConvidadoNoEvento(Convidado convidado, Integer codigoEvento){
        Boolean resultado = false;
        List<Convidado> convidadosEvento = carregarRegistroConvidado(codigoEvento);  
        if (convidadosEvento.contains(convidado)) {
            resultado = true;
        }
        return resultado;
    }
}