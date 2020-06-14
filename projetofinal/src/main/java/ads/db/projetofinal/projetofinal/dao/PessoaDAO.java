package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.Pessoa;

public class PessoaDAO {
    public boolean cadastrarPessoa(Pessoa pessoa) {
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
            System.out.println("erro ao cadastrar pessoa \n" + e);
        }
        return resultado;
    }

    public Pessoa selectCPFPessoa(String cpf) {
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
            System.out.println("Erro ao localizar pessoa por CPF \n" + e);
        }
        return pessoa;
    }

    public ArrayList<Pessoa> selectNomePessoa(String nome) {
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
            System.out.println("Nome de pessoa não encontrado " + e);
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
            System.out.println("Pessoa não encontrada " + e);
        }
        return resultado;
    }  

    public Boolean deletePessoa(String cpf){
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
            System.out.println("CPF não encontrado " + e);
        }
        return resultado;
    }
}
