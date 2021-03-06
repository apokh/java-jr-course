package io.github.bael.javacourse.knight.warriors;

import io.github.bael.javacourse.knight.chances.Chance;

public abstract class Warrior implements Actor {

    private static final int CRITICAL_DAMAGE_EDGE = 95;
    public static final int CRITICAL_DAMAGE_RATE = 3;
    ActorState state;

    protected Warrior() {
    }

    public Warrior(ActorState state) {
        this.state = state;
    }


    /**
     * Сила атаки = уровень атаки + сила + признак критичности удара.
     */
    @Override
    public void attackEnemy(Chance chance, Warrior warrior) {
        System.out.println("Attacking the enemy! " + warrior);
        int percent = chance.getProbability();
        // to do insert damage calculation
        Damage damage = new Damage(state.getAttackLevel() + state.getStrength(), detectCriticalAttackChance(percent));
        System.out.println("Attack damage is ! " + damage);
        warrior.receiveAttack(damage);
    }

    // считаем что урон критический если вероятность критического удара + уровень воина выше порога
    protected boolean detectCriticalAttackChance(int percent) {
        // todo
        //   возвращать true если процент + уровень больше или равен CRITICAL_DAMAGE_EDGE;
        return (percent + state.getLevel()) >= CRITICAL_DAMAGE_EDGE;

    }


    /***
     * Принимаемый урон от атаки = уровень атаки * (мультипликатор критичности удара) - сила защиты.
     */
    @Override
    public void receiveAttack(Damage strike) {
        System.out.println("Recieveing the attack: " + strike);


        // todo - поглощенный урон = защита - урон
        // если удар  критический - утраиваем урон
        int damage;
        if (strike.getIsCritical()) {
            damage = strike.getValue() * CRITICAL_DAMAGE_RATE - state.getDefenceLevel(); // todo
        } else {
            damage = strike.getValue() - state.getDefenceLevel();
        }

        // todo нужно уменьшить здоровье юнит. для этого в интерфейсе ActorState есть метод takeDamage. Примените его
        this.state = this.state.takeDamage(damage);

    }

    @Override
    public ActorState getState() {
        return state;
    }


}
