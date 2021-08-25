package EngimonWorld.Engimons;
import EngimonWorld.*;
import java.util.*;

// Fire/Electric engimon
public class Watermon extends Engimon  {
    public static final String[] skills = new String[]{"WaterWheel 90 1 Water","WaterfallBasin 105 1 Water","Whirlpool 120 1 Water","ConstantFlux 150 1 Water","AirMancur 100 1 Water"};

    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: sini lo gue mandiin, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch ratatataw!");
        } else {
            System.out.printf("[%s]: Ugh sup daddy ;) %s is ready to help ya, sini lo gue mandiin!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    
    //ctor
    public Watermon(String[] arg){
        super(arg);
        this.setSpecies(arg[0]);
        String[] elmts = arg[14].split("-");
        for (String elmt : elmts){
            this.element.add(elmt);
        }
    }
    public Watermon(){
        super();
        this.setSpecies("watermon");
        this.setElement("Water");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Watermon(int x, int y){
        super(x,y);
        this.setSpecies("watermon");
        this.setElement("Water");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Watermon(String _namaEngi, boolean _isLiar){
        super(_namaEngi, _isLiar);
        this.setSpecies("watermon");
        this.setElement("Water");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Watermon(String _namaEngi, boolean _isLiar, String[] _namaParent, String[] _speciesParent){
        super(_namaEngi, _isLiar, _namaParent, _speciesParent);
        this.setSpecies("watermon");
        this.setElement("Water");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
}