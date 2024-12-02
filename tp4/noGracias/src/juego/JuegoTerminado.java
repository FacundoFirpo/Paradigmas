package juego;

import java.util.ArrayList;

public class JuegoTerminado extends EstadoDeJuego {

    public void paga(Jugador jugador){
        throw new RuntimeException("Juego terminado");
    }

    public void toma(Jugador jugador, ArrayList<Carta> cartas){
        throw new RuntimeException("Juego terminado");
    }

}
