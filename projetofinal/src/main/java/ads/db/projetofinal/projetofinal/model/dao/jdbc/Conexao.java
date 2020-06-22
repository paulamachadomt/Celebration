package ads.db.projetofinal.projetofinal.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection getConexao() {
        Settings settings = new Settings();
        Connection conexao = null;
        try {
            Class.forName(settings.getDriver());
            try {
                try {
                    conexao = DriverManager.getConnection(settings.getUrl_localhost(), settings.getUser(), settings.getSenha());
                } catch (Exception e) {
                    conexao = DriverManager.getConnection(settings.getUrl_docker(), settings.getUser(), settings.getSenha());
                }
            } catch (Exception e) {
                System.out.println("erro ao conectar no banco \n" + e);
            }
        } catch (Exception e) {
            System.out.println("erro ao pesquisar driver \n" + e);
        }
        return conexao;
    }
}