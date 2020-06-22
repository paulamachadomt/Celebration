package ads.db.projetofinal.projetofinal.model.entity;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Convidado other = (Convidado) obj;
        if (codigoEvento == null) {
            if (other.codigoEvento != null)
                return false;
        } else if (!codigoEvento.equals(other.codigoEvento))
            return false;
        if (cpfPessoa == null) {
            if (other.cpfPessoa != null)
                return false;
        } else if (!cpfPessoa.equals(other.cpfPessoa))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoEvento == null) ? 0 : codigoEvento.hashCode());
        result = prime * result + ((cpfPessoa == null) ? 0 : cpfPessoa.hashCode());
        return result;
    }
}