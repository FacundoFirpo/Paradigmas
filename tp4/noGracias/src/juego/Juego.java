package juego;

import java.util.ArrayList;

public class Juego {

    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private ArrayList<Carta> pilon;

    public Juego(ArrayList<String> jugadores, ArrayList<Carta> mazo){
        if (jugadores.size() < 3 || jugadores.size() > 7){
            throw new RuntimeException("Cantidad de jugadores invalida");
        }

        if (jugadores.size() <=5 ){
            jugadores.forEach(jug -> this.jugadores.add(new Jugador(jug, 11)));
        } else if (jugadores.size() == 6) {
            jugadores.forEach(jug -> this.jugadores.add(new Jugador(jug, 9)));
        } else {
            jugadores.forEach(jug -> this.jugadores.add(new Jugador(jug, 7)));
        }

        this.pilon =mazo;
    }

    public int puntajeDe(String jugador){
        Jugador objetivo = buscarJugador(jugador);
        return objetivo.puntaje();
    }

    public void toma(String jugador){
        Jugador objetivo = buscarJugador(jugador);
        objetivo.toma(pilon.removeFirst());
    }

    public void paga(String jugador){
        Jugador objetivo = buscarJugador(jugador);
        objetivo.paga();
        pilon.getFirst().pagaron();
    }

    private Jugador buscarJugador(String jugador) {
        return jugadores.stream()
                .filter(jug -> jug.nombre() == jugador)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

}
