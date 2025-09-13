package io.github.nmuddkidd;

import java.util.Scanner;
class Goblin extends Enemy{
    Scanner input = new Scanner(System.in);
    private String action = "";
    private int enemyMove = 0;
    private boolean win = false;

    private int health = 5;
    private int magic = 5;
    private int attack = 2;
    private int defense = 0;
    private int moveAmt = 3;
    private int exp = 5;
    private int maxHealth=5;
    private int maxMagic=5;

    public Goblin(int hp, int mp, int w, int a, int m,int x){
        super(hp,mp,w,a,m,x);
        health=hp;
        maxHealth=hp;
        magic=mp;
        maxMagic=mp;
        attack=w;
        defense=a;
        moveAmt=m;
        exp=x;
    }

    public void refresh(){
        health=maxHealth;
        magic=maxMagic;
    }

    //moveset, 1 is always squirm

    public int slap(){
        System.out.println("He slaps you! -"+attack+" HP");
        return attack;
    }

    public int stab(){
        int temp=attack+2;
        System.out.println("He slaps the shit outta you! -"+temp+" HP");
        return attack+2;
    }

    public void battle(Player guy, Goblin goblin){
        System.out.println("a you find a hostile goblin far from camp ("+goblin.getHealth()+"/"+goblin.getMagic()+")");
        System.out.println("You should probably try and murderize whatever that thing is. . .");
        System.out.println("");
        while(win==false&&guy.getHealth()>0){
            if(guy.getHealth()<=0){
                System.out.println("You fought a good fight, but all good adventures must come to an end eventually");
            }

            //guy turn

            System.out.println("Action?");
            action=input.nextLine();
            if(action.equals("stab")){
                goblin.hurt(guy.stab());
            }else if(action.equals("murderize")){
                goblin.hurt(guy.murderize());
            }else if(action.equals("recover")){
                guy.heal(guy.recover());
            }else if(action.equals("finale")){
                goblin.hurt(guy.finale(goblin));
            }else if(action.equals("sacrifice")){
                goblin.hurt(guy.sacrifice());
            }else if(action.equals("shoot")){
                goblin.hurt(guy.shoot());
            }else if(action.equals("drain")){
                goblin.hurt(guy.drain(goblin));
            }else if(action.equals("energize")){
                guy.energize();
            }else if(action.equals("inferno")){
                goblin.fire(true,4,guy.inferno());
            }else if(action.equals("devour")){
                goblin.hurt(guy.devour());
            }else if(action.equals("garfiel")){
                goblin.hurt(guy.garfiel());
            }else if(action.equals("lasagna")){
                guy.lasagna();
            }else if(action.equals("whack")){
                goblin.hurt(guy.whack());
            }else if(action.equals("die")){
                guy.hurt(9999);
            }else{
                System.out.println("what");
            }

            System.out.println("");

            goblin.status();
            if(goblin.getHealth()<1){
                win=true;
            }else{
                System.out.println("Goblin (" + goblin.getHealth()+"/"+goblin.getMagic()+") is still alive");
                //enemy move
                enemyMove = (int)(Math.random()*goblin.getMoveAmt()+1);
                if(enemyMove==1){
                    guy.hurt(goblin.squirm());
                }else if(enemyMove==2){
                    guy.hurt(goblin.slap());
                }else if(enemyMove==3){
                    guy.hurt(goblin.stab());
                }

                System.out.println("");

                System.out.println("Status: ("+guy.getHealth()+"/"+guy.getMagic()+")");
            }

        }

        if(guy.getHealth()>0){
            System.out.println("Screw that guy, you won! +"+goblin.getXP()+" exp");
            System.out.println("");
            guy.EXPup(goblin.getXP());
            guy.lv();
        }

        win=false;
        goblin.setHealth(5);
        goblin.fire(false,0,0);
    }
}