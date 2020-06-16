package ads.db.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class ListaComesBebes {

    @PostMapping("/item")
    public String inserirItem() {

        // params
        // nome

        // verificar se existe na base, cadastrar se não houver;
        // associar (o código) à lista de evento;
        // atualizar o cookie do Evento;
        // e retornar os dados atualizados (inserindo na lista).

        return "";
    }

    @DeleteMapping("/item")
    public String deletarItem() {

        // params
        // codigo

        // remove da tabela lista de comes bebes;
        return "";
    }

}
