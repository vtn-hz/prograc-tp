package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsociadoGenerator {
    private final Random random = new Random();

    private static final String[] NAMES = {
            "Juan", "María", "Carlos", "Lucía", "Pedro", "Sofía", "Javier", "Ana",
            "Diego", "Valentina", "Agustín", "Victoria", "Nicolás", "Camila",
            "Martín", "Julieta", "Facundo", "Florencia", "Tomás", "Milagros",
            "Emilia", "Mateo", "Lautaro", "Abril"
    };

    private static final String[] SURNAMES = {
            "Gómez", "Fernández", "Rodríguez", "Pérez", "López", "Martínez",
            "Sánchez", "Ramírez", "Torres", "Álvarez", "Romero", "Suárez",
            "Herrera", "Aguirre", "Molina", "Silva", "Ortiz", "Benítez",
            "Castro", "Domínguez"
    };

    private static final String[] CITIES = {
            "Buenos Aires", "Córdoba", "Rosario", "Mendoza", "La Plata", "Salta",
            "Tucumán", "Mar del Plata", "Santa Fe", "Resistencia", "Corrientes",
            "Neuquén", "San Juan", "San Luis", "Posadas", "Bahía Blanca",
            "Paraná", "Rawson", "Bariloche"
    };

    private static final String[] STREET_NAMES = {
            "Av. Luro", "San Martín", "Belgrano", "Mitre",
            "Rivadavia", "9 de Julio", "Independencia", "Sarmiento",
            "Hipólito Yrigoyen", "Las Heras", "Perón", "Lavalle",
            "Pueyrredón", "Dorrego", "Catamarca", "Jujuy", "La Rioja"
    };

    private static final String[] AR_AREA_CODES = {
            "11", "221", "223", "261", "341", "351", "381", "299", "387", "376"
    };

    private String randomDni() {
        return String.format("%08d", random.nextInt(100_000_000));
    }

    private String randomName() {
        return NAMES[random.nextInt(NAMES.length)];
    }

    private String randomSurname() {
        return SURNAMES[random.nextInt(SURNAMES.length)];
    }

    private String randomPhone() {
        String area = AR_AREA_CODES[random.nextInt(AR_AREA_CODES.length)];
        int first = 100 + random.nextInt(900);
        int second = 1000 + random.nextInt(9000);
        return area + " " + first + "-" + second;
    }

    private String randomCity() {
        return CITIES[random.nextInt(CITIES.length)];
    }

    private String randomAddress() {
        String street = STREET_NAMES[random.nextInt(STREET_NAMES.length)];
        int number = 1 + random.nextInt(4000);
        return street + " " + number;
    }

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

    public List<AsociadoDTO> generateUsers(int count) {
        List<AsociadoDTO> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(generateUser());
        }
        return list;
    }
}
