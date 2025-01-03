

public class torre extends figura {
    torre(int x, int y, boolean color,TABLERO tablero) {
        super(x, y, color,tablero);
    }

    @Override
    public boolean mover(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7)
            return false;

        int dx = Math.abs(getX() - x);
        int dy = Math.abs(getY() - y);

        // Verificar si el movimiento es recto (horizontal o vertical).
        if (!(dx == 0 || dy == 0))
            return false;

        // Verificar obstrucciones en el camino.
        int stepX = Integer.compare(x, getX());
        int stepY = Integer.compare(y, getY());
        int currentX = getX() + stepX;
        int currentY = getY() + stepY;

        while (currentX != x || currentY != y) {
            for (figura f : tablero.figuras) {
                if (f.getX() == currentX && f.getY() == currentY && f.getState())
                    return false;
            }
            currentX += stepX;
            currentY += stepY;
        }

        // Verificar la posición final.
        for (figura f : tablero.figuras) {
            if (f.getX() == x && f.getY() == y && f.getState()) {
                if (f.getColor() == getColor())
                    return false;

                // Capturar pieza enemiga.
                f.matar();
                break;
            }
        }

        // Mover la torre.
        super.mover(x, y);
        tablero.dibujar();
        return true;
    }

    @Override
    public void dibujar() {
        printChart(" T ");
    }

}
