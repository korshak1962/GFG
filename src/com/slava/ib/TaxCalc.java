package com.slava.ib;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TaxCalc {

    @Test
    public void test1() {
        double[] brackets = {0, 1000};
        double[] rates = {.1, .5};
        double salary = 2000;
        double res = tax_calculator(brackets, rates, salary);
        Assert.assertEquals(600, res);
    }

    @Test
    public void test2() {
        double[] brackets = {0, 1000, 2000};
        double[] rates = {.1, .5, .8};
        double salary = 100;
        double res = tax_calculator1(brackets, rates, salary);
        Assert.assertEquals(10.0, res, 0);
        salary = 10000;
        res = tax_calculator1(brackets, rates, salary);
        Assert.assertEquals(7000.0, res, 0);
    }

    @Test
    public void test3() {
        double[] brackets = {0, 1000, 2000};
        double[] rates = {.1, .5, .8};
        double salary = 10000;
        double res = tax_calculator1(brackets, rates, salary);
        Assert.assertEquals(7000.0, res, 0);
    }
    static public double tax_calculator(double[] brackets, double[] rates, double salary) {
        double totalTax = 0;
        int i = 0;
        double taxableIncome;
        while (i < brackets.length - 1 && salary > brackets[i]) {
            taxableIncome = Double.min(salary, brackets[i + 1]) - brackets[i];
            totalTax += rates[i] * taxableIncome;
            i++;
        }
        taxableIncome = salary - brackets[i];
        if (taxableIncome > 0) {
            totalTax += rates[i] * taxableIncome;
        }
        return totalTax;
    }

    static public double tax_calculator1(double[] brackets, double[] rates, double salary) {
        double totalTax = 0;
        int i = 0;
        double prevRates = 0;
        while (i < brackets.length && salary > brackets[i]) {
            totalTax += (salary - brackets[i]) * (rates[i] - prevRates);
            prevRates = rates[i];
            i++;
        }
        return totalTax;
    }
}
