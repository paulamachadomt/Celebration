package ads.db.projetofinal.projetofinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.Pessoa;

public class PessoaDAO {
    public boolean cadastrarPessoa(Pessoa pessoa) {
        boolean resultado = false;

        try {
            Connection conexao = Conexao.getConexao();
            String comandoSQL = "INSERT INTO Pessoa VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, pessoa.getCpf());
            statement.setString(2, pessoa.getNome());
            resultado = statement.execute();
        } catch (Exception e) {
            System.out.println("erro ao cadastrar pessoa \n" + e);
            
        }
        return resultado;
    }
    
}