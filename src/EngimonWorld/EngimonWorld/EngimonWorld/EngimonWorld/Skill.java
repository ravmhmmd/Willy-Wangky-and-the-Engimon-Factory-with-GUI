package EngimonWorld;
import java.util.*;

public class Skill implements InventoryItem, Comparable<Skill> {

    static final String DEFAULT_SKILL_NAME = "Crot";
    static final int DEFAULT_BASE_POWER = 69;
    static final int DEFAULT_MASTERY_LEVEL = 1;
    static final String DEFAULT_ELEMENT = "avatar";

    private String namaSkill;
    private int basePower;
    private int masteryLevel;
    private ArrayList<String> reqElement;


    @Override
    public int compareTo(Skill other){
        if (this.getMasteryLevel() == other.getMasteryLevel()){
            return this.getBasePower() - other.getBasePower();
        }
        return this.getMasteryLevel() - other.getMasteryLevel();
    }
    @Override
    public String toString(){
        return String.format("%s / %d", this.getNamaSkill(), this.getBasePower());
    }
    @Override
    public void printNama() {
        System.out.printf("%s / %d\n", this.getNamaSkill(), this.getBasePower());
    }
    @Override
    public void printInfo() {
        System.out.println("<< Info Skill >>");
        System.out.print("Nama Skill : ");
        System.out.println(this.getNamaSkill());
        System.out.print("Base Power : ");
        System.out.println(this.getBasePower());
        System.out.print("Mastery Level : ");
        System.out.println(this.getMasteryLevel());
        System.out.print("Element Pembentuk : \n");
        for (int i=0;i<reqElement.size();i++){
            System.out.printf("[%d] ",i+1);
            System.out.print(reqElement.get(i));
            System.out.println();
        }
    }
    @Override
    public String getNama(){
        return this.namaSkill;
    }

    public Skill (){
        namaSkill = DEFAULT_SKILL_NAME;
        basePower = DEFAULT_BASE_POWER;
        masteryLevel = DEFAULT_MASTERY_LEVEL;
        reqElement = new ArrayList<String>();
    }
    public Skill(String _nama, int _basePower, String elmt){
        namaSkill = _nama;
        basePower = _basePower;
        masteryLevel = DEFAULT_MASTERY_LEVEL;
        reqElement = new ArrayList<String>();
        reqElement.add(elmt);
    }
    public Skill(String _nama, int _basePower, String elmt1, String elmt2){
        namaSkill = _nama;
        basePower = _basePower;
        masteryLevel = DEFAULT_MASTERY_LEVEL;
        reqElement = new ArrayList<String>();
        reqElement.add(elmt1);
        reqElement.add(elmt2);
    }
    
    // ctor import load game
    public Skill(String[] arg){
        namaSkill = arg[0];
        basePower = Integer.parseInt(arg[1]);
        masteryLevel = Integer.parseInt(arg[2]);
        reqElement = new ArrayList<String>();
        reqElement.add(arg[3]);
        if (arg.length > 4){
            reqElement.add(arg[4]);    
        }
    }

    public void setMasteryLv(int _mastery){
        if (_mastery < 1 || _mastery > 3) {
            throw new IllegalArgumentException("Mastery level maksimum bernilai 3");
        } else {
            masteryLevel = _mastery;
        }
    }

    public void setReqElement(String _reqElement){
        if (_reqElement.equals("Fire")){
            reqElement.add("Fire");
        } else if (_reqElement.equals("Water")){
            reqElement.add("Water");
        } else if (_reqElement.equals("Electric")){
            reqElement.add("Electric");
        } else if (_reqElement.equals("Ground")){
            reqElement.add("Ground");
        } else if (_reqElement.equals("Ice")){
            reqElement.add("Ice");
        } else if (_reqElement.equals("FirexElectric")){
            reqElement.add("Fire");
            reqElement.add("Electric");
        } else if (_reqElement.equals("WaterxIce")){
            reqElement.add("Water");
            reqElement.add("Ice");
        } else if (_reqElement.equals("WaterxGround")){
            reqElement.add("Water");
            reqElement.add("Ground");
        }
    }

    public String getNamaSkill(){
        return this.namaSkill;
    }

    public int getBasePower(){
        return this.basePower;
    }
    
    public int getMasteryLevel(){
        return this.masteryLevel;
    }

    public String[] getReqElement(){
        return this.reqElement.toArray(new String[0]);
    }

    public String[] exportSkill(){
        String[] res = new String[] { this.namaSkill, Integer.toString(this.basePower), Integer.toString(this.masteryLevel), reqElement.get(0)};
        if (this.reqElement.size() > 1){
            res[3] = this.reqElement.get(1);
        }
        return res;
    }
}