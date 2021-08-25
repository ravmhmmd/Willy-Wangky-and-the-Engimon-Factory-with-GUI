package EngimonWorld.Engimons;
import EngimonWorld.*;
import java.util.*;

// Fire/Electric engimon
public class Groundmon extends Engimon  {
    public static final String[] skills = new String[]{"EarthShake 85 1 Ground","RumblingEarth 110 1 Ground","Avalanche 120 1 Ground","Apocalypse 155 1 Ground","AzabKubur 200 1 Ground"};
    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: imgrrroot, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch imgrrroot!");
        } else {
            System.out.printf("[%s]: imgrrroot, ugh sup daddy ;) %s is ready to help ya imgrrroot!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    
    //ctor
    public Groundmon(String[] arg){
        super(arg);
        this.setSpecies(arg[0]);
        String[] elmts = arg[14].split("-");
        for (String elmt : elmts){
            this.element.add(elmt);
        }
    }
    public Groundmon(){
        super();
        this.setSpecies("groundmon");
        this.setElement("Ground");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Groundmon(int x, int y){
        super(x,y);
        this.setSpecies("groundmon");
        this.setElement("Ground");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Groundmon(String _namaEngi, boolean _isLiar){
        super(_namaEngi, _isLiar);
        this.setSpecies("groundmon");
        this.setElement("Ground");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public Groundmon(String _namaEngi, boolean _isLiar, String[] _namaParent, String[] _speciesParent){
        super(_namaEngi, _isLiar, _namaParent, _speciesParent);
        this.setSpecies("groundmon");
        this.setElement("Ground");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
}