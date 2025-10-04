package sistema.gestion.clinica.Interfaces;

public interface IProtocoloOcuparSala {
    void ocuparSala();
    void ocuparSalaNino(IProtocoloOcuparSala ocuparSala);
    void ocuparSalaJoven(IProtocoloOcuparSala ocuparSala);
    void ocuparSalaMayor(IProtocoloOcuparSala ocuparSala);
}
