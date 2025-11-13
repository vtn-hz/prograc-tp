package mdp.ingenieria.clinicagestion.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConnectionManager {
    private static final ConnectionManager INSTANCE = new ConnectionManager();
    private String url;
    private String user;
    private String pass;

    private ConnectionManager() {
        loadConfig();
        loadDriver();
    }

    public static ConnectionManager getInstance() { return INSTANCE; }

    private void loadConfig() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            Properties p = new Properties();
            if (in != null) p.load(in);

            // Override por variables de entorno si existen
            this.url  = getenvOrDefault("DB_URL",  p.getProperty("db.url"));
            this.user = getenvOrDefault("DB_USER", p.getProperty("db.user"));
            this.pass = getenvOrDefault("DB_PASS", p.getProperty("db.pass"));

            if (url == null || user == null || pass == null)
                throw new IllegalStateException("Faltan propiedades de conexión (url/user/pass)");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar configuración de DB", e);
        }
    }

    private static String getenvOrDefault(String key, String def) {
        String v = System.getenv(key);
        return (v != null && !v.isEmpty()) ? v : def;
    }

    private void loadDriver() {
        try { Class.forName("org.mariadb.jdbc.Driver"); }
        catch (ClassNotFoundException e) { throw new RuntimeException("Driver MariaDB no encontrado", e); }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}