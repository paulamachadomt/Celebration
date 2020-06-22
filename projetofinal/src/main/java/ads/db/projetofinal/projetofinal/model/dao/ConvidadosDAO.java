package ads.db.projetofinal.projetofinal.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ads.db.projetofinal.projetofinal.model.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.entity.Convidado;

public class ConvidadosDAO {

    public boolean create(Convidado convidado) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO convidado (confirmacao, cpfPessoa, codigoEvento, criadorEvento) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setBoolean(1, convidado.getConfirmacao());
            statement.setString(2, convidado.getCpfPessoa());
            statement.setInt(3, convidado.getCodigoEvento());
            statement.setBoolean(4, convidado.getCriadorEvento());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Convidado(a):  " + e);
        }
        return resultado;
    }

    public Convidado read_cpfEvento(String cpfPessoa, Integer codigoEvento) {
        Convidado convidados = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM convidado WHERE cpfpessoa = ? AND codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, cpfPessoa);
            statement.setInt(1, codigoEvento);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                convidados = new Convidado(
                    resultadoSelect.getBoolean("confirmacao"),
                    resultadoSelect.getInt("codigoItem"),
                    resultadoSelect.getString("cpfPessoa"), 
                    resultadoSelect.getInt("codigoEvento"),
                    resultadoSelect.getBoolean("criadorEvento"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Convidado(a):  " + e);
        }
        return convidados;
    }

    public List<Convidado> read(String cpfPessoa) {
        List<Convidado> convidados = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM convidado WHERE cpfpessoa = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, cpfPessoa);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                Convidado convidadoEvento = new Convidado(
                    resultadoSelect.getBoolean("confirmacao"),
                    resultadoSelect.getInt("codigoItem"),
                    resultadoSelect.getString("cpfPessoa"), 
                    resultadoSelect.getInt("codigoEvento"),
                    resultadoSelect.getBoolean("criadorEvento"));
                    convidados.add(convidadoEvento);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Convidado(a):  " + e);
        }
        return convidados;
    }

    public List<Convidado> readAll(Integer codigoEvento) {
        List<Convidado> convidados = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM convidado WHERE codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigoEvento);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                Convidado convidadoEvento = new Convidado(
                    resultadoSelect.getBoolean("confirmacao"),
                    resultadoSelect.getInt("codigoItem"),
                    resultadoSelect.getString("cpfPessoa"), 
                    resultadoSelect.getInt("codigoEvento"),
                    resultadoSelect.getBoolean("criadorEvento"));
                    convidados.add(convidadoEvento);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Convidado(a):  " + e);
        }
        return convidados;
    }

    public List<Convidado> readAll() {
        List<Convidado> convidados = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM convidado";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                Convidado convidadoEvento = new Convidado(
                    resultadoSelect.getBoolean("confirmacao"),
                    resultadoSelect.getInt("codigoItem"),
                    resultadoSelect.getString("cpfPessoa"), 
                    resultadoSelect.getInt("codigoEvento"),
                    resultadoSelect.getBoolean("criadorEvento"));
                    convidados.add(convidadoEvento);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Convidado(a):  " + e);
        }
        return convidados;
    }

    public boolean update_confirmacao(Convidado convidado) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE convidado SET confirmacao = ? WHERE cpfPessoa = ? AND codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setBoolean(1, convidado.getConfirmacao());
            statement.setString(2, convidado.getCpfPessoa());
            statement.setInt(3, convidado.getCodigoEvento());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Convidado(a):  " + e);
        }
        return resultado;
    }

    public boolean update_item(Convidado convidado) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE convidado SET codigoItem = ? WHERE cpfPessoa = ? AND codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, convidado.getCodigoItem());
            statement.setString(2, convidado.getCpfPessoa());
            statement.setInt(3, convidado.getCodigoEvento());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Convidado(a):  " + e);
        }
        return resultado;
    }

    public boolean delete(Integer codigoEvento, String cpfPessoa) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "DELETE FROM convidado WHERE cpfPessoa = ? AND codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, cpfPessoa);
            statement.setInt(2, codigoEvento);
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao deletar Convidado(a):  " + e);
        }
        return resultado;
    }

}