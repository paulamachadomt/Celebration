package ads.db.projetofinal.projetofinal.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ads.db.projetofinal.projetofinal.model.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.entity.Item;

public class ItemDAO {

    public int create_getCodigo(String nomeItem) {
        int codigo = -1;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO item (nomeItem) VALUES (?)";
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

    public Item read(Integer codigoItem) {
        Item item = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM item WHERE codigoItem = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigoItem);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                item = new Item(resultadoSelect.getInt("codigoItem"), resultadoSelect.getString("nomeItem"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Item: " + e);
        }
        return item;
    }

    public Item read(String nomeItem) {
        Item comesBebes = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM item WHERE nomeItem = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, nomeItem);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                comesBebes = new Item(resultadoSelect.getInt("codigoItem"), resultadoSelect.getString("nomeItem"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Item: " + e);
        }
        return comesBebes;
    }

    public List<Item> readAll(String nomeItem) {
        List<Item> itens = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM item WHERE nomeItem LIKE %?%";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, "%" + nomeItem + "%");
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                Item item = new Item(resultadoSelect.getInt("codigoItem"), resultadoSelect.getString("nomeItem"));
                itens.add(item);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Item: " + e);
        }
        return itens;
    }

    public Boolean update(Item item) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE item SET nomeItem = ? WHERE codigoItem = ?";
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

    public Boolean delete(int codigoItem) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "DELETE FROM item WHERE codigoItem = ?";
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