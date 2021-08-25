package EngimonWorld;
import EngimonWorld.Engimons.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Game {
  private Scanner sc;
  private Player player;
  private ArrayList<Engimon> engimonliar;
  private ArrayList<Skill> containerskill;
  private Map map;
  private int turn;
  private int maxLiar;

  public Game() {
    sc = new Scanner(System.in);
    turn = 0;
    maxLiar = 10;
  }

  public Player getPlayer(){
    return this.player;
  }
  public ArrayList<Engimon> getContainerLiar(){
    return this.engimonliar;
  }

  public void start(){
    intro();
    play();
  }

  private void play() {
    String cmd = "";
    System.out.println();
    while (!cmd.equals("exit") && !player.getIsLose())
    {
      map.printMap();
      System.out.printf("Turn: %d\n", turn+1);
      System.out.print("> ");
      cmd = sc.nextLine();
      handleCMD(cmd);
      turn++;
      if(turn%5 == 0){
        if (engimonliar.size()>0){
          moveRandom(turn);
        }
      }
      if(turn%10 == 0){
        spawnRandom();
      }
    }
    if (player.getIsLose()){
      System.out.println("Sorry mister tp km kalah");
    } else {
      System.out.println("Terima kasih telah memainkan permainan ini :)");
    }
  }

  private void intro() {
    System.out.println("Selamat datang, calon pemain!");
    System.out.println("Mau new game apa kaga? (new/load)");
    System.out.print("New/Load ??: ");
    String he = sc.nextLine();
    if (he.equals("load")){
      System.out.print("Masukkan nama file: ");
      load(sc.nextLine());
    } else if (he.equals("new")){
      init();
    }
  }

  private void init()
  {
    String name;
    System.out.print("Input name: ");
    name = sc.nextLine();
    System.out.println("Hello, "+name);

    /* init semua attr */
    this.engimonliar = new ArrayList<>();

    // init container skills
    this.containerskill = new ArrayList<>();
    initSkills();

    // init player
    Engimon engimonDefault = new Engimon();
    System.out.println("<< Pilih Engimon >>");
    System.out.println("[1] Firemon");
    System.out.println("[2] Watermon");
    System.out.println("[3] Groundmon");
    System.out.println("[4] Icemonon");
    System.out.println("[5] Electricmon");

		int engi = -1;
    do {
			System.out.print("Mau engimon nomor berapa nih bang: ");
			engi = Integer.parseInt(sc.nextLine());
		} while (!(engi > 0 && engi < 6));

		String namaEngi;
		System.out.print("Cakepp bet dah, kasih nama siapa nih: ");
    namaEngi = sc.nextLine();

		switch (engi)
		{
		case 1:
			engimonDefault = new Firemon(namaEngi, false);
			break;
		case 2:
			engimonDefault = new Watermon(namaEngi, false);
			break;
		case 3:
			engimonDefault = new Groundmon(namaEngi, false);
			break;
		case 4:
			engimonDefault = new Icemon(namaEngi, false);
			break;
		case 5:
			engimonDefault = new Electricmon(namaEngi, false);
			break;
		default:
			break;
		}
		this.player = new Player(name, engimonDefault);
    player.getActiveEngimon().printInfo();
    initmap();
    spawnRandom();
  }

  private void initmap() {
    map = new Map();
    map.playerInisiasi(player);
    player.getActiveEngimon().move(5,5);
    map.engimonInisiasi(player.getActiveEngimon());
  }

  private void handleCMD(String cmd){
    /* handle cmd */
    int choice,choice2, choice3;
    if (cmd.equals("help")){
      System.out.println("Daftar Command:");
      System.out.println("- w/a/s/d - untuk berpindah pada map");
      System.out.println("- changeNamaEngi - untuk mengubah nama engimon");
      System.out.println("- listEngimon - menampilkan daftar engimon saat ini");
      System.out.println("- listSkill - menampilkan daftar skill item di inventory saat ini");
      System.out.println("- infoEngimon - menampilkan informasi engimon sesuai pilihan");
      System.out.println("- replaceSkillEngi - mengganti skill engimon dengan skill baru");
      System.out.println("- switchActive - mengganti status engimon sesuai pilihan");
      System.out.println("- useSkill - menggunakan skill yang dimiliki sesuai pilihan");
      System.out.println("- breeding - mengawinkan engimon berdasarkan dua engimon yang dimiliki sesuai pilihan");
      System.out.println("- battle - melakukan battle dengan engimon liar yang sedang bersisian dengan player pada map");
      System.out.println("- removeEngimon - mengeluarkan Engimon dari Inventory");
      System.out.println("- removeSkill - mengurangi X skill dari Inventory");
      System.out.println("- changeNameEngimon - mengganti nama Engimon pada Inventory");
      System.out.println("- interract - berinteraksi dengan active Engimon");
      System.out.println("- save - menyimpan state permainan saat ini");
    } else if (cmd.equals("w") || cmd.equals("a") || cmd.equals("s") || cmd.equals("d")){
      int deltaX=0, deltaY=0;
      int deltaX2=0, deltaY2=0;
      int bX = player.getX(), bY = player.getY();
      Engimon aktif = player.getActiveEngimon();
      int bX2 = aktif.getX(), bY2 = aktif.getY();
      if (cmd.equals("w")){
        deltaY = -1;
        deltaY2 = 1;
      } else if (cmd.equals("a")){
        deltaX = -1;
        deltaX2 = 1;
      } else if (cmd.equals("s")){
        deltaY = 1;
        deltaY2 = -1;
      } else if (cmd.equals("d")){
        deltaX = 1;
        deltaX2 = -1;
      }
      if (bX+deltaX < map.getXsize() && bX+deltaX >= 0 && bY+deltaY < map.getYsize() && bY+deltaY >= 0){
        try{
          if (map.isAdaEntity(bX+deltaX, bY+deltaY)){
            if (aktif.getX() == bX+deltaX && aktif.getY() == bY+deltaY){
              aktif.setX(bX+deltaX2);
              aktif.setY(bY+deltaY2);
              map.pseudomoveEngimon(bX2, bY2, aktif);
              bX2 = aktif.getX();
              bY2 = aktif.getY();
              player.move(bX+deltaX, bY+deltaY);
              map.pseudomovePlayer(bX, bY, player);
              aktif.setX(bX+deltaX+deltaX2);
              aktif.setY(bY+deltaY+deltaY2);
              map.pseudomoveEngimon(bX2, bY2, aktif);
            }
          } else {
            player.move(bX+deltaX, bY+deltaY);
            map.pseudomovePlayer(bX, bY, player);
            aktif.setX(bX+deltaX+deltaX2);
            aktif.setY(bY+deltaY+deltaY2);
            map.pseudomoveEngimon(bX2, bY2, aktif);
          }
        } catch (Exception e){
          player.move(bX, bY);
          map.pseudomovePlayer(bX, bY, player);
          aktif.setX(bX2);
          aktif.setY(bY2);
          map.pseudomoveEngimon(bX2, bY2, aktif);
          System.out.println("Mentok brok, cari arah lain");
        }
      }
    } else if (cmd.equals("listEngimon")){
      player.getMyEngimons().printInvenEngimon();
    } else if (cmd.equals("changeNamaEngi")) {
      player.getMyEngimons().printInvenEngimon();
      System.out.print("Mau ganti nama siape nih: ");
      choice = Integer.parseInt(sc.nextLine());
      System.out.print("Masukin nama baru: ");
      String newname = sc.nextLine();
      player.getMyEngimons().getItem(choice-1).setNamaEngimon(newname);
      System.out.printf("Berhasil mengganti nama menajdi %s\n",newname);
    } else if (cmd.equals("listSkill")){
      player.getMySkillItems().printInvenSkill();
    } else if (cmd.equals("replaceSkillEngi")){
      player.getMyEngimons().printInvenEngimon();
      System.out.print("\nMau ganti skill siape nih: ");
      choice = Integer.parseInt(sc.nextLine());
      player.getMyEngimons().getItem(choice-1).printSkill();
      System.out.print("\nGanti skill yang mane nih: ");
      choice2 = Integer.parseInt(sc.nextLine());
      player.getMySkillItems().printInvenSkill();
      if (player.getMySkillItems().countItem() != 0){
        System.out.print("Ganti pake yang mana: ");
        choice3 = Integer.parseInt(sc.nextLine());
        player.getMyEngimons().getItem(choice2-1).replaceSkill(player.getMyEngimons().getItem(choice-1).skillEngimon.get(choice2-1), player.getMySkillItems().getItem(choice-1));
      }
    } else if (cmd.equals("infoEngimon")){
      player.getMyEngimons().printInvenEngimon();
      System.out.print("Mau liat yang mana: ");
      choice = Integer.parseInt(sc.nextLine());
      player.getMyEngimons().getItem(choice-1).printInfo();
    } else if (cmd.equals("switchActive")){
      Engimon aktip = player.getActiveEngimon();
      int bX = aktip.getX(), bY = aktip.getY();
      System.out.println("Current Active Engimon: "+player.getActiveEngimon());
      player.getActiveEngimon().printInfo();
      System.out.println();

      player.getMyEngimons().printInvenEngimon();
      System.out.print("\nMau switch sama yang mana: ");
      choice = Integer.parseInt(sc.nextLine());
      player.switchActiveEngimon(player.getMyEngimons().getItem(choice-1));
      map.removeFromMap(bX, bY);
      player.getActiveEngimon().setX(bX);
      player.getActiveEngimon().setY(bY);
      map.engimonInisiasi(player.getActiveEngimon());
    } else if (cmd.equals("useSkill")){
      player.getMySkillItems().printInvenSkill();
      if (player.getMySkillItems().countItem() != 0){
        System.out.print("Mau pake yang mana: ");
        choice = Integer.parseInt(sc.nextLine());
        player.getMyEngimons().printInvenEngimon();
        System.out.print("Pakein ke siapa: ");
        choice2 = Integer.parseInt(sc.nextLine());
        player.getMyEngimons().getItem(choice2-1).learnSkill(player.getMySkillItems().getItem(choice-1));
      }
    } else if (cmd.equals("breeding")){
      player.getMyEngimons().printInvenEngimon();
      System.out.print("Pilih parent 1: ");
      choice = Integer.parseInt(sc.nextLine());
      System.out.print("Pilih parent 2: ");
      choice2 = Integer.parseInt(sc.nextLine());
      player.breeding(player.getMyEngimons().getItem(choice-1), player.getMyEngimons().getItem(choice2-1));
    } else if (cmd.equals("battle")){
      Engimon aktip = player.getActiveEngimon();
      int bX = aktip.getX(), bY = aktip.getY();
      List<Engimon> enemys = this.adjacentEngimon();
      if (!enemys.isEmpty()){
        System.out.println("<< List Engimon liar dekat kamu >>");
        int i = 0;
        for (Engimon e : enemys){
          System.out.printf("[%d] %s\n",i+1,e);
          e.printInfo();
          System.out.println();
          i++;
       }
       System.out.print("Challenge engimon nomor (0 kalo mau kabur): ");
       choice = Integer.parseInt(sc.nextLine());
       if (choice == 0){
        System.out.println("<< Kamu kabur >>");
        return;
       }
       System.out.println("<< BATTLE BEGIN >>\n");
       player.getActiveEngimon().printInfo();
       System.out.print("\n\n -- VERSUS --\n\n");
       enemys.get(choice-1).printInfo();
       System.out.println();
       int result = player.battle(enemys.get(choice-1));
       if (result == 1){
         engimonliar.remove(engimonliar.indexOf(enemys.get(choice-1)));
         map.removeFromMap(enemys.get(choice-1).getX(), enemys.get(choice-1).getY());
       } else {
         if (result == -1){
          map.removeFromMap(bX, bY);
          player.getActiveEngimon().setX(bX);
          player.getActiveEngimon().setY(bY);
          map.engimonInisiasi(player.getActiveEngimon());
         }
       }
      } else {
        System.out.println("Gaada engimon deket kamu master!");
      }
    } else if (cmd.equals("removeEngimon")){
      player.getMyEngimons().printInvenEngimon();
      System.out.print("Mana yang mau dibuang: ");
      choice = Integer.parseInt(sc.nextLine());
      player.getMyEngimons().removeItem(player.getMyEngimons().getItem(choice-1));
    } else if (cmd.equals("removeSkill")){
      player.getMySkillItems().printInvenSkill();
      if (player.getMySkillItems().countItem() != 0){
        System.out.print("Mana yang mau dibuang: ");
        choice = Integer.parseInt(sc.nextLine());
        if (player.getMySkillItems().getQuantity(player.getMySkillItems().getItem(choice-1)) > 1){
          System.out.print("Berapa: ");
          choice2 = Integer.parseInt(sc.nextLine());
          for (int i = 0; i < choice2; i ++){
            player.getMySkillItems().removeItem(player.getMySkillItems().getItem(choice-1));
          }
        } else {
          player.getMySkillItems().removeItem(player.getMySkillItems().getItem(choice-1));
        }
      }
    } else if (cmd.equals("changeNamaEngimon")){
      player.getMyEngimons().printInvenEngimon();
      System.out.print("Mana yang mau diganti: ");
      choice = Integer.parseInt(sc.nextLine());
      System.out.print("Ganti nama jadi apa: ");
      String newname = sc.nextLine();
      player.getMyEngimons().getItem(choice-1).setNamaEngimon(newname);
    } else if (cmd.equals("interract")){
      List<Engimon> adj = this.adjacentEngimon();
      if (adj.isEmpty()){
        player.getActiveEngimon().interract();
      } else {
        Engimon e = adj.get(ThreadLocalRandom.current().nextInt(0, adj.size()));
        e.interract();
      }
    } else if (cmd.equals("save")){
      System.out.print("Masukkan nama file: ");
      save(sc.nextLine());
    }
    
  }

  private void moveRandom(int turn) {
    int next[][] = new int[4][2];
    next[0][0] = 0;
    next[0][1] = -1;
    next[1][0] = 0;
    next[1][1] = 1;
    next[2][0] = 1;
    next[2][1] = 0;
    next[3][0] = -1;
    next[3][1] = 0;

    for (int i = 0 ; i < engimonliar.size(); i++){
      Engimon e = engimonliar.get(i);
      if (e.isMati()){
        continue;
      }
      int bX = e.getX(), bY = e.getY();
      int finalX = 0, finalY = 0, choice = 0;

      boolean condition = false;
      String specEng = e.getSpecies();   

      int limit = 0;
      while (!condition){
        limit++;
        if (limit>100){
          break;
        }
        choice = ThreadLocalRandom.current().nextInt(0, next.length);
        finalX = bX+next[choice][0];
        finalY = bY+next[choice][1];
        while (!(finalX < map.getXsize() && finalX >= 0 && finalY < map.getYsize() && finalY >= 0 && !map.isAdaEntity(finalX, finalY))){
          choice = ThreadLocalRandom.current().nextInt(0, next.length);
          finalX = bX+next[choice][0];
          finalY = bY+next[choice][1];
        }
        if(specEng.equals("watermon")){
          condition = map.isSea(finalX, finalY);
        } else if(specEng.equals("watericemon")){
          condition = map.isSea(finalX, finalY) || map.isTundra(finalX, finalY);
        } else if(specEng.equals("watergroundmon")){
          condition = map.isSea(finalX, finalY) ||  map.isGrassLand(finalX, finalY);
        } else if(specEng.equals("icemon")){
          condition = map.isTundra(finalX, finalY);
        } else if(specEng.equals("groundmon")){
          condition = map.isGrassLand(finalX, finalY);
        } else if(specEng.equals("firemon")){
          condition = map.isMountain(finalX, finalY);
        } else if(specEng.equals("fireelectricmon")){
          condition = map.isGrassLand(finalX,finalY) ||  map.isMountain(finalX, finalY);
        } else if(specEng.equals("electricmon")){
          condition = map.isGrassLand(finalX, finalY);
        }
      }
      if (limit>100){
        continue;
      }
      if (turn%15==0){
        e.addExp(100);
      }
      if (e.isMati()){
        engimonliar.remove(engimonliar.indexOf(e));
        map.removeFromMap(bX, bY);
      } else {
        e.move(finalX, finalY);
        map.pseudomoveEngimon(bX, bY, e);
      }
    }
  }

  private void spawnRandom() {
    if (engimonliar.size() >= maxLiar){
      return;
    }
    List<Engimon> tes = new ArrayList<>();
    Engimon e;
    int x = ThreadLocalRandom.current().nextInt(0, 12);
    int y = ThreadLocalRandom.current().nextInt(0, 10);
    while (map.isAdaEntity(x,y)){
      x = ThreadLocalRandom.current().nextInt(0, 12);
      y = ThreadLocalRandom.current().nextInt(0, 10);
    }
    tes.add(new FireElectricmon(x,y));
    tes.add(new Electricmon(x,y));
    tes.add(new Firemon(x,y));
    tes.add(new Groundmon(x,y));
    tes.add(new Icemon(x,y));
    tes.add(new WaterGroundmon(x,y));
    tes.add(new WaterIcemon(x,y));
    tes.add(new Watermon(x,y));

    e = tes.get(ThreadLocalRandom.current().nextInt(0, 8));
    boolean condition = false;
    String specEng = e.getSpecies();   

    while (!condition){
      x = ThreadLocalRandom.current().nextInt(0, 12);
      y = ThreadLocalRandom.current().nextInt(0, 10);
      while (map.isAdaEntity(x,y)){
        x = ThreadLocalRandom.current().nextInt(0, 12);
        y = ThreadLocalRandom.current().nextInt(0, 10);
      }
      e.setX(x); e.setY(y);
      if(specEng == "watermon"){
        condition = map.isSea(e.getX(), e.getY());
      } else if(specEng == "watericemon"){
        condition = map.isSea(e.getX(), e.getY()) || map.isTundra(e.getX(), e.getY());
      } else if(specEng == "watergroundmon"){
        condition = map.isSea(e.getX(), e.getY()) ||  map.isGrassLand(e.getX(), e.getY());
      } else if(specEng == "icemon"){
        condition = map.isTundra(e.getX(), e.getY());
      } else if(specEng == "groundmon"){
        condition = map.isGrassLand(e.getX(), e.getY());
      } else if(specEng == "firemon"){
        condition = map.isMountain(e.getX(), e.getY());
      } else if(specEng == "fireelectricmon"){
        condition = map.isGrassLand(e.getX(), e.getY()) ||  map.isMountain(e.getX(), e.getY());
      } else if(specEng == "electricmon"){
        condition = map.isGrassLand(e.getX(), e.getY());
      }
    }
    e.setLevel(player.getMaxLevelEngimon());
    engimonliar.add(e);
    map.spawnEngimonLiar(e);
  }
  
  private void initSkills(){
    GameFile skillsfile = new GameFile("C:\\Users\\ASUS\\Documents\\IF2210_TB_04_buruhNilai\\src\\EngimonWorld\\EngimonWorld\\EngimonWorld\\EngimonWorld\\Skill.txt"," ");
    List<String[]> skills = skillsfile.read();
    for (String[] arg : skills){
      if (arg.length > 3){
          containerskill.add(new Skill(arg[0],Integer.parseInt(arg[1]),arg[2],arg[3]));
      }
      containerskill.add(new Skill(arg[0],Integer.parseInt(arg[1]),arg[2]));
    }
  }

  private List<Engimon> adjacentEngimon(){
    int x = player.getX(), y = player.getY();
    List<Engimon> out = new ArrayList<>();
    int next[][] = new int[4][2];
    next[0][0] = 0;
    next[0][1] = -1;
    next[1][0] = 0;
    next[1][1] = 1;
    next[2][0] = 1;
    next[2][1] = 0;
    next[3][0] = -1;
    next[3][1] = 0;

    for (Engimon e : engimonliar){
      if (e.isMati()){
        continue;
      }
      for (int[] delta : next){
        int dx = x+delta[0], dy = y+delta[1];
        if (e.getX() == dx && e.getY() == dy){
          out.add(e);
          break;
        }
      }
    }
    return out;
  }

  private void save(String filename) {
    /* save Game state */
    GameFile a = new GameFile(filename, " ");
    List<String[]> out = new ArrayList<>();

    out.add(player.exportPlayer());
    out.add(new String[] {Integer.toString(turn), Integer.toString(maxLiar)});

    for (String[] row : map.export()){
      out.add(row);
    }

    for (Engimon e : engimonliar){
      out.add(e.exportEngimon());
    }

    a.write(out);
  }

  private void load(String filename) {
    GameFile filedata = new GameFile(filename, " ");
    List<String[]> file = filedata.read();
    this.engimonliar = new ArrayList<>();
    this.containerskill = new ArrayList<>();
    initSkills();

    player = new Player(file.get(0));
    turn = Integer.parseInt(file.get(1)[0]);
    maxLiar = Integer.parseInt(file.get(1)[1]);

    String x = file.get(2)[0], y = file.get(3)[0];
    List<String[]> loadmap = new ArrayList<>();
    int lastmap = 4+Integer.parseInt(y);

    loadmap.add(new String[] {x});
    loadmap.add(new String[] {y});
    int i;
    for (i = 4; i < lastmap; i++){
      loadmap.add(file.get(i));
    }

    map = new Map(loadmap);
    map.playerInisiasi(player);
    map.engimonInisiasi(player.getActiveEngimon());

    for (i = lastmap; i < file.size(); i++){
      Engimon e;
      String [] engi = file.get(i);
      String specs = engi[0];
      if (specs.equals("firemon")){
        e = new Firemon(engi);
      } else if (specs.equals("electricmon")){
        e = new Electricmon(engi);
      } else if (specs.equals("fireelectricmon")){
        e = new FireElectricmon(engi);
      } else if (specs.equals("groundmon")){
        e = new Groundmon(engi);
      } else if (specs.equals("icemon")){
        e = new Icemon(engi);
      } else if (specs.equals("watergroundmon")){
        e = new WaterGroundmon(engi);
      } else if (specs.equals("watericemon")){
        e = new WaterIcemon(engi);
      } else if (specs.equals("watermon")){
        e = new Watermon(engi);
      } else {
        e = new Engimon(engi);
      }
      engimonliar.add(e);
      map.spawnEngimonLiar(e);
    }

    System.out.print("Welcome back, ");
    System.out.println(player.getNamaPlayer());
  }
}