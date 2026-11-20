package ajedrez.piezas;

import ajedrez.Tablero;

/** Torre: se mueve en línea recta, horizontal o vertical. */
public class Torre extends Pieza {
    public Torre(int x, int y, boolean color, Tablero tablero) {
        super(x, y, color, tablero);
    }

    @Override
    public boolean mover(int x, int y) {
        if (!dentroDelTablero(x, y))
            return false;

        int dx = Math.abs(getX() - x);
        int dy = Math.abs(getY() - y);

        // Debe ser horizontal o vertical (y no quedarse en el mismo lugar)
        if (!(dx == 0 || dy == 0) || dx + dy == 0)
            return false;

        if (!caminoLibre(x, y))
            return false;

        return moverOCapturar(x, y);
    }

    @Override
    public void dibujar() {
        imprimir(" T ");
    }
}
