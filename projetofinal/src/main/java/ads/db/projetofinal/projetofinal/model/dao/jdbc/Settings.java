package ads.db.projetofinal.projetofinal.model.dao.jdbc;

import lombok.Data;

@Data
public class Settings {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url_localhost = "jdbc:mysql://localhost:3306/projetofinaldb?useTimezone=true&serverTimezone=UTC";
    private String url_docker = "jdbc:mysql://172.17.0.1:3306/projetofinaldb?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String senha = "123456";
}