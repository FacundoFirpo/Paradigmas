package juego;

public abstract class EstadoDeJuego {
    public abstract void paga(Jugador jugador);
    public abstract void toma(Jugador jugador, Carta carta);
}
