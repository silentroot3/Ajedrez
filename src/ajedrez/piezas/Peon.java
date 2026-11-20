package ajedrez.piezas;

import ajedrez.Tablero;

/**
 * Peón: avanza una casilla (o dos desde su posición inicial)
 * y captura en diagonal hacia adelante.
 */
public class Peon extends Pieza {
    public Peon(int x, int y, boolean color, Tablero tablero) {
        super(x, y, color, tablero);
    }

    @Override
    public boolean mover(int x, int y) {
        if (!dentroDelTablero(x, y))
            return false;

        int direccion = (getColor() == BLANCO) ? 1 : -1; // Blancas suben, negras bajan
        boolean posicionInicial = (getColor() == BLANCO && getY() == 1)
                || (getColor() == NEGRO && getY() == 6);

        // Avance de una casilla
        if (x == getX() && y == getY() + direccion) {
            if (tablero.piezaEn(x, y) != null)
                return false;
            return colocarEn(x, y);
        }

        // Avance de dos casillas desde la posición inicial
        if (posicionInicial && x == getX() && y == getY() + 2 * direccion) {
            if (tablero.piezaEn(x, getY() + direccion) != null || tablero.piezaEn(x, y) != null)
                return false;
            return colocarEn(x, y);
        }

        // Captura en diagonal
        if (Math.abs(x - getX()) == 1 && y == getY() + direccion) {
            Pieza rival = tablero.piezaEn(x, y);
            if (rival != null && rival.getColor() != getColor()) {
                rival.capturar();
                return colocarEn(x, y);
            }
        }

        return false;
    }

    @Override
    public void dibujar() {
        imprimir(" P ");
    }
}
