// Cell.java
package EngimonWorld;
public class Cell{
    private int x;
    private int y;
    private String symbol;
    /* list symbol :
           W = water hi level
           w = water low level
           I = ice hi level
           i = ice low level
           F = fire hi level
           f = fire low level
           G = ground hi level
           g = ground low level
           E = electric hi level
           e = electric low level
           L = fire electric hi level
           l = fire electric low level
           S = water ice hi level
           s = water ice low level
           N = water ground hi level
           n = water ground low level
           P = player
           X = active engimon
           - = grassland
           o = sea
        */
    private boolean isAdaEntity;

    public Cell(){
        this.x = 0;
        this.y = 0;
        this.symbol = "#";
        this.isAdaEntity = false;
    }

    public Cell(int xc,int yc, String sc, boolean ic){
        this.x = xc;
        this.y = yc;
        this.symbol = sc;
        this.isAdaEntity = ic;
    }

    public int getX(){
        return this.x;
    }

    public void setX(int xc){
        this.x = xc;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int yc){
        this.y = yc;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public void setSymbol(String sc){
        this.symbol = sc;
    }

    public boolean getIsAdaEntity(){
        return this.isAdaEntity;
    }

    public void setIsAdaEntity(boolean ic){
        this.isAdaEntity = ic;
    }
}