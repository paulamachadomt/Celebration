package ads.db.projetofinal.projetofinal.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    public static Connection getConexao() {
        Settings settings = new Settings();
        Connection conexao = null;
        try { Class.forName(settings.getDriver());
            try { conexao = DriverManager.getConnection(
                    settings.getUrl(),
                    settings.getUser(),
                    settings.getSenha()
                );
            } catch (Exception e) { System.out.println("erro ao conectar no banco \n" + e); }
        } catch (Exception e) { System.out.println("erro ao pesquisar driver \n" + e); }
        return conexao;
    }
}