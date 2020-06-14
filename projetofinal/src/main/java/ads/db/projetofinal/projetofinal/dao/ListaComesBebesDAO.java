package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.ListaComesBebes;

public class ListaComesBebesDAO {

    public boolean cadastrarListaComesBebes(ListaComesBebes comesBebesEvento) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO listacomesbebes (codigoEvento, codigoComesBebes) VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, comesBebesEvento.getCodigoEvento());
            statement.setInt(2, comesBebesEvento.getCodigoComesBebes());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Algum erro ao cadastrar item no evento " + e);
        }
        return resultado;
    }

    public ArrayList<ListaComesBebes> selectListaComesBebes() {
        ArrayList<ListaComesBebes> listaItens = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM listacomesbebes";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                ListaComesBebes comesBebesEvento = new ListaComesBebes(resultadoSelect.getInt("codigoComesBebes"),
                        resultadoSelect.getInt("codigoEvento"));
                listaItens.add(comesBebesEvento);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Item não encontrado" + e);
        }
        return listaItens;
    }

    // Update doesnt need

    public boolean deletarListaComesBebes(Integer codigoEvento, Integer codigoComesBebes) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "DELETE FROM listacomesbebes WHERE codigoEvento = ? AND codigoComesBebes = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigoEvento);
            statement.setInt(2, codigoComesBebes);
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Item não encontrado" + e);
        }
        return resultado;
    }
}