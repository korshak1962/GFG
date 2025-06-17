package com.slava.meta.array;

import java.util.ArrayList;
import java.util.List;

public class MaxDamageDealt1 {


    public static double getMaxDamageDealt(int N, List<Integer> H, List<Integer> D, int B) {
      // Precompute standalone damage for each warrior
      List<Long> C = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        C.add((long) H.get(i) * D.get(i));
      }

      long maxDamage = 0;
      int bestWarrior = 0;
      boolean run = true;

      while (run) {
        run = false;
        int nextBest = bestWarrior;

        for (int i = 0; i < N; i++) {
          if (i == bestWarrior) {
            continue;
          }

          // Total = solo damage + overlap phase where both attack
          long damage = C.get(bestWarrior) + C.get(i) +
              Math.max((long) H.get(bestWarrior) * D.get(i),
                  (long) H.get(i) * D.get(bestWarrior));

          if (damage > maxDamage) {
            maxDamage = damage;
            nextBest = i;
            run = true;
          }
        }

        bestWarrior = nextBest;
      }

      return (double) maxDamage / B;
    }

    // Alternative version using arrays instead of Lists
    public static double getMaxDamageDealt(int N, int[] H, int[] D, int B) {
      // Precompute standalone damage for each warrior
      long[] C = new long[N];
      for (int i = 0; i < N; i++) {
        C[i] = (long) H[i] * D[i];
      }

      long maxDamage = 0;
      int bestWarrior = 0;
      boolean run = true;

      while (run) {
        run = false;
        int nextBest = bestWarrior;

        for (int i = 0; i < N; i++) {
          if (i == bestWarrior) {
            continue;
          }

          // Total = solo damage + overlap phase where both attack
          long damage = C[bestWarrior] + C[i] +
              Math.max((long) H[bestWarrior] * D[i],
                  (long) H[i] * D[bestWarrior]);

          if (damage > maxDamage) {
            maxDamage = damage;
            nextBest = i;
            run = true;
          }
        }

        bestWarrior = nextBest;
      }

      return (double) maxDamage / B;
    }
  }

