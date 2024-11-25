package juego;

public class JuegoChico extends TiposDeJuego {

    public boolean cantJugadores( int jugadores){
        return jugadores >= 3 && jugadores <= 5;
    }

    public int fichasIniciales(){
        return 11;
    }
}
