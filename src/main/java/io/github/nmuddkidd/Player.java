package io.github.nmuddkidd;

import java.util.Scanner;
class Player{
    Scanner input = new Scanner(System.in);

    private int maxHealth=20;
    private int health = 0;
    private int maxMagic = 5;
    private int magic = 0;
    private int attack = 1;
    private int power = 0;
    private int defense = 0;
    private int exp = 0;
    private int lvths = 1;
    private int level = 0;
    private String[] moves = {"stab","murderize","",""};
    private String[] moveset = {"stab","murderize","recover","finale","sacrifice","drain","energize","inferno","devour","garfiel"};
    private String[][] items = {{"Worm Heart (+1 Max HP)","Goblin Heart (+3 Max HP)","Dragon Heart (+5 Max HP)"},{"Leather Shield (+1% DEF)","Wooden Shield(+3% DEF)","Metal Shield (+5% DEF)"},{"Calming Spot (+1 Max MP)","Beautiful Area (+2 Max MP)","Majesty of Nature (+3 Max MP)"},{"Dark Necklace (+1 power -3 Max HP)","Demonic Necklace (+2 power -5 Max HP)","Satanic Necklace (+3 power -8 Max HP)"}};
    private String[] sItems = {"Book of Forbidden Knowledge (+3 Power)","Demon Sword (+3 attack)", "Hot Spring (all stats up)","Gun (shoot them)"};

    private boolean gun = false;
    private int bullets = 3;

    private boolean status = false;
    private int duration = 0;

    private boolean lasagna = false;

    private int dmgmod = 0;

    public Player(){
        health=20;
        magic=5;
    }

    public void lv(){
        if(lvths<=exp){
            System.out.println("You leveled up! DEF +1% max HP & MP +2 HP & MP Full");
            maxHealth+=2;
            health=maxHealth;
            maxMagic+=2;
            magic=maxMagic;
            if(defense<49){
                defense+=1;
            }else{
                System.out.println("defense max reached!");
            }
            exp=0;
            lvths*=2;
            level++;
            System.out.println("");
        }
    }

    //status

    public void status(){
        if(duration>0){
            System.out.println("You are ON FIRE - 1 HP");
            health--;
            if(duration==1){
                System.out.println("The fire puts itself out");
                System.out.println("");
            }
            duration--;
        }
    }

    public void fire(boolean f,int d){
        status = f;
        duration+=d;
    }

    public void end(){
        duration=0;
        status= false;
        attack-=2*dmgmod;
        power-=2*dmgmod;
        dmgmod=0;
    }

    public void garfield(){
        System.out.println("         /\\                /\\         ");System.out.println("        /||\\--------------/||\\        ");System.out.println("       /   _______  ________  \\       ");System.out.println("      /\\  /////////\\////////\\ /\\      ");System.out.println("     /  _|  -0-  _||_  -0-  |_  \\     ");System.out.println("    /  /__\\_____/\\\\//\\______/__\\ \\    ");System.out.println("   /      \\_______/\\_______/      \\   ");System.out.println("  /\\/\\/\\                      /\\/\\/\\  ");
        System.out.println(" /        <^>            <^>        \\ ");System.out.println("/\\/\\/\\    |=|            |=|    /\\/\\/\\");System.out.println();moves[0]="garfiel";System.out.println("use garfiel as weapon"); System.out.println("and");System.out.println("lasagna for full heal and other stats");System.out.println();lasagna=true;
    }

    //moveset

    public int stab(){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("stab")){
                System.out.println("Stabed em good "+attack+" damage");
                return attack;
            }
        }
        System.out.println("I don't know that!");
        return 0;
    }

    public int murderize(){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("murderize")){
                if(magic>=2){
                    int temp=attack+2;
                    magic-=2;
                    System.out.println("MURDERIZEDD "+temp+" damage -2 mana");
                    return temp;
                }else{
                    System.out.println("Spell fizzles - no mana");
                    return 0;
                }
            }
        }
        System.out.println("I dont' know that move!");
        return 0;
    }

    public int recover(){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("recover")){
                int temp =(int)(Math.random()*3+3+power);
                if(magic>=3){
                    System.out.println("You steel yourself and recover "+temp+" health -3 mana");
                    status=false;
                    magic-=3;
                    return temp;
                }else{
                    System.out.println("the spell fizzles -- no mana");
                    return 0;
                }
            }
        }
        System.out.println("I don't know that move!");
        return 0;
    }

    public int whack(){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("whack")){
                if(magic>=5){
                    int temp=(power+1)*999;
                    System.out.println("yeah they're dead "+temp+" damage -5 mana");
                    magic-=5;
                    return temp;
                }else{
                    System.out.println("the spell fizzles -- no mana");
                    return 0;
                }
            }
        }
        System.out.println("I don't know that move!");
        return 0;
    }

    //showoff or finale
    public int finale(Enemy obj){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("finale")){
                if(magic>4){
                    int temp = 2+power;
                    magic-=5;
                    System.out.println("You showoff for a grand finale "+temp+" damage -5 mana");
                    if(obj.getHealth()<=temp){
                        System.out.println("The thrill of a good end gives you +2 health and +5 mana");
                        magic+=5;
                        health+=2;
                        obj.setHealth(obj.getHealth()-temp);
                    }
                    return temp;
                }else{
                    System.out.println("Spell fizzles - no mana");
                    return 0;
                }
            }
        }
        System.out.println("I don't know that move!");
        return 0;
    }

    public int sacrifice(){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("sacrifice")){
                int temp = (int)(2.5*attack);
                System.out.println("You stab wildly "+temp+" damage and "+attack+" self damage");
                health-=attack;
                return temp;
            }
        }
        System.out.println("I don't know that move!");
        return 0;
    }

    public int shoot(){
        if(gun==true){
            if(bullets>0){
                bullets--;
                System.out.println("*BLAM* 15 damage -1 bullet");
                if(bullets==0){
                    System.out.println("The gun feels hollow");
                }
                return 15;
            }else{
                System.out.println("*Click* --No bullets--");
                return 0;
            }
        }else{
            System.out.println("what");
            return 0;
        }
    }

    public int drain(Enemy goon){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("drain")){
                int temp=2+power;
                System.out.println("You steal its magic! 1 damage +"+temp+" magic");
                if(goon.getMagic()>0){
                    magic+=temp;
                    goon.setMagic(goon.getMagic()-1);
                }else{
                    System.out.println("But theres no magic to steal!");
                }
                return 1;
            }
        }
        System.out.println("I don't know that move!");
        return 0;
    }

    public void energize(){
        boolean known = false;
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("energize")){
                if(magic>0){
                    System.out.println("You work out, FEEL THE BURN!");
                    System.out.println("attack and magic +2 for fight -1 mana");
                    attack+=2;
                    power+=2;
                    dmgmod++;
                    known=true;
                    magic--;
                }else{
                    System.out.println("Spell fizzles - no mana");
                }
            }
        }
        if(!known){
            System.out.println("I don't know that move!");
        }
    }

    //implement this power dmg

    public int inferno(){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("inferno")){
                if(magic>2){
                    int temp = power+2;
                    System.out.println("You light them up with magic fire! -"+temp+" damage per turn -2 magic");
                    magic-=2;
                    return temp;
                }else{
                    System.out.println("Spell fizzles --no mana--");
                    return 0;
                }
            }
        }
        System.out.println("I don't know that move!");
        return 0;
    }

    public int devour(){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("devour")){
                System.out.println("You take a chunk out of them -1 damage +2 health!");
                health+=2;
                return 1;
            }
        }
        System.out.println("I don't know that move!");
        return 0;
    }

    public int garfiel(){
        for(int i = 0; moves.length>i;i++){
            if(moves[i].equals("garfiel")){
                int temp=(int)(Math.random()*11);
                System.out.println("you throw a peculiar orange creature at them -"+temp+" damage!");
                return temp;
            }
        }
        System.out.println("I don't know that move!");
        return 0;
    }

    public void lasagna(){
        if(lasagna){
            System.out.println();
            System.out.println("Memories of home flood your mind. . .");
            System.out.println("why are you even out here?");
            System.out.println();
            System.out.println("44 years of expirence come back");
            System.out.println("full heal, power and attack +1, regret + 1");
            maxHealth+=2;
            health=maxHealth;
            maxMagic+=2;
            magic=maxMagic;
            if(defense<49){
                defense+=1;
            }else{
                System.out.println("defense max reached!");
            }
            exp=0;
            lvths*=2;
            level++;
            System.out.println("");
            power++;
            attack++;
        }else{
            System.out.println("what");
        }
    }
    //items

    //upgrade

    public void upgrade(Ascii img){
        if(Math.random()>.90){
            String action = "";
            System.out.println("//////////////////////////////////////");
            System.out.println("");
            System.out.println("You have an hour of solitude");
            System.out.println("rest, train, or scavange?");
            while(!(action.equals("rest")||action.equals("train")||action.equals("scavange"))){
                action=input.nextLine();
            }
            System.out.println("");
            if(action.equals("rest")){
                img.rest();
                System.out.println("You camp and restore your health and magic");
                health=maxHealth;
                magic=maxMagic;
            }else if(action.equals("train")){
                System.out.println("stats or skills?");
                action=input.nextLine();
                if(action.equals("skills")){
                    int temp=(int)(Math.random()*moveset.length);
                    while(moveset[temp].equals(moves[0])||
                            moveset[temp].equals(moves[1])||
                            moveset[temp].equals(moves[2])||
                            moveset[temp].equals(moves[3])){
                        temp=(int)(Math.random()*moveset.length);
                    }
                    img.skills();
                    System.out.println("would you like to learn "+moveset[temp]+"?");
                    action=input.nextLine();
                    if(action.equals("yes")){
                        System.out.println("");
                        for(int i=0; moves.length>i;i++){
                            System.out.print(i+". "+moves[i]+" ");
                        }
                        System.out.println("");
                        System.out.println("which move should be replaced?");
                        int decide=input.nextInt();
                        moves[decide]=moveset[temp];
                    }else{

                    }
                }else{
                    img.stats();
                    System.out.println("For magic or attack?");
                    action=input.nextLine();
                    if(action.equals("magic")){
                        System.out.println("you feel more powerful!");
                        power++;
                    }else{
                        System.out.println("you feel stronger");
                        attack++;
                    }
                }
            }else if(action.equals("scavange")){
                int temp=(int)(Math.random()*4);
                int temp2=(int)(Math.random()*3);
                if(Math.random()<.85){
                    if(temp==0){
                        img.heart();
                        maxHealth+=2*temp2+1;
                        health+=2*temp2+1;
                    }else if(temp==1){
                        img.shield();
                        defense+=2*temp2+1;
                    }else if(temp==2){
                        img.peace();
                        maxMagic+=temp2+1;
                        magic+=temp2+1;
                    }else if(temp==3){
                        int RNG=(int)(Math.random()*2+1);
                        if(RNG==1){
                            img.curse();
                            System.out.println("You feel stronger in your core!");
                            attack+=temp2+1;
                            maxHealth-=temp2+3;
                            health-=temp2+3;
                        }else{
                            img.curse();
                            System.out.println("You feel that power is at your fingertips!");
                            power+=temp2+1;
                            maxHealth-=temp2+3;
                            health-=temp2+3;
                        }
                    }
                    System.out.println("You find a "+items[temp][temp2]+" in the brush");
                }else{
                    System.out.println("You find a "+sItems[temp]+" in the brush");
                    if(temp==0){
                        img.book();
                        System.out.println("you feel new POWER coursing through your veins");
                        power+=3;
                    }else if(temp==1){
                        img.sword();
                        System.out.println("You feel the STRENGTH of the Satan himself");
                        attack+=3;
                    }else if(temp==2){
                        img.spring();
                        System.out.println("a nice relaxing spring among the chaos.");
                        attack++;
                        power++;
                        maxHealth++;
                        maxMagic++;
                        defense++;
                        health=maxHealth;
                        magic=maxMagic;
                    }else if(temp==3){
                        img.gun();
                        System.out.println("you know what to do...");
                        gun=true;
                        bullets+=3;
                    }
                }
            }
            System.out.println("");
        }
    }

    //Items
    //Animal Heart +1 HP, Human Heart +3 HP, God Heart + 5 HP;
    //leather shield +1 DEF, wooden Shield +2 DEF, metal Shield +3 DEF
    //An calming spot +1 MP, A beautiful area + 2 MP, A Magisty of Nature +3 MP

    //getters & setters

    public void hurt(int pain){
        int temp=(int)(Math.random()*100);
        if(defense<temp){
            health-=pain;
        }else{
            System.out.println("blocked!");
        }
    }

    public void mphurt(int pain){
        magic-=pain;
    }

    public void cast(int drain){
        magic=magic-drain;
    }

    public void heal(int amt){
        health+=amt;
    }

    public void ATKup(int better){
        attack=better;
    }

    public void DEFup(int better){
        defense=better;
    }

    public void EXPup(int xp){
        exp+=xp;
    }

    public int getHealth(){
        return health;
    }

    public int getMagic(){
        return magic;
    }

    public int getlv(){
        return lvths;
    }

    public int getXP(){
        return exp;
    }

    public int getLevel(){
        return level;
    }
}
