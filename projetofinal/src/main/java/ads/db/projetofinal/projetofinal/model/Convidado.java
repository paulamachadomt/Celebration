package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

@Data
public class Convidado {

    private Boolean confirmacao;
    private Integer codigoItem;

    private String cpfPessoa;
    private Integer codigoEvento;
    
    private Boolean criadorEvento;

    public Convidado(Boolean confirmacao, String cpfPessoa, Integer codigoEvento, Boolean criadorEvento) {
        this.confirmacao = confirmacao;
        this.cpfPessoa = cpfPessoa;
        this.codigoEvento = codigoEvento;
        this.criadorEvento = criadorEvento;
    }

    public Convidado(Integer codigoItem, String cpfPessoa, Integer codigoEvento, Boolean criadorEvento) {
        this.codigoItem = codigoItem;
        this.cpfPessoa = cpfPessoa;
        this.codigoEvento = codigoEvento;
        this.criadorEvento = criadorEvento;
    }

    public Convidado(Boolean confirmacao,  Integer codigoItem, String cpfPessoa, Integer codigoEvento, Boolean criadorEvento) {
        this.confirmacao = confirmacao;
        this.cpfPessoa = cpfPessoa;
        this.codigoEvento = codigoEvento;
        this.codigoItem = codigoItem;
        this.criadorEvento = criadorEvento;
    }

}