package ads.db.projetofinal.projetofinal.controller;

public class _baseController {


    // ---------------------- HomeController.java

    // (home) - redireciona para a página home, (remove cookies do evento);

    // (logar) nome, cpf -> Cookier (informações usuário, listas de festas participantes e criadas), Eventos (Nome, local, data, descrição, proprietario)

    // (logout) nome, cpf -> Destroi cookie, fecha sistema
    
    // ---------------------- EventoController.java

    // (Criar evento) Nome_festa, data_festa, Local_festa -> 
            // Cria a primeira versão na base de dados, retorna o evento com código -> cadastr como evento da pessoa; 
            // Cria um cookie com os dados do evento
            // direciona para a página de edição do evento {envaminha view}

    // (Deletar evento) - códiogo_evento -> deleta a lista de comesbebes, deleta a lista de convidados e deleta o evento; direciona para a página inicial {index} (remove cookies do evento)

    // (Editar evento) - cpf, evento(todos os dados) -> Cookie (faz a leitura do cookie de evento) -> atualzia o evento por completo na base // nome, local, data, descrição, 

    // (carregar dados do evento) -> carregar dados atualizados do evento, preencher cookies no usuário; e direcionar para a pagina de evento;

    // ---------------------- ConvidadosController.java

    // (Inserir convidado) - nome, cpf -> verificar se existe na base, cadastrar se não houver; associar à lista de evento; atualizar o cookie do Evento; e retornar os dados atualizados (inserindo na lista).
    
    // (Remover convidado) - cpf -> remove da tabela lista de convidados;

    // (Confirmar) cpf -> leitura do cookies, atualiza a lista de convidados a flag de confirmação do convidado. Atualiza cookies do evento; Libera combobox de alimentos.
    //--==>> Deve funcionar como um botão! ;;; (Desconfirmar) cpf -> Leitura do cookies, atualiza a lista de convidados a flag de confirmação do convidado. Atualiza cookies do evento; Deleta alimento escolhido e bloqueia combobox de alimentos.

    // (Escolha do alimento) cpf, código do alimento -> Leitura do cookies; atualiza o codigo na tabela lista de convidados. Atualiza cookies do evento; Bloqueia combobox de alimentos.

    // ---------------------- ItemEventoController.java

    // (Inserir comida) - nome -> verificar se existe na base, cadastrar se não houver; associar (o código) à lista de evento; atualizar o cookie do Evento; e retornar os dados atualizados (inserindo na lista).

    // (Remover comida) - codigo -> remove da tabela lista de comes bebes;

    // ----------------------
}