package ads.db.projetofinal.projetofinal.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ads.db.projetofinal.projetofinal.model.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.entity.ItemEvento;

public class ItemEventoDAO {

    public boolean create(ItemEvento itemEvento) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO itemevento (codigoEvento, codigoItem) VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, itemEvento.getCodigoEvento());
            statement.setInt(2, itemEvento.getCodigoItem());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar item de comes e bebes no evento: " + e);
        }
        return resultado;
    }

    public List<ItemEvento> readAll() {
        List<ItemEvento> itens = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM itemevento";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                ItemEvento itemEvento = new ItemEvento(
                    resultadoSelect.getInt("codigoEvento"),
                    resultadoSelect.getInt("codigoItem"));
                itens.add(itemEvento);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar item de comes e bebes no evento: " + e);
        }
        return itens;
    }

    public List<ItemEvento> readAll(Integer codigoEvento) {
        List<ItemEvento> itens = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM itemevento WHERE codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigoEvento);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                ItemEvento itemEvento = new ItemEvento(
                    resultadoSelect.getInt("codigoEvento"),
                    resultadoSelect.getInt("codigoItem"));
                itens.add(itemEvento);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar item de comes e bebes no evento: " + e);
        }
        return itens;
    }


    public boolean delete(Integer codigoEvento, Integer codigoItem) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "DELETE FROM itemevento WHERE codigoEvento = ? AND codigoItem = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigoEvento);
            statement.setInt(2, codigoItem);
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao deletar item de comes e bebes no evento: " + e);
        }
        return resultado;
    }
}