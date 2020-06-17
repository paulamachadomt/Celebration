package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import ads.db.projetofinal.projetofinal.dao.EventoDAO;
import ads.db.projetofinal.projetofinal.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Pessoa;
import ads.db.projetofinal.projetofinal.dao.ItemDAO;
import ads.db.projetofinal.projetofinal.dao.ItemEventoDAO;
import ads.db.projetofinal.projetofinal.model.Item;
import ads.db.projetofinal.projetofinal.model.ItemEvento;

public class UtilEvento {

    static List<Convidado> eteventosConvidado(Pessoa pessoa) {
        return new ConvidadosDAO().read(pessoa.getCpf());
    }

    static Integer cadastrarEvento(Evento evento) {
        Integer codigoEvento = -1;
        try {
            codigoEvento = new EventoDAO().create(evento);
            if (codigoEvento >= 1) {
                Util.log("SUCCESS: " + "\nSucesso ao cadastrar novo evento " + evento.toString());
            } else {
                Util.log("ERROR: " + "\nErro ao cadastrar novo evento. Problema ao resgatar código.");
            }

        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro ao cadastrar novo evento.");
        }
        return codigoEvento;
    }

    static boolean atualizarEvento(Evento evento) {
        boolean ressultado = false;
        try {
            ressultado = new EventoDAO().update(evento);
            if (ressultado) {
                Util.log("SUCCESS: " + "\nSucesso ao atualizar o evento " + evento.toString());
            } else {
                Util.log("ERROR: " + "\nErro ao atualizar o evento.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro  ao atualizar o evento.");
        }
        return ressultado;
    }

    static Evento carregarEvento(Integer codigoEvento) {
        Evento evento = null;
        try {
            evento = new EventoDAO().read(codigoEvento);
            if (evento.getCodigo() >= 1) {
                Util.log("SUCCESS: " + "\nSucesso ao carregar evento " + evento.toString());
            } else {
                Util.log("ERROR: " + "\nErro ao carregar evento. Problema com o código.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro ao carregar evento.");
        }
        return evento;
    }

    static List<Convidado> carregarRegistroConvidado(Integer codigoEvento) {
        List<Convidado> registroConvidados = new ArrayList<>();
        try {
            registroConvidados = new ConvidadosDAO().readAll(codigoEvento);
            if (!registroConvidados.isEmpty()) {
                Util.log("SUCCESS: " + "\nSucesso ao carregar registros convidados " + registroConvidados.toString());
            } else {
                Util.log("ERROR: " + "\nLista vazia, ou Erro ao carregar registros convidados.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar registros convidados.");
        }
        return registroConvidados;
    }

    static List<Pessoa> carregarConvidado(Integer codigoEvento) {
        List<Pessoa> convidados = new ArrayList<>();
        try {
            List<Convidado> registroConvidados = carregarRegistroConvidado(codigoEvento);
            for (Convidado convidado : registroConvidados) {
                convidados.add(new PessoaDAO().read(convidado.getCpfPessoa()));
            }
            if (!convidados.isEmpty()) {
                Util.log("SUCCESS: " + "\nSucesso ao carregar convidados " + convidados.toString());
            } else {
                Util.log("ERROR: " + "\nLista vazia, ou Erro ao carregar convidados.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar convidados.");
        }
        return convidados;
    }

    static List<ItemEvento> carregarRegistroItens(Integer codigoEvento) {
        List<ItemEvento> registroItens = new ArrayList<>();
        try {
            registroItens = new ItemEventoDAO().readAll(codigoEvento);
            if (!registroItens.isEmpty()) {
                Util.log("SUCCESS: " + "\nSucesso ao registros carregar itens " + registroItens.toString());
            } else {
                Util.log("ERROR: " + "\nLista vazia, ou Erro ao registros carregar itens.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nLista vazia, ou Erro ao registros carregar itens.");
        }
        return registroItens;
    }

    static List<Item> carregarItens(Integer codigoEvento) {
        List<Item> itens = new ArrayList<>();
        try {
            List<ItemEvento> registroItens = carregarRegistroItens(codigoEvento);
            for (ItemEvento item : registroItens) {
                itens.add(new ItemDAO().read(item.getCodigoItem()));
            }
            if (!itens.isEmpty()) {
                Util.log("SUCCESS: " + "\nSucesso ao carregar itens " + itens.toString());
            } else {
                Util.log("ERROR: " + "\nLista vazia, ou Erro ao carregar itens.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nLista vazia, ou Erro ao carregar itens.");
        }
        return itens;
    }

    static boolean cadastrarConvidadoDono(Convidado convidado) {
        boolean ressultado = false;
        try {
            ressultado = new ConvidadosDAO().create(convidado);
            if (ressultado) {
                Util.log("SUCCESS: " + "\nSucesso ao cadastrar o dono do evento " + convidado.toString());
            } else {
                Util.log("ERROR: " + "\nErro ao cadastrar o dono do evento.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro ao cadastrar o dono do evento.");
        }
        return ressultado;
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

    static boolean cadastrarItemEvento(ItemEvento itemEvento) {
        boolean resultado = false;
        try {
            resultado = new ItemEventoDAO().create(itemEvento);
            if (resultado) {
                Util.log("SUCCESS: " + "\nSucesso ao cadastrar novo item ao evento" + itemEvento.toString());
            } else {
                Util.log("ERROR: " + "\nErro ao cadastrar novo item ao evento. Problema ao resgatar por código.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro ao cadastrar novo item evento.");
        }
        return resultado;
    }

    static Item cadastrarItem(String nomeItem) {
        Item item = null;
        try {
            int codigoItem = new ItemDAO().create_getCodigo(nomeItem);
            if (codigoItem >= 1) {
                item =  new Item(codigoItem, nomeItem);
                Util.log("SUCCESS: " + "\nSucesso ao cadastrar novo item " + new Item(codigoItem, nomeItem).toString());
            } else {
                Util.log("ERROR: " + "\nErro ao cadastrar novo item. Problema ao resgatar código.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro ao cadastrar novo item.");
        }
        return item;
    }

    static Item CarregarItem(String nomeItem) {
        Item item = null;
        try {
            item = new ItemDAO().read(nomeItem);
            if (item.getCodigoItem() >= 1) {
                Util.log("SUCCESS: " + "\nSucesso ao carregar evento " + item.toString());
            } else {
                Util.log("ERROR: " + "\nErro ao carregar item. Problema com o nome.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro ao carregar item.");
        }
        return item;
    }

    static Item CarregarItem(Integer codigoItem) {
        Item item = null;
        try {
            item = new ItemDAO().read(codigoItem);
            if (item.getCodigoItem() >= 1) {
                Util.log("SUCCESS: " + "\nSucesso ao carregar item " + item.toString());
            } else {
                Util.log("ERROR: " + "\nErro ao carregar item. Problema com o código.");
            }
        } catch (Exception e) {
            Util.log("ERROR: " + e + "\nErro ao carregar item.");
        }
        return item;
    }


}