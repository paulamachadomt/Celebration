package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.Evento;

public class EventoDAO {

    /**
     * Para gerar senha de evento é necessário o valor do código auto_increment. 
     * Esse método cadastra, gera a senha e atualiza. Portanto gera o evento inicial. 
     */
    public Evento gerarEvento(Evento gerarEvento){
        Integer resultado = createEvento(gerarEvento);
        if (resultado >= 1) {
            gerarEvento.setCodigo(resultado);
            gerarEvento.gerarSenhaEvento();
            updateEvento(gerarEvento);
        }
        return gerarEvento;
    }

    public int createEvento(Evento evento) {
        int codigo = -1;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO evento (local, data, descricao, nome) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, evento.getLocal());
            statement.setDate(2, Date.valueOf(evento.getData()));
            statement.setString(3, evento.getDescricao());
            statement.setString(4, evento.getNome());
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                codigo = resultSet.getInt(1);
            } else {
                System.out.println("Erro ao cadastrar evento \n");
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Evento: " + e);
        }
        return codigo;
    }

    public Evento readEventoBySenha(Integer senha) {
        Evento evento = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM evento WHERE senha = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, senha);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                evento = new Evento(
                        resultadoSelect.getInt("codigo"),
                        resultadoSelect.getInt("senha"),
                        resultadoSelect.getString("local"), 
                        resultadoSelect.getDate("data").toLocalDate(),
                        resultadoSelect.getString("descricao"), 
                        resultadoSelect.getString("nome"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Evento: " + e);
        }
        return evento;
    }

    public Evento readEventoByCodigo(Integer codigo) {
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
                    resultadoSelect.getInt("senha"),
                    resultadoSelect.getString("local"), 
                    resultadoSelect.getDate("data").toLocalDate(),
                    resultadoSelect.getString("descricao"), 
                    resultadoSelect.getString("nome"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Evento: " + e);
        }
        return evento;
    }
 
    public Boolean updateEvento(Evento evento) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE evento SET senha = ?, local = ?, data = ?, descricao = ?, nome = ? WHERE codigo = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, evento.getSenha());
            statement.setString(2, evento.getLocal());
            statement.setDate(3, Date.valueOf(evento.getData()));
            statement.setString(4, evento.getDescricao());
            statement.setString(5, evento.getNome());
            statement.setInt(6, evento.getCodigo());
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

    public Boolean deleteEventoByCodigo(Integer codigo) {
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