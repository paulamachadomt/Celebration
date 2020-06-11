package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.ComesBebes;

public class ComesBebesDAO {
    
    public boolean cadastrarItem(String nomeItem) {
        boolean resultado = false;

        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO comesbebes (nomeItem) VALUES (?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, nomeItem);
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar " + nomeItem + "\n" + e);
        }
        return resultado;
    }

    public ComesBebes selectCodigoItem(Integer codigoItem){
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
            System.out.println("Erro ao localizar item com código " + codigoItem + "\n" + e);
        }
        return item;        
    }

    public ArrayList<ComesBebes> selectNomeItem(String nomeItem){
        ArrayList<ComesBebes> listaItens = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM comesbebes WHERE nomeItem LIKE ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, "%" + nomeItem + "%");
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                ComesBebes item = new ComesBebes(resultadoSelect.getInt("codigoItem"), resultadoSelect.getString("nomeItem"));
                listaItens.add(item);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Item não encontrado" + e);
        }
        return listaItens;
        }
    
    public Boolean updateItem(ComesBebes item){
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
            System.out.println("Item não encontrado " + e);
        }
        return resultado;
    }

    public Boolean deleteItem(int codigoItem){
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
            System.out.println("Item não encontrado " + e);
        }
        return resultado;
    }
    
}