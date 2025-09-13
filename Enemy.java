package io.github.nmuddkidd;

import java.util.Scanner;
class Enemy{
    Scanner input = new Scanner(System.in);
    private String action = "";
    private int enemyMove = 0;
    private boolean win = false;

    private int health = 1;
    private int magic = 0;
    private int attack = 0;
    private int defense = 0;
    private int moveAmt = 1;
    private int exp = 1;
    private int maxHealth=1;
    private int maxMagic=0;

    private boolean status = false;
    private int duration = 0;
    private int damage = 0;

    private boolean tutorial = true;

    public Enemy(int hp, int mp, int w, int a, int m, int x){
        health=hp;
        magic=mp;
        attack=w;
        defense=a;
        moveAmt=m;
        exp=x;
    }

    public void refresh(){
        health=maxHealth;
        magic=maxMagic;
    }

    public void status(){
        if(duration>0&&damage>0){
            System.out.println("It is ON FIRE -"+damage+" HP");
            health-=damage;
            if(duration==1){
                System.out.println("The fire puts itself out");
                System.out.println("");
            }
            duration--;
        }
    }

    public void fire(boolean f,int d, int dm){
        status = f;
        duration+=d;
        damage=dm;
    }

    //moveset

    public int squirm(){
        System.out.println("Enemy squirms all over you! -1 HP");
        return 1;
    }
    //stats

    public void hurt(int pain){
        int temp=(int)(Math.random()*100);
        if(defense<temp){
            health-=pain;
        }else{
            System.out.println("blocked!");
        }
    }

    public void cast(int drain){
        magic=magic-drain;
    }

    public void setHealth(int h){
        health=h;
    }

    public void setMagic(int m){
        magic=m;
    }

    public int getHealth(){
        return health;
    }

    public int getMagic(){
        return magic;
    }

    public int getXP(){
        return exp;
    }

    public int getMoveAmt(){
        return moveAmt;
    }

    public int getDuration(){
        return duration;
    }

    public void battle(Player guy, Enemy worm){
        System.out.println("a schlime crawls from the soil ("+worm.getHealth()+"/"+worm.getMagic()+")");
        if(tutorial==true){
            System.out.println("Stab him!! (type stab into the action bar)");
        }else{
            System.out.println("ew kill it or something");
        }
        System.out.println("");
        while(win==false&&guy.getHealth()>0){
            if(guy.getHealth()<=0){
                System.out.println("You fought a good fight, but all good adventures must come to an end eventually");
                throw new IndexOutOfBoundsException();
            }

            System.out.println("Action?");
            action=input.nextLine();
            if(action.equals("stab")){
                worm.hurt(guy.stab());
            }else if(action.equals("murderize")){
                worm.hurt(guy.murderize());
            }else if(action.equals("recover")){
                guy.heal(guy.recover());
            }else if(action.equals("finale")){
                worm.hurt(guy.finale(worm));
            }else if(action.equals("sacrifice")){
                worm.hurt(guy.sacrifice());
            }else if(action.equals("shoot")){
                worm.hurt(guy.shoot());
            }else if(action.equals("drain")){
                worm.hurt(guy.drain(worm));
            }else if(action.equals("energize")){
                guy.energize();
            }else if(action.equals("inferno")){
                worm.fire(true,4,guy.inferno());
            }else if(action.equals("devour")){
                worm.hurt(guy.devour());
            }else if(action.equals("garfiel")){
                worm.hurt(guy.garfiel());
            }else if(action.equals("garfield")&&tutorial){
                System.out.println();
                guy.garfield();
            }else if(action.equals("lasagna")){
                guy.lasagna();
            }else if(action.equals("whack")){
                worm.hurt(guy.whack());
            }else if(action.equals("die")){
                guy.hurt(9999);
            }else{
                System.out.println("what");
            }

            //enemy move

            worm.status();

            if(worm.getHealth()<1){
                win=true;
            }else{
                System.out.println();
                System.out.println("schlime (" + worm.getHealth()+"/"+worm.getMagic()+") is still alive");

                enemyMove = (int)(Math.random()*worm.getMoveAmt()+1);
                if(enemyMove==1){
                    guy.hurt(worm.squirm());
                }
            }

            System.out.println("");

            guy.status();

            System.out.println("Status: ("+guy.getHealth()+"/"+guy.getMagic()+")");
            tutorial=false;
        }

        if(guy.getHealth()>0){
            System.out.println("");
            System.out.println("you win! +"+worm.getXP()+" exp");
            System.out.println("");
            guy.EXPup(worm.getXP());
            guy.lv();
        }

        win=false;
        worm.refresh();
        worm.fire(false,0,0);
    }
}