package juego;


import java.util.ArrayList;

public class JuegoEnProgreso extends EstadoDeJuego {

        public void paga(Jugador jugador){
            jugador.paga();
        }

        public void toma(Jugador jugador, ArrayList<Carta> cartas){
            jugador.toma(cartas.removeFirst());
        }

}
