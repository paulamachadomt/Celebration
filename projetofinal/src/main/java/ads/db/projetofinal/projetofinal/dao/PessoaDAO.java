package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.Pessoa;

public class PessoaDAO {
    
    public boolean createPessoa(Pessoa pessoa) {
        boolean resultado = false;

        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO pessoa VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, pessoa.getCpf());
            statement.setString(2, pessoa.getNome());
            if (statement.executeUpdate() >= 1) {
                resultado = true;
            }
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Pessoa: " + e);
        }
        return resultado;
    }

    public Pessoa readPessoaByCPF(String cpf) {
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

    public ArrayList<Pessoa> readPessoaByName(String nome) {
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "SELECT * FROM pessoa WHERE nome = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, nome);
            ResultSet resultadoSelect = statement.executeQuery();
            while (resultadoSelect.next()) {
                Pessoa pessoa = new Pessoa(resultadoSelect.getString("cpf"), resultadoSelect.getString("nome"));
                listaPessoas.add(pessoa);
            }
            resultadoSelect.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println("Erro ao localizar Pessoa: " + e);
        }
        return listaPessoas;
    }

    public Boolean updatePessoa(Pessoa pessoa){
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

    public Boolean deletePessoaByCPF(String cpf){
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
