package mdp.ingenieria.clinicagestion.controller;

import mdp.ingenieria.clinicagestion.persistence.AsociadoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsociadoGenerator {
    private static final String[] NAMES = {
            "Juan", "María", "Carlos", "Lucía", "Pedro", "Sofía", "Javier", "Ana"
    };

    private static final String[] SURNAMES = {
            "Gómez", "Fernández", "Rodríguez", "Pérez", "López", "Martínez", "Sánchez", "Ramírez"
    };

    private static final String[] CITIES = {
            "Buenos Aires", "Córdoba", "Rosario", "Mendoza", "La Plata", "Salta", "Tucumán", "Mar del Plata"
    };

    private static final String[] ADDRESSES = {
            "Av. Luro 742", "Calle Falsa 123", "San Martín 450", "Belgrano 999",
            "Mitre 210", "Rivadavia 330", "9 de Julio 1200", "Independencia 678"
    };

    private final Random random = new Random();

    private String randomPhone() {
        return String.format("%03d %03d-%04d",
                random.nextInt(1000),
                random.nextInt(1000),
                random.nextInt(10000));
    }

    private String randomDni() {
        return String.format("%08d", random.nextInt(100_000_000));
    }

    private String randomName() {
        return NAMES[random.nextInt(NAMES.length)];
    }

    private String randomSurname() {
        return SURNAMES[random.nextInt(SURNAMES.length)];
    }

    private String randomCity() {
        return CITIES[random.nextInt(CITIES.length)];
    }

    private String randomAddress() {
        return ADDRESSES[random.nextInt(ADDRESSES.length)];
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
