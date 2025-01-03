import java.io.IOException;
public class TABLERO {
    public figura figuras[] = new figura[32];
    private boolean turno = figura.Blanco; // Variable para gestionar el turno

    public TABLERO() {
        int cont = 0;
        // Inicialización de peones blancos
        for (int i = 0; i < 8; i++) {
            figuras[cont] = new peon(i, 1, figura.Blanco, this);
            cont++;
        }

        // Inicialización de peones negros
        for (int i = 0; i < 8; i++) {
            figuras[cont] = new peon(i, 6, figura.Negro, this);
            cont++;
        }

        // Inicialización de piezas blancas
        figuras[cont++] = new torre(0, 0, figura.Blanco, this);
        figuras[cont++] = new caballo(1, 0, figura.Blanco, this);
        figuras[cont++] = new alfil(2, 0, figura.Blanco, this);
        figuras[cont++] = new reina(3, 0, figura.Blanco, this);
        figuras[cont++] = new rey(4, 0, figura.Blanco, this);
        figuras[cont++] = new alfil(5, 0, figura.Blanco, this);
        figuras[cont++] = new caballo(6, 0, figura.Blanco, this);
        figuras[cont++] = new torre(7, 0, figura.Blanco, this);

        // Inicialización de piezas negras
        figuras[cont++] = new torre(0, 7, figura.Negro, this);
        figuras[cont++] = new caballo(1, 7, figura.Negro, this);
        figuras[cont++] = new alfil(2, 7, figura.Negro, this);
        figuras[cont++] = new reina(4, 7, figura.Negro, this);
        figuras[cont++] = new rey(3, 7, figura.Negro, this);
        figuras[cont++] = new alfil(5, 7, figura.Negro, this);
        figuras[cont++] = new caballo(6, 7, figura.Negro, this);
        figuras[cont++] = new torre(7, 7, figura.Negro, this);
    }

    void dibujar() {
        System.out.println("   a  b  c  d  e  f  g  h");
        for (int y = 7; y >= 0; y--) {
            System.out.print((y + 1) + " ");
            for (int x = 0; x < 8; x++) {
                boolean ocupado = false;
                for (int i = 0; i < 32; i++) {
                    if (figuras[i].getX() == x && figuras[i].getY() == y && figuras[i].getState()) {
                        figuras[i].dibujar();
                        ocupado = true;
                        break;
                    }
                }
                if (!ocupado) {
                    if ((x + y) % 2 == 0) {
                        System.out.print(figuras[0].color_rojo_fondo);
                    } else {
                        System.out.print(figuras[0].color_verde_fondo);
                    }
                    System.out.print("   "); // Espacio vacío para el cuadro
                    System.out.print(figuras[0].color_reset);
                }
            }
            System.out.println(" " + (y + 1));
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }

    boolean checkGameOver() {
        boolean whiteKingAlive = false;
        boolean blackKingAlive = false;

        for (figura f : figuras) {
            if (f instanceof rey && f.getState()) {
                if (f.getColor() == figura.Blanco) {
                    whiteKingAlive = true;
                } else {
                    blackKingAlive = true;
                }
            }
        }

        if (!whiteKingAlive) {
            System.out.println("¡Piezas Negras ganan!");
            return true;
        }
        if (!blackKingAlive) {
            System.out.println("¡Piezas Blancas ganan!");
            return true;
        }
        return false;
    }

    void tomar_turno() {
        byte[] buf = new byte[10];
        try {
            // Mostrar de quién es el turno
            if (turno == figura.Blanco) {
                System.out.println("Turno de Blancos -> ");
            } else {
                System.out.println("Turno de Negros -> ");
            }

            int len = System.in.read(buf);
            String instruction = new String(buf, 0, len).trim();
            if (instruction.length() != 4) {
                System.out.println("Instrucción inválida. Usa formato como 'a2a3'.");
                return;
            }

            byte x0 = (byte) (instruction.charAt(0) - 'a');
            byte y0 = (byte) (instruction.charAt(1) - '1');
            byte x = (byte) (instruction.charAt(2) - 'a');
            byte y = (byte) (instruction.charAt(3) - '1');

            // Validar que las coordenadas estén dentro del tablero
            if (x0 < 0 || x0 > 7 || y0 < 0 || y0 > 7 || x < 0 || x > 7 || y < 0 || y > 7) {
                System.out.println("Coordenadas fuera del tablero.");
                return;
            }

            boolean movCorr = false;
            for (int i = 0; i < 32; i++) {
                if (figuras[i].getX() == x0 && figuras[i].getY() == y0 && figuras[i].getState() && figuras[i].getColor() == turno) {
                    movCorr = figuras[i].mover(x, y);
                    break;
                }
            }
            if (!movCorr) {
                System.out.println("Movimiento incorrecto");
            } else {
                // Verificar si el juego ha terminado después del movimiento
                if (checkGameOver()) {
                    System.out.println("Juego terminado.");
                    System.exit(0); // Terminar el programa
                }
                turno = !turno; // Cambiar el turno
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

