package com.slava.meta.array;

import java.util.*;

public class MaxDamageDealt {
    public double getMaxDamageDealt(int N, int[] H, int[] D, int B) {
        List<Warrior> healthWarriors = new ArrayList<>();
        List<Warrior> damageWarriors= new ArrayList<>();
        for (int i = 0; i < N; i++) {
            healthWarriors.add( new Warrior(i, H[i], D[i]));
            damageWarriors.add( new Warrior(i, H[i], D[i]));
        }
        healthWarriors.sort(Comparator.comparingInt(Warrior::getHealth).reversed());
        damageWarriors.sort(Comparator.comparingInt(Warrior::getDamage).reversed());
        Set<Warrior> used = new HashSet<>();

        double result = 0;

        return result;
    }
    int iHelth = 0;
    int iDamage = 0;


    double computeDamage(Warrior wH,Warrior w2, int b) {

        return (damage1 + damage2) * b / (helth1 + heth2);
    }

    class Warrior {
        int id;
        int health;
        int damage;

        Warrior(int id, int health, int damage) {
            this.id = id;
            this.health = health;
            this.damage = damage;
        }

        public int getHealth() {
            return health;
        }

        public int getDamage() {
            return damage;
        }

    }

}
