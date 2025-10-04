package sistema.gestion.clinica.Interfaces;

public interface IProtocoloOcuparSala {
    void ocuparSala(int numeroSala);
    void ocuparSalaNino(IProtocoloOcuparSala ocuparSala);
    void ocuparSalaJoven(IProtocoloOcuparSala ocuparSala);
    void ocuparSalaMayor(IProtocoloOcuparSala ocuparSala);
}
