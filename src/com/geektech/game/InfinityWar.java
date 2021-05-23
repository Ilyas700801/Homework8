package com.geektech.game;

import com.geektech.players.*;

/*● Добавить в проект каждому классу героя свою уникальную способность


        ДЗ на сообразительность:
● Добавить еще игрока, Thor, удар по боссу имеет шанс оглушить босса на 1 раунд, вследствие чего босс пропускает 1 раунд и не наносит урон героям.

● Добавить еще игрока, Tank, который имеет увеличенную жизнь но слабый удар.
Может принимать на себя 1/5 часть урона исходящего от босса по другим игрокам.

● Добавить еще игрока Witcher, не наносит урон боссу, но получает урон от босса.
Имеет 1 шанс оживить первого погибшего героя, отдав ему свою жизнь, при этом погибает сам.

● Создать героя Avrora, которая может входить в режим невидимости на 2 раунда (т.е не получает урон от босса),
 в тоже время полученный урон в режиме невидимости возвращает боссу в последующих раундах

● Добавить игрока Друид, который имеет способность рандомно призывать помощника
или создавать копию союзника в виде героя или босса на 1 раунд. Помощник "Ворон"
 увеличивает способность медика лечить героев на n кол-во. При этом у босса проявляется агрессия
 (увеличивается урон на 50%), если его жизнь меньше 50%.

● Создать героя Hacker, который будет через раунд забирать у Босса N-ое количество здоровья
 и переводить его одному из героев.

● Создать героя TrickyBastard , способность которого будет состоять в том,
чтобы притвориться мертвым в определенном раунде(из случайного выбора),
 но в следующем раунде он снова вступает в бой.

● Создать героя Antman, в каждом раунде он может увеличится или же уменьшится на N-ный размер,
так же увеличиваются/уменьшаются жизнь и урон, после раунда он возвращается в исходный размер*/

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
