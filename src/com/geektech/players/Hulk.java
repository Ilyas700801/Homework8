package com.geektech.players;

import java.util.Random;

public class Hulk extends Avenger {

    public Hulk(int health, int damage, String name,
                EnumSuperAbility superAbility) {
        super(health, damage, name, superAbility);
    }

    @Override
    public void applySuperAbility(Boss thanos, Avenger[] avengers) {
        Random random = new Random();
        int magicCoef = random.nextInt(4) + 2;

        for (int i = 0; i < avengers.length; i++) {
            if (avengers[i].getHealth() > 0 && this.getHealth() > 0) {
                avengers[i].setDamage(avengers[i].getDamage()+ magicCoef);
            }
        }
        System.out.println("Маг повысил урон на " +  magicCoef);


    }
}
