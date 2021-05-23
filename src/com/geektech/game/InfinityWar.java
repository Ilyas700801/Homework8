package com.geektech.game;

import com.geektech.players.*;


public class InfinityWar {

    private static int round = 0;

    public static void startBattle(){
        Boss thanos = new Boss(1000, 50,
                "Thanos");
        CaptainAmerica captainAmerica = new CaptainAmerica(
                250,25,"Captain America",
                EnumSuperAbility.SHIELD_DAMAGE);

        IronMan ironMan = new IronMan(
                220,30,"Iron Man",
                EnumSuperAbility.TO_FLY);

        Hulk hulk = new Hulk(
                300,35,"Hulk",
                EnumSuperAbility.ALL_CRASH);

        Medic doc = new Medic(200, 0,
                "Doctor", EnumSuperAbility.TO_HEAL, 10);

        Medic  assistant = new Medic(230, 0,
                "Assistant", EnumSuperAbility.TO_HEAL, 5);

        Avenger[] avengers = {captainAmerica, ironMan, hulk,
                doc, assistant};

        System.out.println("____THE INFINITY WAR STARTED_____");
        printStatistics(thanos, avengers);

        while (!isGameFinished(thanos, avengers)){
            round(thanos, avengers);
        }

    }

    private static void round(Boss thanos, Avenger[] avengers) {
        round++;
        System.out.println("_________ROUND " + round + "_________");
        thanosDamage(thanos, avengers);
        avengersDamage(thanos, avengers);
        applySuperAbility(thanos, avengers);
        printStatistics(thanos, avengers);
    }

    private static void applySuperAbility(
            Boss thanos, Avenger[] avengers) {
        for (int i = 0; i < avengers.length; i++) {
            if (thanos.getHealth() > 0 &&
                    avengers[i].getHealth() > 0){
                avengers[i].applySuperAbility(thanos, avengers);
            }
        }
    }

    private static boolean isGameFinished(
            Boss thanos, Avenger[] avengers){
        if (thanos.getHealth() <= 0){
            System.out.println("___Avengers Won___");
            return true;
        }

        boolean allAvengersDead = true;
        for (Avenger avenger: avengers) {
            if (avenger.getHealth() > 0){
                allAvengersDead = false;
                break;
            }
        }
        if (allAvengersDead){
            System.out.println("___Thanos WON!!!___");
        }
        return allAvengersDead;
    }

    private static void avengersDamage(
            Boss thanos, Avenger[] avengers){
        for (int i = 0; i < avengers.length; i++) {
            if (thanos.getHealth() > 0 &&
                    avengers[i].getHealth() > 0){
                thanos.setHealth(thanos.getHealth() -
                        avengers[i].getDamage());
            }
        }
    }

    private static void thanosDamage(
            Boss thanos, Avenger[] avengers){
        for (int i = 0; i < avengers.length; i++) {
            if (thanos.getHealth() > 0 &&
                    avengers[i].getHealth() > 0){
                avengers[i].setHealth(avengers[i].getHealth()
                        - thanos.getDamage());
            }
        }
    }

    private static void printStatistics(
            Boss thanos, Avenger[] avengers){

        System.out.println(thanos.getName() + " : " +
                thanos.getHealth() + " damage " +
                thanos.getDamage());

        for (int i = 0; i < avengers.length; i++) {
            System.out.println(avengers[i].getName() + " : " +
                    avengers[i].getHealth() + " damage " +
                    avengers[i].getDamage());
        }
    }
}
