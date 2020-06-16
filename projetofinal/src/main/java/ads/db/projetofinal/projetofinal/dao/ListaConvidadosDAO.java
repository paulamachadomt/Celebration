package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.ListaConvidados;

public class ListaConvidadosDAO {

    public boolean createConvidado(ListaConvidados convidadosEvento) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO listaconvidados (confirmacao, cpfPessoa, codigoEvento) VALUES (?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setBoolean(1, false);
            statement.setString(2, convidadosEvento.getCpfPessoa());
            statement.setInt(3, convidadosEvento.getCodigoEvento());
            // statement.setInt(4, convidadosEvento.getCodigoComesBebes());
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

    public ArrayList<ListaConvidados> readConvidado() {
        ArrayList<ListaConvidados> listaConvidados = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM listaconvidados";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                ListaConvidados convidadoEvento = new ListaConvidados(resultadoSelect.getBoolean("confirmacao"),
                        resultadoSelect.getString("cpfPessoa"), resultadoSelect.getInt("codigoEvento"),
                        resultadoSelect.getInt("codigoComesBebes"));
                listaConvidados.add(convidadoEvento);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Convidado(a):  " + e);
        }
        return listaConvidados;
    }

    public boolean updateConvidadoByConfirmacao(ListaConvidados convidadosEvento) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE listaconvidados SET confirmacao = ? WHERE cpfPessoa = ? AND codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setBoolean(1, convidadosEvento.getConfirmacao());
            statement.setString(2, convidadosEvento.getCpfPessoa());
            statement.setInt(3, convidadosEvento.getCodigoEvento());
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

    public boolean updateConvidadoByComesBebes(ListaConvidados convidadosEvento) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE listaconvidados SET codigoComesBebes = ? WHERE cpfPessoa = ? AND codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, convidadosEvento.getCodigoComesBebes());
            statement.setString(2, convidadosEvento.getCpfPessoa());
            statement.setInt(3, convidadosEvento.getCodigoEvento());
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

    public boolean deleteConvidadoByCodigoCPF(Integer codigoEvento, Integer cpfPessoa) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "DELETE FROM listaconvidados WHERE codigoEvento = ? AND cpfPessoa = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setInt(1, codigoEvento);
            statement.setInt(2, cpfPessoa);
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