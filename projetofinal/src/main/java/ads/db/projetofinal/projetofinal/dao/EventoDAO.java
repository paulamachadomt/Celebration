package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.Evento;
import ads.db.projetofinal.projetofinal.model.Pessoa;

public class EventoDAO {

    public int cadastroEvento(Evento evento) {
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
                System.out.println(codigo);
            } else {
                System.out.println("Algum erro ao resgatar auto_increment evento \n");
            }
            System.out.println(resultSet);
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar evento \n" + e);
        }
        return codigo;
    }

    public Evento selectSenhaEvento(Integer senha) {
        Evento evento = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM evento WHERE senha = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, senha);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                evento = new Evento(
                        resultadoSelect.getString("local"), 
                        resultadoSelect.getDate("data").toLocalDate(),
                        resultadoSelect.getString("descricao"), 
                        resultadoSelect.getString("nome"));
                evento.setCodigo(resultadoSelect.getInt("codigo"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar evento por senha \n" + e);
        }
        return evento;
    }

    public Evento selectCodEvento(Integer codigo) {
        Evento evento = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM evento WHERE senha = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigo);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                evento = new Evento(
                    resultadoSelect.getString("local"), 
                    resultadoSelect.getDate("data").toLocalDate(),
                    resultadoSelect.getString("descricao"), 
                    resultadoSelect.getString("nome"));
                evento.setCodigo(resultadoSelect.getInt("codigo"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar evento por codigo \n" + e);
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
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar evento " + e);
        }
        return resultado;
    }

    public Boolean deleteEvento(Integer codigo) {
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
            System.out.println("Erro ao localizar evento " + e);
        }
        return resultado;
    }

}