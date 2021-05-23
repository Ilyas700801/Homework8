package com.geektech.players;

public class CaptainAmerica extends Avenger {

    public CaptainAmerica(int health, int damage, String name,
                          EnumSuperAbility superAbility) {
        super(health, damage, name, superAbility);
    }

    @Override
    public void applySuperAbility(Boss thanos, Avenger[] avengers) {

        if (thanos.getHealth() > 0 && this.getHealth() > 0) {

            int captainAmericaDamage = thanos.getDamage() / 5; // 50 / 5 = 10

            thanos.setDamage (thanos.getDamage() - captainAmericaDamage); //50 - captainAmericaDamage, boss damage = 40
            System.out.println("Captain America block boss damage" + captainAmericaDamage);
        }
    }
}
