package EngimonWorld;
import java.util.*;


public class Engimon extends Entities implements Comparable<Engimon>, InventoryItem, Interraction{
    // constants 
    static final int MAX_CUMEXP = 9999; 
    static final String DEFAULT_ENGIMON_NAME = "default";
    static final String DEFAULT_PARENT_NAME = "default";
    static final String DEFAULT_PARENT_SPECIES = "default";
    static final String DEFAULT_ELEMENT = "default";
    static final Skill DEFAULT_SKILL = new Skill();
    static final int MAX_ELEMENTS = 2;
    static final int MAX_SKILLS = 4;

    protected String namaEngimon;
    protected String[] namaParent;
    protected String[] speciesParent;
    public ArrayList<Skill> skillEngimon;
    protected int life;
    protected int level;
    protected int exp;
    protected int cumExp;
    protected boolean isActive;
    protected boolean isLiar;
    protected boolean isMati;
    protected String species;
    protected ArrayList<String> element;

    @Override
    public void interract(){
        if (this.getisLiar() == true){
            System.out.printf("[%s]: Ratatatataww, kenalin gue %s udah level %d !!! ",this.getNamaEngimon(), this.getSpecies(),this.getLevel());
            System.out.println("Fight me if you can, bitch ratatataw!");
        } else {
            System.out.printf("[%s]: Ratatataww, ugh sup daddy ;) %s is ready to help ya ratatawww!! \n", this.getNamaEngimon(), this.getSpecies());
        }
    }
    @Override
    public int compareTo(Engimon other){
        if (this.getSpecies().compareTo(other.getSpecies()) == 0){
            return this.getLevel() - other.getLevel();
        }
        return this.getSpecies().compareTo(other.getSpecies());
    }
    @Override
    public String toString(){
        return String.format("%s / %s / Lv.%d\n", this.getNamaEngimon(), this.getSpecies(), this.getLevel());
    }
    @Override
    public void printNama() {
        System.out.printf("%s / %s / Lv.%d\n", this.getNamaEngimon(), this.getSpecies(), this.getLevel());
    }
    @Override
    public void printInfo() {
        System.out.println("Engimon Info:");
        System.out.printf("Nama: %s\n",this.getNamaEngimon());
        System.out.printf("Species: %s\n",this.getSpecies());
        System.err.printf("Life: %d\n",this.getLife());
        System.out.println("Nama dan Species Parent: ");
        System.out.printf("%s (%s) ",this.namaParent[0],this.speciesParent[0]);
        System.out.printf("%s (%s)\n",this.namaParent[1],this.speciesParent[1]);
        System.out.printf("Level: %s\n",this.getLevel());
        System.out.printf("EXP: %s\n",this.getExp());
        System.out.printf("Element: ");
        for (String elmt : this.getElement()){
            System.out.print(elmt + " ");
        }
        System.out.println();
        this.printSkill();
    }
    @Override
    public String getNama(){
        return this.namaEngimon;
    }

    // firemon a 1 2 budi-budu da-di sembur,100,1,fire-cuih,100,2,fire 3 12 1200 9999 true false false Fire-Water
    public Engimon(String [] arg){
        super(Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
        String[] parentname = arg[4].split("-");
        String[] parentspecies = arg[5].split("-");
        String[] skills = arg[6].split("-");

        this.namaEngimon = arg[1];
        this.namaParent = new String[]{parentname[0], parentname[1]};
        this.speciesParent = new String[]{parentspecies[0], parentspecies[1]};
        this.level = Integer.parseInt(arg[8]);
        this.exp = Integer.parseInt(arg[9]);
        this.life = Integer.parseInt(arg[7]);
        this.cumExp = Integer.parseInt(arg[10]);;
        this.isLiar = Boolean.parseBoolean(arg[12]);
        this.isActive = Boolean.parseBoolean(arg[11]);
        this.isMati = Boolean.parseBoolean(arg[13]);
        this.element = new ArrayList<String>();
        this.skillEngimon = new ArrayList<Skill>();
        for (String skill : skills) {
            String[] skillarg = skill.split("_");
            this.skillEngimon.add(new Skill(skillarg));
            Collections.sort(this.skillEngimon, Collections.reverseOrder());
        }
    }
    public Engimon(){
        super(5,5);
        namaEngimon = DEFAULT_ENGIMON_NAME;
        level = 1;
        exp = 0;
        life = 1;
        cumExp = MAX_CUMEXP;
        isLiar = true;
        isActive = false;
        namaParent = new String[]{DEFAULT_PARENT_NAME, DEFAULT_PARENT_NAME};
        speciesParent = new String[]{DEFAULT_PARENT_SPECIES, DEFAULT_PARENT_SPECIES};
        element = new ArrayList<String>();
        skillEngimon = new ArrayList<Skill>();
        isMati = false;
    }
    public Engimon(int x, int y){
        super(x,y);
        namaEngimon = DEFAULT_ENGIMON_NAME;
        level = 1;
        exp = 0;
        life = 1;
        cumExp = MAX_CUMEXP;
        isLiar = true;
        isActive = false;
        namaParent = new String[]{DEFAULT_PARENT_NAME, DEFAULT_PARENT_NAME};
        speciesParent = new String[]{DEFAULT_PARENT_SPECIES, DEFAULT_PARENT_SPECIES};
        element = new ArrayList<String>();
        skillEngimon = new ArrayList<Skill>();
        isMati = false;
    }
    public Engimon(String _namaEngi, boolean _isLiar){
        super(5,4);
        namaEngimon = _namaEngi;
        level = 1;
        exp = 0;
        life = (_isLiar == true) ? 1 : 3;
        if (isLiar) life = 1;
        else life = 3;
        cumExp = MAX_CUMEXP;
        isLiar = _isLiar;
        isActive = false;
        namaParent = new String[]{DEFAULT_PARENT_NAME, DEFAULT_PARENT_NAME};
        speciesParent = new String[]{DEFAULT_PARENT_SPECIES, DEFAULT_PARENT_SPECIES};
        element = new ArrayList<String>();
        skillEngimon = new ArrayList<Skill>();
        isMati = false;
    }
    public Engimon(String _namaEngi, boolean  _isLiar, String[] _namaParent, String[] _speciesParent){
        super(5,4);
        namaEngimon = _namaEngi;
        level = 1;
        exp = 0;
        life = (_isLiar == true) ? 1 : 3;
        cumExp = MAX_CUMEXP;
        isLiar = _isLiar;
        isActive = false;
        namaParent = new String[2];
        speciesParent = new String[2];
        for (int i=0; i < 2; i++){
            namaParent[i] = _namaParent[i];
            speciesParent[i] = _speciesParent[i];
        }
        element = new ArrayList<String>();
        skillEngimon = new ArrayList<Skill>();
        isMati = false;
    }
    public void setNamaEngimon(String _newname){
        namaEngimon = _newname;
    }
    public void setSpecies(String _species){
        species = _species;
    }

    // untuk engimon 1 elemen
    public void setElement(String el){
        element.add(el);
    }
    // untuk engimon 2 elemen
    public void setElement(String el1, String el2){
        element.add(el1);
        element.add(el2);
    }

    public void setLevel(int newlvl){
        level = newlvl;
    }
    public void setExp(int eks){
        exp = eks;
    }
    public void setLife(int lv){
        life = lv;
    }
    public void setActive(){
        if (isActive == false){
            isActive = true;
        }
    }
    public void setInactive(){
        if (isActive == true){
            isActive = false;
        }
    }
    public void setMati(Game g){
        if (this.getisLiar() == true) {
            g.getContainerLiar().remove(this);
        } else {
            g.getPlayer().getMyEngimons().removeItem(this);
        }
    }

    public void setParentName(String[] _namaParent){
        for (int i = 0; i < 2; i++){
            namaParent[i] = _namaParent[i];
        }
    }
    public void setParentSpecies(String[] _speciesParent){
        for (int i = 0; i < 2; i++){
            speciesParent[i] = _speciesParent[i];
        }
    }
    public String getNamaEngimon(){
        return namaEngimon;
    }
    public boolean getisLiar(){
        return isLiar;
    }
    public boolean getisActive(){
        return isActive;
    }
    public int getLevel(){
        return level;
    }
    public int getLife(){
        return life;
    }
    public int getExp(){
        return exp;
    }

    public Skill[] getSkill(){
        return this.skillEngimon.toArray(new Skill[0]);
    }
 
    public String[] getElement(){
        return this.element.toArray(new String[0]);
    }
    public String getSpecies(){
        return this.species;
    }
    public int countElement(){
        return this.element.size();
    }
    public int countSkill(){
        return this.skillEngimon.size();
    }   

    public void tame(){
        this.isLiar = false;
        this.setLife(3);
    }
    public void levelUp(){
        level++;
    }

    public void learnSkill(Skill newskill){
        List<String> elmt = Arrays.asList(this.getElement());
        List<String> elmtskill = Arrays.asList(newskill.getReqElement());
        if (elmtskill.size() > 1){
            if (elmt.contains(elmtskill.get(0)) || elmt.contains(elmtskill.get(1))){
                if (this.countSkill() < MAX_SKILLS){
                    this.skillEngimon.add(newskill);
                    Collections.sort(this.skillEngimon, Collections.reverseOrder());    
                } else {
                    System.out.println("Sorry, udah kepenuhan nih!");
                    // TO DO
                }
            } else {
                System.out.println("Element mismatched between required element and engimon element, try another skill!");
            }   
        } else {
            if (elmt.contains(Arrays.asList(newskill.getReqElement()).get(0))){
                if (this.countSkill() < MAX_SKILLS){
                    this.skillEngimon.add(newskill);
                    Collections.sort(this.skillEngimon, Collections.reverseOrder());    
                } else {
                    System.out.println("Sorry, udah kepenuhan nih!");
                    // TO DO
                }
            } else {
                System.out.println("Element mismatched between required element and engimon element, try another skill!");
            }
        }
    }
    public void replaceSkill(Skill removedskill, Skill newskill){
        this.removeSkill(removedskill);
        this.learnSkill(newskill);
    }
    public void removeSkill(Skill removedskill){
        this.skillEngimon.removeIf(s-> s.getNamaSkill().equals(removedskill.getNamaSkill()));
        System.out.printf("%s has been removed! %d skill slot(s) are still available\n",removedskill.getNamaSkill(),this.countSkill());
    }


    public void printSkill(){
        System.out.printf("%s's skill(s): \n",this.getNamaEngimon());
        if (this.countSkill() > 0){
            int i = 0;
            for (Skill s : this.skillEngimon){
                System.out.printf("[%d] %s\n",i+1,s);
                i++;
            }
        } else {
            System.out.printf("%s CUPU, it has no skills :(\n",this.getNamaEngimon());
        }
    }

    public void addExp(int xp){
        this.setExp(xp+this.exp);
        if (this.getExp() % 100 == 0)
        {
            this.levelUp();
            if (this.isLiar == false){
                System.out.printf("%s leveled up!! It's %d now Daddy ;)\n",this.getNamaEngimon(), this.getLevel());
            }
        }
        if (this.getExp() >= MAX_CUMEXP)
        {
            this.setMati();
        }
        
    }
    public void decreaseLife(){
        this.setLife(this.getLife()-1);
        if (this.getLife() == 0){
            this.setMati();
        }
    }
    public void setMati(){
        // System.out.printf("Innalillahi wa innailahi rojiun, telah berpulang ke rahmatullah, %s ...\n",this.activeEngimon.getNama());
        // System.err.println("Semoga amal ibadahnya diterima oleh Allah SWT, aamiin ... ");
        // this.myEngimons.removeItem(this.activeEngimon);
        isMati = true;
    }

    public boolean isMati(){
        return isMati;
    }
    
    public void move(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    // save load purposes
    public String[] exportEngimon(){
        List<String> res = new ArrayList<>();
        res.add(this.species);
        res.add(this.namaEngimon);
        res.add(Integer.toString(this.getX()));
        res.add(Integer.toString(this.getY()));
        res.add(this.namaParent[0]+"-"+this.namaParent[1]);
        res.add(this.speciesParent[0]+"-"+this.speciesParent[1]);
        String skilltostring = "";
        for (Skill s : this.skillEngimon){
            String[] skillexport = s.exportSkill();
            for (String skillarg : skillexport){
                skilltostring += skillarg;
                skilltostring += "_";
                
            }
            skilltostring = skilltostring.substring(0, skilltostring.length() - 1);
            skilltostring += "-";
        }
        res.add(skilltostring.substring(0, skilltostring.length() - 1));
        res.add(Integer.toString(this.life));
        res.add(Integer.toString(this.level));
        res.add(Integer.toString(this.exp));
        res.add(Integer.toString(this.cumExp));
        res.add(Boolean.toString(this.isActive));
        res.add(Boolean.toString(this.isLiar));
        res.add(Boolean.toString(this.isMati));
        String elmtstr = "";
        for (String elmt : this.element){
            elmtstr += elmt;
            elmtstr += "-";
        }
        res.add(elmtstr.substring(0, elmtstr.length() - 1));
        return res.toArray(new String[0]);
    }
}