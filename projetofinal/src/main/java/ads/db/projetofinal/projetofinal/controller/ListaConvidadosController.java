package ads.db.projetofinal.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.ListaConvidadosDAO;
import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.ListaConvidados;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class ListaConvidadosController {

    ArrayList<ListaConvidados> listaConvidados;

    @GetMapping("/cadastroConvidadosEvento")
    public String doGet(String nomePessoa, String cpfPessoa, Integer codigoEvento) {

        // Carrega lista de convidados
        listaConvidados = new ListaConvidadosDAO().selectListaConvidados();
        System.out.println(listaConvidados.toString());

        boolean resultado = false;

        Pessoa convidado = new PessoaDAO().selectCPFPessoa(cpfPessoa);
        if (convidado == null) {
            String cadastroPessoa = new PessoaController().doGet(nomePessoa, cpfPessoa);
            System.out.println("Resultado do cadastro: " + cadastroPessoa);
            resultado = new ListaConvidadosDAO()
                    .cadastrarListaConvidados(new ListaConvidados(false, cpfPessoa, codigoEvento));
        } else {
            boolean isPresent_event = false;
            for (ListaConvidados convidadoConfima : listaConvidados) {
                if (convidado.getCpf().equals(cpfPessoa)) {
                    isPresent_event = true;
                }
            }
            if (isPresent_event == false) {
                resultado = new ListaConvidadosDAO()
                        .cadastrarListaConvidados(new ListaConvidados(false, cpfPessoa, codigoEvento));
            }
        }
        return "" + resultado;
    }

    @GetMapping("/confirmarPresenca")
    public String doGet(boolean confirmacao, String cpfPessoa, Integer codigoEvento) {

        boolean resultado = false;

        resultado= new ListaConvidadosDAO().updadeListaConvidadosConfirmacao(new ListaConvidados(confirmacao, cpfPessoa, codigoEvento));

        return "Confirmada a presença ? : " + resultado;
    }

    @GetMapping("/confirmarComesBebes")
    public String doGet(Integer codigoComesBebes, String cpfPessoa, Integer codigoEvento) {

        boolean resultado = false;

        resultado = new ListaConvidadosDAO().updadeListaConvidadosComesBebes(new ListaConvidados(codigoComesBebes, cpfPessoa, codigoEvento));

        return "Confirmou que levará o produto : " + codigoComesBebes;
    }

    @GetMapping("/listaConvidados")
    public String listaConvidados(Integer codigoEvento) {

        ArrayList<Pessoa> listaConvidados = new ArrayList<>();

        ArrayList<ListaConvidados> listaConvidadosEvento = new ListaConvidadosDAO().selectListaConvidados();

        for (ListaConvidados convidadosEvento : listaConvidadosEvento) {
            listaConvidados.add(new PessoaDAO().selectCPFPessoa(convidadosEvento.getCpfPessoa()));
        }
        return "" + listaConvidados.toString();
    }

    @GetMapping("/deletaConvidado")
    public String listaComesBebesEvento(Integer codigoEvento, Integer cpfPessoa) {

        boolean resultado = new ListaConvidadosDAO().deleteListaConvidados(codigoEvento, cpfPessoa);

        return resultado ? "Convidado deletado com sucesso" : "falha ao deletar convidado";
    }




}
