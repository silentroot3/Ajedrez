package ajedrez.piezas;

import ajedrez.Tablero;

/** Reina: combina los movimientos de la torre y el alfil. */
public class Reina extends Pieza {
    public Reina(int x, int y, boolean color, Tablero tablero) {
        super(x, y, color, tablero);
    }

    @Override
    public boolean mover(int x, int y) {
        if (!dentroDelTablero(x, y))
            return false;

        int dx = Math.abs(getX() - x);
        int dy = Math.abs(getY() - y);

        // Diagonal, vertical u horizontal
        if (!(dx == dy || dx == 0 || dy == 0) || dx + dy == 0)
            return false;

        if (!caminoLibre(x, y))
            return false;

        return moverOCapturar(x, y);
    }

    @Override
    public void dibujar() {
        imprimir(" W ");
    }
}
