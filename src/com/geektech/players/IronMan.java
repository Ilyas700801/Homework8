package com.geektech.players;

import java.util.Random;

public class IronMan extends Avenger{

    public IronMan(int health, int damage, String name,
                   EnumSuperAbility superAbility) {
        super(health, damage, name, superAbility);
    }

    @Override
    public void applySuperAbility(Boss thanos, Avenger[] avengers) {
        Random random = new  Random();
        if (thanos.getHealth()>0 && this.getHealth() >0 ) {
            int yu = random.nextInt(3) + 2;
            int AOD = this.getDamage() * yu;
            thanos.setHealth(thanos.getHealth() - AOD);
            System.out.println("Ironman creatdamage " + ((this.getDamage() * yu) - this.getDamage()));
        }
    }
}
