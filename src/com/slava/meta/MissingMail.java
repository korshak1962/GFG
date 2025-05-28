package com.slava.meta;

import java.util.ArrayList;
import java.util.List;

//https://www.metacareers.com/profile/coding_puzzles?puzzle=401886137594615&source=career_site_login_panel_sign_up_button
public class MissingMail {

        static class Trajectory {
            double potentialValue;
            double finalValue;

            Trajectory(double potentialValue, double finalValue) {
                this.potentialValue = potentialValue;
                this.finalValue = finalValue;
            }
        }

        public static double getMaxExpectedProfit(int N, List<Integer> V, int C, double S) {
            // Keep track of the best trajectory, where i is the number of days without pickup
            // Keep track of both the potential value for next pickup, and current profit
            List<Trajectory> bestTraj = new ArrayList<>();
            bestTraj.add(new Trajectory(0, 0));

            for (int price : V) {
                System.out.println(price + " " + printTrajectories(bestTraj));

                // Add the new day : (expected value to be picked up, final expected value)
                bestTraj.add(0, new Trajectory(0, 0));

                // Update the best trajectories so far for the new day, considering the packages could've been stolen
                for (int i = 0; i < bestTraj.size(); i++) {
                    Trajectory traj = bestTraj.get(i);
                    bestTraj.set(i, new Trajectory(traj.potentialValue * (1 - S), traj.finalValue));
                }

                // Possibility #1 : pickup that day
                // In this case, find the best possible choice among all saved so far (just keep the best one)
                double maxVal = Double.NEGATIVE_INFINITY;
                for (Trajectory traj : bestTraj) {
                    maxVal = Math.max(maxVal, traj.finalValue + traj.potentialValue);
                }
                double val = maxVal + price - C;
                bestTraj.set(0, new Trajectory(0, val));

                // Possibility #2 : don't pickup
                // In this case, update the on-going price
                for (int i = 1; i < bestTraj.size(); i++) {
                    Trajectory traj = bestTraj.get(i);
                    bestTraj.set(i, new Trajectory(traj.potentialValue + price, traj.finalValue));
                }

                System.out.println(printTrajectories(bestTraj) + "\n==========");
            }

            // Return the maximum final value
            double maxFinalValue = Double.NEGATIVE_INFINITY;
            for (Trajectory traj : bestTraj) {
                maxFinalValue = Math.max(maxFinalValue, traj.finalValue);
            }

            return maxFinalValue;
        }

        // Helper method to print trajectories for debugging
        private static String printTrajectories(List<Trajectory> trajectories) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < trajectories.size(); i++) {
                Trajectory traj = trajectories.get(i);
                sb.append("(").append(traj.potentialValue).append(", ").append(traj.finalValue).append(")");
                if (i < trajectories.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

        public static void main(String[] args) {
            // Example usage
            List<Integer> V = new ArrayList<>();
            V.add(10);
            V.add(2);
            V.add(8);
            V.add(6);
            V.add(4);

            double result = getMaxExpectedProfit(V.size(), V, 3, 0.15);
            System.out.println("Maximum expected profit: " + result);
        }
}
