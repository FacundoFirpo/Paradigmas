package juego;

public class JuegoTerminado extends EstadoDeJuego {

    public void paga(Jugador jugador){
        throw new RuntimeException("Juego terminado");
    }

    public void toma(Jugador jugador, Carta carta){
        throw new RuntimeException("Juego terminado");
    }

}
