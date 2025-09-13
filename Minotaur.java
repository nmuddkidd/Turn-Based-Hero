package io.github.nmuddkidd;

import java.util.Scanner;
class Minotaur extends Enemy{
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
    private int maxHealth=15;
    private int maxMagic=5;
    private int anger = 0;

    public Minotaur(int hp, int mp, int w, int a, int m,int x){
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

    //moveset

    public void pumped(){
        //-1 magic
        if(magic>0){
            System.out.println("The minotaur roars! +1 anger");
            anger++;
        }else{
            System.out.println("Hes a beast but now his voice is just horse");
        }
    }

    public int charge(){
        int temp = anger+1;
        if(magic==0){
            System.out.println("The minotaur thrashes around wildly -3 self HP +1 anger");
            anger++;
            return 3;
        }else{
            System.out.println("The minotaur thrashes around wildly -"+temp+" self HP +2 anger");
            anger+=2;
        }
        return temp;
    }

    public int punch(){
        int temp=2*anger+attack;
        System.out.println("The minotaur uppercuts you! -"+temp+" HP");
        return temp;
    }

    public int assault(){
        int temp=3*anger;
        System.out.println("The minotaur tires himself out with a flurry of punches! -"+ temp+" HP");
        anger=0;
        return temp;
    }

    public int flex(){
        int temp=anger*3;
        System.out.println("The minotaur flexes out the pain! +"+temp+" HP");
        anger=0;
        return temp;
    }

    public void battle(Player guy, Minotaur minotaur){
        System.out.println("A Man Beast stops grazing and charges you ("+minotaur.getHealth()+"/"+minotaur.getMagic()+")");
        System.out.println("An abomination known for their short temper");
        System.out.println("");
        while(win==false&&guy.getHealth()>0){
            if(guy.getHealth()<=0){
                System.out.println("HOW THR FUCK DID  YOU DIE???");
            }

            //guy turn

            System.out.println("Action?");
            action=input.nextLine();
            if(action.equals("stab")){
                minotaur.hurt(guy.stab());
            }else if(action.equals("murderize")){
                minotaur.hurt(guy.murderize());
            }else if(action.equals("recover")){
                guy.heal(guy.recover());
            }else if(action.equals("finale")){
                minotaur.hurt(guy.finale(minotaur));
            }else if(action.equals("sacrifice")){
                minotaur.hurt(guy.sacrifice());
            }else if(action.equals("shoot")){
                minotaur.hurt(guy.shoot());
            }else if(action.equals("drain")){
                minotaur.hurt(guy.drain(minotaur));
            }else if(action.equals("energize")){
                guy.energize();
            }else if(action.equals("inferno")){
                minotaur.fire(true,4,guy.inferno());
            }else if(action.equals("devour")){
                minotaur.hurt(guy.devour());
            }else if(action.equals("garfiel")){
                minotaur.hurt(guy.garfiel());
            }else if(action.equals("lasagna")){
                guy.lasagna();
            }else if(action.equals("die")){
                guy.hurt(9999);
            }else if(action.equals("whack")){
                minotaur.hurt(guy.whack());
            }else{
                System.out.println("what");
            }

            System.out.println("");

            minotaur.status();
            if(minotaur.getHealth()<1){
                win=true;
            }else{
                System.out.println("Minotaur (" + minotaur.getHealth()+"/"+minotaur.getMagic()+") is still alive");
                //enemy move
                enemyMove = (int)(Math.random()*minotaur.getMoveAmt()+1);
                if(minotaur.getAnger()==0&&magic>=1){
                    enemyMove=(int)(Math.random()+2);
                }else if(minotaur.getAnger()==0&&magic==0){
                    enemyMove=3;
                }
                if(enemyMove==1){
                    guy.hurt(minotaur.squirm());
                }else if(enemyMove==2){
                    if(minotaur.getMagic()>0){
                        minotaur.pumped();
                        minotaur.setMagic(minotaur.getMagic()-1);
                    }else{
                        minotaur.hurt(minotaur.charge());
                        if(minotaur.getHealth()<=0){
                            win=true;
                            System.out.println("The minotaur throws itself down, dead");
                        }
                    }
                }else if(enemyMove==3){
                    minotaur.hurt(minotaur.charge());
                    if(minotaur.getMagic()>0){
                        minotaur.setMagic(minotaur.getMagic()-1);
                    }
                    if(minotaur.getHealth()<=0){
                        win=true;
                        System.out.println("The minotaur throws itself down, dead");
                    }
                }else if(enemyMove==4){
                    guy.hurt(minotaur.punch());
                }else if(enemyMove==5){
                    guy.hurt(minotaur.assault());
                }else if(enemyMove==6){
                    minotaur.setHealth(minotaur.getHealth()+minotaur.flex());
                }

                System.out.println("");

                guy.status();

                System.out.println("Status: ("+guy.getHealth()+"/"+guy.getMagic()+")");

            }

        }
        System.out.println("");

        if(guy.getHealth()>0){
            System.out.println("The beast has been put down +"+minotaur.getXP()+" exp");
            System.out.println("");
            guy.EXPup(minotaur.getXP());
            guy.lv();
        }

        win=false;
        minotaur.setHealth(15);
        minotaur.setMagic(5);
        anger=0;
        minotaur.fire(false,0,0);
    }

    public int getAnger(){
        return anger;
    }
}
