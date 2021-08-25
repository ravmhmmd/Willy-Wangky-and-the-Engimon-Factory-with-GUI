package EngimonWorld;
import java.util.*;

import EngimonWorld.Engimons.*;

public class test {

    public static void main(String[] args) {
        // Map map = new Map();
        // map.printMap();
        
        // Inventory<Engimon> g = new Inventory<>();
        // Engimon c = new FireElectricmon();
        // g.addItem(c);
        // Engimon d = g.getItem(0);
        // d.printInfo();
        // d.printNama();
        // System.out.println(d.countSkill());
        // g.removeItem(d);
        // System.out.println(g.countItem());
        // Player p = new Player("Alif",d);
        // map.playerInisiasi(p);
        // map.engimonInisiasi(d);
        // p.getMyEngimons().printInvenEngimon();
        // p.getActiveEngimon().printNama();
        // map.printMap();
        // Engimon co = new FireElectricmon("cow",false);
        // Engimon ce = new Electricmon("cew",false);
        // co.setLevel(39);
        // ce.setLevel(35);
        // p.getMyEngimons().addItem(co);
        // p.getMyEngimons().addItem(ce);
        // p.getMyEngimons().printInvenEngimon();
        // Engimon musuh = new Firemon("musuh",true);
        // musuh.setLevel(2);
        // p.getActiveEngimon().setLife(1);
        // p.battle(musuh);
        // p.getMyEngimons().printInvenEngimon();
        // p.getActiveEngimon().printNama();
        // p.battle(musuh);
        // p.getMyEngimons().printInvenEngimon();
        // p.getMySkillItems().printInvenSkill();
        // p.getActiveEngimon().printNama();
        // p.breeding(co, ce);
        // co.learnSkill(new Skill("as",100,"Water"));
        // p.getMyEngimons().printInvenEngimon();
        // co.printSkill();

       // Game game = new Game();
        //game.start();

        
        // String[] arg = new String[]{"electricmon", "a", "1", "2", "budi-budu", "da-di", "sembur_100_1_Electric-cuih_100_2_Electric", "3", "12", "1200", "9999", "true", "false", "false", "Fire-Water"};
        // String[] argplayer = new String[]{"6", "0", "false", "alip", "groundmon,agung,8,0,default-default,default-default,azabKubur_100_1_Ground,2,2,200,9999,false,false,false,Ground/fireelectricmon,cups,7,0,default-default,default-default,petiryangberapi_100_1_Electric,2,2,100,9999,true,false,false,Fire-Electric", "petiryangberapi_100_1_Electric", "fireelectricmon,cups,7,0,default-default,default-default,petiryangberapi_100_1_Electric,2,2,100,9999,true,false,false,Fire-Electric"};
        // Player p = new Player(argplayer);
        // p.printMyEngimons();
        // Engimon c = new Engimon();
        // if (arg[0].equals("electricmon")){
        //     c = new Electricmon(arg);
        // }
        // c.printInfo();
        // String[] cstr = c.exportEngimon();

        Engimon a = new Firemon();
        Engimon b = new FireElectricmon();
        Engimon c = new WaterGroundmon();
        Engimon d = new WaterIcemon();
        Engimon e = new Watermon();
        Engimon f = new Electricmon();
        Engimon g = new Icemon();
        Engimon h = new Groundmon();
        
        a.printInfo();
        b.printInfo();
        c.printInfo();
        d.printInfo();
        e.printInfo();
        f.printInfo();
        g.printInfo();
        h.printInfo();


        //c.printInfo();
    }
}
