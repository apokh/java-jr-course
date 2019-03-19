package io.github.bael.javacourse.knight.warriors;

import io.github.bael.javacourse.knight.chances.Chance;

/**
 * Варвар. при низком здоровье (20 %) сила умножается на 5.
 */
public class Barbarian extends Warrior {

    private static final double RAGE_HEALTH_EDGE = .2d;

    private Barbarian() {
    }

    public Barbarian(ActorState state) {
        super(state);
    }

    /**
     * При пониженном здоровье урон от силы в пять раз выше
     */
    @Override
    public void attackEnemy(Chance chance, Warrior warrior) {

        int multiplier;

        if (state.getHPState() <= RAGE_HEALTH_EDGE){
            System.out.println("Barbarian is furious!");
            multiplier = 5;
        }
        else multiplier = 1;

        System.out.println("Attacking the enemy! " + warrior);
        int percent = chance.getProbability();
        // to do insert damage calculation
        // int damagevalue = 0;
        Damage damage = new Damage(state.getAttackLevel()+multiplier*state.getStrength(), detectCriticalAttackChance(percent));
        System.out.println("Attack damage is ! " + damage);
        warrior.receiveAttack(damage);

    }

}
