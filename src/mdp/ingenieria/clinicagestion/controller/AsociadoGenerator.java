package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Generador de datos ficticios para instancias de {@link AsociadoDTO}.
 * <p>
 * Permite crear asociados con información coherente (DNI, nombre, dirección,
 * teléfono, etc.) para pruebas, simulaciones o carga inicial de datos.
 */
public class AsociadoGenerator {
    /**
     * Fuente de números aleatorios utilizada para generar los datos.
     */
    private final Random random = new Random();

    /**
     * Conjunto de nombres posibles para los asociados generados.
     */
    private static final String[] NAMES = {
            "Juan", "María", "Carlos", "Lucía", "Pedro", "Sofía", "Javier", "Ana",
            "Diego", "Valentina", "Agustín", "Victoria", "Nicolás", "Camila",
            "Martín", "Julieta", "Facundo", "Florencia", "Tomás", "Milagros",
            "Emilia", "Mateo", "Lautaro", "Abril"
    };

    /**
     * Conjunto de apellidos posibles para los asociados generados.
     */
    private static final String[] SURNAMES = {
            "Gómez", "Fernández", "Rodríguez", "Pérez", "López", "Martínez",
            "Sánchez", "Ramírez", "Torres", "Álvarez", "Romero", "Suárez",
            "Herrera", "Aguirre", "Molina", "Silva", "Ortiz", "Benítez",
            "Castro", "Domínguez"
    };

    /**
     * Conjunto de ciudades posibles para los asociados generados.
     */
    private static final String[] CITIES = {
            "Buenos Aires", "Córdoba", "Rosario", "Mendoza", "La Plata", "Salta",
            "Tucumán", "Mar del Plata", "Santa Fe", "Resistencia", "Corrientes",
            "Neuquén", "San Juan", "San Luis", "Posadas", "Bahía Blanca",
            "Paraná", "Rawson", "Bariloche"
    };

    /**
     * Conjunto de nombres de calles posibles para los asociados generados.
     */
    private static final String[] STREET_NAMES = {
            "Av. Luro", "San Martín", "Belgrano", "Mitre",
            "Rivadavia", "9 de Julio", "Independencia", "Sarmiento",
            "Hipólito Yrigoyen", "Las Heras", "Perón", "Lavalle",
            "Pueyrredón", "Dorrego", "Catamarca", "Jujuy", "La Rioja"
    };

    /**
     * Conjunto de códigos de área posibles para los teléfonos generados.
     */
    private static final String[] AR_AREA_CODES = {
            "11", "221", "223", "261", "341", "351", "381", "299", "387", "376"
    };

    /**
     * Genera un DNI aleatorio de 8 dígitos en formato de texto.
     *
     * <b>pre:</b> el generador de números aleatorios debe estar inicializado <br>
     * <b>post:</b> se devuelve una cadena de exactamente 8 caracteres numéricos, que representan un DNI ficticio
     *
     * @return DNI aleatorio de 8 dígitos, con ceros a la izquierda si corresponde
     */
    private String randomDni() {
        return String.format("%08d", random.nextInt(100_000_000));
    }

    /**
     * Selecciona un nombre aleatorio del conjunto predefinido.
     *
     * <b>pre:</b> el arreglo NAMES debe contener al menos un elemento <br>
     * <b>post:</b> se devuelve uno de los nombres definidos en NAMES
     *
     * @return nombre aleatorio
     */
    private String randomName() {
        return NAMES[random.nextInt(NAMES.length)];
    }

    /**
     * Selecciona un apellido aleatorio del conjunto predefinido.
     *
     * <b>pre:</b> el arreglo SURNAMES debe contener al menos un elemento <br>
     * <b>post:</b> se devuelve uno de los apellidos definidos en SURNAMES
     *
     * @return apellido aleatorio
     */
    private String randomSurname() {
        return SURNAMES[random.nextInt(SURNAMES.length)];
    }

    /**
     * Genera un número de teléfono argentino ficticio con código de área.
     *
     * <b>pre:</b> el arreglo AR_AREA_CODES debe contener al menos un código de área <br>
     * <b>post:</b> se devuelve una cadena con el formato "AAA XXX-XXXX",
     * donde AAA es el código de área y el resto es un número aleatorio
     *
     * @return teléfono aleatorio con código de área argentino
     */
    private String randomPhone() {
        String area = AR_AREA_CODES[random.nextInt(AR_AREA_CODES.length)];
        int first = 100 + random.nextInt(900);
        int second = 1000 + random.nextInt(9000);
        return area + " " + first + "-" + second;
    }

    /**
     * Selecciona una ciudad aleatoria del conjunto predefinido.
     *
     * <b>pre:</b> el arreglo CITIES debe contener al menos una ciudad <br>
     * <b>post:</b> se devuelve una de las ciudades definidas en CITIES
     *
     * @return ciudad aleatoria
     */
    private String randomCity() {
        return CITIES[random.nextInt(CITIES.length)];
    }

    /**
     * Genera una dirección ficticia compuesta por una calle y un número.
     *
     * <b>pre:</b> el arreglo STREET_NAMES debe contener al menos una calle <br>
     * <b>post:</b> se devuelve una cadena con el formato "NombreCalle NNNN",
     * donde NNNN es un número entre 1 y 4000
     *
     * @return dirección aleatoria (calle y número)
     */
    private String randomAddress() {
        String street = STREET_NAMES[random.nextInt(STREET_NAMES.length)];
        int number = 1 + random.nextInt(4000);
        return street + " " + number;
    }

    /**
     * Genera un arreglo de cadenas con los campos básicos de un asociado.
     *
     * <b>post:</b> se devuelve un arreglo no nulo de longitud 6 con los campos generados
     *
     * @return arreglo de campos de texto que representan los datos de un asociado
     */
    public String[] generateFields() {
        return new String[]{
                randomDni(),
                randomName(),
                randomSurname(),
                randomPhone(),
                randomCity(),
                randomAddress()
        };
    }

    /**
     * Genera un AsociadoDTO con datos ficticios coherentes.
     *
     * <b>pre:</b> la clase AsociadoDTO debe disponer de un constructor compatible
     * con los parámetros generados (dni, nombre completo, teléfono, ciudad y dirección) <br>
     * <b>post:</b> se devuelve una instancia no nula de AsociadoDTO,
     * con valores aleatorios para todos sus campos
     *
     * @return nuevo asociado con datos generados aleatoriamente
     */
    public AsociadoDTO generateUser() {
        String name = randomName();
        String surname = randomSurname();

        return new AsociadoDTO(
                randomDni(),
                name + " " + surname,
                randomPhone(),
                randomCity(),
                randomAddress()
        );
    }

    /**
     * Genera una lista de asociados con datos ficticios.
     *
     * <b>pre:</b> count debe ser mayor o igual que 0 <br>
     * <b>post:</b> se devuelve una lista no nula de tamaño count,
     * donde cada elemento es una instancia distinta de AsociadoDTO
     *
     * @param count cantidad de asociados a generar
     * @return lista de asociados generados aleatoriamente; vacía si count es 0
     */
    public List<AsociadoDTO> generateUsers(int count) {
        List<AsociadoDTO> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(generateUser());
        }
        return list;
    }
}
