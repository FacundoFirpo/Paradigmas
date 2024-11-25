package juego;

public class JuegoGrande extends TiposDeJuego {

    public boolean cantJugadores( int jugadores){
        return jugadores == 7;
    }

    public int fichasIniciales(){
        return 7;
    }
}
