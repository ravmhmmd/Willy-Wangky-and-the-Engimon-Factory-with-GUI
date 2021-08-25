
import java.util.*;

// Fire/Electric engimon
public class Firemon extends Engimon {
    public static final String[] skills = new String[]{"FlameTiger 95 1 Fire",
    "RisingScorchingSun 105 1 Fire","UnknowingFire 130 1 Fire","BlazingUniverse 155 1 Fire","ApiNeraka 200 1 Fire"};

    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: huhahuhhah panas, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch huhahuhhah panas!");
        } else {
            System.out.printf("[%s]: huhahuhhah panas, ugh sup daddy ;) %s is ready to help ya huhahuhhah panas!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    
    //ctor
    public Firemon(String[] arg){
        super(arg);
        this.setSpecies(arg[0]);
        String[] elmts = arg[14].split("-");
        for (String elmt : elmts){
            this.element.add(elmt);
        }
    }
    public Firemon(){
        super();
        this.setSpecies("firemon");
        this.setElement("Fire");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Firemon(int x, int y){
        super(x, y);
        this.setSpecies("firemon");
        this.setElement("Fire");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Firemon(String _namaEngi, boolean _isLiar){
        super(_namaEngi, _isLiar);
        this.setSpecies("firemon");
        this.setElement("Fire");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Firemon(String _namaEngi, boolean _isLiar, String[] _namaParent, String[] _speciesParent){
        super(_namaEngi, _isLiar, _namaParent, _speciesParent);
        this.setSpecies("firemon");
        this.setElement("Fire");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
}