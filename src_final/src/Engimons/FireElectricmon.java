
import java.util.*;

// Fire/Electric engimon
public class FireElectricmon extends Engimon  {
    public static final String[] skills = new String[]{"HeatLightning 135 1 Fire Electric",
    "FlamingThunderGod 160 1 Fire Electric",
    "BurningZeus 220 1 Fire Electric", "petiryangberapi 100 1 Fire Electric", "dewiperzik 120 1 Fire Electric"};

    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: Panaspanaspanas, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch Panaspanaspanas!");
        } else {
            System.out.printf("[%s]: Panaspanaspanas, ugh sup daddy ;) %s is ready to help ya Panaspanaspanas!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    
    //ctor
    public FireElectricmon(String[] arg){
        super(arg);
        this.setSpecies(arg[0]);
        String[] elmts = arg[14].split("-");
        for (String elmt : elmts){
            this.element.add(elmt);
        }
    }
    public FireElectricmon(){
        super();
        this.setSpecies("fireelectricmon");
        this.setElement("Fire","Electric");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public FireElectricmon(int x, int y){
        super(x,y);
        this.setSpecies("fireelectricmon");
        this.setElement("Fire","Electric");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public FireElectricmon(String _namaEngi, boolean _isLiar){
        super(_namaEngi, _isLiar);
        this.setSpecies("fireelectricmon");
        this.setElement("Fire","Electric");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public FireElectricmon(String _namaEngi, boolean _isLiar, String[] _namaParent, String[] _speciesParent){
        super(_namaEngi, _isLiar, _namaParent, _speciesParent);
        this.setSpecies("fireelectricmon");
        this.setElement("Fire","Electric");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
}