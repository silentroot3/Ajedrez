
public abstract class figura {
    //Variables para el funcionamiento de las piezas
    private final boolean activa;
    private int x;
    private int y;
    private boolean color;
    private boolean state;

    static public boolean Blanco = true;
    static public boolean Negro = false;

    String color_blanco = "\u001B[38;5;51m";
    String color_negro = "\u001B[38;5;196m";

    String color_rojo_fondo = "\u001B[48;5;0m";
    String color_verde_fondo = "\u001B[48;5;235m";

    String color_reset="\u001B[0m";

    TABLERO tablero;

    public figura(int x, int y, boolean color, TABLERO tablero) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.tablero = tablero;
        this.activa = true;
        state = true;
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
    public boolean getState() {
        return state;
    }

    void matar() {
        state = false;
    }

    public boolean mover(int x, int y){
        this.x = x;
        this.y = y;
        return true;
    }

    abstract public void dibujar();
    public boolean isActiva() {
        return activa;
    }

    void printChart(String color){
        if(getColor()==figura.Blanco)
            System.out.print(color_blanco);
        else
            System.out.print(color_negro);

        if(getX()%2== 0 && getY()%2==0 || getX()%2==1 && getY()%2==1)
            System.out.print(color_rojo_fondo);
        else
            System.out.print(color_verde_fondo);

        System.out.print(color);
        System.out.print(color_reset);
    }
}
