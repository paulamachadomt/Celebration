package ads.db.projetofinal.projetofinal.model;

import lombok.Data;

import ads.db.projetofinal.projetofinal.model.entity.Convidado;
import ads.db.projetofinal.projetofinal.model.entity.Pessoa;

@Data
public class ResponseConvidado {

    private String cpf;
    private String nome;

    private Boolean confirmacao;
    private Integer codigoItem;

    public ResponseConvidado (Pessoa pessoa, Convidado convidado) {
        this.cpf = pessoa.getCpf();
        this.nome = pessoa.getNome();
        this.confirmacao = convidado.getConfirmacao();
        this.codigoItem = convidado.getCodigoItem();
    }
}