package ajedrez.piezas;

import ajedrez.Tablero;

/** Caballo: se mueve en "L" y puede saltar otras piezas. */
public class Caballo extends Pieza {
    public Caballo(int x, int y, boolean color, Tablero tablero) {
        super(x, y, color, tablero);
    }

    @Override
    public boolean mover(int x, int y) {
        if (!dentroDelTablero(x, y))
            return false;

        int dx = Math.abs(getX() - x);
        int dy = Math.abs(getY() - y);

        if (!((dx == 1 && dy == 2) || (dx == 2 && dy == 1)))
            return false;

        return moverOCapturar(x, y);
    }

    @Override
    public void dibujar() {
        imprimir(" C ");
    }
}
