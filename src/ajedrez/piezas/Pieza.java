package ajedrez.piezas;

import ajedrez.Tablero;

/**
 * Clase base de todas las piezas del tablero.
 * Guarda la posición (x, y), el color y si la pieza sigue en juego.
 */
public abstract class Pieza {
    public static final boolean BLANCO = true;
    public static final boolean NEGRO = false;

    // Colores ANSI para la consola
    public static final String COLOR_BLANCO = "\u001B[38;5;51m";
    public static final String COLOR_NEGRO = "\u001B[38;5;196m";
    public static final String FONDO_OSCURO = "\u001B[48;5;0m";
    public static final String FONDO_CLARO = "\u001B[48;5;235m";
    public static final String COLOR_RESET = "\u001B[0m";

    private int x;
    private int y;
    private final boolean color;
    private boolean enJuego;

    protected final Tablero tablero;

    protected Pieza(int x, int y, boolean color, Tablero tablero) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.tablero = tablero;
        this.enJuego = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getColor() {
        return color;
    }

    public boolean enJuego() {
        return enJuego;
    }

    /** Marca la pieza como capturada. */
    public void capturar() {
        enJuego = false;
    }

    /**
     * Intenta mover la pieza a (x, y). Cada pieza valida sus propias reglas
     * y, si el movimiento es válido, llama a esta implementación para actualizar la posición.
     */
    public boolean mover(int x, int y) {
        return colocarEn(x, y);
    }

    /** Actualiza la posición sin validar reglas (las reglas ya se revisaron antes). */
    protected final boolean colocarEn(int x, int y) {
        this.x = x;
        this.y = y;
        return true;
    }

    /** Imprime la pieza en la consola con su color y el color de su casilla. */
    public abstract void dibujar();

    protected void imprimir(String simbolo) {
        System.out.print(color == BLANCO ? COLOR_BLANCO : COLOR_NEGRO);
        System.out.print((x + y) % 2 == 0 ? FONDO_OSCURO : FONDO_CLARO);
        System.out.print(simbolo);
        System.out.print(COLOR_RESET);
    }

    /** Verifica que las coordenadas estén dentro del tablero de 8x8. */
    protected static boolean dentroDelTablero(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    /**
     * Revisa que no haya piezas entre la posición actual y (x, y), sin incluir el destino.
     * Sirve para movimientos en línea recta o diagonal (torre, alfil y reina).
     */
    protected boolean caminoLibre(int x, int y) {
        int pasoX = Integer.compare(x, getX());
        int pasoY = Integer.compare(y, getY());
        int actualX = getX() + pasoX;
        int actualY = getY() + pasoY;

        while (actualX != x || actualY != y) {
            if (tablero.piezaEn(actualX, actualY) != null)
                return false;
            actualX += pasoX;
            actualY += pasoY;
        }
        return true;
    }

    /**
     * Revisa la casilla destino: si hay una pieza propia el movimiento no es válido;
     * si hay una pieza rival, la captura. Al final mueve la pieza.
     */
    protected boolean moverOCapturar(int x, int y) {
        Pieza destino = tablero.piezaEn(x, y);
        if (destino != null) {
            if (destino.getColor() == getColor())
                return false;
            destino.capturar();
        }
        return colocarEn(x, y);
    }
}
