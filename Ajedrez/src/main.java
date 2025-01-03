import java.io.IOException;
public class main {
    public static void main(String[] args) {
        TABLERO tablero = new TABLERO();
        tablero.dibujar();

        while (true) {
            tablero.tomar_turno();
            tablero.dibujar();
        }
    }
}