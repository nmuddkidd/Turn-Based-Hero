package io.github.nmuddkidd;

import java.util.Scanner;
class Dragon extends Enemy{
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
    private int maxHealth=20;
    private int maxMagic=5;

    public Dragon(int hp, int mp, int w, int a, int m,int x){
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

    //moveset 1 is always squirm

    public int claw(){
        System.out.println("The dragon Rakes its claws across your chest! -"+attack+" HP");
        return attack;
    }

    public int flame(){
        if(magic>0){
            int temp = (int)(attack/2);
            System.out.println("You are engulfled in flame "+temp+" damage! -"+temp+" HP");
            magic--;
            return temp;
        }else{
            System.out.println("The dragon coughs up smoke");
            return 0;
        }
    }

    public void brood(){
        if(magic>0){
            System.out.println("The dragon just ignores you! +"+3+"HP healed");
            health+=3;
            magic--;
        }else{
            System.out.println("The dragon just ignores you!");
        }
    }

    public int windstorm(){
        System.out.println("The dragon flaps its wings at you! -3 MP");
        return 3;
    }

    //battle

    public void battle(Player guy, Dragon dragon){
        System.out.println("a dragon flies down from the mountains ("+dragon.getHealth()+"/"+dragon.getMagic()+")");
        System.out.println("You thought the legends were fake. . .");
        System.out.println("");
        while(win==false&&guy.getHealth()>0){
            if(guy.getHealth()<=0){
                System.out.println("You fought a good fight, but all good adventures must come to an end eventually");
            }

            //guy turn

            System.out.println("Action?");
            action=input.nextLine();
            if(action.equals("stab")){
                dragon.hurt(guy.stab());
            }else if(action.equals("murderize")){
                dragon.hurt(guy.murderize());
            }else if(action.equals("recover")){
                guy.heal(guy.recover());
            }else if(action.equals("finale")){
                dragon.hurt(guy.finale(dragon));
            }else if(action.equals("sacrifice")){
                dragon.hurt(guy.sacrifice());
            }else if(action.equals("shoot")){
                dragon.hurt(guy.shoot());
            }else if(action.equals("drain")){
                dragon.hurt(guy.drain(dragon));
            }else if(action.equals("energize")){
                guy.energize();
            }else if(action.equals("inferno")){
                System.out.println("Who would've guessed a fire breathing dragon is immune to fire?");
            }else if(action.equals("devour")){
                dragon.hurt(guy.devour());
            }else if(action.equals("garfiel")){
                dragon.hurt(guy.garfiel());
            }else if(action.equals("lasagna")){
                guy.lasagna();
            }else if(action.equals("whack")){
                dragon.hurt(guy.whack());
            }else if(action.equals("die")){
                guy.hurt(9999);
            }else{
                System.out.println("what");
            }

            System.out.println("");

            if(dragon.getHealth()<1){
                win=true;
            }else{
                System.out.println("Dragon (" + dragon.getHealth()+"/"+dragon.getMagic()+") is still alive");
                //enemy move
                enemyMove = (int)(Math.random()*dragon.getMoveAmt()+1);
                if(enemyMove==1){
                    guy.hurt(dragon.squirm());
                }else if(enemyMove==2){
                    guy.hurt(dragon.claw());
                }else if(enemyMove==3){
                    guy.hurt(dragon.flame());
                    if(dragon.getMagic()>0){
                        guy.fire(true,3);
                        dragon.setMagic(dragon.getMagic()-1);
                    }
                }else if(enemyMove==4){
                    dragon.brood();
                    if(dragon.getMagic()>0){
                        dragon.setHealth(dragon.getHealth()+3);
                        dragon.setMagic(dragon.getMagic()-1);
                    }
                }else if(enemyMove==5){
                    guy.mphurt(dragon.windstorm());
                }

                System.out.println("");

                guy.status();

                System.out.println("Status: ("+guy.getHealth()+"/"+guy.getMagic()+")");
            }

        }
        System.out.println("");

        if(guy.getHealth()>0){
            System.out.println("A titan of the skies has fallen! +"+dragon.getXP()+" exp");
            System.out.println("");
            guy.EXPup(dragon.getXP());
            guy.lv();
        }

        win=false;
        dragon.setHealth(20);
        dragon.setMagic(5);
    }
}
