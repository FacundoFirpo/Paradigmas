package juego;
public class JuegoMediano extends TiposDeJuego {

    public boolean cantJugadores( int jugadores){
        return jugadores == 6;
    }

    public int fichasIniciales(){
        return 9;
    }
}
