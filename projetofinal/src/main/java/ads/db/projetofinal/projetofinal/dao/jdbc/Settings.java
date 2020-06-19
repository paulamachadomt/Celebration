package ads.db.projetofinal.projetofinal.dao.jdbc;

import lombok.Data;

@Data
public class Settings {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/projetofinaldb?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String senha = "123456";
}