package ads.db.projetofinal.projetofinal.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection getConexao() {
        Connection conexao = null;
        try {            
            Class.forName(Settings.getDriver());
            try {
                conexao = DriverManager.getConnection(
                    Settings.getUrl(),
                    Settings.getUser(),
                    Settings.getSenha()
                );
            } catch (Exception e) {
                System.out.println("erro ao conectar no banco \n" + e);
            }
        } catch (Exception e) {
            System.out.println("erro ao pesquisar driver \n" + e);       
        }
        return conexao;
    }
}