package ajedrez;

/** Punto de entrada: crea el tablero y alterna turnos hasta que se captura un rey. */
public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.dibujar();

        while (tablero.tomarTurno()) {
            tablero.dibujar();
            if (tablero.juegoTerminado()) {
                System.out.println("Juego terminado.");
                break;
            }
        }
    }
}
