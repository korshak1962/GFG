package com.slava.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ZombieInfection {

    @Test
    public void test1() {
        int nX = 4;
        int nY = 14;
        int[][] grid = new int[nX][nY];
        for (int i = 0; i < nX; i++) {
            for (int k = 0; k < nY; k++) {
                if (k == i) grid[i][k] = 1;
            }
        }
        int lifeSpan = getlifeSpan(grid);
        System.out.println(lifeSpan);
        Assert.assertEquals(13, lifeSpan);
    }

    public ZombieInfection() {
    }

    List<Zombie> oldGeneration=new ArrayList<>();
    List<Zombie> newGeneration;
    int[][] grid;

    public void init(int[][] grid) {
        this.grid = grid;
        initOldGeneration();
    }


    int getlifeSpan(int[][] grid) {
        init(grid);
        int forReturn = 0;
        while (true) {
            newGeneration = new ArrayList<>();
            for (Zombie zombie : oldGeneration) {
                infectX(zombie, zombie.x + 1);
                infectX( zombie, zombie.x - 1);
                infectY( zombie, zombie.y + 1);
                infectY( zombie, zombie.y - 1);
            }
            if (newGeneration.isEmpty()) {
                return forReturn;
            } else {
                forReturn++;
                oldGeneration = newGeneration;
            }
        }
    }

    private void infectX(Zombie p, int newX) {
        if (newX >= 0 && newX < grid.length && grid[newX][p.y] != 1) {
            newGeneration.add(new Zombie(newX, p.y));
            grid[newX][p.y] = 1;
        }
    }

    private void infectY(Zombie p, int newY) {
        if (newY >= 0 && newY < grid[0].length && grid[p.x][newY] != 1) {
            newGeneration.add(new Zombie(p.x, newY));
            grid[p.x][newY] = 1;
        }
    }

    private void initOldGeneration() {
        for (int i = 0; i < grid.length; i++) {
            for (int k = 0; k < grid[0].length; k++) {
                if (grid[i][k] == 1) {
                    oldGeneration.add(new Zombie(i, k));
                }
            }
        }
    }

    class Zombie {
        int x;
        int y;
        public Zombie(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
