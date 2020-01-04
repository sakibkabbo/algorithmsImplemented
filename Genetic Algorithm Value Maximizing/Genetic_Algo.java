/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab04;

import java.util.Scanner;

/**
 *
 * @author Muhtasim
 */
public class Genetic_Algo {

    int x = 0;
    static int n = 6;
    double ffunc = (15 * x) - Math.pow(x, 2);
    static int[] chrom;
    static double[] fitness;
    static double[] fitratio;
    static int crosspairs = 2;
    static double bestfitind = 0;
    static double bestfitav = 0;
    static int bestfitindgen = 0;
    static int bestfitavgen = 0;
    static int fittestchrom = 0;
    double pc = 0.7;
    double pm = 0.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter no. of chromosomes");
        //n = sc.nextInt();
        chrom = new int[n];
        fitness = new double[n];
        fitratio = new double[n];
        for (int i = 0; i < n; i++) {
            if (Math.random() > 0.5) {
                chrom[i] += 8;
            }
            if (Math.random() > 0.5) {
                chrom[i] += 4;
            }
            if (Math.random() > 0.5) {
                chrom[i] += 2;
            }
            if (Math.random() > 0.5) {
                chrom[i] += 1;
            }
        }
        crossing_cycle();
        System.out.println("Fitness Function : " + "(15 * x) - Math.pow(x, 2)");
        System.out.println("Chromosome Population Size : " + n);
        System.out.println("Crossover bit : " + 2);
        System.out.println("Fittest Chromosome : " + Integer.toBinaryString(fittestchrom));
        System.out.println("Best Individual fitness value : " + bestfitind);
        System.out.println("Best Average fitness value : " + bestfitav);
        System.out.println("Best Individual fitness value found in gen : " + bestfitindgen);
        System.out.println("Best Average fitness value found in gen : " + bestfitavgen);
    }

    static double fitnessFunction(int x) {
        return (15 * x) - Math.pow(x, 2);
    }

    static int gencnt = 0;

    static void crossing_cycle() {
        double totalfitness = 0;
        double genbestfitind = 0;
        for (int i = 0; i < n; i++) {
            fitness[i] = fitnessFunction(chrom[i]);
            totalfitness += fitness[i];
            if (fitness[i] > bestfitind) {
                bestfitind = fitness[i];
                bestfitindgen = gencnt;
                fittestchrom = chrom[i];
            }
            if (fitness[i] > genbestfitind) {
                genbestfitind = fitness[i];
            }
        }
        double genbestfitavg = totalfitness / n;
        if ((totalfitness) > bestfitav) {
            bestfitav = totalfitness / 5;
            bestfitavgen = gencnt;
        }
        for (int i = 0; i < n; i++) {
            fitratio[i] = fitness[i] / totalfitness;
        }
        for (int x = 0; x < 3; x++) {
            int p1 = 500, p2 = 500;
            for (int k = 0; k < 2; k++) {
                double pointer = Math.random() * 100;
                double wheel = 0;
                for (int i = 0; i < n; i++) {
                    wheel += fitratio[i];
                    if (pointer < wheel && p1 == 500) {
                        p1 = i;
                    }
                    if (pointer < wheel && p1 != i) {
                        p2 = i;
                    }
                }
                if (p1 == 500 || p2 == 500) {
                    k--;
                }
            }
            int crosspos = (int) (Math.random() * 2);
            int bitno = (int) (Math.random() * 2);
            char[] c1 = bit_builder(chrom[p1]);
            char[] c2 = bit_builder(chrom[p2]);
            char temp;
            for (int f = crosspos % 4; f < bitno; f++) {
                temp = c1[f];
                c1[f] = c2[f];
                c2[f] = temp;
            }
            chrom[p1] = bit_breaker(c1);
            chrom[p2] = bit_breaker(c2);
        }
        gencnt++;
        if (genbestfitavg < bestfitav && genbestfitind < bestfitind && gencnt > bestfitavgen + 2 || gencnt > bestfitindgen + 2) {

        } else {
            crossing_cycle();
        }

    }

    static char[] bit_builder(int x) {
        char[] ret = new char[4];
        if (x / 8 > 0 && Math.random() > 0.99) {
            ret[0] = '1';
            x -= 8;
        } else {
            ret[0] = '0';
        }
        if (x / 4 > 0) {
            ret[1] = '1';
            x -= 4;
        } else {
            ret[1] = '0';
        }
        if (x / 2 > 0) {
            ret[2] = '1';
            x -= 2;
        } else {
            ret[2] = '0';
        }
        if (x / 1 > 0) {
            ret[3] = '1';
            x -= 1;
        } else {
            ret[3] = '0';
        }
        return ret;
    }

    static int bit_breaker(char[] x) {
        int ret = 0;
        if (x[3] == '1') {
            ret += 8;
        }
        if (x[2] == '1') {
            ret += 4;
        }
        if (x[1] == '1') {
            ret += 2;
        }
        if (x[0] == '1') {
            ret += 1;
        }
        return ret;
    }

}
