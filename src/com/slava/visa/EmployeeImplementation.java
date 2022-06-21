package com.slava.visa;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.String.format;

public class EmployeeImplementation {

    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(new File("resources/employee.txt"));

        String[] count = sc.nextLine().split(" ");

        EngineerFirm e = new EngineerFirm(Integer.parseInt(count[0]));
        AccountantFirm a = new AccountantFirm(Integer.parseInt(count[1]));

        count = sc.nextLine().split(" ");

        int[] incomeEngineers = new int[count.length];
        for (int i=0; i < count.length; i++) {
            incomeEngineers[i] = Integer.parseInt(count[i]);
        }
        e.assignSalaries(incomeEngineers);

        count = sc.nextLine().split(" ");
        int[] incomeAccountants = new int[count.length];
        for (int i=0; i < count.length; i++) {
            incomeAccountants[i] = Integer.parseInt(count[i]);
        }
        a.assignSalaries(incomeAccountants);

        e.averageSalary();
        e.maxSalary();
        e.minSalary();

        a.averageSalary();
        a.maxSalary();
        a.minSalary();
    }


    interface Company {
        void assignSalaries(int[] salaries);

        void averageSalary();

        void minSalary();

        void maxSalary();

    }

    static class EngineerFirm implements Company {
        int[] income;

        public EngineerFirm(int n) {
            this.income = new int[n];
        }

        @Override
        public void assignSalaries(int[] salaries) {
            for (int i = 0; i < Integer.min(salaries.length, income.length); i++) {
                income[i] = salaries[i];
            }
            System.out.println("Incomes of engineers credited");
        }

        @Override
        public void averageSalary() {
            String strDouble = String.format("%.2f", Arrays.stream(income).summaryStatistics().getAverage());
            System.out.println("Average salary of engineers is "
                    + strDouble);
        }

        @Override
        public void minSalary() {
            System.out.println("Minimum salary amongst engineers is "
                    +Arrays.stream(income).summaryStatistics().getMin());
        }

        @Override
        public void maxSalary() {
            System.out.println("Maximum salary amongst engineers is "
                    +Arrays.stream(income).summaryStatistics().getMax());
        }
    }

   static  class AccountantFirm implements Company {
        int[] income;

        public AccountantFirm(int n) {
            this.income = new int[n];
        }

        @Override
        public void assignSalaries(int[] salaries) {
            for (int i = 0; i < Integer.min(salaries.length, income.length); i++) {
                income[i] = salaries[i];
            }
            System.out.println("Incomes of accountants credited");
        }

        @Override
        public void averageSalary() {
            String strDouble = String.format("%.2f", Arrays.stream(income).summaryStatistics().getAverage());
            System.out.println("Average salary of accountants is "
                    + strDouble);
        }

        @Override
        public void minSalary() {
            System.out.println("Minimum salary amongst accountants is "
                    +Arrays.stream(income).summaryStatistics().getMin());
        }

        @Override
        public void maxSalary() {
            System.out.println("Maximum salary amongst accountants is "
                    +Arrays.stream(income).summaryStatistics().getMax());
        }
    }
}
