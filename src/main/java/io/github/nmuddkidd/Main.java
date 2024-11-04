package io.github.nmuddkidd;

import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // enemy innitialization
        Enemy worm = new Enemy(1,0,1,1,1,1);
        Goblin goblin = new Goblin(5,0,2,0,3,3);
        Minotaur minotaur = new Minotaur(15,4,1,10,6,8);
        Dragon dragon = new Dragon(20,5,5,5,5,13);
        Ascii img = new Ascii();

        //System.out.println("Choose class");
        //System.out.println("Warrior (20/5) Starts with stab and murderize");
        //System.out.println("Wizard (10/20) Starts with zap and stun");
        Player guy = new Player();


        String action = "";
        boolean battle = false;
        boolean win = false;
        int enemyMove = 0;
        int enemyType = 0;
        int highscore = 0;

        //tutorial start
    /*img.wormStart();
    img.minotaurStart();
    img.goblinStart();
    img.dragonStart();
    img.stats();
    img.skills();
    img.rest();
    img.shield();
    img.heart();
    img.book();
    img.gun();
    img.sword();
    img.spring();
    img.peace();
    img.curse();*/


        img.wormStart();
        worm.battle(guy,worm);

        //tutorial end

        while(guy.getHealth()>0){
            battle=false;

            dragon.refresh();
            goblin.refresh();
            worm.refresh();
            System.out.println("//////////////////////////////////////");
            System.out.println("");
            while(battle==false){
                enemyType=(int)(Math.random()*4+1);
                if(enemyType==1){
                    img.wormStart();
                    battle = true;
                    worm.battle(guy,worm);
                    if(guy.getHealth()>0){
                        highscore+=worm.getXP();
                    }
                }else if(enemyType==2){
                    img.goblinStart();
                    battle = true;
                    goblin.battle(guy,goblin);
                    if(guy.getHealth()>0){
                        highscore+=goblin.getXP();
                    }
                }else if(enemyType==3&&guy.getLevel()>=7){
                    img.dragonStart();
                    battle = true;
                    dragon.battle(guy,dragon);
                    if(guy.getHealth()>0){
                        highscore+=dragon.getXP();
                    }
                }else if(enemyType==4&&guy.getLevel()>=5){
                    img.minotaurStart();
                    battle = true;
                    minotaur.battle(guy,minotaur);
                    if(guy.getHealth()>0){
                        highscore+=dragon.getXP();
                    }
                }
            }

            System.out.println("//////////////////////////////////////");
            System.out.println("");

            guy.end();

            System.out.println("level: "+guy.getLevel());
            System.out.println("exp: "+guy.getXP()+"/"+guy.getlv());
            System.out.println("Status: ("+guy.getHealth()+"/"+guy.getMagic()+")");
            System.out.println("score: "+highscore);
            System.out.println("");
            if(guy.getHealth()>0){
                guy.upgrade(img);
            }
        }

        System.out.println("Rest In Peace");
        System.out.println("score: "+highscore);
    }

}