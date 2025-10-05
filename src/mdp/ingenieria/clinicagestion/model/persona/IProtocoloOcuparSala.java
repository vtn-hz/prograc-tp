package mdp.ingenieria.clinicagestion.model.persona;

public interface IProtocoloOcuparSala {
    void ocuparSala();
    void ocuparSalaNino(IProtocoloOcuparSala ocuparSala);
    void ocuparSalaJoven(IProtocoloOcuparSala ocuparSala);
    void ocuparSalaMayor(IProtocoloOcuparSala ocuparSala);
}
