package Model;

public class Auth {
    public static boolean isAllowed(String username, String password) {
        return username.contentEquals("admin") && password.contentEquals("password");
    }
}