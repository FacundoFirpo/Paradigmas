package juego;

import java.util.ArrayList;

public abstract class EstadoDeJuego {
    public abstract void paga(Jugador jugador);
    public abstract void toma(Jugador jugador, ArrayList<Carta> cartas);
}
