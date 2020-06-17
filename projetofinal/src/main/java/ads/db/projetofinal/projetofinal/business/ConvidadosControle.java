package ads.db.projetofinal.projetofinal.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ads.db.projetofinal.projetofinal.dao.ConvidadosDAO;
import ads.db.projetofinal.projetofinal.dao.PessoaDAO;
import ads.db.projetofinal.projetofinal.model.Convidado;
import ads.db.projetofinal.projetofinal.model.Pessoa;

@RestController
public class ConvidadosControle {

    private boolean cadastroPessoa(String nome, String cpf) {

        boolean resultado = new PessoaDAO().create(new Pessoa(nome, cpf));

        return resultado;
    }

    @GetMapping("/CreateConvidado")
    public String CreateConvidado(String nomePessoa, String cpfPessoa, Integer codigoEvento) {

        // Organiza os dados para verificar: 1. se o novo convidado já está na base de
        // dados. 2. se o convidado já está presente na festa.

        boolean resultadoCadastroConvidado = false;

        Convidado novoConvidado = new Convidado(false, cpfPessoa, codigoEvento, false);

        List<Convidado> convidadosEvento = new ConvidadosDAO().readAll();

        Pessoa convidadoNoBancoDados = new PessoaDAO().read(cpfPessoa);

        // 1.novo convidado já está na base de dado?
        if (convidadoNoBancoDados == null) {

            // se não... cadastra!
            boolean resultadoCadastroNovaPessoa = cadastroPessoa(nomePessoa, cpfPessoa);
            // e registra convidado.
            resultadoCadastroConvidado = new ConvidadosDAO().create(novoConvidado);

        } else {

            for (Convidado convidadoConfima : convidadosEvento) {
                // 2. verifica: já está presente na festa?
                if (!convidadoNoBancoDados.getCpf().equals(cpfPessoa)) {

                    resultadoCadastroConvidado = new ConvidadosDAO().create(novoConvidado);
                }
            }

        }

        return "" + resultadoCadastroConvidado;
    }

    @GetMapping("/confirmarPresenca")
    public String confirmarPresenca(boolean confirmacao, String cpfPessoa, Integer codigoEvento) {

        boolean resultado = false;

        resultado = new ConvidadosDAO().update_confirmacao(new Convidado(confirmacao, cpfPessoa, codigoEvento, false)); // é melhor receber o objeto pronto do front, esse false pode cagar o sistema 

        return "Confirmada a presença ? : " + resultado;
    }

    @GetMapping("/confirmarComesBebes")
    public String doGet(Integer codigoComesBebes, String cpfPessoa, Integer codigoEvento) {

        boolean resultado = false;

        resultado = new ConvidadosDAO().update_item(new Convidado(codigoComesBebes, cpfPessoa, codigoEvento, false)); // é melhor receber o objeto pronto do front, esse false pode cagar o sistema 

        return "Confirmou que levará o produto : " + codigoComesBebes;
    }

    @GetMapping("/listaConvidados")
    public String listaConvidados(Integer codigoEvento) {

        List<Pessoa> convidados = new ArrayList<>();

        List<Convidado> convidadosEvento = new ConvidadosDAO().readAll();
        
        for (Convidado convidadoEvento : convidadosEvento) {

            convidados.add(new PessoaDAO().read(convidadoEvento.getCpfPessoa()));
        }
        return "" + convidados.toString();
    }

    @GetMapping("/deletaConvidado")
    public String deletaConvidado(Integer codigoEvento, Integer cpfPessoa) {

        boolean resultado = new ConvidadosDAO().delete(codigoEvento, cpfPessoa); 
        
        return resultado ? "Convidado deletado com sucesso" : "falha ao deletar convidado";
    }

}
