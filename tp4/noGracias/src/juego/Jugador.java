package juego;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private int fichas;
    private ArrayList<Carta> pilon = new ArrayList<>();

    public Jugador(String nombre, int fichas){
        this.nombre = nombre;
        this.fichas = fichas;
    }

    public int puntaje(){
        return pilon.stream().reduce(0, (total,carta) -> total + carta.valor(), Integer::sum) + fichas;
    }

    public String nombre(){
        return nombre;
    }

    public void toma(Carta carta){
        pilon.add(carta);
        this.fichas += carta.fichas();
    }

    public void paga(){
        if (this.fichas == 0){
            throw new RuntimeException("No tiene fichas");
        }
        this.fichas -= 1;
    }
}
