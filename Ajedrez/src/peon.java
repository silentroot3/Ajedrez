public class peon extends figura {

    peon(int x, int y, boolean color,TABLERO tablero) {
        super(x, y, color,tablero);
    }

    @Override
    public boolean mover(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7)
            return false;

        int direction = (getColor() == Blanco) ? 1 : -1; // Blancos suben, negros bajan.
        boolean isInitial = (getColor() == Blanco && getY() == 1) || (getColor() == Negro && getY() == 6);
                                                            // Movimiento simple hacia adelante.
        if (x == getX() && y == getY() + direction) {
            for (figura f : tablero.figuras) {
                if (f.getX() == x && f.getY() == y && f.getState())
                    return false;
            }
            super.mover(x, y);
            tablero.dibujar();
            return true;
        }

                                                             // Movimiento doble desde posición inicial.
        if (isInitial && x == getX() && y == getY() + 2 * direction) {
            for (figura f : tablero.figuras) {
                if ((f.getX() == x && f.getY() == getY() + direction) || (f.getX() == x && f.getY() == y))
                    return false;
            }
            super.mover(x, y);
            tablero.dibujar();
            return true;
        }
                                                            // Captura en diagonal.
        if (Math.abs(x - getX()) == 1 && y == getY() + direction) {
            for (figura f : tablero.figuras) {
                if (f.getX() == x && f.getY() == y && f.getState() && f.getColor() != getColor()) {
                    f.matar();
                    super.mover(x, y);
                    tablero.dibujar();
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void dibujar() {
        printChart(" P ");
    }

}
