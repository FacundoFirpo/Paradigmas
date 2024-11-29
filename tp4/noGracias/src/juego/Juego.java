package juego;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private ArrayList<Carta> pilon;
    private Jugador turnoDe;
    private static ArrayList<TiposDeJuego> tiposDeJuego = new ArrayList<>(List.of( new JuegoChico(), new JuegoMediano(), new JuegoGrande() ));

    public Juego(ArrayList<String> jugadores, ArrayList<Carta> mazo){

        TiposDeJuego modoDeJuego = tiposDeJuego.stream()
                .filter(tipo -> tipo.cantJugadores(jugadores.size()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cantidad de jugadores invalida"));

        jugadores.forEach(jug -> this.jugadores.add(new Jugador(jug, modoDeJuego.fichasIniciales())));

        this.pilon = mazo;
        this.turnoDe = this.jugadores.getFirst();
    }

    public int puntajeDe(String jugador){
        Jugador objetivo = buscarJugador(jugador);
        return objetivo.puntaje();
    }

    public void toma(String jugador){
        Jugador objetivo = buscarJugador(jugador);
        esTurno(objetivo);
        objetivo.toma(pilon.removeFirst());
        pasarTurno();
    }

    public void paga(String jugador){
        Jugador objetivo = buscarJugador(jugador);
        esTurno(objetivo);
        objetivo.paga();
        pilon.getFirst().pagaron();
        pasarTurno();
    }

    public void esTurno(Jugador objetivo){
        if (turnoDe != objetivo){
            throw new RuntimeException("No es el turno de " + objetivo.nombre());
        }
    }

    private void pasarTurno() {
        turnoDe = jugadores.get((jugadores.indexOf(turnoDe) + 1) % jugadores.size());
    }

    private Jugador buscarJugador(String jugador) {
        return jugadores.stream()
                .filter(jug -> jug.nombre() == jugador)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    public ArrayList<Carta> getPilon(){
        return pilon;
    }

}
