package com.slava.meta.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class MaxDamageDealt {
  public double getMaxDamageDealt(int N, int[] H, int[] D, int B) {
    List<Warrior> healthWarriors = new ArrayList<>();
    List<Warrior> damageWarriors = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      healthWarriors.add(new Warrior(i, H[i], D[i]));
      damageWarriors.add(new Warrior(i, H[i], D[i]));
    }
    healthWarriors.sort(Comparator.comparingInt(Warrior::getHealth).reversed());
    damageWarriors.sort(Comparator.comparingInt(Warrior::getDamage).reversed());
    Set<Integer> used = new HashSet<>();
    Warrior wHealth;
    Warrior wDamage;
    double result = 0;
    while (!healthWarriors.isEmpty() && !damageWarriors.isEmpty()) {
      wHealth = healthWarriors.removeFirst();
      while (used.contains(wHealth.id) && !healthWarriors.isEmpty()) {
        wHealth = healthWarriors.removeFirst();
      }
      if (!used.contains(wHealth.id)) {
        used.add(wHealth.id);
      } else {
        break;
      }

      wDamage = damageWarriors.removeFirst();
      while (used.contains(wDamage.id) && !damageWarriors.isEmpty()) {
        wDamage = damageWarriors.removeFirst();
      }
      if (!used.contains(wDamage.id)) {
        used.add(wDamage.id);
      } else {
        break;
      }

      result += computeDamage(wHealth, wDamage, B);
    }
    return result;
  }

  double computeDamage(Warrior wHealth, Warrior wDamage, int b) {
    double damage = (wHealth.damage + wDamage.damage) * (wHealth.health) / (double) b;
    damage += wDamage.damage * (wDamage.health) / (double) b;
    return damage;
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

  @Test
  public void test() {
    int N = 3;
    int[] H = {2, 1, 4};
    int[] D = {3, 1, 2};
    int B = 4;
    double res = getMaxDamageDealt(N, H, D, B);
    System.out.println(res);
  }

  @Test
  public void test1() {
    int N = 4;
    int[] H = {1, 1, 2, 100};  //12.5*(3+2)=62.5 +.125*2=.5  total 63 next pair .25*1+.125*1
    int[] D = {1, 2, 1, 3};
    int B = 8;
    double res = getMaxDamageDealt(N, H, D, B);
    System.out.println(res);
  }

}
