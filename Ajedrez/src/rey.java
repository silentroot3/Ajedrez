
public class rey extends figura {
    rey(int x, int y, boolean color,TABLERO tablero) {
        super(x, y, color,tablero);
    }

   @Override
   public boolean mover(int x, int y) {
        if(x<0 || x>7 || y<0 || y >7)
            return false;

        if(!((Math.abs(getX()-x) == 1 || Math.abs(getY()-y) == 1) &&
                Math.abs(getX()-x) <= 1 && Math.abs(getY()-y) <=1))
            return false;

        for(int i=0 ; i<32 ; i++) {

            if (tablero.figuras[i].getX() == x &&
                    tablero.figuras[i].getY() == y &&
                    tablero.figuras[i].isActiva()){

                if (tablero.figuras[i].getColor() == getColor())
                    return false;
                else {
                    tablero.figuras[i].matar();
                    break;

                }
            }
        }
        super.mover(x,y);
        tablero.dibujar();
        return true;
    }

    @Override
    public void dibujar() {
        printChart(" R ");
    }

}
