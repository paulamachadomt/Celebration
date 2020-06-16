package ads.db.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListaConvidadosController {

    @PostMapping("/convidado")
    public String createConvidado() {

        //params
        //nome, cpf

        //verificar se existe na base, 
        //cadastrar se não houver; 
        //associar à lista de evento; 
        //atualizar o cookie do Evento; 
        //e retornar os dados atualizados (inserindo na lista).

        return "";
    }

    @DeleteMapping("/convidado") 
    public String removeConvidado() {

        //params
        //cpf

        //remove da tabela lista de convidados;
        return "";
    }
    
    @PostMapping("/convidado/confirma")
    public String confirmaConvidado() {

        // Se não confirmado, deve confirmar
        // se confirmado, deve desconfirmar

        //params
        //cpg

        //leitura do cookies, 
        //atualiza a lista de convidados a FLAG de confirmação do convidado.
        //Atualiza cookies do evento; 
        //Libera combobox de alimentos.
        return "";
    }

    @PostMapping("/convidado/item/")
    public String escolhaItem() {

        //params
        //cpf, código do alimento

        //Leitura do cookies; 
        //atualiza o codigo na tabela lista de convidados. 
        //Atualiza cookies do evento; 
        //Bloqueia combobox de alimentos.
       return "";
    }
}