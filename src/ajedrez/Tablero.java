package ajedrez;

import ajedrez.piezas.Alfil;
import ajedrez.piezas.Caballo;
import ajedrez.piezas.Peon;
import ajedrez.piezas.Pieza;
import ajedrez.piezas.Reina;
import ajedrez.piezas.Rey;
import ajedrez.piezas.Torre;

import java.util.Scanner;

/**
 * Tablero de 8x8: guarda las 32 piezas, dibuja el tablero en consola
 * y gestiona los turnos.
 */
public class Tablero {
    private final Pieza[] piezas = new Pieza[32];
    private boolean turno = Pieza.BLANCO;
    private final Scanner entrada = new Scanner(System.in);

    public Tablero() {
        int i = 0;

        // Peones
        for (int x = 0; x < 8; x++) {
            piezas[i++] = new Peon(x, 1, Pieza.BLANCO, this);
            piezas[i++] = new Peon(x, 6, Pieza.NEGRO, this);
        }

        // Piezas mayores: fila 0 para blancas, fila 7 para negras
        i = colocarFilaPrincipal(i, 0, Pieza.BLANCO);
        colocarFilaPrincipal(i, 7, Pieza.NEGRO);
    }

    private int colocarFilaPrincipal(int i, int y, boolean color) {
        piezas[i++] = new Torre(0, y, color, this);
        piezas[i++] = new Caballo(1, y, color, this);
        piezas[i++] = new Alfil(2, y, color, this);
        piezas[i++] = new Reina(3, y, color, this);
        piezas[i++] = new Rey(4, y, color, this);
        piezas[i++] = new Alfil(5, y, color, this);
        piezas[i++] = new Caballo(6, y, color, this);
        piezas[i++] = new Torre(7, y, color, this);
        return i;
    }

    /** Devuelve la pieza en juego que está en (x, y), o null si la casilla está vacía. */
    public Pieza piezaEn(int x, int y) {
        for (Pieza p : piezas) {
            if (p.enJuego() && p.getX() == x && p.getY() == y)
                return p;
        }
        return null;
    }

    public void dibujar() {
        System.out.println("   a  b  c  d  e  f  g  h");
        for (int y = 7; y >= 0; y--) {
            System.out.print((y + 1) + " ");
            for (int x = 0; x < 8; x++) {
                Pieza p = piezaEn(x, y);
                if (p != null) {
                    p.dibujar();
                } else {
                    System.out.print((x + y) % 2 == 0 ? Pieza.FONDO_OSCURO : Pieza.FONDO_CLARO);
                    System.out.print("   ");
                    System.out.print(Pieza.COLOR_RESET);
                }
            }
            System.out.println(" " + (y + 1));
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }

    /** El juego termina cuando uno de los reyes es capturado. */
    public boolean juegoTerminado() {
        boolean reyBlanco = false;
        boolean reyNegro = false;

        for (Pieza p : piezas) {
            if (p instanceof Rey && p.enJuego()) {
                if (p.getColor() == Pieza.BLANCO)
                    reyBlanco = true;
                else
                    reyNegro = true;
            }
        }

        if (!reyBlanco) {
            System.out.println("¡Piezas Negras ganan!");
            return true;
        }
        if (!reyNegro) {
            System.out.println("¡Piezas Blancas ganan!");
            return true;
        }
        return false;
    }

    /**
     * Pide un movimiento en notación de coordenadas (por ejemplo "e2e4") y lo ejecuta.
     *
     * @return false si ya no hay más entrada (fin del programa), true en otro caso
     */
    public boolean tomarTurno() {
        System.out.println(turno == Pieza.BLANCO ? "Turno de Blancas -> " : "Turno de Negras -> ");

        if (!entrada.hasNextLine())
            return false;
        String jugada = entrada.nextLine().trim().toLowerCase();

        if (jugada.length() != 4) {
            System.out.println("Instrucción inválida. Usa el formato 'e2e4'.");
            return true;
        }

        int x0 = jugada.charAt(0) - 'a';
        int y0 = jugada.charAt(1) - '1';
        int x = jugada.charAt(2) - 'a';
        int y = jugada.charAt(3) - '1';

        if (x0 < 0 || x0 > 7 || y0 < 0 || y0 > 7 || x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Coordenadas fuera del tablero.");
            return true;
        }

        Pieza pieza = piezaEn(x0, y0);
        boolean movimientoValido = pieza != null && pieza.getColor() == turno && pieza.mover(x, y);

        if (!movimientoValido) {
            System.out.println("Movimiento incorrecto");
            return true;
        }

        turno = !turno;
        return true;
    }
}
