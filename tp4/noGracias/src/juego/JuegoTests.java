package juego;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTests {

    ArrayList<Carta> mazoDe3;

    @BeforeEach
    public void setUp(){
        Carta carta3 = new Carta(3);
        Carta carta4 = new Carta(4);
        Carta carta5 = new Carta(5);
        mazoDe3 = new ArrayList<>(List.of(carta3, carta4, carta5));
    }

    @Test
    public void test01JuegoDe3ArrancaConFichasCorrectas(){
        Juego juego = juegoDe3();
        assertEquals(11, juego.puntajeDe("Julio"));
        assertEquals(11, juego.puntajeDe("Emilio"));
        assertEquals(11, juego.puntajeDe("Bruno"));
    }

    @Test
    public void test02JuegoDe5ArrancaConPuntajeCorrecto(){
        Juego juego = new Juego(new ArrayList<>(List.of("Julio", "Emilio", "Bruno", "Pedro", "Facu")), mazoDe3);
        assertEquals(11, juego.puntajeDe("Julio"));
        assertEquals(11, juego.puntajeDe("Emilio"));
        assertEquals(11, juego.puntajeDe("Bruno"));
        assertEquals(11, juego.puntajeDe("Pedro"));
        assertEquals(11, juego.puntajeDe("Facu"));
    }

    @Test
    public void test03JuegoDe6ArrancaConPuntajeCorrecto(){
        ArrayList<String> jugadores = new ArrayList<>(List.of("Julio", "Emilio", "Bruno", "Pedro", "Facu", "Juan"));
        Juego juego = new Juego(jugadores, mazoDe3);
        assertEquals(9, juego.puntajeDe("Julio"));
        assertEquals(9, juego.puntajeDe("Emilio"));
        assertEquals(9, juego.puntajeDe("Bruno"));
        assertEquals(9, juego.puntajeDe("Pedro"));
        assertEquals(9, juego.puntajeDe("Facu"));
        assertEquals(9, juego.puntajeDe("Juan"));
    }

    @Test
    public void test04JuegoDe7ArrancaConPuntajeCorrecto(){
        Juego juego = juegoDe7();
        assertEquals(7, juego.puntajeDe("Julio"));
        assertEquals(7, juego.puntajeDe("Emilio"));
        assertEquals(7, juego.puntajeDe("Bruno"));
        assertEquals(7, juego.puntajeDe("Pedro"));
        assertEquals(7, juego.puntajeDe("Facu"));
        assertEquals(7, juego.puntajeDe("Juan"));
        assertEquals(7, juego.puntajeDe("Carlos"));
    }

    @Test
    public void test05JuegoDeMenosDe3NoSePuede(){
        ArrayList<String> jugadores = new ArrayList<>(List.of("Julio", "Emilio"));
        assertThrowsLike("Cantidad de jugadores invalida", () -> new Juego(jugadores, mazoDe3));
    }

    @Test
    public void test06JuegoDeMasDe7NoSePuede(){
        ArrayList<String> jugadores = new ArrayList<>(List.of("Julio", "Emilio", "Bruno", "Pedro", "Facu", "Juan", "Carlos", "Miguel"));
        assertThrowsLike("Cantidad de jugadores invalida", () -> new Juego(jugadores, mazoDe3));
    }

    @Test
    public void test07JugadorTomaCartaRestaPuntos(){
        Juego juego = juegoDe3().toma("Julio");
        assertEquals(8, juego.puntajeDe("Julio"));
        assertEquals(11, juego.puntajeDe("Emilio"));
        assertEquals(11, juego.puntajeDe("Bruno"));
    }

    @Test
    public void test08JugadorTomaCartaQuePagaron(){
        Juego juego = juegoDe3().paga("Julio").toma("Emilio");
        assertEquals(10, juego.puntajeDe("Julio"));
        assertEquals(9, juego.puntajeDe("Emilio"));
        assertEquals(11, juego.puntajeDe("Bruno"));
    }

    @Test
    public void test09JugadorTomaCuandoNoEsSuTurno(){
        assertThrowsLike("No es el turno de Emilio", () ->  juegoDe3().toma("Emilio"));
    }

    @Test
    public void test10JugadorPagaCuandoNoEsSuTurno(){
        assertThrowsLike("No es el turno de Emilio", () ->  juegoDe3().paga("Emilio"));
    }

    @Test
    public void test11JugadorQueNoEstaJugandoToma(){
        assertThrowsLike("Jugador no encontrado", () ->  juegoDe3().paga("Carlos"));
    }

    @Test
    public void test12JugadorQueNoEstaJugandoPaga(){
        assertThrowsLike("Jugador no encontrado", () ->  juegoDe3().paga("Carlos"));
    }

    @Test
    public void test13JugadorQueNoEstaJugandoPuntaje(){
        assertThrowsLike("Jugador no encontrado", () ->  juegoDe3().puntajeDe("Carlos"));
    }

    @Test
    public void test14JugadorSinFichasNoPuedepagar() {
        Juego juego = juegoDe7();
        todosPagan(juego);
        todosPagan(juego);
        todosPagan(juego);
        todosPagan(juego);
        todosPagan(juego);
        todosPagan(juego);
        todosPagan(juego);

        assertThrowsLike("No tiene fichas", () -> juego.paga("Julio"));
    }

    private void todosPagan(Juego juego) {
        juego.paga("Julio");
        juego.paga("Emilio");
        juego.paga("Bruno");
        juego.paga("Pedro");
        juego.paga("Facu");
        juego.paga("Juan");
        juego.paga("Carlos");
    }

    @Test
    public void test15CartaMenorATresInvalida() {
        assertThrowsLike("Numero de carta inexistente",() -> juegoDe3().getPilon().add(new Carta(2)));
    }

    @Test
    public void test16CartaMayorATreintaYCincoInvalida() {
        assertThrowsLike("Numero de carta inexistente",() -> juegoDe3().getPilon().add(new Carta(36)));
    }

    @Test
    public void test17TerminaElMazoYElJuego() {
        Juego juego = juegoDe3();

        juego.toma("Julio");
        juego.toma("Emilio");
        juego.toma("Bruno");

        assertEquals("El ganador es: Julio", juego.ganador());

        assertThrowsLike("Juego terminado", () -> juego.toma("Julio"));
        assertThrowsLike("Juego terminado", () -> juego.paga("Julio"));

    }

    private Juego juegoDe3() {
        return new Juego(new ArrayList<>(List.of("Julio", "Emilio", "Bruno")) , mazoDe3);
    }

    private Juego juegoDe7() {
        return new Juego(new ArrayList<>(List.of("Julio", "Emilio", "Bruno", "Pedro", "Facu", "Juan", "Carlos")), mazoDe3);
    }


    private static void assertThrowsLike( String expectedMsg, Executable expression ) {
        assertEquals( expectedMsg,
                assertThrows( RuntimeException.class, expression ).getMessage() );
    }
}
