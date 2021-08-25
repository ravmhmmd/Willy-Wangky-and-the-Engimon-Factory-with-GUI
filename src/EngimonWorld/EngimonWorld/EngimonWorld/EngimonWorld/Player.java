package EngimonWorld;
import java.util.*;

import EngimonWorld.Engimons.*;

public class Player extends Entities{
    static final int MAX_CUMEXP = 9999;
    static Scanner sc;

    private boolean isLose;
    private String namaPlayer;
    private Inventory<Engimon> myEngimons;
    private Inventory<Skill> mySkillItems;
    private Engimon activeEngimon;

    public Player(String nama, Engimon A){
        super(5, 4);
        myEngimons = new Inventory<>();
        mySkillItems = new Inventory<>();
        this.isLose = false;
        this.namaPlayer = nama;
        this.myEngimons.addItem(A);
        this.activeEngimon = A;
        activeEngimon.setActive();
    }

    // x y islose nama invengi invskill acteng
    public Player(String arg[]){
        super(Integer.parseInt(arg[0]),Integer.parseInt(arg[1]));
        this.isLose = Boolean.parseBoolean(arg[2]);
        this.namaPlayer = arg[3];

        String[] activeengi = arg[6].split(",");
        String specs = activeengi[0];
        if (specs.equals("firemon")){
            this.activeEngimon = new Firemon(activeengi);
        } else if (specs.equals("electricmon")){
            this.activeEngimon = new Electricmon(activeengi);
        } else if (specs.equals("fireelectricmon")){
            this.activeEngimon = new FireElectricmon(activeengi);
        } else if (specs.equals("groundmon")){
            this.activeEngimon = new Groundmon(activeengi);
        } else if (specs.equals("icemon")){
            this.activeEngimon = new Icemon(activeengi);
        } else if (specs.equals("watergroundmon")){
            this.activeEngimon = new WaterGroundmon(activeengi);
        } else if (specs.equals("watericemon")){
            this.activeEngimon = new WaterIcemon(activeengi);
        } else if (specs.equals("watermon")){
            this.activeEngimon = new Watermon(activeengi);
        }
        
        this.myEngimons = new Inventory<>();
        this.mySkillItems = new Inventory<>();

        String[] engimons = arg[4].split("/");
        for (String engimon : engimons){
            String[] engimonarg = engimon.split(",");
            specs = engimonarg[0];
            if (specs.equals("firemon")){
                this.myEngimons.addItem(new Firemon(engimonarg));
            } else if (specs.equals("electricmon")){
                this.myEngimons.addItem(new Electricmon(engimonarg));
            } else if (specs.equals("fireelectricmon")){
                this.myEngimons.addItem(new FireElectricmon(engimonarg));
            } else if (specs.equals("groundmon")){
                this.myEngimons.addItem(new Groundmon(engimonarg));
            } else if (specs.equals("icemon")){
                this.myEngimons.addItem(new Icemon(engimonarg));
            } else if (specs.equals("watergroundmon")){
                this.myEngimons.addItem(new WaterGroundmon(engimonarg));
            } else if (specs.equals("watericemon")){
                this.myEngimons.addItem(new WaterIcemon(engimonarg));
            } else if (specs.equals("watermon")){
                this.myEngimons.addItem(new Watermon(engimonarg));
            }
        }
        String[] skillitems = arg[5].split("-");
        if (skillitems.length != 0){
            for (String skill : skillitems){
                String[] skillarg = skill.split("_");
                this.mySkillItems.addItem(new Skill(skillarg));
            }
        }
    }
    
    public boolean getIsLose(){
        return this.isLose;
    }

    public String getNamaPlayer(){
        return this.namaPlayer;
    }
    
    public Inventory<Engimon> getMyEngimons(){
        return this.myEngimons;
    }
    
    public Inventory<Skill> getMySkillItems(){
        return this.mySkillItems;
    }
    
    public Engimon getActiveEngimon(){
        return this.activeEngimon;
    }
    
    public void setIsLose(boolean l){
        this.isLose = l;
    }

    public void setNamaPlayer(String nama){
        this.namaPlayer = nama;
    }
    
    public void setMyEngimons(Inventory<Engimon> engimons){
        this.myEngimons = engimons;
    }
    
    public void setMySkillItems(Inventory<Skill> skillItems){
        this.mySkillItems = skillItems;
    }
    
    public void setActiveEngimon(Engimon A){
        this.activeEngimon = A;
    }
    
    public void interact(){
        System.out.println("Halo guyss, " + this.activeEngimon.getSpecies() + " disini, dengan siapa dimana ??");
    }
    
    public int battle(Engimon Enemy){
        sc = new Scanner(System.in);

        boolean isWin = this.isWinBattle(this.activeEngimon, Enemy);
    
        if (isWin){
            System.out.println("CONGRATS, YOU WON THE BATTLE, MASTER!");
            System.out.print("Dapet Engimon nihhh, mau kasih nama sokap: ");
            String nama = sc.nextLine();
            Enemy.setNamaEngimon(nama);
            Enemy.tame();
            this.myEngimons.addItem(Enemy);
            Skill[] skillEnemy = Enemy.getSkill();
            Skill randomSkill = skillEnemy[0];
            this.mySkillItems.addItem(randomSkill); 
            this.addExpToActEng(Enemy.getLevel()*100);
            return 1;
        } else {
            System.out.println("Sorry, Engimon kamu cupu, sana latihan lagi :(");
            this.decreaseLifeActEng();
            if (this.activeEngimon.getLife() == 0){
                if (this.myEngimons.countItem() > 0){
                    switchActiveEngimon(this.myEngimons.getItem(0));
                    return -1;
                } else {
                    // kalah game, engimon abis
                    this.isLose = true;
                    return -2;
                }
            } 
        }
        return 0;

            
    }
    
    public void printEngimon(Engimon A){
        A.printInfo();
    }
    
    public void printMyEngimons(){
        System.out.println("Halo " + this.namaPlayer + "!");
        System.out.println("Berikut adalah Engimon yang kamu miliki :");
        this.myEngimons.printInvenEngimon();
    }
    
    public void printMySkillItems(){
        System.out.println("Halo " + this.namaPlayer + "!");
        System.out.println("Berikut adalah Skill Item yang kamu miliki :");
        this.mySkillItems.printInvenSkill();
    }
    
    public void switchActiveEngimon(Engimon switchEngimon){
        switchEngimon.setActive();
        this.activeEngimon.setInactive();
        this.activeEngimon = switchEngimon;
    }
    
    public void useSkillItem(Engimon A, Skill S){
        if (A.countSkill() < 4){
            A.learnSkill(S);
        } else{
            Scanner sc = new Scanner(System.in);
            System.out.println("Pilih skill yang ingin di-remove :");
            A.printSkill();
            int choose = sc.nextInt();
            Skill[] skillA = A.getSkill();
            A.replaceSkill(skillA[choose], S);
        }
    }
    
    public void breeding(Engimon A, Engimon B){
        try{
            if (A.countElement() > 1 || B.countElement() > 1 || A.getLevel() < 4 || B.getLevel() < 4){
                throw new IllegalArgumentException("Tidak dapat melakukan breeding!");
            } 
            Scanner sc = new Scanner(System.in);
            String[] pname = {A.getNamaEngimon(), B.getNamaEngimon()};
            String[] pspecies = {A.getSpecies(), B.getSpecies()};
            String namaEngimon = ""; 
            String species = "";
            int i;
            System.out.print("Cieee kawin ciee mau kasih nama siapa nih anaknya: ");
            namaEngimon = sc.nextLine();
            if (A.getSpecies().equals(B.getSpecies())){
                species = A.getSpecies();
            } else{
                double[] adv = this.getAdvantage(A, B);
                if (adv[0] > adv[1]){
                    species = A.getSpecies();
                } else if (adv[0] < adv[1]){
                    species = B.getSpecies();
                } else{
                    if (A.getSpecies().equals("watermon") && B.getSpecies().equals("icemon")){
                        species = "watericemon";
                    } else if (A.getSpecies().equals("icemon") && B.getSpecies().equals("watermon")){
                        species = "watericemon";
                    } else if (A.getSpecies().equals("watermon") && B.getSpecies().equals("groundmon")){
                        species = "watergroundmon";
                    } else if (A.getSpecies().equals("groundmon") && B.getSpecies().equals("watermon")){
                        species = "watergroundmon";
                    } else if (A.getSpecies().equals("firemon") && B.getSpecies().equals("electricmon")){
                        species = "fireelectricmon";
                    } else if (A.getSpecies().equals("electricmon") && B.getSpecies().equals("firemon")){
                        species = "fireelectricmon";
                    }
                }
            }
            Engimon C = new Engimon();
            if (species.equals("electricmon")){
                C = new Electricmon(namaEngimon, false, pname, pspecies);
            } else if (species.equals("fireelectricmon")){
                C = new FireElectricmon(namaEngimon, false, pname, pspecies);
            } else if (species.equals("firemon")){
                C = new Firemon(namaEngimon, false, pname, pspecies);
            } else if (species.equals("groundmon")){
                C = new Groundmon(namaEngimon, false, pname, pspecies);
            } else if (species.equals("icemon")){
                C = new Icemon(namaEngimon, false, pname, pspecies);
            } else if (species.equals("watergroundmon")){
                C = new WaterGroundmon(namaEngimon, false, pname, pspecies);
            } else if (species.equals("watericemon")){
                C = new WaterIcemon(namaEngimon, false, pname, pspecies);
            } else if (species.equals("watermon")){
                C = new Watermon(namaEngimon, false, pname, pspecies);
            }
            
            // ngasih skill
            Skill[] skillC;
            if (C.getSpecies() == A.getSpecies()){
                skillC = A.getSkill();
                for (i = 0; i < A.countSkill(); i++){
                    if (!(skillC[i].getNamaSkill().equals(C.getSkill()[0].getNamaSkill()))){
                        C.learnSkill(skillC[i]);
                    }
                }
            } else if (C.getSpecies() == B.getSpecies()){
                skillC = B.getSkill();
                for (i = 0; i < B.countSkill(); i++){
                    if (!(skillC[i].getNamaSkill().equals(C.getSkill()[0].getNamaSkill()))){
                        C.learnSkill(skillC[i]);
                    }
                }
            } else{
                Skill[] skillCA = A.getSkill();
                Skill[] skillCB = B.getSkill();
                ArrayList<Skill> skillCList = new ArrayList<Skill>();
                ArrayList<String> namaSkill = new ArrayList<String>();
                int count = 0;
                int cekCountSkill = 4;
                int a = 0;
                int b = 0;
                if ((A.countSkill() + B.countSkill()) < 4){
                    cekCountSkill = A.countSkill() + B.countSkill();
                } 
                if (cekCountSkill < 4){
                    for (Skill x : skillCA){
                        skillCList.add(x);
                    }
                    for (Skill y : skillCB){
                        skillCList.add(y);
                    }
                } else {
                    while(count < cekCountSkill && a < A.countSkill() && b < B.countSkill()){
                        if (!skillCA[a].getNamaSkill().equals(skillCB[b].getNamaSkill())){
                            if ((skillCA[a].getMasteryLevel() >= skillCB[b].getMasteryLevel())){
                                if (!namaSkill.contains(skillCA[a].getNamaSkill())){
                                    skillCList.add(skillCA[a]);
                                    namaSkill.add(skillCA[a].getNamaSkill());
                                    count++;
                                }
                                a++;
                            } else{
                                if (!namaSkill.contains(skillCB[b].getNamaSkill())){
                                    skillCList.add(skillCB[b]);
                                    namaSkill.add(skillCA[b].getNamaSkill());
                                    count++;
                                }
                                b++;
                            }
                        } else{
                            if ((skillCA[a].getMasteryLevel() > skillCB[b].getMasteryLevel())){
                                if (!namaSkill.contains(skillCA[a].getNamaSkill())){
                                    skillCList.add(skillCA[a]);
                                    namaSkill.add(skillCA[a].getNamaSkill());
                                    count++;
                                }
                                a++;
                            } else if ((skillCA[a].getMasteryLevel() < skillCB[b].getMasteryLevel())){
                                if (!namaSkill.contains(skillCB[b].getNamaSkill())){
                                    skillCList.add(skillCB[b]);
                                    namaSkill.add(skillCA[b].getNamaSkill());
                                    count++;
                                }
                                b++;
                            } else{
                                if (!namaSkill.contains(skillCA[a].getNamaSkill())){
                                    if (skillCA[a].getMasteryLevel() == 3){
                                        skillCList.add(skillCA[a]);
                                        namaSkill.add(skillCA[a].getNamaSkill());
                                        count++;
                                    } else if (skillCA[a].getMasteryLevel() < 3){
                                        Skill addSkill = skillCA[a];
                                        addSkill.setMasteryLv(skillCA[a].getMasteryLevel()+1);
                                        skillCList.add(addSkill);
                                        namaSkill.add(addSkill.getNamaSkill());
                                        count++;
                                    }
                                }
                                a++;
                                b++;
                            }
                        }
                    }
                }   
                
                //skillC = skillCList.toArray(new Skill[0]);
                for (i = 0; i < skillCList.size(); i++){
                    if (!(skillCList.get(i).getNamaSkill().equals(C.getSkill()[0].getNamaSkill()))){
                        C.learnSkill(skillCList.get(i));
                    }
                }
            }
            C.printSkill();
            A.setLevel(A.getLevel()-3);
            B.setLevel(B.getLevel()-3);
    
            System.out.println("Berhasil melakukan breeding!");
            this.myEngimons.addItem(C);
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());

        }
        
    }
    
    
    public double[] getAdvantage(Engimon A,Engimon B){
        double[] arrAdv = new double[2];
        if (A.getSpecies().equals("firemon")){
            if (B.getSpecies().equals("firemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watermon")){
                arrAdv[0]=0;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("electricmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("groundmon")){
                arrAdv[0]=0.5;
                arrAdv[1]=1.5;
            } else if (B.getSpecies().equals("icemon")){
                arrAdv[0]=2;
                arrAdv[1]=0;
            } else if (B.getSpecies().equals("fireelectricmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watergroundmon")){
                arrAdv[0]=0.5;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watericemon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            }
        } else if (A.getSpecies().equals("watermon")){
            if (B.getSpecies().equals("firemon")){
                arrAdv[0]=2;
                arrAdv[1]=0;
            } else if (B.getSpecies().equals("watermon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("electricmon")){
                arrAdv[0]=0;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("groundmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("icemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("fireelectricmon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watergroundmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watericemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            }
        } else if (A.getSpecies().equals("electricmon")){
            if (B.getSpecies().equals("firemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watermon")){
                arrAdv[0]=2;
                arrAdv[1]=0;
            } else if (B.getSpecies().equals("electricmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("groundmon")){
                arrAdv[0]=0;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("icemon")){
                arrAdv[0]=1.5;
                arrAdv[1]=0.5;
            } else if (B.getSpecies().equals("fireelectricmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watergroundmon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watericemon")){
                arrAdv[0]=2;
                arrAdv[1]=0.5;
            }
        } else if (A.getSpecies().equals("groundmon")){
            if (B.getSpecies().equals("firemon")){
                arrAdv[0]=1.5;
                arrAdv[1]=0.5;
            } else if (B.getSpecies().equals("watermon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("electricmon")){
                arrAdv[0]=2;
                arrAdv[1]=0;
            } else if (B.getSpecies().equals("groundmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("icemon")){
                arrAdv[0]=0;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("fireelectricmon")){
                arrAdv[0]=2;
                arrAdv[1]=0.5;
            } else if (B.getSpecies().equals("watergroundmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watericemon")){
                arrAdv[0]=1;
                arrAdv[1]=2;
            }
        } else if (A.getSpecies().equals("icemon")){
            if (B.getSpecies().equals("firemon")){
                arrAdv[0]=0;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watermon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("electricmon")){
                arrAdv[0]=0.5;
                arrAdv[1]=1.5;
            } else if (B.getSpecies().equals("groundmon")){
                arrAdv[0]=2;
                arrAdv[1]=0;
            } else if (B.getSpecies().equals("icemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("fireelectricmon")){
                arrAdv[0]=0.5;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watergroundmon")){
                arrAdv[0]=2;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watericemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            }
        } else if (A.getSpecies().equals("fireelectricmon")){
            if (B.getSpecies().equals("firemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watermon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("electricmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("groundmon")){
                arrAdv[0]=0.5;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("icemon")){
                arrAdv[0]=2;
                arrAdv[1]=0.5;
            } else if (B.getSpecies().equals("fireelectricmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watergroundmon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watericemon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            }
        } else if (A.getSpecies().equals("watergroundmon")){
            if (B.getSpecies().equals("firemon")){
                arrAdv[0]=2;
                arrAdv[1]=0.5;
            } else if (B.getSpecies().equals("watermon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("electricmon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("groundmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("icemon")){
                arrAdv[0]=1;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("fireelectricmon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watergroundmon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watericemon")){
                arrAdv[0]=1;
                arrAdv[1]=2;
            }
        } else if (A.getSpecies().equals("watericemon")){
            if (B.getSpecies().equals("firemon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watermon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("electricmon")){
                arrAdv[0]=0.5;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("groundmon")){
                arrAdv[0]=2;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("icemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("fireelectricmon")){
                arrAdv[0]=2;
                arrAdv[1]=2;
            } else if (B.getSpecies().equals("watergroundmon")){
                arrAdv[0]=2;
                arrAdv[1]=1;
            } else if (B.getSpecies().equals("watericemon")){
                arrAdv[0]=1;
                arrAdv[1]=1;
            }
        }

        return arrAdv;
    }
    
    public Boolean isWinBattle(Engimon A,Engimon B){
        double adv[] = getAdvantage(A, B);
        Skill[] aSkill = A.getSkill();
        Skill[] bSkill = B.getSkill();
        double sumPowerA = (A.getLevel())*(adv[0]);
        double sumPowerB = (B.getLevel())*(adv[1]);
        for (int i=0; i<aSkill.length; i++){
            sumPowerA += aSkill[i].getBasePower()*aSkill[i].getMasteryLevel();
        }
        for (int i=0; i<bSkill.length; i++){
            sumPowerB += bSkill[i].getBasePower()*bSkill[i].getMasteryLevel();
        }

        if (sumPowerA >= sumPowerB){
            return true;
        } else {
            return false;
        }
    }

    public void showStats(Engimon A,Engimon B){
        double adv[] = getAdvantage(A, B);
        Skill[] aSkill = A.getSkill();
        Skill[] bSkill = B.getSkill();
        double sumPowerA = (A.getLevel())*(adv[0]);
        double sumPowerB = (B.getLevel())*(adv[1]);
        for (int i=0; i<aSkill.length; i++){
            sumPowerA += aSkill[i].getBasePower()*aSkill[i].getMasteryLevel();
        }
        for (int i=0; i<bSkill.length; i++){
            sumPowerB += bSkill[i].getBasePower()*bSkill[i].getMasteryLevel();
        }

        System.out.print("Power Engimon Kamu : ");
        System.out.println(sumPowerA);
        System.out.print("Power Lawan Kamu : ");
        System.out.println(sumPowerB);

    }

    // METHODS UNTUK ACTIVE ENGIMON
    //other methods
    public void addExpToActEng(int xp){
        this.activeEngimon.setExp(xp+this.activeEngimon.getExp());
        if (this.activeEngimon.getExp() % 100 == 0)
        {
            this.activeEngimon.levelUp();
            System.out.printf("%s leveled up!! It's %d now Daddy ;)\n",this.activeEngimon.getNamaEngimon(), this.activeEngimon.getLevel());
        }
        if (this.activeEngimon.getExp() >= MAX_CUMEXP)
        {
            this.setActEngMati();
        }
        
    }
    public void decreaseLifeActEng(){
        this.activeEngimon.setLife(this.activeEngimon.getLife()-1);
        if (this.activeEngimon.getLife() == 0){
            this.setActEngMati();
        }
    }
    public void setActEngMati(){
        System.out.printf("Innalillahi wa innailahi rojiun, telah berpulang ke rahmatullah, %s ...\n",this.activeEngimon.getNama());
        System.err.println("Semoga amal ibadahnya diterima oleh Allah SWT, aamiin ... ");
        this.myEngimons.removeItem(this.activeEngimon);
    }

    public void move(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public int getMaxLevelEngimon(){
        Inventory<Engimon> es = getMyEngimons();
        int max = es.getItem(0).getLevel();
  
        for (int i = 1; i < es.countItem(); i++){
          if(max < es.getItem(i).getLevel()){
            max = es.getItem(i).getLevel();
          }
        }
  
        return max;
    }   
    // x y islose nama invengi invskill acteng
    public String[] exportPlayer(){
        // private boolean isLose;
        // private String namaPlayer;
        // private Inventory<Engimon> myEngimons;
        // private Inventory<Skill> mySkillItems;
        // private Engimon activeEngimon;
        List<String> res = new ArrayList<>();
        res.add(Integer.toString(this.getX()));
        res.add(Integer.toString(this.getY()));
        res.add(Boolean.toString(this.isLose));
        res.add(this.namaPlayer);
        String invEng = "";
        for (int i=0; i < this.myEngimons.countItem(); i++){
            String[] engiexport = this.myEngimons.getItem(i).exportEngimon();
            for (String engiarg : engiexport){
                invEng += engiarg;
                invEng += ",";
                
            }
            invEng = invEng.substring(0, invEng.length() - 1);
            invEng += "/";
        }
        res.add(invEng.substring(0, invEng.length() - 1));
        String invSkill = "";
        if (this.mySkillItems.countItem() != 0){
            for (int i=0; i < this.mySkillItems.countItem(); i++){
                String[] skillexport = this.mySkillItems.getItem(i).exportSkill();
                for (String skillarg : skillexport){
                    invSkill += skillarg;
                    invSkill += "_";
                    
                }
                invSkill = invSkill.substring(0, invSkill.length() - 1);
                invSkill += "-";
            }
            res.add(invSkill.substring(0, invSkill.length() - 1));
        } else {
            invSkill += "-";
            res.add(invSkill);
        }
        String[] actEng = this.activeEngimon.exportEngimon();
        String actEngStr = "";
        for (String s : actEng){
            actEngStr += s;
            actEngStr += ",";
        }
        res.add(actEngStr.substring(0, actEngStr.length() - 1));
        return res.toArray(new String[0]);
    }
}

