package ajedrez.piezas;

import ajedrez.Tablero;

/** Rey: se mueve una casilla en cualquier dirección. */
public class Rey extends Pieza {
    public Rey(int x, int y, boolean color, Tablero tablero) {
        super(x, y, color, tablero);
    }

    @Override
    public boolean mover(int x, int y) {
        if (!dentroDelTablero(x, y))
            return false;

        int dx = Math.abs(getX() - x);
        int dy = Math.abs(getY() - y);

        if (dx > 1 || dy > 1 || dx + dy == 0)
            return false;

        return moverOCapturar(x, y);
    }

    @Override
    public void dibujar() {
        imprimir(" R ");
    }
}
