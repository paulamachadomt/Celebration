package ads.db.projetofinal.projetofinal.dao.jdbc;

public class Settings {

    private static String driver = "com.mysql.jdbc.driver";
    private static String url = "jdbc:mysql:localhost:3306/projetofinaldb?useTimezone=true&serverTimezone=UTC";
    private static String user = "root";
    private static String senha = "123456";

    public static String getDriver(){
        return driver;
    }
    public static String getUrl() {
        return url;
    }
    public static String getUser() {
        return user;
    }
    public static String getSenha() {
        return senha;
    }
}