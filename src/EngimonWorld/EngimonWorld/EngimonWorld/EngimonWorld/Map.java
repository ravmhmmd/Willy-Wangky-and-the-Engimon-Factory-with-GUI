// Map.java
package EngimonWorld;
import java.util.*;
public class Map{
    private int x;
    private int y;
    private Cell[][] maps;
    private Cell[][] entityMaps;

    public Map(){
        GameFile inputMapz = new GameFile("C:\\Users\\ASUS\\Documents\\IF2210_TB_04_buruhNilai\\src\\EngimonWorld\\EngimonWorld\\EngimonWorld\\EngimonWorld\\mapFile.txt"," ");
        List<String[]> testBacaMap = inputMapz.read();

        this.x = Integer.parseInt(testBacaMap.get(0)[0]);
        this.y = Integer.parseInt(testBacaMap.get(1)[0]);
        this.entityMaps = new Cell[y][x];
        this.maps = new Cell[y][x];

        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                entityMaps[i][j] = new Cell(j,i,"#",false);
                if (testBacaMap.get(2+i)[j].equals("o")){
                    maps[i][j] = new Cell(j,i,"o",false);
                }
                else if (testBacaMap.get(2+i)[j].equals("^")){
                    maps[i][j] = new Cell(j,i,"^",false);
                }
                else if (testBacaMap.get(2+i)[j].equals("*")){
                    maps[i][j] = new Cell(j,i,"*",false);
                }
                else if (testBacaMap.get(2+i)[j].equals("-")){
                    maps[i][j] = new Cell(j,i,"-",false);
                }
            }
        }   
    }

    public Map (List<String[]> testBacaMap){
        this.x = Integer.parseInt(testBacaMap.get(0)[0]);
        this.y = Integer.parseInt(testBacaMap.get(1)[0]);
        this.entityMaps = new Cell[y][x];
        this.maps = new Cell[y][x];

        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                entityMaps[i][j] = new Cell(j,i,"#",false);
                if (testBacaMap.get(2+i)[j].equals("o")){
                    maps[i][j] = new Cell(j,i,"o",false);
                }
                else if (testBacaMap.get(2+i)[j].equals("^")){
                    maps[i][j] = new Cell(j,i,"^",false);
                }
                else if (testBacaMap.get(2+i)[j].equals("*")){
                    maps[i][j] = new Cell(j,i,"*",false);
                }
                else if (testBacaMap.get(2+i)[j].equals("-")){
                    maps[i][j] = new Cell(j,i,"-",false);
                }
            }
        } 
    }

    public List<String[]> export(){
      List<String[]> out = new ArrayList<>();
      out.add(new String[] {Integer.toString(x)});
      out.add(new String[] {Integer.toString(y)});
      for(int i = 0; i < y; i++){
        List<String> row = new ArrayList<>();
        for (int j = 0; j < x; j++){
          row.add(maps[i][j].getSymbol());
        }
        out.add(row.toArray(new String[0]));
      }
      return out;
    }

    public List<String[]> toList(){
        List<String[]> out = new ArrayList<>();
        for(int i = 0; i < y; i++){
          List<String> row = new ArrayList<>();
          for (int j = 0; j < x; j++){
            if (entityMaps[i][j].getSymbol().equals("#")){
                row.add(maps[i][j].getSymbol());
            } else{
                row.add(entityMaps[i][j].getSymbol());
            }
          }
          out.add(row.toArray(new String[0]));
        }
        return out;
      }

    public int getYsize(){
        return y;
    }

    public int getXsize(){
        return x;
    }

    public boolean isSea(int xm, int ym){
        if (maps[ym][xm].getSymbol().equals("o")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isGrassLand(int xm, int ym){
        if (maps[ym][xm].getSymbol().equals("-")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isMountain(int xm, int ym){
        if (maps[ym][xm].getSymbol().equals("^")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isTundra(int xm, int ym){
        if (maps[ym][xm].getSymbol().equals("*")){
            return true;
        }
        else{
            return false;
        }
    }

    public void spawnEngimonLiar(Engimon e){
        engimonInisiasi(e);
    }

    public boolean isAdaPlayer(int xm, int ym){
        if (entityMaps[ym][xm].getSymbol().equals("P")) {
            return true;
        }
        else {
            return false;
        }
    }

    public void playerInisiasi(Player p){
        int xPosInMap = p.getX();
        int yPosInMap = p.getY();
        entityMaps[yPosInMap][xPosInMap] = new Cell(xPosInMap,yPosInMap,"P",true);
        maps[yPosInMap][xPosInMap].setIsAdaEntity(true);
    }

    public void engimonInisiasi(Engimon e){
        int minLeveltoCaps = 30;
        int xPosinMap = e.getX();
        int yPosinMap = e.getY();
        int levEng = e.getLevel();
        String specEng = e.getSpecies();
        entityMaps[yPosinMap][xPosinMap] = new Cell(xPosinMap,yPosinMap,"#",true);
        if (e.getisActive() == true){
            entityMaps[yPosinMap][xPosinMap].setSymbol("X");
        }
        else if (e.getisLiar() == true){
            if(levEng >= minLeveltoCaps){
                if(specEng.equals("watermon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("W");
                }
                else if(specEng.equals("watericemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("S");
                }
                else if(specEng.equals("watergroundmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("N");
                }
                else if(specEng.equals("icemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("I");
                }
                else if(specEng.equals("groundmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("G");
                }
                else if(specEng.equals("firemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("F");
                }
                else if(specEng.equals("fireelectricmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("L");
                }
                else if(specEng.equals("electricmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("E");
                }
            }
            else if(levEng < minLeveltoCaps){
                if(specEng.equals("watermon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("w");
                }
                else if(specEng.equals("watericemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("s");
                }
                else if(specEng.equals("watergroundmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("n");
                }
                else if(specEng.equals("icemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("i");
                }
                else if(specEng.equals("groundmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("g");
                }
                else if(specEng.equals("firemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("f");
                }
                else if(specEng.equals("fireelectricmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("l");
                }
                else if(specEng.equals("electricmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("e");
                }
            }
        }
        maps[yPosinMap][xPosinMap].setIsAdaEntity(true);
    }

    public void pseudomovePlayer(int beforeX, int beforeY, Player p){
        int afterX = p.getX();
        int afterY = p.getY();
        entityMaps[beforeY][beforeX].setX(beforeX);
        entityMaps[beforeY][beforeX].setY(beforeY);
        entityMaps[beforeY][beforeX].setSymbol("#");
        entityMaps[beforeY][beforeX].setIsAdaEntity(false);
        maps[beforeY][beforeX].setIsAdaEntity(false);
        entityMaps[afterY][afterX].setX(afterX);
        entityMaps[afterY][afterX].setY(afterY);
        entityMaps[afterY][afterX].setSymbol("P");
        entityMaps[afterY][afterX].setIsAdaEntity(true);
        maps[afterY][afterX].setIsAdaEntity(true);
    }

    public void pseudomoveEngimon(int beforeX, int beforeY, Engimon e){
        int minLeveltoCaps = 30;
        int xPosinMap = e.getX();
        int yPosinMap = e.getY();
        int levEng = e.getLevel();
        String specEng = e.getSpecies();
        entityMaps[yPosinMap][xPosinMap].setIsAdaEntity(true);
        entityMaps[beforeY][beforeX].setSymbol("#");
        entityMaps[beforeY][beforeX].setIsAdaEntity(false);
        maps[beforeY][beforeX].setIsAdaEntity(false);
        if (e.getisActive() == true){
            entityMaps[yPosinMap][xPosinMap].setSymbol("X");
        }
        else if (e.getisLiar() == true){
            if(levEng >= minLeveltoCaps){
                if(specEng.equals("watermon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("W");
                }
                else if(specEng.equals("watericemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("S");
                }
                else if(specEng.equals("watergroundmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("N");
                }
                else if(specEng.equals("icemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("I");
                }
                else if(specEng.equals("groundmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("G");
                }
                else if(specEng.equals("firemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("F");
                }
                else if(specEng.equals("fireelectricmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("L");
                }
                else if(specEng.equals("electricmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("E");
                }
            }
            else if(levEng < minLeveltoCaps){
                if(specEng.equals("watermon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("w");
                }
                else if(specEng.equals("watericemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("s");
                }
                else if(specEng.equals("watergroundmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("n");
                }
                else if(specEng.equals("icemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("i");
                }
                else if(specEng.equals("groundmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("g");
                }
                else if(specEng.equals("firemon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("f");
                }
                else if(specEng.equals("fireelectricmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("l");
                }
                else if(specEng.equals("electricmon")){
                    entityMaps[yPosinMap][xPosinMap].setSymbol("e");
                }
            }
        }
        maps[yPosinMap][xPosinMap].setIsAdaEntity(true);
    }

    public void printMap(){
        for(int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                if (entityMaps[i][j].getSymbol().equals("#")){
                    System.out.print(maps[i][j].getSymbol()+ " ");
                }
            else{
                    System.out.print(entityMaps[i][j].getSymbol()+ " ");;
                }
            }
            System.out.println();;
        }
    }
    public boolean isAdaEntity(int x, int y){
        return entityMaps[y][x].getIsAdaEntity() || maps[y][x].getIsAdaEntity();
    }

    public void removeFromMap(int x, int y){
        entityMaps[y][x].setSymbol("#");
        entityMaps[y][x].setIsAdaEntity(false);
        maps[y][x].setIsAdaEntity(false);
    }
}