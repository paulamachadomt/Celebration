package ads.db.projetofinal.projetofinal.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ads.db.projetofinal.projetofinal.model.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.entity.Evento;

public class EventoDAO {

    /**
     * Para gerar senha de evento é necessário o valor do código auto_increment. 
     * Esse método cadastra, gera a senha e atualiza. Portanto gera o evento inicial. 
     */
    public Evento create_getEvento(Evento evento){
        Integer codigoEvento = create(evento);
        if (codigoEvento >= 1) {
            evento = read(codigoEvento);
        }
        return evento;
    }

    public int create(Evento evento) {
        int codigo = -1;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO evento (local, data, nome) VALUES (?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, evento.getLocal());
            statement.setDate(2, Date.valueOf(evento.getData()));
            statement.setString(3, evento.getNome());
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                codigo = resultSet.getInt(1);
            } else {
                System.out.println("Erro ao cadastrar evento e retornar o codigo");
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Evento: " + e);
        }
        return codigo;
    }

    public Evento read(Integer codigo) {
        Evento evento = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM evento WHERE codigo = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigo);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                evento = new Evento(
                    resultadoSelect.getInt("codigo"),
                    resultadoSelect.getString("local"), 
                    resultadoSelect.getDate("data").toLocalDate(), 
                    resultadoSelect.getString("nome"),
                    resultadoSelect.getString("descricao"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Evento: " + e);
        }
        return evento;
    }

    public List<Evento> readAll() {
        List<Evento> eventos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM evento";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                Evento evento = new Evento(
                        resultadoSelect.getInt("codigo"),
                        resultadoSelect.getString("local"), 
                        resultadoSelect.getDate("data").toLocalDate(),
                        resultadoSelect.getString("nome"),
                        resultadoSelect.getString("descricao"));
                eventos.add(evento);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Evento: " + e);
        }
        return eventos;
    }
 
    public Boolean update(Evento evento) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE evento SET local = ?, data = ?, descricao = ?, nome = ? WHERE codigo = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, evento.getLocal());
            statement.setDate(2, Date.valueOf(evento.getData()));
            statement.setString(3, evento.getDescricao());
            statement.setString(4, evento.getNome());
            statement.setInt(5, evento.getCodigo());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Evento: " + e);
        }
        return resultado;
    }

    public Boolean delete(Integer codigo) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "DELETE FROM evento WHERE codigo = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigo);
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao deletar Evento: " + e);
        }
        return resultado;
    }

}