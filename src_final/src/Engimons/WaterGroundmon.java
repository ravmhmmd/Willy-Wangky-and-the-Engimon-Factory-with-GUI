
import java.util.*;
// Fire/Electric engimon
public class WaterGroundmon extends Engimon  {
    public static final String[] skills = new String[]{"MuddyEarth 100 1 Water Ground","MudRock 140 1 Water Ground","LumpurLapindo 190 1 Water Ground","TanahBecek 100 1 Water Ground","TanahCampurPup 200 1 Water Ground"};
    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: kukuruyyukkk, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch kukuruyyukkk!");
        } else {
            System.out.printf("[%s]: kukuruyyukkk, ugh sup daddy ;) %s is ready to help ya kukuruyyukkk!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    
    //ctor
    public WaterGroundmon(String[] arg){
        super(arg);
        this.setSpecies(arg[0]);
        String[] elmts = arg[14].split("-");
        for (String elmt : elmts){
            this.element.add(elmt);
        }
    }
    public WaterGroundmon(){
        super();
        this.setSpecies("watergroundmon");
        this.setElement("Water", "Ground");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public WaterGroundmon(int x, int y){
        super(x,y);
        this.setSpecies("watergroundmon");
        this.setElement("Water", "Ground");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public WaterGroundmon(String _namaEngi, boolean _isLiar){
        super(_namaEngi, _isLiar);
        this.setSpecies("watergroundmon");
        this.setElement("Water", "Ground");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
    public WaterGroundmon(String _namaEngi, boolean _isLiar, String[] _namaParent, String[] _speciesParent){
        super(_namaEngi, _isLiar, _namaParent, _speciesParent);
        this.setSpecies("watergroundmon");
        this.setElement("Water", "Ground");
        Random rand = new Random();
        int skilrand = rand.nextInt(5);
        learnSkill(new Skill(skills[skilrand].split(" ")));
    }
}