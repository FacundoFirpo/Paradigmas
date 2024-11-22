package juego;

public class Carta {
    private int valor;
    private int fichas = 0;

    public Carta(int valor){
        if (valor < 3 || valor > 35){
            throw new RuntimeException("Numero de carta inexistente");
        }
        this.valor = valor;
    }

    public int valor(){
        return (-1)*valor;
    }

    public void pagaron(){
        fichas += 1;
    }

    public int fichas(){
        return fichas;
    }
}
