
import java.util.*;
// Fire/Electric engimon
public class Icemon extends Engimon  {
    public static final String[] skills = new String[]{"FreezingCloud 85 1 Ice","FrozenLotus 105 1 Ice","WintryIcicles 130 1 Ice","ColdWhitePrincesses 155 1 Ice","IceJuice 120 1 Ice"};
    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: brrrrrr, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch brrrrrr!");
        } else {
            System.out.printf("[%s]: brrrrrr, ugh sup daddy ;) %s is ready to help ya brrrrrr!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    
    //ctor
    public Icemon(String[] arg){
        super(arg);
        this.setSpecies(arg[0]);
        String[] elmts = arg[14].split("-");
        for (String elmt : elmts){
            this.element.add(elmt);
        }
    }
    public Icemon(){
        super();
        this.setSpecies("icemon");
        this.setElement("Ice");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Icemon(int x, int y){
        super(x,y);
        this.setSpecies("icemon");
        this.setElement("Ice");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Icemon(String _namaEngi, boolean _isLiar){
        super(_namaEngi, _isLiar);
        this.setSpecies("icemon");
        this.setElement("Ice");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Icemon(String _namaEngi, boolean _isLiar, String[] _namaParent, String[] _speciesParent){
        super(_namaEngi, _isLiar, _namaParent, _speciesParent);
        this.setSpecies("icemon");
        this.setElement("Ice");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
}