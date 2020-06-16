package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.ComesBebes;

public class ComesBebesDAO {

    public int createItem(String nomeItem) {
        int codigo = -1;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO comesbebes (nomeItem) VALUES (?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, nomeItem);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                codigo = resultSet.getInt(1);
            } else {
                System.out.println("Erro ao cadastrar item");
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Item: " + e);
        }
        return codigo;
    }

    public ComesBebes readItemByCodigo(Integer codigoItem) {
        ComesBebes item = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM comesbebes WHERE codigoItem = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigoItem);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                item = new ComesBebes(resultadoSelect.getInt("codigoItem"), resultadoSelect.getString("nomeItem"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Item: " + e);
        }
        return item;
    }

    public ComesBebes readItemByNome(String nomeItem) {
        ComesBebes comesBebes = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM comesbebes WHERE nomeItem = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, nomeItem);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                comesBebes = new ComesBebes(resultadoSelect.getInt("codigoItem"),
                        resultadoSelect.getString("nomeItem"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Item: " + e);
        }
        return comesBebes;
    }

    public ArrayList<ComesBebes> readItensByNome(String nomeItem) {
        ArrayList<ComesBebes> listaItens = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM comesbebes WHERE nomeItem LIKE %?%";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, "%" + nomeItem + "%");
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                ComesBebes item = new ComesBebes(resultadoSelect.getInt("codigoItem"),
                        resultadoSelect.getString("nomeItem"));
                listaItens.add(item);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Item: " + e);
        }
        return listaItens;
    }

    public Boolean updateItem(ComesBebes item) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE comesbebes SET nomeItem = ? WHERE codigoItem = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, item.getNomeItem());
            statement.setInt(2, item.getCodigoItem());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Item: " + e);
        }
        return resultado;
    }

    public Boolean deleteItemByCodigo(int codigoItem) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "DELETE FROM comesbebes WHERE codigoItem = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigoItem);
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao deletar Item: " + e);
        }
        return resultado;
    }

}