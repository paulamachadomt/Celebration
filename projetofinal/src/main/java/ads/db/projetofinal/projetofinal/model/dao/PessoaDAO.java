package ads.db.projetofinal.projetofinal.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ads.db.projetofinal.projetofinal.model.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.entity.Pessoa;

public class PessoaDAO {

    public boolean create(Pessoa pessoa) {
        boolean resultado = false;
        try {
            Connection conn = Conexao.getConexao();
            String sql = "INSERT INTO pessoa VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getCpf());
            stmt.setString(2, pessoa.getNome().toLowerCase());
            if (stmt.executeUpdate() >= 1) {
                resultado = true;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Pessoa: " + e);
        }
        return resultado;
    }

    public Pessoa read(String cpf) {
        Pessoa pessoa = null;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM pessoa WHERE cpf = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, cpf);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                pessoa = new Pessoa(resultadoSelect.getString("cpf"), resultadoSelect.getString("nome"));
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Pessoa: " + e);
        }
        return pessoa;
    }

    public ArrayList<Pessoa> read_ByNome(String nome) {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM pessoa WHERE nome = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, nome);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                Pessoa pessoa = new Pessoa(resultadoSelect.getString("cpf"), resultadoSelect.getString("nome"));
                pessoas.add(pessoa);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Pessoa: " + e);
        }
        return pessoas;
    }

    public Boolean update(Pessoa pessoa) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "UPDATE pessoa SET nome = ? WHERE cpf = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCpf());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Pessoa: " + e);
        }
        return resultado;
    }

    public Boolean delete(String cpf) {
        boolean resultado = false;
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "DELETE FROM pessoa WHERE cpf = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, cpf);
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao deletar Pessoa: " + e);
        }
        return resultado;
    }

}
