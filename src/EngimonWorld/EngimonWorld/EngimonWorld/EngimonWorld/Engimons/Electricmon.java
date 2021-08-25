package EngimonWorld.Engimons;
import EngimonWorld.*;
import java.util.*;

// Fire/Electric engimon
public class Electricmon extends Engimon {
    public static final String[] skills = new String[]{"ThunderClap 90 1 Electric","ThunderSwarm 110 1 Electric","DistantThunder 125 1 Electric","RumbleAndFlash 150 1 Electric","genHalilintar 100 1 Electric"};
    
    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: Zigggyzagggaaa, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch Zigggyzagggaaa!");
        } else {
            System.out.printf("[%s]: Zigggyzagggaaa, ugh sup daddy ;) %s is ready to help ya Zigggyzagggaaa!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    
    //ctor
    public Electricmon(String[] arg){
        super(arg);
        this.setSpecies(arg[0]);
        String[] elmts = arg[14].split("-");
        for (String elmt : elmts){
            this.element.add(elmt);
        }
    }
    public Electricmon(){
        super();
        this.setSpecies("electricmon");
        this.setElement("Electric");
        
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Electricmon(int x, int y){
        super(x,y);
        this.setSpecies("electricmon");
        this.setElement("Electric");

        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Electricmon(String _namaEngi, boolean _isLiar){
        super(_namaEngi, _isLiar);
        this.setSpecies("electricmon");
        this.setElement("Electric");
        
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Electricmon(String _namaEngi, boolean _isLiar, String[] _namaParent, String[] _speciesParent){
        super(_namaEngi, _isLiar, _namaParent, _speciesParent);
        this.setSpecies("electricmon");
        this.setElement("Electric");
        
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
}