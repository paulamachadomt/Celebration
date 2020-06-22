package ads.db.projetofinal.projetofinal.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ads.db.projetofinal.projetofinal.model.dao.jdbc.Conexao;
import ads.db.projetofinal.projetofinal.model.entity.Convidado;
import ads.db.projetofinal.projetofinal.model.entity.Pessoa;
import ads.db.projetofinal.projetofinal.model.ResponseConvidado;

public class ResponseDAO {

    public ResponseConvidado read(String cpfPessoa, Integer codigoEvento) {

        Pessoa pessoa = null;
        Convidado convidado = null;
        ResponseConvidado responseConvidado = null;

        try {

            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM convidado WHERE cpfpessoa = ? AND codigoEvento = ?";
            PreparedStatement statement = conexao.prepareStatement(comandoSQL);

            statement.setString(1, cpfPessoa);
            statement.setInt(2, codigoEvento);

            ResultSet resultadoSelect = statement.executeQuery();

            while (resultadoSelect.next()) {
                convidado = new Convidado(
                    resultadoSelect.getBoolean("confirmacao"),
                    resultadoSelect.getInt("codigoItem"),
                    resultadoSelect.getString("cpfPessoa"), 
                    resultadoSelect.getInt("codigoEvento"),
                    resultadoSelect.getBoolean("criadorEvento"));
            }

            comandoSQL = "SELECT * FROM pessoa WHERE cpf = ?";
            statement = conexao.prepareStatement(comandoSQL);
            statement.setString(1, cpfPessoa);

            resultadoSelect = statement.executeQuery();

            while (resultadoSelect.next()) {
                pessoa = new Pessoa(
                    resultadoSelect.getString("cpf"), 
                    resultadoSelect.getString("nome"));
            }

            if (pessoa != null && convidado != null) {
                responseConvidado = new ResponseConvidado(
                    pessoa, convidado
                );
            }

            resultadoSelect.close();
            statement.close();
            conexao.close();

        } catch (Exception e) {
            System.out.println("Erro ao localizar Convidado(a):  " + e);
        }

        return responseConvidado;

    }
    
}