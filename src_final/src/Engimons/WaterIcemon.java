
import java.util.*;

// Fire/Electric engimon
public class WaterIcemon extends Engimon {
    public static final String[] skills = new String[]{"Glacier 130 1 Water Ice","WinterCloud 150 1 Water Ice","WaterLilyBodhisattva 200 1 Water Ice","AirIce 100 1 Water Ice","EsBatuCair 120 1 Water Ice"};
    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: gukkgukkk, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch gukkgukkk!");
        } else {
            System.out.printf("[%s]: gukkgukkk, ugh sup daddy ;) %s is ready to help ya gukkgukkk!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    
    //ctor
    public WaterIcemon(String[] arg){
        super(arg);
        this.setSpecies(arg[0]);
        String[] elmts = arg[14].split("-");
        for (String elmt : elmts){
            this.element.add(elmt);
        }
    }
    public WaterIcemon(){
        super();
        this.setSpecies("watericemon");
        this.setElement("Water", "Ice");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public WaterIcemon(int x, int y){
        super(x,y);
        this.setSpecies("watericemon");
        this.setElement("Water", "Ice");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public WaterIcemon(String _namaEngi, boolean _isLiar){
        super(_namaEngi, _isLiar);
        this.setSpecies("watericemon");
        this.setElement("Water", "Ice");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public WaterIcemon(String _namaEngi, boolean _isLiar, String[] _namaParent, String[] _speciesParent){
        super(_namaEngi, _isLiar, _namaParent, _speciesParent);
        this.setSpecies("watericemon");
        this.setElement("Water", "Ice");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
}