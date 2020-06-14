package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class ListaConvidados {

    Boolean confirmacao;
    String cpfPessoa;
    Integer codigoEvento;
    Integer codigoComesBebes;

    public ListaConvidados(boolean confirmacao, String cpfPessoa, Integer codigoEvento) {
        this.confirmacao = confirmacao;
        this.cpfPessoa = cpfPessoa;
        this.codigoEvento = codigoEvento;
    }

    public ListaConvidados(Integer codigoComesBebes, String cpfPessoa, Integer codigoEvento) {
        this.cpfPessoa = cpfPessoa;
        this.codigoEvento = codigoEvento;
        this.codigoComesBebes = codigoComesBebes;
    }

    public ListaConvidados(boolean confirmacao, String cpfPessoa, Integer codigoEvento, Integer codigoComesBebes) {
        this.confirmacao = confirmacao;
        this.cpfPessoa = cpfPessoa;
        this.codigoEvento = codigoEvento;
        this.codigoComesBebes = codigoComesBebes;
    }

}