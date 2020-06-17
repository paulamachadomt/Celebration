package ads.db.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvidadosController {



        // (Inserir convidado) - nome, cpf -> verificar se existe na base, cadastrar se não houver; associar à lista de evento; atualizar o cookie do Evento; e retornar os dados atualizados (inserindo na lista).
    
    // (Remover convidado) - cpf -> remove da tabela lista de convidados;

    // (Confirmar) cpf -> leitura do cookies, atualiza a lista de convidados a flag de confirmação do convidado. Atualiza cookies do evento; Libera combobox de alimentos.
    //--==>> Deve funcionar como um botão! ;;; (Desconfirmar) cpf -> Leitura do cookies, atualiza a lista de convidados a flag de confirmação do convidado. Atualiza cookies do evento; Deleta alimento escolhido e bloqueia combobox de alimentos.

    // (Escolha do alimento) cpf, código do alimento -> Leitura do cookies; atualiza o codigo na tabela lista de convidados. Atualiza cookies do evento; Bloqueia combobox de alimentos.
}