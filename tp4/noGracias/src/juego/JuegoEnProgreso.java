package juego;


public class JuegoEnProgreso extends EstadoDeJuego {

        public void paga(Jugador jugador){
            jugador.paga();
        }

        public void toma(Jugador jugador, Carta carta){
            jugador.toma(carta);
        }

}
