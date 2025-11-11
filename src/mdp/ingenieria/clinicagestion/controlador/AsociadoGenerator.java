package mdp.ingenieria.clinicagestion.controlador;

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

    public String[] generateUser() {
        String id = String.format("%08d", random.nextInt(100_000_000));
        String name = NAMES[random.nextInt(NAMES.length)];
        String surname = SURNAMES[random.nextInt(SURNAMES.length)];
        String phone = String.format("%03d %03d-%04d",
                random.nextInt(1000),
                random.nextInt(1000),
                random.nextInt(10000));
        String city = CITIES[random.nextInt(CITIES.length)];
        String address = ADDRESSES[random.nextInt(ADDRESSES.length)];

        return new String[]{id, name, surname, phone, city, address};
    }
}
