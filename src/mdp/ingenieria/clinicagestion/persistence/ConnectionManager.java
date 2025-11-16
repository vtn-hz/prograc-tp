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

    /**
     * Constructor privado.
     * Carga la configuración y registra el driver JDBC.
     */
    private ConnectionManager() {
        loadConfig();
        loadDriver();
    }

    /**
     * Obtiene la instancia única de ConnectionManager.
     *
     * @return instancia Singleton del administrador de conexiones
     */
    public static ConnectionManager getInstance() { return INSTANCE; }

    /**
     * Carga configuración desde db.properties o desde variables de entorno.
     *
     * <b>pre:</b> debe existir el archivo o las variables de entorno necesarias <br>
     * <b>post:</b> url, user y pass quedan inicializados con valores válidos
     */
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

    /**
     * Devuelve el valor de una variable de entorno, o un valor por defecto si no existe.
     *
     * @param key nombre de la variable de entorno
     * @param def valor por defecto si no se encuentra
     * @return valor final usado
     */
    private static String getenvOrDefault(String key, String def) {
        String v = System.getenv(key);
        return (v != null && !v.isEmpty()) ? v : def;
    }

    /**
     * Carga el driver JDBC de MariaDB.
     *
     * <b>post:</b> si el driver no está disponible, se lanza una excepción
     */
    private void loadDriver() {
        try { Class.forName("org.mariadb.jdbc.Driver"); }
        catch (ClassNotFoundException e) { throw new RuntimeException("Driver MariaDB no encontrado", e); }
    }

    /**
     * Obtiene una nueva conexión a la base de datos.
     *
     * <b>pre:</b> url, user y pass deben estar configurados correctamente <br>
     * <b>post:</b> se devuelve una conexión activa lista para usar
     *
     * @return conexión JDBC
     * @throws SQLException si falla la apertura de conexión
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}