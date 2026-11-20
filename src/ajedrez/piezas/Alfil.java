package ajedrez.piezas;

import ajedrez.Tablero;

/** Alfil: se mueve en diagonal. */
public class Alfil extends Pieza {
    public Alfil(int x, int y, boolean color, Tablero tablero) {
        super(x, y, color, tablero);
    }

    @Override
    public boolean mover(int x, int y) {
        if (!dentroDelTablero(x, y))
            return false;

        int dx = Math.abs(getX() - x);
        int dy = Math.abs(getY() - y);

        // Debe ser diagonal
        if (dx != dy || dx == 0)
            return false;

        if (!caminoLibre(x, y))
            return false;

        return moverOCapturar(x, y);
    }

    @Override
    public void dibujar() {
        imprimir(" A ");
    }
}
