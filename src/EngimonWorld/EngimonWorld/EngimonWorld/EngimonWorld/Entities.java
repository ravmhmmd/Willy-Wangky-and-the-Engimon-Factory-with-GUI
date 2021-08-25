package EngimonWorld;

abstract class Entities {
  protected int posX;
  protected int posY;
  protected int turn;

  public Entities() {
    turn = 0;
    posX = 0;
    posY = 0;
  }

  public Entities(int x, int y) {
    posX = x;
    posY = y;
  }

  public Entities(Entities other){
    posX = other.posX;
    posY = other.posY;
  }

  public int getX(){
    return posX;
  }
  public int getY(){
    return posY;
  }
  public void setX(int x){
    posX = x;
  }
  public void setY(int y){
    posY = y;
  }

  public abstract void move(int x, int y);
}