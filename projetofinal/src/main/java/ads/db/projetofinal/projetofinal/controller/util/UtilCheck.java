package ads.db.projetofinal.projetofinal.controller.util;

public class UtilCheck {

    public static boolean loginIsAuthenticated(String cpfCookie){
        return !cpfCookie.equalsIgnoreCase("null");
    }

    public static boolean codigoEventoIsAuthenticated(String codigo_eventoCookie, Integer codigoEventoURL) {
        return codigo_eventoCookie.equalsIgnoreCase(""+codigoEventoURL);
    }

    public static boolean criadorEventoIsAuthenticated(String criador_evento){
        return criador_evento.equalsIgnoreCase("true");
    }

    public static boolean convidadoIsAuthenticate(String criador_evento){
        return criador_evento.equalsIgnoreCase("false");
    }

    public static boolean convidadoCriadorIsAuthenticated(String criador_evento){
        return !criador_evento.equalsIgnoreCase("null");
    }

    public static boolean cpfIsValid(String cpfCookie, String cpfUrl) {
        return cpfCookie.equalsIgnoreCase(cpfUrl);
    }
}